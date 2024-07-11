package org.example.trabajofinalparalela.Entiddades;

public class HitboxCollider {
    private double x;
    private double y;
    private double width;
    private double height;

    public HitboxCollider(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean checkCollision(Vehiculo vehiculo) {
        return vehiculo.getPosicionX() < x + width &&
                vehiculo.getPosicionX() + Vehiculo.ANCHO_CARRO > x &&
                vehiculo.getPosicionY() < y + height &&
                vehiculo.getPosicionY() + Vehiculo.ALTO_CARRO > y;
    }

    // Getters for drawing hitboxes
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
