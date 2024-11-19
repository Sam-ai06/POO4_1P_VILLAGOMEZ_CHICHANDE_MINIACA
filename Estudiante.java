public class Estudiante extends Usuario {

    private String matricula;
    private String carrera;
    

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Estudiante(String cedula, String nombre, String apellido, String codigo, String contrasena, String correo,
            String rol, String matricula, String carrera) {
        super(cedula, nombre, apellido, codigo, contrasena, correo, rol);
        this.matricula = matricula;
        this.carrera = carrera;
    }
    @Override
    public void reservar() {
        //por llenar
    }
    @Override
    public void Enviarcorreo(String mensaje) {
       //por llenar
    }

    

    

    

}
