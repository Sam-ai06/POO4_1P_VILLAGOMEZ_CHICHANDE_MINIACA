package com.example;

import java.time.LocalDate;
import java.util.Scanner;

public class Estudiante extends Usuario {
    private String matricula;
    private String carrera;

    Scanner sc = new Scanner(System.in);

    public Estudiante(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contraseña, String correo, String rol, String matricula, String carrera) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contraseña, correo, rol);
        this.matricula = matricula;
        this.carrera = carrera;
    }

    //metodos
    @Override
    public void mostrarMenu(Usuario user) {
        int opcion;
        do {
            System.out.println("Menú de Estudiante: ");
            System.out.println("1.- Reservar");
            System.out.println("2.- Consultar el estado de la reserva");
            System.out.println("3.- Salir");

            opcion = sc.nextInt();
            sc.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la fecha para la cual desea reservar");
                    String fechaSR = sc.nextLine();
                    LocalDate dateR = LocalDate.parse(fechaSR);
                    reservar(dateR, user);
                    break;
                case 2:
                    System.out.println("Ingrese la fecha de la reserva a consultar");
                    String fechaSC = sc.nextLine();
                    LocalDate dateC = LocalDate.parse(fechaSC);
                    consultarReserva(dateC);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion !=3);
    }

    @Override
    public void reservar(LocalDate fecha, Usuario usuario) {
        TipoEspacio tipoEspacio = null;
        String fechaS = fecha.format(formatoFecha);
        //mostrando los espacios disponibles
        EspaciosDisponibles(fecha, usuario);
        //elegiendo el espacio a reservar
        System.out.println("Ingrese el codigo del espacio que desea reservar");
        String codigo = sc.nextLine();
        System.out.println("Ingrese el tipo de espacio que desea reservar");
        String tipoE = sc.nextLine();
        System.out.println("Ingrese el motivo por el que desea reservar el espacio");
        String motivo = sc.nextLine();
        System.out.println("Desea crear la reserva? (S/N)");
        String crearReserva = sc.nextLine();
        if(crearReserva.equals("S")){
            //generando codigo de reserva
            generarCodigoR++;
            String codigoR = String.valueOf(generarCodigoR);
            //guardando la reserva
            if (tipoE.toUpperCase().equals("AULA")) {
                tipoEspacio = TipoEspacio.AULA;
            }else if (tipoE.toUpperCase().equals("CANCHA")) {
                tipoEspacio = TipoEspacio.CANCHA;
            }
            reservas.add(new Reserva(codigoR, usuario.getCodigoUnico(), usuario.getCedula(), fechaS, codigo, tipoEspacio, EstadoReserva.PENDIENTE, motivo));
            String linea = codigoR+" | "+usuario.getCodigoUnico()+" | "+usuario.getCedula()+" | "+fechaS+" | "+codigo+" | "+tipoE+" | "+"PENDIENTE"+" | "+motivo;
            ManejoArchivo.EscribirArchivo("reservas.txt", linea);
            
            //incremento de la cantidad de reservas creadas
            contReserva++;
            //envio de correo
            enviarCorreo();
            System.out.println("Reserva creada con éxito y correo enviado al administrador.");
        } else {
            System.out.println("Reserva cancelada.");
        }
            
      }

    @Override
    public void enviarCorreo(){
        String destinatario = "";
        String asunto = "Reserva realizada";
        String mensaje = "El estudiante "+this.getNombres()+" "+this.getApellidos()+" ha realizado una reserva con código ";
        for(Reserva reserva: reservas){
            if (this.getCodigoUnico().equals(reserva.getCodigoUsuario())) {
                mensaje += reserva.getCodigoUnicoReserva()+" para la fecha "+reserva.getFechaReserva();
            }

            for(Espacio espacio: espacios){
                if (reserva.getCodigoEspacioReservado().equals(espacio.getCodigoUnico())) {
                    if (espacio.getTipoEspacio().equals(TipoEspacio.AULA)) {
                        mensaje += "en el aula "+espacio.getNombre()+".";
                    }else if (espacio.getTipoEspacio().equals(TipoEspacio.CANCHA)) {
                        mensaje += "en la cancha "+espacio.getNombre()+".";
                    }
                }
            }
        }
        mensaje += "\\Ingrese al sistema para aprobar o rechazar la reserva";
        EnvioCorreo.enviarCorreo(destinatario, asunto, mensaje);
    }

    //getter y setter
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

}