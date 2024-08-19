package org.example.trabajofinalparalela.Escenario2;

import org.example.trabajofinalparalela.Entidades.Vehiculo;
import org.example.trabajofinalparalela.enumeraciones.Accion;
import org.example.trabajofinalparalela.enumeraciones.TipoCarril;

import java.util.ArrayList;
import java.util.List;

public class Carril {
    private TipoCarril tipo;
    private List<Vehiculo> vehiculosSuperiores;

    public TipoCarril getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarril tipo) {
        this.tipo = tipo;
    }

    public List<Vehiculo> getVehiculosSuperiores() {
        return vehiculosSuperiores;
    }

    public void setVehiculosSuperiores(List<Vehiculo> vehiculosSuperiores) {
        this.vehiculosSuperiores = vehiculosSuperiores;
    }

    public void setVehiculosInferiores(List<Vehiculo> vehiculosInferiores) {
        this.vehiculosInferiores = vehiculosInferiores;
    }

    private List<Vehiculo> vehiculosInferiores;

    public Carril(TipoCarril tipo) {
        this.tipo = tipo;
        this.vehiculosSuperiores = new ArrayList<>();
        this.vehiculosInferiores = new ArrayList<>();
    }

    public boolean puedeRealizarAccion(Accion accion) {
        switch (tipo) {
            case CENTRO:
                return accion == Accion.SEGUIR_RECTO;
            case DERECHA:
                return accion == Accion.DOBLAR_DERECHA || accion == Accion.GIRAR_U;
            case IZQUIERDA:
                return accion == Accion.DOBLAR_IZQUIERDA;
            default:
                return false;
        }
    }


    public boolean tieneEspacioDisponible() {
        // Implementa la lógica para verificar si hay espacio para un nuevo vehículo
        // Por ejemplo, podrías tener un límite máximo de vehículos por carril
        return vehiculosSuperiores.size() < 5; // Ejemplo: máximo 5 vehículos por carril
    }

    public boolean tieneEspacioDisponibleInferiores() {
        // Implementa la lógica para verificar si hay espacio para un nuevo vehículo
        // Por ejemplo, podrías tener un límite máximo de vehículos por carril
        return vehiculosInferiores.size() < 5; // Ejemplo: máximo 5 vehículos por carril
    }

    public void moverVehiculos() {
        for (Vehiculo vehiculo : vehiculosSuperiores) {
            vehiculo.mover();
        }
        // Opcionalmente, elimina los vehículos que hayan salido del carril
        vehiculosSuperiores.removeIf(this::haySalidoDelCarril);
    }

    public void moverVehiculosInferiores() {
        for (Vehiculo vehiculo : vehiculosInferiores) {
            vehiculo.mover2();
        }
        // Opcionalmente, elimina los vehículos que hayan salido del carril
        vehiculosInferiores.removeIf(this::haySalidoDelCarril);
    }

    private boolean haySalidoDelCarril(Vehiculo vehiculo) {
        // Implementa la lógica para determinar si el vehículo ha salido del carril
        // Por ejemplo:
        return vehiculo.getPosX() > 1200 || vehiculo.getPosX() < 0 || vehiculo.getPosY() > 800 || vehiculo.getPosY() < 0;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculosSuperiores.add(vehiculo);
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculosSuperiores;
    }


    public void agregarVehiculoInferiores(Vehiculo vehiculo) {
        vehiculosInferiores.add(vehiculo);
    }

    public List<Vehiculo> getVehiculosInferiores() {
        return vehiculosInferiores;
    }


}
