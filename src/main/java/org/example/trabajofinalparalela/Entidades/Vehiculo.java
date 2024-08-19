package org.example.trabajofinalparalela.Entidades;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.trabajofinalparalela.enumeraciones.*;

public class Vehiculo {
    private String id;
    private TipoVehiculo tipo;
    private Direccion direccion;
    private EstadoVehiculo estado;
    private double posX;
    private double posY;
    private double posXX;
    private double posYY;
    private double velocidad;
    private boolean detenido;
    private boolean detenidoUnaVez;
    private long tiempoDetenido;
    private Accion accion;
    private boolean accionAplicada = false;
    private TipoCarril tipoCarril;
    private DoblarDonde doblarDonde;
    boolean inferior = true;
    private Cruces cruces = Cruces.NINGUNO; // O el valor por defecto que consideres adecuado

    public Vehiculo(String id, TipoVehiculo tipo, Direccion direccion, EstadoVehiculo estado, double posX, double posY, double velocidad, Accion accion) {
        this.id = id;
        this.tipo = tipo;
        this.direccion = direccion;
        this.estado = estado;
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.detenido = false;
        this.detenidoUnaVez = false;
        this.tiempoDetenido = 0;
        this.accion = accion;
    }

    public Vehiculo(String id, TipoVehiculo tipo, EstadoVehiculo estado, double posXX, double posYY, double velocidad, Accion accion, TipoCarril tipoCarril, DoblarDonde doblarDonde, Direccion direccion, boolean inferior){
        this.id = id;
        this.tipo = tipo;
        this.direccion = direccion;
        this.estado = estado;
        this.posXX = posXX;
        this.posYY = posYY;
        this.velocidad = velocidad;
        this.detenido = false;
        this.detenidoUnaVez = false;
        this.tiempoDetenido = 0;
        this.accion = accion;
        this.tipoCarril = tipoCarril;
        this.doblarDonde = doblarDonde;
        this.inferior = inferior;
    }

    public Vehiculo(String id, TipoVehiculo tipo, EstadoVehiculo estado, double posX, double posY, double velocidad, Accion accion, TipoCarril tipoCarril, DoblarDonde doblarDonde, Direccion direccion) {
        this.id = id;
        this.tipo = tipo;
        this.direccion = direccion;
        this.estado = estado;
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.detenido = false;
        this.detenidoUnaVez = false;
        this.tiempoDetenido = 0;
        this.accion = accion;
        this.tipoCarril = tipoCarril;
        this.doblarDonde = doblarDonde;
    }

    public boolean getAccionAplicada() {
        return accionAplicada;
    }

    public void setAccionAplicada(boolean accionAplicada) {
        this.accionAplicada = accionAplicada;
    }
    public void dibujar(GraphicsContext gc) {
        gc.save(); // Guardar el estado actual del contexto gráfico

        switch (direccion) {
            case DERECHA:
                gc.setFill(tipo == TipoVehiculo.NORMAL ? Color.BLUE : Color.RED);
                gc.fillRect(posX, posY, 30, 15); // Dibuja un rectángulo en dirección derecha
                break;
            case IZQUIERDA:
                gc.setFill(tipo == TipoVehiculo.NORMAL ? Color.BLUE : Color.RED);
                gc.fillRect(posX, posY, 30, 15); // Dibuja un rectángulo en dirección izquierda
                break;
            case ABAJO:
                gc.translate(posX, posY); // Trasladar el contexto gráfico al punto del vehículo
                gc.rotate(90); // Rotar 90 grados para que apunte hacia abajo
                gc.translate(-posX, -posY); // Volver a la traslación original
                gc.setFill(tipo == TipoVehiculo.NORMAL ? Color.BLUE : Color.RED);
                gc.fillRect(posX, posY, 30, 15); // Dibuja un rectángulo
                break;
            case ARRIBA:
                gc.translate(posX, posY); // Trasladar el contexto gráfico al punto del vehículo
                gc.rotate(-90); // Rotar -90 grados para que apunte hacia arriba
                gc.translate(-posX, -posY); // Volver a la traslación original
                gc.setFill(tipo == TipoVehiculo.NORMAL ? Color.BLUE : Color.RED);
                gc.fillRect(posX, posY, 30, 15); // Dibuja un rectángulo
                break;
        }

        gc.restore(); // Restaurar el estado original del contexto gráfico
    }




