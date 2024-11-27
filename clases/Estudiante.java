package clases;
import java.util.Scanner;

public class Estudiante extends Usuario {

    private String matricula;
    private String carrera;
    Scanner sc = new Scanner(System.in);

    //metodos
    @Override
    public void reservar(String fecha, String espacio, Usuario user) {
        String codigoR = "";
        //mostrando los espacios disponibles
        EspaciosDisponibles(fecha, espacio);
        //elegiendo el espacio a reservar
        System.out.println("Ingrese el codigo del espacio que desea reservar");
        String codigo = sc.nextLine();
        System.out.println("Ingrese el motivo por el que desea reservar el espacio");
        String motivo = sc.nextLine();
        System.out.println("Desea crear la reserva? (S/N)");
        String crearReserva = sc.nextLine();
        if(crearReserva.equals("S")){
            //generando codigo de reserva
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
            contReserva++;
            //envio de email

            
        }
    }
    
    @Override
    public void enviarCorreo() {
       try {
         String asunto = "Reserva realizada";
         String mensaje = "El estudiante " + this.getNombre() + " " + this.getApellido() +
                         " ha realizado una reserva con código " + codigo +
                         " para la fecha " + fecha +
                         " en la cancha " + espacio + ".\n\n" +
                         "Ingrese al sistema para aprobar o rechazar la reserva.";

        // Cambiar al correo administrador en ese caso no se si sera el mismo con el que coloque el codigo de apps de google
         String correoAdministrador = "correoAdministrador@appreservas.com";
         EnvioCorreo.enviarCorreo(  // Llama al método estático de EnvioCorreo con los datos personalizados
             correoAdministrador, // destino admin
             asunto,               // Asunto del correo
             mensaje               // Mensaje del correo
         );
      } catch (MessagingException e) {
        System.out.println("Error al enviar correo: " + e.getMessage());
      }
    }

    @Override
    public void mostrarmenu() {
        int opcion;
        do  {
        
            System.out.println("Menú de estudiantes: ");
            System.out.println("1.- Reservar");
            System.out.println("2.- Consultar el estado de la reserva: ");
            System.out.println("3.- Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    //metodo para enviar correo
                Estudiante.enviarCorreo();
                System.out.println("Se ha enciado el correo al administrador");
                    break;
                case 2:
                    //aqui va el metodo para consultar el estado de la reserva
                consultarReserva(fecha);
                System.out.println("");
                case 3:
                System.out.println("saliendo...");

                default:
                System.out.println("opción no valida");
                    break;
            
                    break;
            }

        } while (opcion != 0);
    }
    
    //getters y setters
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
    
}
