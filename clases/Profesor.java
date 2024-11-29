package clases;
import java.util.ArrayList;
import java.util.Scanner;

public class Profesor extends Usuario {

    private String facultad;
    private String materiaSeleccionada;
    private ArrayList<String> materias;
    Scanner sc = new Scanner(System.in);

    //constructor
    public Profesor(String[] datos, String facultad, ArrayList<String> materias) {
        super(datos);
        this.facultad = facultad;
        this.materias = materias;
    }

    //metodos
    @Override
    public void mostrarmenu(Usuario user) {
        int opcion;
        do  {
        
            System.out.println("Menú de profesores: ");
            System.out.println("=".repeat(50));
            System.out.println("1.- Reservar");
            System.out.println("2.- Consultar el estado de la reserva: ");
            System.out.println("3.- Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la fecha para la cual quiere reservar");
                    String fechaR = sc.nextLine();
                    System.out.println("Ingrese el tipo de espacio que desea reservar");
                    String tipoEspacio = sc.nextLine();
                    reservar(fechaR, tipoEspacio, user);
                    break;
                case 2:
                    System.out.println("Ingrese la fecha de la reserva que quiere consultar");
                    String fechaC = sc.nextLine();
                    consultarReserva(fechaC);
                case 3:
                System.out.println("saliendo...");

                default:
                System.out.println("opción no valida");
                    break;
            }

        } while (opcion != 0);
    }

    @Override
    public void reservar(String fecha, String espacio, Usuario user) {
        int cont = 1;
        //mostrando los espacios disponibles
        EspaciosDisponibles(fecha, espacio);

        //materias que da el profesor
        Profesor p = (Profesor) user;
        for(String materias: p.materias){
            System.out.println(cont+") "+materias);
            cont++;
        }
        //materias que da el profesor
        Profesor p = (Profesor) user;
        for(String materias: p.materias){
            System.out.println(cont+") "+materias);
            cont++;
        }
        
        //elegira el espacio a reservar
        System.out.println("Ingrese el codigo del espacio que desea reservar");
        String codigo = sc.nextLine();
        System.out.println("Ingrese la materia para la cual usara el espacio");
        String motivo = sc.nextLine();        
        System.out.println("Desea crear la reserva? (S/N)");
        String crearReserva = sc.nextLine();
        if(crearReserva.equals("S")){
            //generar codigo de reserva
            generarCodigoR++;
            String codigoR = String.valueOf(generarCodigoR);
            //crear la reserva
            String linea = codigoR+" | "+user.getCodigoUnico()+" | "+user.getCedula()+" | "+fecha+" | "+codigo+" | "+espacio.toUpperCase()+" | "+"APROBADO"+" | "+motivo;
            ManejoArchivo.EscribirArchivo("reservas.txt", linea);
            contReserva++;

            //envio de notificacion
            p.setMateriaSeleccionada(materiaSeleccionada); //con esto le asigno la materia al obj del profesor
            p.enviarCorreo(); //llamo al metodo para enviar el correo
        }
    }

         @Override
         public void enviarCorreo() {
            try {
                // Formar el mensaje personalizado
                String asunto = "Reserva realizada";
                String mensaje = "Se le notifica que el profesor " + this.getNombre() + " " + this.getApellido() +
                                 " ha realizado una reserva con código " + codigoReserva +
                                 " para la fecha " + fechaReserva +
                                 " en el auditorio " + espacioReserva.toUpperCase() +
                                 " para la materia " + materiaSeleccionada + "."; //la materiaSeleccionada falta por agregar

                 // Llamar al método estático de EnvioCorreo
                   EnvioCorreo.enviarCorreo(
                   correoAdministrador, // destinatario
                   asunto,              // asunto
                   mensaje              // mensaje
                   );

                  System.out.println("Correo enviado correctamente al administrador.");
              } catch (MessagingException e) {
                   System.out.println("Error al enviar correo: " + e.getMessage());
                }
         }


    //getters y setters
    public String getFacultad() {
        return facultad;
    }
    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
    public ArrayList<String> getMaterias() {
        return materias;
    }
    public void setMaterias(ArrayList<String> materias) {
        this.materias = materias;
    }
    public void setMateriaSeleccionada(String materia) {
        this.materiaSeleccionada = materia;
    }

    public String getMateriaSeleccionada() {
        return this.materiaSeleccionada;
    }

}

    

    




    
    





