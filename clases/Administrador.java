package clases;
public class Administrador extends Usuario {

    private String cargo;

    //constructor
    public Administrador(String cedula, String nombre, String apellido, String codigo, String contrasena, String correo,
            String rol, String cargo) {
        super(cedula, nombre, apellido, codigo, contrasena, correo, rol);
        this.cargo = cargo;
    }

    //metodos
    @Override
    public void reservar() {
        //NO RESERVA
    }

    @Override
    public void Enviarcorreo(String mensaje) {
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
    
    @override
    public void consultarReserva(Date fecha){
        SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
        String fechaS = dateF.format(fecha);
        String codigoE = "";
        //mostrando la cantidad de reservas creadas
        System.out.println("Número de reservas creadas: "+contReserva);
        //mostrando las reservas creadas
        for(String LR: listaReserva){
            String[] partesR = LR.split(" | ");
            if (partesR[3].equals(fechaS)) {
                for(String LU: listaUsuario){
                    String[] partesU = LR.split(" | ");
                    if(partesU[7].equals("E")){
                        for(String LE: listaEstudiantes){
                            String[] partesE = LE.split(" | ");
                            if (partesU[1].equals(partesE[1])) {
                                System.out.println(partesR[0]+" - "+partesR[6]+" - "+fechaS+" - "+partesU[2]+" "+partesU[3]+" - "+partesE[4]+" - "+"ESTUDIANTE");
                            }
                        }      
                    }else if (partesU[7].equals("P")) {
                        System.out.println(partesR[0]+" - "+partesR[6]+" - "+fechaS+" - "+partesU[2]+" "+partesU[3]+" - "+partesR[7]+" - "+"PROFESOR");
                    }
                }
            }
        }
    }
    
    //getters y setters
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}
