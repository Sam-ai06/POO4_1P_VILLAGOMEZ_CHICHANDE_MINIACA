import java.util.ArrayList;

public class Profesor extends Usuario {

    private String facultad;
    private ArrayList<String> materias;


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

    public Profesor(String cedula, String nombre, String apellido, String codigo, String contrasena, String correo,
            String rol, String facultad, ArrayList<String> materias) {
        super(cedula, nombre, apellido, codigo, contrasena, correo, rol);
        this.facultad = facultad;
        this.materias = materias;
    }

        
    @Override
    public void reservar() {
        //por llenar
    }
    @Override
    public void enviarCorreo(EnvioCorreo EnvioCorreo) {
        try {
            EnvioCorreo.enviarCorreo(
                this.correo,
                "Notificación para Profesor",
                "Este es un mensaje de prueba para un profesor." //se puede cambiar este mensaje al necesitado solo es para tener una idea
            );
        } catch (MessagingException e) {
            System.out.println("Error al enviar correo: " + e.getMessage());
        }
      }
    }



    
}
