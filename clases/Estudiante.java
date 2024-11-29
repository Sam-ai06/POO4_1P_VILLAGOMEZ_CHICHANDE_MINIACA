package clases;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Estudiante extends Usuario {

    private String matricula;
    private String carrera;
    Scanner sc = new Scanner(System.in);
    public Estudiante(String[] datos, String matricula, String carrera) {
        super(datos);
        this.matricula = matricula;
        this.carrera = carrera;
    }

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
            // Llamar al método de enviar correo al administrador
            enviarCorreo();  // Asumiendo que el método está correctamente implementado en la clase Estudiante

            System.out.println("Reserva creada con éxito y correo enviado al administrador.");
        } else {
            System.out.println("Reserva cancelada.");
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
                    
               System.out.println("ingrese la fecha para la que quiere reservar el espacio: ");
               String fechaReserva = sc.nextLine();
               System.out.println("ingrese el tipo de espacio que desea reservar: ");
               String TipoEspacio = sc.nextLine();
               SimpleDateFormat fechaDate = new SimpleDateFormat("dd/MM/yyyy");
               Date fecha = null;


               try {
                fecha = fechaDate.parse(fechaReserva);
               } catch (Exception e) {
                System.out.println("formato de fecha incorrecto. Asegúrese de usar dd/MM/yyyy");
               }
               reservar(fecha, TipoEspacio, null);
               break;

               case 2:
               System.out.println("Ingrese la fecha de la reserva que desea consultar: ");
                String fechaConsulta = sc.nextLine();
                Date fechaConsultaDate = null;


                try {
                    fechaConsultaDate = fechaDate.parse(fechaConsulta);
                    System.out.println("Fecha válida: " + fechaConsultaDate);
                } catch (Exception e) {
                    System.out.println("Formato de fecha incorrecto. Asegúrese de usar dd/MM/yyyy.");
                     break;
                    }

                consultarReserva(fechaConsultaDate); 
                break;
               

               case 3:
               System.out.println("saliendo...");
               
               default:
               System.out.println("opción no válida");
                
            }

        } while (opcion != 3);
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
