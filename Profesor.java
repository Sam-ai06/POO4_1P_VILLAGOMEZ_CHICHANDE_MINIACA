import java.util.ArrayList;
import java.util.Scanner;

public class Profesor extends Usuario {

    private String facultad;
    private ArrayList<String> materias;
    Scanner sc = new Scanner(System.in);


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
    public Profesor(String[] datos, String facultad, ArrayList<String> materias) {
        super(datos);
        this.facultad = facultad;
        this.materias = materias;
    }
    @Override
    public void mostrarmenu() {
        int opcion;
        do  {
        
           System.out.println("menú de profesores: ");
            System.out.println("=".repeat(50));
            System.out.println("1.- reservar");
            System.out.println("2.- consultar el estado dela reserva: ");
            System.out.println("3.- salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    //aquí va el metodo para reservar
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
}

    

    




    
    





