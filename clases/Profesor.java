package clases;
import java.util.ArrayList;
import java.util.Scanner;

public class Profesor extends Usuario {

    private String facultad;
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
    public void mostrarmenu() {
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
                    //metodo para enviar correo
                    break;
                case 2:
                    //aqui va el metodo para consultar el estado de la reserva
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
        String codigoR = "";
        int cont = 1;
        //mostrara los espacios disponibles del tipo de espacio elegido
        for(String LE: listaEspacio){
            String[] partesE = LE.split(" | ");
            if(espacio.toUpperCase().equals(partesE[1]) && partesE[4].equals("DISPONIBLE")){
                System.out.println("Codigo: "+partesE[0]+" - Nombre: "+partesE[2]+" - Capacidad: "+partesE[3]);
            }
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
            for(String LR: listaReserva){
                String[] partesR = LR.split(" | ");
                codigoR = partesR[0];
            }
            int codigoUR = Integer.parseInt(codigoR);
            codigoUR++;
            codigoR = String.valueOf(codigoUR);
            //crear la reserva
            String linea = codigoR+" | "+user.getCodigo()+" | "+user.cedula+" | "+fecha+" | "+codigo+" | "+espacio.toUpperCase()+" | "+"APROBADO"+" | "+motivo;
            plataforma.EscribirArchivo("reservas.txt", linea);
            contReserva++;
            //envio del mail

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
}

    

    




    
    





