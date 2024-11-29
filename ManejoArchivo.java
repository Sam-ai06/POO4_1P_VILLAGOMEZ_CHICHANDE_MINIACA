package com.proyecto;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ManejoArchivo {

    // Método para leer archivos
    public static ArrayList<String> LeeFichero(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }

    // Método para escribir una línea al final del archivo (sin sobrescribir)
    public static void EscribirArchivo(String nombreArchivo, String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, true))) {  // true permite añadir al final
            bw.write(linea);  // Escribir la línea
            bw.newLine();     // Añadir una nueva línea
        } catch (IOException e) {
            e.printStackTrace();  // Manejo de excepciones
        }
    }

    // Método para modificar el estado de un espacio
    public static void ModificarEstadoEspacio(String nombreArchivo, String codigoEspacio, EstadoEspacio nuevoEstado) {
        // Leer las líneas del archivo
        ArrayList<String> lineas = LeeFichero(nombreArchivo);

        // Modificar la línea correspondiente
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            String[] partes = linea.split("\\|");  // Usar el delimitador correcto (sin espacio antes del '|')
            
            if (partes[0].trim().equals(codigoEspacio)) {  // Comprobamos si el código coincide
                if (partes.length >= 5) {  // Aseguramos que haya al menos 5 partes
                    partes[4] = nuevoEstado.name();  // Modificamos el estado
                }
                // Volver a juntar la línea modificada
                lineas.set(i, String.join(" | ", partes));
                break;  // Salimos del bucle una vez que encontramos el código
            }
        }

        // Escribir de nuevo el archivo con las modificaciones
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, StandardCharsets.UTF_8))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();  // Escribir una nueva línea
            }
        } catch (IOException e) {
            e.printStackTrace();  // Manejo de excepciones de escritura
        }
    }
}
