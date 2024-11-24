import java.util.Scanner;

public class Estudiante extends Usuario {

    private String matricula;
    private String carrera;
    

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Estudiante(String cedula, String nombre, String apellido, String codigo, String contrasena, String correo,
            String rol, String matricula, String carrera) {
        super(cedula, nombre, apellido, codigo, contrasena, correo, rol);
        this.matricula = matricula;
        this.carrera = carrera;
    }
    @Override
    public void reservar(String fecha, String espacio, Usuario user) {
        String codigoR = "";
        //mostrando los espacios disponibles
        EspaciosDisponibles(fecha, espacio);
        //elegir el espacio a reservar
        System.out.println("Ingrese el codigo del espacio que desea reservar");
        String codigo = sc.nextLine();
        System.out.println("Ingrese el motivo por el que desea reservar el espacio");
        String motivo = sc.nextLine();
        System.out.println("Desea crear la reserva? (S/N)");
        String crearReserva = sc.nextLine();
        if(crearReserva.equals("S")){
            //generar codigo de reserva
            for(String LR: listaReserva){
                String[] partesR = LR.split(" | ");
                codigoR = partesR[0];
            }
            int codigoUR = Integer.parseInt(codigoR);
            codigoUR++;
            codigoR = String.valueOf(codigoUR);
            //guardando la reserva
            String linea = codigoR+" | "+user.getCodigo()+" | "+user.cedula+" | "+fecha+" | "+codigo+" | "+espacio.toUpperCase()+" | "+"PENDIENTE"+" | "+motivo;
            plataforma.EscribirArchivo("reservas.txt", linea);
        }else{
            mostrarMenu();
        }
    }
    
    @Override
    public void Enviarcorreo(EnvioCorreo EnvioCorreo) {
       try {
            EnvioCorreo.enviarCorreo(
                this.correo,
                "Notificación para Estudiante",
                "Este es un mensaje de prueba para un estudiante."  //se pueden cambiar estos mensajes
            );
        } catch (MessagingException e) {
            System.out.println("Error al enviar correo: " + e.getMessage());
        }
     }
    }

    @Override
    public int mostrarMenu(){
        System.out.println("Bienvenido");
        System.out.println("1.  Reservar");
        System.out.println("2.  Consultar Reserva");
        System.out.println("Escoja la opccion que desea realizar");
        int op = sc.nextInt();
        sc.nextLine();
        return op;
    }
    

    

    

}
