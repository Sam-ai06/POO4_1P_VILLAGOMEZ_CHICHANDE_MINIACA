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

    public static void EscribirArchivo(String nombreArchivo, ArrayList<String> lineas) {
      
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String linea : lineas) {
                writter.write(linea);
                writter.newLine();
            }

        } catch (Exception e) {
            System.out.println("error al escribir el archivo: "+e.getMessage());
              // Maneja los errores de escritura en el archivo
        }
    
    }



    
}
