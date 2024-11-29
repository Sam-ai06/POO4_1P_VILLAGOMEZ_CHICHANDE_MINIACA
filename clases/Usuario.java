package clases;

import java.sql.Date;

public abstract class Usuario {
    
    private String codigoUnico;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String usuario;
    private String contrasena;
    private String correo;
    private String rol;
    protected static int contReserva;

    //listas
    ArrayList<String> listaUsuario = ManejoArchivo.LeeFichero("espacios.txt");
    ArrayList<String> listaEstudiantes = ManejoArchivo.LeeFichero("estudianes.txt");
    ArrayList<String> listaProfesores = ManejoArchivo.LeeFichero("profesores.txt");
    ArrayList<String> listaAdministradores = ManejoArchivo.LeeFichero("administradores.txt");
    ArrayList<String> listaReserva = ManejoArchivo.LeeFichero("reservas.txt");
    ArrayList<String> listaEspacio = ManejoArchivo.LeeFichero("espacios.txt");
    
    //constructor
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
    
    //metodos abstractos
    public abstract void mostrarmenu(List<Espacio>espacios, List<Reserva> reservas, TipoEspacio TipoEspacio);

    protected abstract void enviarCorreo();
    
    protected abstract void reservar(Date fecha, String espacio, Usuario user);

    //metodos
    protected void consultarReserva(Date fecha){
        SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
        String fechaS = dateF.format(fecha);
        String codigoE = "";
        for(String LR: listaReserva){
            String[] partesR = LR.split(" | ");
            codigoE = partesR[4];
            for(String LE: listaEspacio){
                String[] partesE = LE.split(" | ");
                for(String LEST: listaEstudiantes){
                    String[] partesEST = LEST.split(" | ");
                    if (partesR[3].equals(fechaS) && partesE[0].equals(codigoE) && partesR[1].equals(partesEST[0])) {
                        System.out.println(partesR[0]+" | "+partesR[3]+" | "+partesR[5]+" | "+partesE[2]+" | "+partesE[3]+" | "+partesEST[2]+partesEST[3]+" | "+partesR[6]);
                    }
                }
            }
        }
    }
    
    public void EspaciosDisponibles(String fecha, String tipoEspacio) {
        System.out.println("Mostrando espacios disponibles para la fecha " + fecha + " y tipo de espacio " + tipoEspacio);
        for(String LE: listaEspacio){
            String[] partesE = LE.split(" | ");
            if(tipoEspacio.toUpperCase().equals(partesE[1]) && partesE[4].equals("DISPONIBLE")){
                System.out.println("Codigo: "+partesE[0]+" - Nombre: "+partesE[2]+" - Capacidad: "+partesE[3]);
            }
        }
    }



    //getters y setters
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
