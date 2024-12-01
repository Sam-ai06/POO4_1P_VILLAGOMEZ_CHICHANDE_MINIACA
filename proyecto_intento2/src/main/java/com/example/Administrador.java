package com.example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Administrador extends Usuario {
    private String cargo;
    Scanner sc = new Scanner(System.in);

    public Administrador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contraseña, String correo, String rol, String cargo) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contraseña, correo, rol);
        this.cargo = cargo;
    }

    //metodos
    @Override
    public void reservar(LocalDate fecha, Usuario usuario){
        //no realiza reservas
    }

    @Override
    public void mostrarMenu(Usuario user) {
        int opcion;
        do  {
        
            System.out.println("Menú de administradores: ");
            System.out.println("1.- Gestionar reserva");
            System.out.println("2.- Consultar el estado de la reserva: ");
            System.out.println("3.- Salir");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    
                    break;
                case 2:
                    
                case 3:
                System.out.println("saliendo...");

                default:
                System.out.println("opción no valida");
                    break;
            }

        } while (opcion != 0);
    }

    public void gestionarReserva(String codigoR){
        //mostrando datos de la reserva
        for(Reserva reserva: reservas){
            if (reserva.getCodigoUnicoReserva().equals(codigoR)) {
                for(Espacio espacio: espacios){
                    if (reserva.getCodigoEspacioReservado().equals(espacio.getCodigoUnico())) {
                        for(Usuario user: usuarios){
                            if (reserva.getCodigoUsuario().equals(user.getCodigoUnico())) {
                                System.out.println(codigoR+" - "+reserva.getFechaReserva()+" - "+reserva.getTipoEspacio()+" - "+espacio.getNombre()+" - "+espacio.getCapacidad()+" - "+user.getNombres()+" "+user.getApellidos());
                            }
                        }
                    }
                }
            }
        }
        
        //toma de decision
        System.out.println("Desea aprobar la peticion? (S/N)");
        String op = sc.nextLine();

        //variables para modificar reserva
        String linea = "";
        String tipoEspacio = "";
        String estadoReserva = "";
        for(Reserva reserva: reservas){
            //asignacion del tipo de espacio y estado de la reserva
            if (reserva.getTipoEspacio().equals(TipoEspacio.AULA)) {
                tipoEspacio = "AULA";
            }else if (reserva.getTipoEspacio().equals(TipoEspacio.CANCHA)) {
                tipoEspacio = "CANCHA";
            }else if (reserva.getTipoEspacio().equals(TipoEspacio.AUDITORIO)) {
                tipoEspacio = "AUDITORIO";
            }else if (reserva.getTipoEspacio().equals(TipoEspacio.LABORATORIO)) {
                tipoEspacio = "LABORATORIO";
            }

            if (reserva.getEstadoReserva().equals(EstadoReserva.APROBADO)) {
                estadoReserva = "APROBADO";
            }else if (reserva.getEstadoReserva().equals(EstadoReserva.PENDIENTE)) {
                estadoReserva = "PENDIENTE";
            }else if (reserva.getEstadoReserva().equals(EstadoReserva.RECHAZADO)) {
                estadoReserva = "RECHAZADO";
            }

            if (reserva.getCodigoUnicoReserva().equals(codigoR) && op.equals("N")) {
                System.out.println("Escriba el motivo del rechazo");
                String motivo = sc.nextLine();
                reserva.setEstadoReserva(EstadoReserva.RECHAZADO);
                linea = reserva.getCodigoUnicoReserva()+" | "+reserva.getCodigoUsuario()+" | "+reserva.getCedulaUsuario()+" | "+reserva.getFechaReserva()+" | "+reserva.getCodigoEspacioReservado()+" | "+tipoEspacio+" | "+"RECHAZADO"+" | "+reserva.getMotivo();
            
                //enviando notificacion del rechazo
                enviarCorreo(reserva.getCodigoUnicoReserva(), false, motivo);
            }else if (reserva.getCodigoUnicoReserva().equals(codigoR) && op.equals("S")) {
                reserva.setEstadoReserva(EstadoReserva.APROBADO);
                linea = reserva.getCodigoUnicoReserva()+" | "+reserva.getCodigoUsuario()+" | "+reserva.getCedulaUsuario()+" | "+reserva.getFechaReserva()+" | "+reserva.getCodigoEspacioReservado()+" | "+tipoEspacio+" | "+"APROBADO"+" | "+reserva.getMotivo();
            
                //enviando notificacion de la aprobacion
                enviarCorreo(reserva.getCodigoUnicoReserva(), true, "");
            }else{
                linea = reserva.getCodigoUnicoReserva()+" | "+reserva.getCodigoUsuario()+" | "+reserva.getCedulaUsuario()+" | "+reserva.getFechaReserva()+" | "+reserva.getCodigoEspacioReservado()+" | "+tipoEspacio+" | "+estadoReserva+" | "+reserva.getMotivo();
            }
            
            ManejoArchivo.EscribirArchivo("reservas.txt", linea);
        }        
    }
    
    @Override
    public void consultarReserva(LocalDate fecha){

    }

    public void consultarReserva(){
        //mostrando la cantidad de reservas creadas
        System.out.println("Número de reservas creadas: "+contReserva);

        //mostrando las reservas creadas
        for(Reserva reserva: reservas){
            for(Usuario user: usuarios){
                if (reserva.getCodigoUsuario().equals(user.getCodigoUnico())) {
                    if (user instanceof Estudiante) {
                        Estudiante est = (Estudiante) user;
                        System.out.println(reserva.getCodigoUnicoReserva()+" - "+reserva.getEstadoReserva()+" - "+reserva.getFechaReserva()+" - "+est.getNombres()+" "+est.getApellidos()+" - "+est.getMatricula()+" - "+"ESTUDIANTE");
                    }else if (user instanceof Profesor) {
                        Profesor prof = (Profesor) user;
                        System.out.println(reserva.getCodigoUnicoReserva()+" - "+reserva.getEstadoReserva()+" - "+reserva.getFechaReserva()+" - "+prof.getNombres()+" "+prof.getApellidos()+" - "+reserva.getMotivo()+" - "+"PROFESOR");
                    }
                }
            }
        }
    }

    @Override
    public void enviarCorreo(){

    }
    
    public void enviarCorreo(String codigoReserva, boolean aprobado, String motivo){
        String destinatario = "";
        String asunto = "Reserva ";
        String mensaje = "Se ha ";

        if (aprobado == true) {
            asunto += "aprobada";
            mensaje += "aprobado su reserva con código "+codigoReserva+".";
        }else{
            asunto += "rechazada";
            mensaje += "rechazado su reserva con código "+codigoReserva+" por el siguiente motivo: "+motivo;
        }
        mensaje += "\\\\Atentamente,\\Departamento Administrativo";
        EnvioCorreo.enviarCorreo(destinatario, asunto, mensaje);
    }

    //getters y setters
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
