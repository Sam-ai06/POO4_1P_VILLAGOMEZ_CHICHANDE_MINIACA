import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
public class plataforma{



    ArrayList<String> usuarios= new ArrayList<>();
    ArrayList<String> estudiantes = new ArrayList<>();
    ArrayList<String> profesores = new ArrayList<>();
    ArrayList<String> administradores = new ArrayList<>();
    ArrayList<String> espacios = new ArrayList<>();


    public class manejoArchivos{

        public static ArrayList<String> leerFichero(String nombreArchivo){
            ArrayList<String> lineas = new ArrayList<>();
            File archivo = null;
            FileReader lector = null;
            BufferedReader buffer = null;
            
            try{
                archivo = new File(nombreArchivo);
                lector = new FileReader(archivo, StandardCharsets.UTF_8);
                buffer = new BufferedReader(buffer);


                String linea;
                while ((linea = buffer.readLine()) !=null) {
                    System.out.println(linea);
                    lineas.add(linea);
                    
                }
            } catch (Exception e){
                e.printStackTrace();
            }finally{

                try {
                    if (null!=lector) {
                        lector.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return lineas;
        }
    }



    public static void EscrirArchivo(String nombreArchivo, String linea){
        FileWriter fichero = null;
        BufferedWriter buffer2 = null;

        try {
            fichero = new FileWriter(nombreArchivo, true);
            buffer2 = new BufferedWriter(fichero);
            buffer2.write(linea+"\n");
            System.out.println("demo");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                
                if (null!= fichero){
                    buffer2.close();
                }
            }   catch (Exception e2) {
                    e2.printStackTrace();
             
            }
        }
    }

    
}

