package org.example.trabajofinalparalela.Entidades;

import org.example.trabajofinalparalela.enumeraciones.Direccion;
import org.example.trabajofinalparalela.enumeraciones.EstadoSemaforo;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Interseccion {
    private String id;
    private Map<Direccion, ConcurrentLinkedQueue<Vehiculo>> vehiculosPorDireccion;
    private Map<Direccion, Semaforo> semaforos;
    private double posX;
    private double posY;
    private double posXX;
    private double posYY;

    private Direccion direccionVerde; // Dirección del semáforo en verde actual

    public Interseccion(String id) {
        this.id = id;
        this.vehiculosPorDireccion = new HashMap<>();
        this.semaforos = new HashMap<>();
        this.direccionVerde = Direccion.DERECHA; // Inicializar con un semáforo en verde
//        this.semaforos = new EnumMap<>(Direccion.class);

        // Inicializar semáforos
        for (Direccion direccion : Direccion.values()) {
            semaforos.put(direccion, new Semaforo(direccion.name(), EstadoSemaforo.ROJO, 10, 20, 2));
        }

        semaforos.get(direccionVerde).cambiarEstado(EstadoSemaforo.VERDE);
    }

    public void agregarVehiculo(Direccion direccion, Vehiculo vehiculo) {
        vehiculosPorDireccion.computeIfAbsent(direccion, k -> new ConcurrentLinkedQueue<>()).add(vehiculo);
    }

    public void controlarSemaforos() {
        // Lógica para cambiar los semáforos
        Semaforo semaforoVerdeActual = semaforos.get(direccionVerde);
        semaforoVerdeActual.actualizarEstado();

        if (semaforoVerdeActual.getEstado() == EstadoSemaforo.ROJO) {
            cambiarSemaforoVerde();
        }
    }

    private void cambiarSemaforoVerde() {
        Direccion[] direcciones = Direccion.values();
        int index = (direccionVerde.ordinal() + 1) % direcciones.length;
        direccionVerde = direcciones[index];

        for (Semaforo semaforo : semaforos.values()) {
            semaforo.cambiarEstado(EstadoSemaforo.ROJO);
        }

        semaforos.get(direccionVerde).cambiarEstado(EstadoSemaforo.VERDE);
    }

    public Direccion getDireccionVerde() {
        for (Map.Entry<Direccion, Semaforo> entry : semaforos.entrySet()) {
            if (entry.getValue().getEstado() == EstadoSemaforo.VERDE) {
                return entry.getKey();
            }
        }
        return null; // Retorna null si no hay semáforo en verde
    }

    public Map<Direccion, ConcurrentLinkedQueue<Vehiculo>> getVehiculosPorDireccion() {
        return vehiculosPorDireccion;
    }

    public Map<Direccion, Semaforo> getSemaforos() {
        return semaforos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVehiculosPorDireccion(Map<Direccion, ConcurrentLinkedQueue<Vehiculo>> vehiculosPorDireccion) {
        this.vehiculosPorDireccion = vehiculosPorDireccion;
    }

    public void setSemaforos(Map<Direccion, Semaforo> semaforos) {
        this.semaforos = semaforos;
    }

    public void setDireccionVerde(Direccion direccionVerde) {
        this.direccionVerde = direccionVerde;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosXX() {
        return posXX;
    }

    public void setPosXX(double posXX) {
        this.posXX = posXX;
    }

    public double getPosYY() {
        return posYY;
    }

    public void setPosYY(double posYY) {
        this.posYY = posYY;
    }
}