package clases;

public abstract class Usuario {
    
    private String codigoUnico;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String usuario;
    private String contrasena;
    private String correo;
    private String rol;

    

    public Usuario(String[] datos) {
       
            this.codigoUnico = datos[0].trim();
            this.cedula = datos[1].trim();
            this.nombres = datos[2].trim();
            this.apellidos = datos[3].trim();
            this.usuario = datos[4].trim();
            this.contrasena = datos[5].trim();
            this.correo = datos[6].trim();
            this.rol = datos[7].trim();        
    }

    public abstract void mostrarmenu(List<Espacio>espacios, List<Reserva> reservas, TipoEspacio TipoEspacio);

    public String getCodigoUnico() {
        return codigoUnico;
    }
    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }


    

}
