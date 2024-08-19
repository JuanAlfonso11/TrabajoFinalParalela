package org.example.trabajofinalparalela.Entidades;

import java.util.List;

public class Calle {
    private String id;
    private int numCarriles;
    private List<Vehiculo> vehiculos;

    public Calle(String id, int numCarriles, List<Vehiculo> vehiculos) {
        this.id = id;
        this.numCarriles = numCarriles;
        this.vehiculos = vehiculos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumCarriles() {
        return numCarriles;
    }

    public void setNumCarriles(int numCarriles) {
        this.numCarriles = numCarriles;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}