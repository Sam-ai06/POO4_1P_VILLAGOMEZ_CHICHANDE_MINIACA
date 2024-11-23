import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class archivos {
    
    public static <Usuario> List<Usuario> cargarUsuarios(String ruta){

        List<Usuario> listaUsuarios = new ArrayList<>();

        try 
            (BufferedReader lector = new BufferedReader(new FileReader(ruta))){
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] segmentos = linea.split("|");
                    String Rol = segmentos[7];

                    switch (Rol) {
                        case "E":
                            listaUsuarios.add(new Estudiante());    //hacer indexación a los segmentos para completar cada parte del constructor
                                                                    //lo mismo para el caso de profesores y administradores, no sé como agregar la
                                                                    //facultad, materias, matricula y cargo. ahí vean
                            break;
                        case "P":
                            listaUsuarios.add(new Profesor("cedula", "nombre", "apellido", "codigo", "contraseña",
                             "correo", "rol", "facultad", "array list de las materias que da"));    
                             break;
                        case "A":
                        listaUsuarios.add(new Administrador("cedula", "nombre", "apellido", "codigo",
                         "contraseña", "correo", "rol", "cargo"));
                    
                        default:
                            break;
                    }
                }
                    
                }
            }

            
        