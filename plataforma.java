import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class plataforma{

    public static String[] CargarUsuarios(String archivo){
        ArrayList<String> usuarios= new ArrayList<>();
        Arraylist<String> estudiantes = new ArrayList<>();
        ArrayList<String> profesores = new ArrayList<>();
        ArrayList<String> administradores = new ArrayList<>();

        //PRUEBA

        try (BufferedReader br= new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea = br.readLine()) != null) {
                
                usuarios.add(linea);
                
            }
        } catch (IOException ex){
            System.out.println("no es posible leer el archivo"+ex.getMessage());
            }
            return usuarios.toArray(new String[0]); ///convierte el arraylist en un array
        }

        public static IniciarSesion(){

        }



        //PRUEBA
    public static void main(String[] args) {
        String archivo = "C:\\Users\\PC\\Desktop\\ESPOL\\P.O.O\\PROYECTO 1P\\usuarios.txt";
        String[] usuarios= CargarUsuarios(archivo);
        for (String usuario : usuarios) {
            System.out.println(usuario); //prueba para comprobar que los lee y los imprime
            
        }
    }
}