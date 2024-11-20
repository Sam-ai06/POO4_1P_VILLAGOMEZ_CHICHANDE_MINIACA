public class Reserva{
  private String codigoReserva;
  private String codigoUsuario;
  private String cedulaUsuario;
  private String fechaReserva;
  private String codigoEspacio;
  private String tipoEspacio;
  private EstadoReserva estadoReserva;
  private String motivo;
  private boolean correoNotificado;

  //constructor
  public Reserva(String codigoReserva, String codigoUsuario, String cedulaUsuario, String fechaReserva, String codigoEspacio, String tipoEspacio, EstadoReserva estadoReserva, String motivo, boolean correoNotificado){
    this.codigoReserva = codigoReserva;
    this.codigoUsuario = codigoUsuario;
    this.cedulaUsuario = cedulaUsuario;
    this.fechaReserva = fechaReserva;
    this.codigoEspacio = codigoEspacio;
    this.tipoEspacio = tipoEspacio;
    this.estadoReserva = estadoReserva;
    this.motivo = motivo;
    this.correoNotificado = correoNotificado;
  }

  // Getters
  public String getCodigoReserva() {
    return codigoReserva;
  }

  public String getCodigoUsuario() {
    return codigoUsuario;
  }

  public String getCedulaUsuario() {
    return cedulaUsuario;
  }

  public String getFechaReserva() {
    return fechaReserva;
  }

  public String getCodigoEspacio() {
    return codigoEspacio;
  }

  public String getTipoEspacio() {
    return tipoEspacio;
  }

  public EstadoReserva getEstadoReserva() {
    return estadoReserva;
  }

  public String getMotivo() {
     return motivo;
  }

  public boolean isCorreoNotificado() {
    return correoNotificado;
  }

  // Setters
  public void setCodigoReserva(String codigoReserva) {
    this.codigoReserva = codigoReserva;
  }

  public void setCodigoUsuario(String codigoUsuario) {
    this.codigoUsuario = codigoUsuario;
  }

  public void setCedulaUsuario(String cedulaUsuario) {
    this.cedulaUsuario = cedulaUsuario;
  }

  public void setFechaReserva(String fechaReserva) {
    this.fechaReserva = fechaReserva;
  }

  public void setCodigoEspacio(String codigoEspacio) {
    this.codigoEspacio = codigoEspacio;
  }

  public void setTipoEspacio(String tipoEspacio) {
    this.tipoEspacio = tipoEspacio;
  }

  public void setEstadoReserva(EstadoReserva estadoReserva) {
    this.estadoReserva = estadoReserva;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

  public void setCorreoNotificado(boolean correoNotificado) {
    this.correoNotificado = correoNotificado;
  }
}
