public class Espacio{
  private String codigo;
  private TipoEspacio tipoEspacio;
  private int capacidad;
  private String nombre;
  private EstadoEspacio espacio;
  private Permiso permiso;
  


  //metodos getters
  public String getCodigo(){
    return codigo;
  }

  public TipoEspacio getTipoEspacio(){
    return tipoEspacio;
  }

  public int getCapacidad(){
    return capacidad;
  }

  public String getNombre(){
    return nombre;
  }

  public EstadoEspacio getEspacio(){
    return espacio;
  }

  public Permiso getPermiso(){
    return permiso;
  }
  //metodos setters
  public void setCodigo(String codigo){
    this.codigo = codigo;
  }

  public void setTipoEspacio(TipoEspacio tipoEspacio){
    this.tipoEspacio = tipoEspacio;
  }

  public void setCapacidad(int capacidad){
    this.capacidad = capacidad;
  }

  public void setNombre(String nombre){
    this.nombre = nombre;
  }

  public void setEspacio(EstadoEspacio espacio){
    this.espacio = espacio;
  }

  public void setPermiso(Permiso permiso){
    this.permiso = permiso;
  }
}
