<<<<<<< HEAD
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
    protected static int contReserva;

    //listas
    ArrayList<String> listaUsuario = plataforma.LeeFichero("espacios.txt");
    ArrayList<String> listaEstudiantes = plataforma.LeeFichero("estudianes.txt");
    ArrayList<String> listaProfesores = plataforma.LeeFichero("profesores.txt");
    ArrayList<String> listaAdministradores = plataforma.LeeFichero("administradores.txt");
    ArrayList<String> listaReserva = plataforma.LeeFichero("reservas.txt");
    ArrayList<String> listaEspacio = plataforma.LeeFichero("espacios.txt");
    
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
    protected abstract void reservar(String fecha, String espacio, Usuario user);

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



    //envio email estudiantes
    public void envioEmail(Reserva reserva, Espacio espacio){
    String destinatario = "";
    String asunto = "";
    String mensaje = "";
    EnvioCorreo.envioCorreo(destinatario, asunto, mensaje);
}

    //envio email profesores
    public void envioEmail(Reserva reserva, Espacio espacio, String materia){
    String destinatario = "";
    String asunto = "";
    String mensaje = "";
    EnvioCorreo.envioCorreo(destinatario, asunto, mensaje);
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
=======
package clases;
//import java.sql.Date;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected String codigo;
    protected String cedula;
    protected String contrasena;
    protected String correo;
    protected String rol;
    //listas
    ArrayList<String> listaUsuario = plataforma.LeeFichero("espacios.txt");
    ArrayList<String> listaEstudiantes = plataforma.LeeFichero("estudianes.txt");
    ArrayList<String> listaProfesores = plataforma.LeeFichero("profesores.txt");
    ArrayList<String> listaAdministradores = plataforma.LeeFichero("administradores.txt");
    ArrayList<String> listaReserva = plataforma.LeeFichero("reservas.txt");
    ArrayList<String> listaEspacio = plataforma.LeeFichero("espacios.txt");

    public Usuario(String cedula, String nombre, String apellido, String codigo, String contrasena, String correo, String rol) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigo = codigo;
        this.contrasena = contrasena;
        this.correo = correo;
        this.rol = rol;
    }

    protected abstract void reservar(String fecha, String espacio, Usuario user);

    public abstract void enviarCorreo();

    protected abstract int mostrarMenu();
  
    public void consultarReserva(Date fecha) {
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

    public void ServicioR(String parametros2) {
        System.out.println("Ejecutando ServicioR...");

    }


    public void CrearReserva() {
        System.out.println("Creando reserva sin parámetros...");
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



    public String getCodigo() {
        return codigo;
    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
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


    public String getCedula() {
        return cedula;
    }


    public void setCedula(String cedula) {
        this.cedula = cedula;
    }


    public String getUsuario() {
        return usuario;
    }


    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getContrasenia() {
        return contrasenia;
    }


    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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

    public abstract void mostrarmenu(); /// por llenar


    }

 
    



//     protected String nombre;
//     protected String apellido;
//     protected String codigo;
//     protected String cedula;
//     protected String contrasena;
//     protected String correo;
//     protected String rol;
//     //listasArrayList<String> listaUsuario = plataforma.LeeFichero("espacios.txt");
//     ArrayList<String> listaEstudiantes = plataforma.LeeFichero("estudiantes.txt");
//     ArrayList<String> listaProfesores = plataforma.LeeFichero("profesores.txt");
//     ArrayList<String> listaAdministradores = plataforma.LeeFichero("administradores.txt");
//     ArrayList<String> listaReserva = plataforma.LeeFichero("reservas.txt");
//     ArrayList<String> listaEspacio = plataforma.LeeFichero("espacios.txt");

//     public Usuario(String cedula, String nombre, String apellido, String codigo, String contrasena, String correo, String rol) {
//         this.cedula = cedula;
//         this.nombre = nombre;
//         this.apellido = apellido;
//         this.codigo = codigo;
//         this.contrasena = contrasena;
//         this.correo = correo;
//         this.rol = rol;
//     }

//     protected abstract void reservar(String fecha, String espacio, Usuario user);

//     public abstract void Enviarcorreo(String mensaje);

//     protected abstract int mostrarMenu();
  
//     public void consultarReserva(Date fecha) {
//         SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
//         String fechaS = dateF.format(fecha);
//         String codigoE = "";
//         for(String LR: listaReserva){
//             String[] partesR = LR.split(" | ");
//             codigoE = partesR[4];
//             for(String LE: listaEspacio){
//                 String[] partesE = LE.split(" | ");
//                 for(String LEST: listaEstudiantes){
//                     String[] partesEST = LEST.split(" | ");
//                     if (partesR[3].equals(fechaS) && partesE[0].equals(codigoE) && partesR[1].equals(partesEST[0])) {
//                         System.out.println(partesR[0]+" | "+partesR[3]+" | "+partesR[5]+" | "+partesE[2]+" | "+partesE[3]+" | "+partesEST[2]+partesEST[3]+" | "+partesR[6]);
//                     }
//                 }
//             }
//         }
//     }

//     public void ServicioR(String parametros2) {
//         System.out.println("Ejecutando ServicioR...");

//     }


//     public void CrearReserva() {
//         System.out.println("Creando reserva sin parámetros...");
//     }

//     public void EspaciosDisponibles(String fecha, String tipoEspacio) {
//         System.out.println("Mostrando espacios disponibles para la fecha " + fecha + " y tipo de espacio " + tipoEspacio);
//     }

//     public void GuardarReserva() {
//         System.out.println("Guardando reserva en archivo...");
//     }

//     public void enviarNotAd() {
//         System.out.println("Enviando notificación al administrador...");
//     }


//     public String toString() {
//         return "Usuario{" +
//                 "nombre='" + nombre + '\'' +
//                 ", apellido='" + apellido + '\'' +
//                 ", codigo='" + codigo + '\'' +
//                 ", cedula='" + cedula + '\'' +
//                 ", contrasena='" + contrasena + '\'' +
//                 ", correo='" + correo + '\'' +
//                 ", rol='" + rol + '\'' +
//                 '}';
//     }

//     public String getNombre() {
//         return nombre;
//     }

//     public void setNombre(String nombre) {
//         this.nombre = nombre;
//     }

//     public String getApellido() {
//         return apellido;
//     }

//     public void setApellido(String apellido) {
//         this.apellido = apellido;
//     }

//     public String getCodigo() {
//         return codigo;
//     }

//     public void setCodigo(String codigo) {
//         this.codigo = codigo;
//     }

//     public String getCedula() {
//         return cedula;
//     }

//     public void setCedula(String cedula) {
//         this.cedula = cedula;
//     }

//     public String getContrasena() {
//         return contrasena;
//     }

//     public void setContrasena(String contrasena) {
//         this.contrasena = contrasena;
//     }

//     public String getCorreo() {
//         return correo;
//     }

//     public void setCorreo(String correo) {
//         this.correo = correo;
//     }

//     public String getRol() {
//         return rol;
//     }

//     public void setRol(String rol) {
//         this.rol = rol;
//     }
// }

>>>>>>> parent of c1956f9 (Update Usuario.java)
