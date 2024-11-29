import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
public class ManejoArchivo {

    // Método para leer el archivo y devolverlo como una lista de líneas
    public static ArrayList<String> LeeFichero(String nombrearchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        
        // Usamos try-with-resources para manejar la apertura y cierre de recursos automáticamente, se usó esto en lugar de finally
        try (BufferedReader br = new BufferedReader(new FileReader(nombrearchivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea); 

            }

        } catch (Exception e) {
            e.printStackTrace();

        } 
        return lineas;

    }

    public static void EscribirArchivo(String nombreArchivo, String linea) {

        FileWriter fichero = null;
        BufferedWriter bw = null;
      
        try {
            fichero = new FileWriter(nombreArchivo,true);
            bw = new BufferedWriter(fichero);
            bw.write(linea+"\n");

        } catch (Exception e) {
            e.printStackTrace();  // Maneja los errores de escritura en el archivo
        }
    
    }

    public static void VaciarArchivo(String nombreArchivo) {

        try {
            FileWriter file = new FileWriter(nombreArchivo,false);
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    //fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
