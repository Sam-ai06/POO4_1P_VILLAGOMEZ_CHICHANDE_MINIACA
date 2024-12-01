package com.example;

import java.time.LocalDate;
import java.util.Scanner;

public class Profesor extends Usuario {
    private String facultad;
    private String[] materias;
    private String materiaSeleccionada;

    Scanner sc = new Scanner(System.in);

    public Profesor(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contraseña, String correo, String rol, String facultad, String[] materias) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contraseña, correo, rol);
        this.facultad = facultad;
        this.materias = materias;
    }

    // Métodos
    @Override
    public void mostrarMenu(Usuario user) {
        int opcion;
        do {
            System.out.println("Menú de profesores: ");
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

        } while (opcion != 3);  // El bucle termina cuando se selecciona la opción 3 (Salir)
    }

    @Override
    public void reservar(LocalDate fecha, Usuario usuario) {
        TipoEspacio tipoEspacio = null;
        String fechaS = fecha.format(formatoFecha);
        int cont = 1;
        // Mostrar los espacios disponibles
        EspaciosDisponibles(fecha, usuario);

        // Mostrar las materias que da el profesor
        if (usuario instanceof Profesor) {
            Profesor prof = (Profesor) usuario;
            for (String materia : prof.getMaterias()) {
                System.out.println(cont + ") " + materia);
                cont++;
            }
        }

        // Elegir el espacio a reservar
        System.out.println("Ingrese el código del espacio que desea reservar");
        String codigo = sc.nextLine();
        System.out.println("Ingrese el tipo de espacio que desea reservar");
        String tipoE = sc.nextLine();
        System.out.println("Ingrese la materia para la cual usará el espacio");
        String motivo = sc.nextLine();        
        System.out.println("Desea crear la reserva? (S/N)");
        String crearReserva = sc.nextLine();

        if (crearReserva.equalsIgnoreCase("S")) {
            //generando codigo de reserva
            generarCodigoR++;
            String codigoR = String.valueOf(generarCodigoR);
            //guardando la reserva
            reservas.add(new Reserva(codigoR, usuario.getCodigoUnico(), usuario.getCedula(), fechaS, codigo, tipoEspacio, EstadoReserva.APROBADO, motivo));
            String linea = codigoR+" | "+usuario.getCodigoUnico()+" | "+usuario.getCedula()+" | "+fechaS+" | "+codigo+" | "+tipoE+" | "+"APROBADO"+" | "+motivo;
            ManejoArchivo.EscribirArchivo("reservas.txt", linea);

            //incremento de la cantidad de reservas creadas
            contReserva++;
            
            // Enviar notificación
            enviarCorreo(motivo);
            System.out.println("Reserva creada con éxito y correo enviado al administrador.");
        }else {
            System.out.println("Reserva cancelada.");
        }
    }

    @Override 
    public void enviarCorreo(){

    }

    public void enviarCorreo(String materia){
        String destinatario = "";
        String asunto = "Reserva realizada";
        String mensaje = "Se le notifica que el profesor "+this.getNombres()+" "+this.getApellidos()+" ha realizado una reserva con código ";
        for(Reserva reserva: reservas){
            if (this.getCodigoUnico().equals(reserva.getCodigoUsuario())) {
                mensaje += reserva.getCodigoUnicoReserva()+" para la fecha "+reserva.getFechaReserva();
            }

            for(Espacio espacio: espacios){
                if (reserva.getCodigoEspacioReservado().equals(espacio.getCodigoUnico())) {
                    if (espacio.getTipoEspacio().equals(TipoEspacio.AULA)) {
                        mensaje += "en el aula "+espacio.getNombre();
                    }else if (espacio.getTipoEspacio().equals(TipoEspacio.AUDITORIO)) {
                        mensaje += "en el auditorio "+espacio.getNombre();
                    }else if (espacio.getTipoEspacio().equals(TipoEspacio.LABORATORIO)) {
                        mensaje += "en el laboratorio "+espacio.getNombre();
                    }
                }
            }
        }
        mensaje += " para la materia "+materia+".";
        EnvioCorreo.enviarCorreo(destinatario, asunto, mensaje);
    }

    // Getters y Setters
    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String[] getMaterias() {
        return materias;
    }

    public void setMaterias(String[] materias) {
        this.materias = materias;
    }

    public void setMateriaSeleccionada(String materia) {
        this.materiaSeleccionada = materia;
    }

    public String getMateriaSeleccionada() {
        return this.materiaSeleccionada;
    }
}
