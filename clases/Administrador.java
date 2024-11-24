package clases;
public class Administrador extends Usuario {

    private String cargo;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Administrador(String cedula, String nombre, String apellido, String codigo, String contrasena, String correo,
            String rol, String cargo) {
        super(cedula, nombre, apellido, codigo, contrasena, correo, rol);
        this.cargo = cargo;
    }

    @Override
    public void reservar() {
        //NO RESERVA
    }

    @Override
    public void Enviarcorreo(String mensaje) {
        //por llenar
    }

    public void gestionarReserva(){
        //por llenar
    }


    

    
    
}
