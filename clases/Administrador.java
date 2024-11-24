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

    
    public void gestionarReserva(String codigoR){
        //mostrando datos de la reserva
        for(String LR: listaReserva){
            String[] partesR = LR.split(" | ");
            for(String LE: listaEspacio){
                String[] partesE = LE.split(" | ");
                for(String LEST: listaEstudiantes){
                    String[] partesEST = LEST.split(" | ");
                    if (codigoR.equals(partesR[0]) && partesR[4].equals(partesE[0]) && partesR[1].equals(partesEST[0])) {
                        System.out.println(codigoR+" | "+partesR[3]+" | "+partesR[5]+" | "+partesE[2]+" | "+partesE[3]+" | "+partesEST[2]+partesEST[3]);
                    }
                }
            }
        }
        //toma de decision
        System.out.println("Desea aprobar la peticion? (S/N)");
        String op = sc.nextLine();
        if (op.equals("N")) {
            System.out.println("Escriba el motivo del rechazo");
            String motivo = sc.nextLine();
            for(String LR: listaReserva){
                String[] partesR = LR.split(" | ");
                if (codigoR.equals(partesR[0])) {
                    partesR[6] = "RECHAZADO";
                    partesR[7] = motivo;
                    String linea = String.join(" | ", partesR);
                    plataforma.EscribirArchivo("reservas.txt", linea);
                }
            }
        }else{
            for(String LR: listaReserva){
                String[] partesR = LR.split(" | ");
                if (codigoR.equals(partesR[0])) {
                    partesR[6] = "APROBADO";
                    String linea = String.join(" | ", partesR);
                    plataforma.EscribirArchivo("reservas.txt", linea);
                }
            }
        }
        //envio de notificacion

    }
    

    
    
}
