package org.example.trabajofinalparalela.Entiddades;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Interseccion {
    private List<Semaforo> semaforos;
    private Queue<Vehiculo> colaNorte;
    private Queue<Vehiculo> colaSur;
    private Queue<Vehiculo> colaEste;
    private Queue<Vehiculo> colaOeste;
    private List<Vehiculo> vehiculosEnMovimiento;
    private int semaforoActivo;
    private long lastTrafficUpdate;

    public Interseccion(List<Semaforo> semaforos) {
        this.semaforos = semaforos;
        this.colaNorte = new ConcurrentLinkedQueue<>();
        this.colaSur = new ConcurrentLinkedQueue<>();
        this.colaEste = new ConcurrentLinkedQueue<>();
        this.colaOeste = new ConcurrentLinkedQueue<>();
        this.vehiculosEnMovimiento = new ArrayList<>();
        this.semaforoActivo = 0;
        this.lastTrafficUpdate = System.currentTimeMillis();
    }

    public List<Semaforo> getSemaforos() {
        return semaforos;
    }

    public List<Vehiculo> getVehiculosEnMovimiento() {
        return vehiculosEnMovimiento;
    }

    public void agregarVehiculo(String direccion, Vehiculo vehiculo) {
        switch (direccion) {
            case "NORTE":
                colaNorte.add(vehiculo);
                break;
            case "SUR":
                colaSur.add(vehiculo);
                break;
            case "ESTE":
                colaEste.add(vehiculo);
                break;
            case "OESTE":
                colaOeste.add(vehiculo);
                break;
        }
    }

    public void gestionarTrafico() {
        long tiempoActual = System.currentTimeMillis();

        // Actualizar el semáforo activo cada 10 segundos
        if (tiempoActual - lastTrafficUpdate >= 10000) {
            Semaforo semaforo = semaforos.get(semaforoActivo);
            semaforo.setVerde(false); // Set current semaforo to red
            semaforoActivo = (semaforoActivo + 1) % semaforos.size();
            semaforos.get(semaforoActivo).setVerde(true); // Set next semaforo to green
            lastTrafficUpdate = tiempoActual;
        }

        moverVehiculos();
    }

    private void moverVehiculos() {
        moverCola(colaNorte, semaforos.get(0)); // Semaforo Norte
        moverCola(colaSur, semaforos.get(1)); // Semaforo Sur
        moverCola(colaEste, semaforos.get(2)); // Semaforo Este
        moverCola(colaOeste, semaforos.get(3)); // Semaforo Oeste

        List<Vehiculo> vehiculosAEliminar = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculosEnMovimiento) {
            if (!vehiculo.haPasadoElSemaforo()) {
                if (vehiculo.haLlegadoAlSemaforo() && !semaforoEnVerde(vehiculo.getDireccion())) {
                    vehiculo.detener();
                } else {
                    vehiculo.avanzar();
                }
            } else {
                vehiculo.avanzar();
            }

            vehiculo.mover();

            // Eliminar vehículos después de 3 segundos tras girar
            if (vehiculo.haDobrado() && vehiculo.tiempoDesdeGiro() > 3000) {
                vehiculosAEliminar.add(vehiculo);
                System.out.println("Vehículo " + vehiculo.getId() + " ha sido eliminado.");
            }
        }

        vehiculosEnMovimiento.removeAll(vehiculosAEliminar);
    }

    private void moverCola(Queue<Vehiculo> cola, Semaforo semaforo) {
        Vehiculo vehiculo = cola.peek();
        if (vehiculo != null) {
            cola.poll();
            vehiculosEnMovimiento.add(vehiculo);
            vehiculo.mover();
            System.out.println("Vehículo " + vehiculo.getId() + " se ha movido.");
        }
    }

    public boolean semaforoEnVerde(String direccion) {
        switch (direccion) {
            case "NORTE":
                return semaforos.get(0).estaVerde();
            case "SUR":
                return semaforos.get(1).estaVerde();
            case "ESTE":
                return semaforos.get(2).estaVerde();
            case "OESTE":
                return semaforos.get(3).estaVerde();
            default:
                return false;
        }
    }

    public List<Vehiculo> getVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.addAll(colaNorte);
        vehiculos.addAll(colaSur);
        vehiculos.addAll(colaEste);
        vehiculos.addAll(colaOeste);
        vehiculos.addAll(vehiculosEnMovimiento);
        return vehiculos;
    }

    @Override
    public String toString() {
        return "Interseccion{" +
                "semaforos=" + semaforos +
                ", colaNorte=" + colaNorte +
                ", colaSur=" + colaSur +
                ", colaEste=" + colaEste +
                ", colaOeste=" + colaOeste +
                ", vehiculosEnMovimiento=" + vehiculosEnMovimiento +
                '}';
    }
}
