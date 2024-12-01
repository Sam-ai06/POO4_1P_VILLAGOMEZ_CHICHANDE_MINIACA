package com.example;

public class Reserva {

    private String codigoUnicoReserva;
    private String codigoUsuario;
    private String cedulaUsuario;
    private String fechaReserva;
    private String codigoEspacioReservado;
    private TipoEspacio tipoEspacio;
    private EstadoReserva estado;
    private String motivo;

    public Reserva(String codigoUnicoReserva, String codigoUsuario, String cedulaUsuario, String fechaReserva,String codigoEspacioReserva, TipoEspacio tipoEspacio, EstadoReserva estado, String motivo){
        this.codigoUnicoReserva = codigoUnicoReserva;
        this.codigoUsuario = codigoUsuario;
        this.cedulaUsuario = cedulaUsuario;
        this.fechaReserva = fechaReserva;
        this.codigoEspacioReservado = codigoEspacioReserva;
        this.tipoEspacio = tipoEspacio;
        this.estado = estado;
        this.motivo = motivo;
    }

    // Getters y Setters
    public String getCodigoUnicoReserva() {
        return codigoUnicoReserva;
    }

    public void setCodigoUnicoReserva(String codigoUnicoReserva) {
        this.codigoUnicoReserva = codigoUnicoReserva;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getCodigoEspacioReservado() {
        return codigoEspacioReservado;
    }

    public void setCodigoEspacioReservado(String codigoEspacioReservado) {
        this.codigoEspacioReservado = codigoEspacioReservado;
    }

    public TipoEspacio getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public EstadoReserva getEstadoReserva() {
        return estado;
    }

    public void setEstadoReserva(EstadoReserva estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

}
