package org.example.trabajofinalparalela;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import org.example.trabajofinalparalela.Entiddades.Interseccion;
import org.example.trabajofinalparalela.Entiddades.Semaforo;
import org.example.trabajofinalparalela.Entiddades.Vehiculo;

public class HelloApplication extends Application {

    private Interseccion interseccion;
    private Canvas canvas;
    private final int ANCHO_CARRO = 20;
    private final int ALTO_CARRO = 10;
    private final int ANCHO_CALLE = ANCHO_CARRO * 4;
    private long lastUpdate = 0;

    @Override
    public void start(Stage primaryStage) {
        // Crear semáforos
        List<Semaforo> semaforos = new ArrayList<>();
        semaforos.add(new Semaforo("Semaforo Norte"));
        semaforos.add(new Semaforo("Semaforo Sur"));
        semaforos.add(new Semaforo("Semaforo Este"));
        semaforos.add(new Semaforo("Semaforo Oeste"));

        // Crear intersección
        interseccion = new Interseccion(semaforos);

        // Configurar la interfaz gráfica
        canvas = new Canvas(800, 600);
        Scene scene = new Scene(new StackPane(canvas), 800, 600);
        primaryStage.setTitle("Simulación de Tráfico");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Crear vehículos manualmente, incluyendo uno de emergencia
        crearVehiculos();

        // Iniciar la simulación
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 100_000_000) { // Update every 100ms
                    interseccion.gestionarTrafico();
                    dibujarInterseccion();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    private void crearVehiculos() {
        // Crear vehículos iniciales
        interseccion.agregarVehiculo("NORTE", new Vehiculo("V1", false, 420, 0, "SUR", 2, "coche"));
        interseccion.agregarVehiculo("NORTE", new Vehiculo("V2", false, 420, -30, "SUR", 2, "coche"));
        interseccion.agregarVehiculo("NORTE", new Vehiculo("V3", false, 420, -60, "SUR", 2, "coche"));
        interseccion.agregarVehiculo("SUR", new Vehiculo("V4", false, 380, 580, "NORTE", 2, "coche"));
        interseccion.agregarVehiculo("SUR", new Vehiculo("V5", false, 380, 610, "NORTE", 2, "coche"));
        interseccion.agregarVehiculo("SUR", new Vehiculo("V6", false, 380, 640, "NORTE", 2, "coche"));
        interseccion.agregarVehiculo("ESTE", new Vehiculo("V7", false, 780, 320, "OESTE", 2, "camion"));
        interseccion.agregarVehiculo("ESTE", new Vehiculo("V8", false, 810, 320, "OESTE", 2, "camion"));
        interseccion.agregarVehiculo("OESTE", new Vehiculo("V9", false, 20, 280, "ESTE", 2, "moto"));
        interseccion.agregarVehiculo("OESTE", new Vehiculo("V10", true, -10, 280, "ESTE", 2, "emergencia"));
    }

    private void dibujarInterseccion() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Dibujar líneas grises para representar las intersecciones
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(ANCHO_CALLE);

        // Línea vertical (norte-sur)
        gc.strokeLine(400, 0, 400, 600);

        // Línea horizontal (este-oeste)
        gc.strokeLine(0, 300, 800, 300);

        // Dibujar semáforos
        for (Semaforo semaforo : interseccion.getSemaforos()) {
            gc.setFill(semaforo.estaVerde() ? Color.GREEN : Color.RED);
            switch (semaforo.getId()) {
                case "Semaforo Norte":
                    gc.fillOval(390, 240, 20, 20);
                    break;
                case "Semaforo Sur":
                    gc.fillOval(390, 360, 20, 20);
                    break;
                case "Semaforo Este":
                    gc.fillOval(470, 290, 20, 20);
                    break;
                case "Semaforo Oeste":
                    gc.fillOval(310, 290, 20, 20);
                    break;
            }
        }

        // Dibujar vehículos en movimiento
        for (Vehiculo vehiculo : interseccion.getVehiculosEnMovimiento()) {
            if (vehiculo.esEmergencia()) {
                gc.setFill(Color.BLUE);
            } else {
                gc.setFill(Color.GREEN);
            }
            gc.fillRect(vehiculo.getPosicionX(), vehiculo.getPosicionY(), ANCHO_CARRO, ALTO_CARRO);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
