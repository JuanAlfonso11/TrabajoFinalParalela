package org.example.trabajofinalparalela.Entiddades;

public class Semaforo {
    private String id;
    private boolean verde;
    private long tiempoVerde;
    private long tiempoRojo;
    private long tiempoUltimoCambio;

    public Semaforo(String id) {
        this.id = id;
        this.verde = false;
        this.tiempoVerde = 10 * 1000; // 10 segundos
        this.tiempoRojo = 30 * 1000; // 30 segundos (triple de verde)
        this.tiempoUltimoCambio = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public boolean estaVerde() {
        return verde;
    }

    public void cambiarEstado() {
        this.verde = !this.verde;
        this.tiempoUltimoCambio = System.currentTimeMillis();
    }

    public void actualizarEstado(long tiempoActual) {
        if (verde && (tiempoActual - tiempoUltimoCambio) >= tiempoVerde) {
            cambiarEstado();
        } else if (!verde && (tiempoActual - tiempoUltimoCambio) >= tiempoRojo) {
            cambiarEstado();
        }
    }

    public void setVerde(boolean verde) {
        this.verde = verde;
        this.tiempoUltimoCambio = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Semaforo{" +
                "id='" + id + '\'' +
                ", verde=" + verde +
                ", tiempoVerde=" + tiempoVerde +
                ", tiempoRojo=" + tiempoRojo +
                ", tiempoUltimoCambio=" + tiempoUltimoCambio +
                '}';
    }
}
