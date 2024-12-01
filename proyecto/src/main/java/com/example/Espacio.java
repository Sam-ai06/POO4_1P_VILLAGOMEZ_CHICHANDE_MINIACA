package com.example;

public class Espacio {
    private String codigoUnico;
    private TipoEspacio tipoEspacio;
    private String nombre;
    private int capacidad;
    private EstadoEspacio estado; // DISPONIBLE, RESERVADO
    private Permiso rolPermitido; // ESTUDIANTE, PROFESOR, AMBOS

    public Espacio(String codigoUnico, TipoEspacio tipoEspacio, String nombre, int capacidad, EstadoEspacio estado, Permiso rolPermitido) {
        this.codigoUnico = codigoUnico;
        this.tipoEspacio = tipoEspacio;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.estado = estado;
        this.rolPermitido = rolPermitido;
    }

    // Getters y Setters
    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public TipoEspacio getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public EstadoEspacio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEspacio estado) {
        this.estado = estado;
    }

    public Permiso getRolPermitido() {
        return rolPermitido;
    }

    public void setRolPermitido(Permiso rolPermitido) {
        this.rolPermitido = rolPermitido;
    }
}