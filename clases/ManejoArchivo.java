import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ManejoArchivo {

    public static ArrayList<String> LeeFichero(String nombreArchivo){ ///metodo para leer archivos
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo, StandardCharsets.UTF_8))){
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
                
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        return lineas;
    } 



    public static void ModificarEstadoEspacio(String nombreArchivo, String codigoEspacio, EstadoEspacio nuevoEstado) {
        ArrayList<String> lineas = LeeFichero(nombreArchivo); 

        
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            
            
            String[] partes = linea.split(" \\| ");
            if (partes[0].equals(codigoEspacio)) {  
               
                if (partes.length >= 4) {
                    partes[4] = nuevoEstado.name();  
                }        
                lineas.set(i, String.join(" | ", partes));
                break;  
            }
        }

        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine(); 
            }
        } catch (IOException e) {
            e.printStackTrace();  
        }
    }
}