package org.example.trabajofinalparalela.Entiddades;

import java.util.Random;

public class Vehiculo {
    public static final int ANCHO_CARRO = 20; // Ancho del carro
    public static final int ALTO_CARRO = 10; // Alto del carro
    private String id;
    private boolean esEmergencia;
    private double posicionX;
    private double posicionY;
    private String direccion; // Puede ser "NORTE", "SUR", "ESTE", "OESTE"
    private double velocidad; // Velocidad del vehículo en unidades por segundo
    private String tipo; // Puede ser "coche", "camion", "moto", etc.
    private boolean detenido; // Indica si el vehículo está detenido
    private boolean haDobrado; // Indica si el vehículo ya ha doblado
    private Random random;
    private long lastActionTime;
    private long tiempoGiro;

    public Vehiculo(String id, boolean esEmergencia, double posicionX, double posicionY, String direccion, double velocidad, String tipo) {
        this.id = id;
        this.esEmergencia = esEmergencia;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.direccion = direccion;
        this.velocidad = velocidad;
        this.tipo = tipo;
        this.detenido = false;
        this.haDobrado = false;
        this.random = new Random();
        this.lastActionTime = System.currentTimeMillis();
        this.tiempoGiro = 0;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public boolean esEmergencia() {
        return esEmergencia;
    }

    public double getPosicionX() {
        return posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean estaDetenido() {
        return detenido;
    }

    public void setDetenido(boolean detenido) {
        this.detenido = detenido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public boolean haDobrado() {
        return haDobrado;
    }

    public void setHaDobrado(boolean haDobrado) {
        this.haDobrado = haDobrado;
    }

    public boolean deberiaDetenerse() {
        return System.currentTimeMillis() - lastActionTime >= 5000;
    }

    public long tiempoDesdeGiro() {
        return System.currentTimeMillis() - tiempoGiro;
    }

    // Métodos para mover el vehículo
    public void mover() {
        if (!detenido) {
            switch (direccion) {
                case "NORTE":
                    posicionY -= velocidad;
                    break;
                case "SUR":
                    posicionY += velocidad;
                    break;
                case "ESTE":
                    posicionX += velocidad;
                    break;
                case "OESTE":
                    posicionX -= velocidad;
                    break;
            }
        }
    }

    public boolean haLlegadoAlSemaforo() {
        switch (direccion) {
            case "NORTE":
                return posicionY <= 250;
            case "SUR":
                return posicionY >= 350;
            case "ESTE":
                return posicionX >= 450;
            case "OESTE":
                return posicionX <= 350;
            default:
                return false;
        }
    }

    public boolean haPasadoElSemaforo() {
        switch (direccion) {
            case "NORTE":
                return posicionY <= 240;
            case "SUR":
                return posicionY >= 360;
            case "ESTE":
                return posicionX >= 470;
            case "OESTE":
                return posicionX <= 310;
            default:
                return false;
        }
    }

    public void doblar() {
        if (!haDobrado) {
            int decision = random.nextInt(3);
            switch (direccion) {
                case "NORTE":
                    if (decision == 0) {
                        direccion = "ESTE"; // Doblar a la derecha
                        posicionX = 420; // Ajustar posición
                    } else if (decision == 1) {
                        direccion = "OESTE"; // Doblar a la izquierda
                        posicionX = 380; // Ajustar posición
                    } else {
                        direccion = "SUR"; // Hacer una vuelta en U
                    }
                    break;
                case "SUR":
                    if (decision == 0) {
                        direccion = "OESTE"; // Doblar a la izquierda
                        posicionX = 380; // Ajustar posición
                    } else if (decision == 1) {
                        direccion = "ESTE"; // Doblar a la derecha
                        posicionX = 420; // Ajustar posición
                    } else {
                        direccion = "NORTE"; // Hacer una vuelta en U
                    }
                    break;
                case "ESTE":
                    if (decision == 0) {
                        direccion = "SUR"; // Doblar a la derecha
                        posicionY = 320; // Ajustar posición
                    } else if (decision == 1) {
                        direccion = "NORTE"; // Doblar a la izquierda
                        posicionY = 280; // Ajustar posición
                    } else {
                        direccion = "OESTE"; // Hacer una vuelta en U
                    }
                    break;
                case "OESTE":
                    if (decision == 0) {
                        direccion = "NORTE"; // Doblar a la izquierda
                        posicionY = 280; // Ajustar posición
                    } else if (decision == 1) {
                        direccion = "SUR"; // Doblar a la derecha
                        posicionY = 320; // Ajustar posición
                    } else {
                        direccion = "ESTE"; // Hacer una vuelta en U
                    }
                    break;
            }
            haDobrado = true;
            tiempoGiro = System.currentTimeMillis();
        }
    }

    // Métodos adicionales
    public void detener() {
        this.detenido = true;
    }

    public void avanzar() {
        this.detenido = false;
    }

    public void acelerar(double incremento) {
        this.velocidad += incremento;
    }

    public void desacelerar(double decremento) {
        this.velocidad = Math.max(0, this.velocidad - decremento);
    }
}
