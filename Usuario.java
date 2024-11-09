
public class Usuario {
    private String nombre;
    private String apellido;
    private String codigo;
    private String cedula;
    private String contrasena;
    private String correo;
    private String rol;

    public Usuario(String cedula, String nombre, String apellido, String codigo, String contrasena, String correo, String rol) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigo = codigo;
        this.contrasena = contrasena;
        this.correo = correo;
        this.rol = rol;
    }

    public void reservarEspacio(String fecha, String tipoEspacio, String motivo) {
        System.out.println("Reservando espacio...");
    }

    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
        System.out.println("Enviando correo a " + destinatario);
    }
  
    public void consultarReserva(Date fecha) {
        System.out.println("Consultando reserva para la fecha: " + fecha);
    }

    public void ServicioR(String parametros2) {
        System.out.println("Ejecutando ServicioR...");

    }


    public void CrearReserva() {
        System.out.println("Creando reserva sin parámetros...");
    }


    public void MostrarMenu() {
        System.out.println("Mostrando menú...");

    }


    public void EspaciosDisponibles(String fecha, String tipoEspacio) {
        System.out.println("Mostrando espacios disponibles para la fecha " + fecha + " y tipo de espacio " + tipoEspacio);
    }

    public void GuardarReserva() {
        System.out.println("Guardando reserva en archivo...");
    }

    public void enviarNotAd() {
        System.out.println("Enviando notificación al administrador...");
    }


    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", codigo='" + codigo + '\'' +
                ", cedula='" + cedula + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", correo='" + correo + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