    public void mover() {
        if (!detenido) {
            switch (direccion) {
                case DERECHA:
                    posX += velocidad * 5; // Aumento de velocidad
                    break;
                case IZQUIERDA:
                    posX -= velocidad * 5;
                    break;
                case ABAJO:
                    posY += velocidad * 5; // Movimiento hacia abajo
                    break;
                case ARRIBA:
                    posY -= velocidad * 5; // Movimiento hacia arriba
                    break;
                default:
                    break;
            }
        }
    }

    public void mover2() {
        if (!detenido) {
            switch (direccion) {
                case DERECHA:
                    posXX -= velocidad * 5; // Aumento de velocidad
                    break;
                case IZQUIERDA:
                    posYY += velocidad * 5;
                    break;
                case ABAJO:
                    posXX += velocidad * 5; // Movimiento hacia abajo
                    break;
                case ARRIBA:
                    posYY += velocidad * 5; // Movimiento hacia arriba
                    break;
                case ABAJO_CARRIL_OPUESTO:
                    posYY -= velocidad * 5; // Movimiento hacia arriba
                    break;

                case PARA_GIRAR_U_INFERIOR:
                    posXX += velocidad * 5; // Movimiento hacia arriba
                    break;
                default:
                    break;
            }
        }
    }


    public void detener() {
        this.detenido = true;
        this.tiempoDetenido = System.currentTimeMillis();
    }

    public void reanudar() {
        this.detenido = false;
        this.detenidoUnaVez = false;
    }



    // Getters y setters según sea necesario
    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public EstadoVehiculo getEstado() {
        return estado;
    }

    public boolean isDetenido() {
        return detenido;
    }

    public boolean isDetenidoUnaVez() {
        return detenidoUnaVez;
    }

    public long getTiempoDetenido() {
        return tiempoDetenido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    public void setDetenido(boolean detenido) {
        this.detenido = detenido;
    }

    public void setDetenidoUnaVez(boolean detenidoUnaVez) {
        this.detenidoUnaVez = detenidoUnaVez;
    }

    public void setTiempoDetenido(long tiempoDetenido) {
        this.tiempoDetenido = tiempoDetenido;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public boolean isAccionAplicada() {
        return accionAplicada;
    }

    public TipoCarril getTipoCarril() {
        return tipoCarril;
    }

    public void setTipoCarril(TipoCarril tipoCarril) {
        this.tipoCarril = tipoCarril;
    }

    public DoblarDonde getDoblarDonde() {
        return doblarDonde;
    }

    public void setDoblarDonde(DoblarDonde doblarDonde) {
        this.doblarDonde = doblarDonde;
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

    public boolean estaEnInterseccion(double interseccionX, double interseccionY) {
        double margen = 50.0; // Ajustar este margen según el tamaño de la intersección
        return (Math.abs(this.posX - interseccionX) < margen) && (Math.abs(this.posY - interseccionY) < margen);
    }

    public boolean estaEnInterseccionInferior(double interseccionX, double interseccionY) {
        double margen = 50.0; // Ajustar este margen según el tamaño de la intersección
        return (Math.abs(this.posXX - interseccionX) < margen) && (Math.abs(this.posYY - interseccionY) < margen);
    }

    public boolean isInferior() {
        return inferior;
    }

    public void setInferior(boolean inferior) {
        this.inferior = inferior;
    }

    public Cruces getCruces() {
        return cruces;
    }

    public void setCruces(Cruces cruces) {
        this.cruces = cruces;
    }
}