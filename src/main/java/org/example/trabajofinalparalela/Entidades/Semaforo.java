package org.example.trabajofinalparalela.Entidades;

import org.example.trabajofinalparalela.enumeraciones.EstadoSemaforo;
public class Semaforo {
    private String id;
    private EstadoSemaforo estado;
    private int tiempoVerde;
    private int tiempoRojo;
    private int tiempoAmarillo;
    private long tiempoInicio;

    public Semaforo(String id, EstadoSemaforo estado, int tiempoVerde, int tiempoRojo, int tiempoAmarillo) {
        this.id = id;
        this.estado = estado;
        this.tiempoVerde = tiempoVerde;
        this.tiempoRojo = tiempoRojo;
        this.tiempoAmarillo = tiempoAmarillo;
        this.tiempoInicio = System.currentTimeMillis();
    }

    public EstadoSemaforo getEstado() {
        return estado;
    }

    public void cambiarEstado(EstadoSemaforo nuevoEstado) {
        this.estado = nuevoEstado;
        this.tiempoInicio = System.currentTimeMillis();
    }

    public void actualizarEstado() {
        long tiempoActual = System.currentTimeMillis();
        long tiempoTranscurrido = (tiempoActual - tiempoInicio) / 1000;

        switch (estado) {
            case ROJO:
                // El semáforo debe permanecer en rojo hasta que otro semáforo lo ponga en verde
                break;
            case VERDE:
                if (tiempoTranscurrido >= tiempoVerde) {
                    cambiarEstado(EstadoSemaforo.AMARILLO);
                }
                break;
            case AMARILLO:
                if (tiempoTranscurrido >= tiempoAmarillo) {
                    cambiarEstado(EstadoSemaforo.ROJO);
                }
                break;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTiempoVerde() {
        return tiempoVerde;
    }

    public void setTiempoVerde(int tiempoVerde) {
        this.tiempoVerde = tiempoVerde;
    }

    public int getTiempoRojo() {
        return tiempoRojo;
    }

    public void setTiempoRojo(int tiempoRojo) {
        this.tiempoRojo = tiempoRojo;
    }

    public int getTiempoAmarillo() {
        return tiempoAmarillo;
    }

    public void setTiempoAmarillo(int tiempoAmarillo) {
        this.tiempoAmarillo = tiempoAmarillo;
    }

    public long getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(long tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public void setEstado(EstadoSemaforo estado) {
        this.estado = estado;
    }
}