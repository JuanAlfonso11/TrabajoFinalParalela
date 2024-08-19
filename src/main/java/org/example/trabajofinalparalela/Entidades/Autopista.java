package org.example.trabajofinalparalela.Entidades;

import java.util.List;

public class Autopista {
    private String id;
    private List<Calle> calles;

    public Autopista(String id, List<Calle> calles) {
        this.id = id;
        this.calles = calles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Calle> getCalles() {
        return calles;
    }

    public void setCalles(List<Calle> calles) {
        this.calles = calles;
    }
}
