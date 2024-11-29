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
    public void mostrarmenu(Usuario user) {
        int opcion;
        do  {
        
            System.out.println("Menú de administradores: ");
            System.out.println("1.- Gestionar reserva");
            System.out.println("2.- Consultar el estado de la reserva: ");
            System.out.println("3.- Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el codigo de la reserva a gestionar");
                    String codigo = sc.nextLine();
                    gestionarReserva(codigo);
                    break;
                case 2:
                    System.out.println("Ingrese la fecha de la reserva que quiere consultar");
                    String fechaC = sc.nextLine();
                    consultarReserva(fechaC);
                case 3:
                System.out.println("saliendo...");

                default:
                System.out.println("opción no valida");
                    break;
            }

        } while (opcion != 0);
    }

@Override
public void enviarCorreo(String codigoReserva, boolean aprobado, String motivo) {
    try {
        String estadoReserva = aprobado ? "aprobada" : "rechazada";
        String mensajeCorreo = "Se ha " + estadoReserva + " su reserva con código " + codigoReserva;

        // Incluir el motivo si la reserva fue rechazada
        if (!aprobado) {
            mensajeCorreo += " por el siguiente motivo: " + motivo + ".";
        }

        mensajeCorreo += "\n\nAtentamente,\nDepartamento Administrativo";
        String asuntoCorreo = "Reserva " + estadoReserva;

        // Aquí hay que recuperar el correo del estudiante desde el archivo
        String correoEstudiante = obtenerCorreoEstudiante(codigoReserva);

        // Enviar el correo
        EnvioCorreo.enviarCorreo(
            "correoAdministrador@app.com", // Correo del administrador qu puede ser el mismo de la app code que saque para el env
            correoEstudiante,
            asuntoCorreo,
            mensajeCorreo 
        );

        System.out.println("Correo enviado al estudiante: " + correoEstudiante);

    } catch (Exception e) {
        System.out.println("Error al enviar correo: " + e.getMessage());
    }
}
    
    public void gestionarReserva(String codigoR){
        ArrayList<String> listReserva = new ArrayList<>();
        //mostrando datos de la reserva
        for(String LR: listaReserva){
            String[] partesR = LR.split(" | ");
            for(String LE: listaEspacio){
                String[] partesE = LE.split(" | ");
                for(String listUsuario: listaUsuario){
                    String[] partesUsuario = listUsuario.split(" | ");
                    if (codigoR.equals(partesR[0]) && partesR[4].equals(partesE[0]) && partesR[1].equals(partesUsuario[0])) {
                        System.out.println(codigoR+" | "+partesR[3]+" | "+partesR[5]+" | "+partesE[2]+" | "+partesE[3]+" | "+partesUsuario[2]+" "+partesUsuario[3]);
                    }
                }
            }
        }
        
        //toma de decision
        String linea = "";
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
                    linea = String.join(" | ", partesR);
                }
            }
            //envio notificacion rechazado
            enviarCorreo(codigoR, false, motivo);
        }else{
            for(String LR: listaReserva){
                String[] partesR = LR.split(" | ");
                if (codigoR.equals(partesR[0])) {
                    partesR[6] = "APROBADO";
                    linea = String.join(" | ", partesR);
                }
            }
            //envio notificacion aprobado
            enviarCorreo(codigoR, true, "");
        }
        //creando la lista de las reservas
        for(String listR: listaReserva){
            String[] partesR = listR.split(" | ");
            String line = partesR[0]+" | "+partesR[1]+" | "+partesR[2]+" | "+partesR[3]+" | "+partesR[4]+" | "+partesR[5]+" | "+partesR[6]+" | "+partesR[7];
            listReserva.add(line);
        }
        //vaciando el archivo reserva
        ManejoArchivo.VaciarArchivo("reservas.txt");
        //añadiendo la lista de las reservas y la reserva modificada
        for(String anadirR: listReserva){
            ManejoArchivo.EscribirArchivo("reservas.txt", anadirR);
        }
        ManejoArchivo.EscribirArchivo("reservas.txt", linea);
    }
    
    @override
    public void consultarReserva(Date fecha){
        SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
        String fechaS = dateF.format(fecha);
        //mostrando la cantidad de reservas creadas
        System.out.println("Número de reservas creadas: "+contReserva);
        //mostrando las reservas creadas
        for(String LR: listaReserva){
            String[] partesR = LR.split(" | ");
            if (partesR[3].equals(fechaS)) {
                for(String LU: listaUsuario){
                    String[] partesU = LU.split(" | ");
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
