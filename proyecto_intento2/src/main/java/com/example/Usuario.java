package com.example;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Usuario {
    protected String codigoUnico;
    protected String cedula;
    protected String nombres;
    protected String apellidos;
    protected String usuario;
    protected String contraseña;
    protected String correo;
    protected String rol;
    protected static int contReserva;
    protected static int generarCodigoR = 5015;

    protected DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Usuario(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contraseña, String correo, String rol) {
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.rol = rol;
    }

    protected static ArrayList<Usuario> usuarios = new ArrayList<>();
    protected static ArrayList<Reserva> reservas = new ArrayList<>();
    protected static ArrayList<Espacio> espacios = new ArrayList<>();

    //metodos abstractos
    public abstract void mostrarMenu(Usuario usuario);

    protected abstract void enviarCorreo();

    protected abstract void reservar(LocalDate fecha, Usuario usuario);

    //metodos
    public static void cargarUsuarios() {
        // Cargar usuarios desde el archivo usuarios.txt
        ArrayList<String> listUser = ManejoArchivo.LeeFichero("usuarios.txt");
        ArrayList<String> listEst = ManejoArchivo.LeeFichero("estudiantes.txt");
        ArrayList<String> listProf = ManejoArchivo.LeeFichero("profesores.txt");
        ArrayList<String> listAdmin = ManejoArchivo.LeeFichero("administradores.txt");
        
        for(String user: listUser){
            String[] datosUser = user.split("\\|");
            //quitar espacios
            for (int i = 0; i < datosUser.length; i++) {
                datosUser[i] = datosUser[i].trim();
            }

            // Crear el objeto correspondiente según el rol
            if (datosUser[7].equals("E")) {
                for(String est: listEst){
                    String[] datosEst = est.split("\\|");
                    //quitar espacios
                    for (int i = 0; i < datosEst.length; i++) {
                        datosEst[i] = datosEst[i].trim();
                    }

                    if (datosUser[0].equals(datosEst[0])) {
                        usuarios.add(new Estudiante(datosUser[0], datosUser[1], datosUser[2], datosUser[3], datosUser[4], datosUser[5], datosUser[6], datosUser[7], datosEst[4], datosEst[5]));
                    }
                }
            } else if (datosUser[7].equals("P")) {
                // Crear el objeto correspondiente según el rol
                for(String prof: listProf){
                    String[] datosProf = prof.split("\\|");
                    //quitar espacios
                    for (int i = 0; i < datosProf.length; i++) {
                        datosProf[i] = datosProf[i].trim();
                    }

                    if (datosUser[0].equals(datosProf[0])) {
                        String[] materias = datosProf[5].split("\\|");
                        usuarios.add(new Profesor(datosUser[0], datosUser[1], datosUser[2], datosUser[3], datosUser[4], datosUser[5], datosUser[6], datosUser[7], datosProf[4], materias));
                    }
                }
            } else if (datosUser[7].equals("A")) {
                for(String admin: listAdmin){
                    String[] datosAdmin = admin.split("\\|");
                    //quitar espacios
                    for (int i = 0; i < datosAdmin.length; i++) {
                        datosAdmin[i] = datosAdmin[i].trim();
                    }
                    if (datosUser[0].equals(datosAdmin[0])) {
                        usuarios.add(new Administrador(datosUser[0], datosUser[1], datosUser[2], datosUser[3], datosUser[4], datosUser[5], datosUser[6], datosUser[7], datosAdmin[4]));
                    }
                }
            }
        }
    }
    

    public static void cargarReservas() {
        // Cargar reservas desde el archivo reservas.txt
        ArrayList<String> listReser = ManejoArchivo.LeeFichero("reservas.txt");
        for(String reserva: listReser){
            String[] reser = reserva.split("\\|");
            //quitar espacios
            for (int i = 0; i < reser.length; i++) {
                reser[i] = reser[i].trim();
            }

            TipoEspacio tipoE = null;
            if (reser[5].equals("AULA")) {
                tipoE = TipoEspacio.AULA;
            }else if (reser[5].equals("LABORATORIO")) {
                tipoE = TipoEspacio.LABORATORIO;
            }else if (reser[5].equals("AUDITORIO")) {
                tipoE = TipoEspacio.AUDITORIO;
            }else if (reser[5].equals("CANCHA")) {
                tipoE = TipoEspacio.CANCHA;
            }

            if (reser[6].equals("APROBADO")) {
                reservas.add(new Reserva(reser[0], reser[1], reser[2], reser[3], reser[4], tipoE, EstadoReserva.APROBADO, reser[7]));
            }else if (reser[6].equals("PENDIENTE")) {
                reservas.add(new Reserva(reser[0], reser[1], reser[2], reser[3], reser[4], tipoE, EstadoReserva.PENDIENTE, reser[7]));
            }else{
                reservas.add(new Reserva(reser[0], reser[1], reser[2], reser[3], reser[4], tipoE, EstadoReserva.RECHAZADO, reser[7]));
            } 
        }
    }

    public static void cargarEspacios() {
        // Cargar espacios desde el archivo espacios.txt
        ArrayList<String> listEspac = ManejoArchivo.LeeFichero("espacios.txt");
        TipoEspacio tipoEspacio = null;
        Permiso permiso = null;
        EstadoEspacio estadoEspacio= null;
        for(String espacio: listEspac){
            String[] espac = espacio.split("\\|");
            //quitar espacios
            for (int i = 0; i < espac.length; i++) {
                espac[i] = espac[i].trim();
            }

            if (espac[1].equals("AULA")) {
                tipoEspacio = TipoEspacio.AULA;
            }else if (espac[1].equals("LABORATORIO")) {
                tipoEspacio = TipoEspacio.LABORATORIO;
            }else if (espac[1].equals("AUDITORIO")) {
                tipoEspacio = TipoEspacio.AUDITORIO;
            }else if (espac[1].equals("CANCHA")) {
                tipoEspacio = TipoEspacio.CANCHA;
            }

            if (espac[4].equals("DISPONIBLE")) {
                estadoEspacio = EstadoEspacio.DISPONIBLE;
            }else if (espac[1].equals("OCUPADO")) {
                estadoEspacio = EstadoEspacio.OCUPADO;
            }

            if (espac[5].equals("ESTUDIANTE")) {
                permiso = Permiso.ESTUDIANTES;
            }else if (espac[5].equals("PROFESOR")) {
                permiso = Permiso.PROFESORES;
            }
            else if (espac[5].equals("AMBOS")) {
                permiso = Permiso.AMBOS;
            }
            int capacidad = Integer.parseInt(espac[3]);
            espacios.add(new Espacio(espac[0], tipoEspacio, espac[2], capacidad, estadoEspacio, permiso));
        }
    }

    protected void consultarReserva(LocalDate fecha) {
        //convercion de localdate a string
        String fechaString = fecha.format(formatoFecha);

        for(Reserva reserva: reservas){
            if (reserva.getFechaReserva().equals(fechaString)) {
                for(Espacio espacio: espacios){
                    if (reserva.getCodigoEspacioReservado().equals(espacio.getCodigoUnico())) {
                        for(Usuario usuario: usuarios){
                            if (reserva.getCedulaUsuario().equals(usuario.getCedula())) {
                                System.out.println(reserva.getCodigoUnicoReserva()+"-"+reserva.getFechaReserva()+"-"+reserva.getTipoEspacio()+"-"+espacio.getNombre()+"-"+espacio.getCapacidad()+"-"+usuario.getNombres()+" "+usuario.getApellidos());
                            }
                        } 
                    }
                }
            }
        }
    }

    // Método para mostrar los espacios disponibles
    public void EspaciosDisponibles(LocalDate fecha, Usuario user) {
        //DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaString = fecha.format(formatoFecha);
        System.out.println("Mostrando todos los espacios disponibles");
        for(Reserva reserva: reservas){
            if (reserva.getFechaReserva().equals(fechaString)) {
                if (user instanceof Estudiante) {
                    //Estudiante usuarioE = (Estudiante) user;
                    for(Espacio espacio: espacios){
                        if ((espacio.getTipoEspacio().equals(TipoEspacio.AULA) || espacio.getTipoEspacio().equals(TipoEspacio.CANCHA)) && espacio.getEstado().equals(EstadoEspacio.DISPONIBLE) && reserva.getCodigoEspacioReservado().equals(espacio.getCodigoUnico())) {
                            System.out.println("Codigo: " + espacio.getCodigoUnico() + " - Nombre: " + espacio.getNombre() + " - Capacidad: " + espacio.getCapacidad());
                        }
                    }
                }else if (user instanceof Profesor) {
                    //Profesor usuarioP = (Profesor) user;
                    for(Espacio espacio: espacios){
                        if ((espacio.getTipoEspacio().equals(TipoEspacio.AULA) || espacio.getTipoEspacio().equals(TipoEspacio.AUDITORIO) || espacio.getTipoEspacio().equals(TipoEspacio.LABORATORIO)) && espacio.getEstado().equals(EstadoEspacio.DISPONIBLE) && reserva.getCodigoEspacioReservado().equals(espacio.getCodigoUnico())) {
                            System.out.println("Codigo: " + espacio.getCodigoUnico() + " - Nombre: " + espacio.getNombre() + " - Capacidad: " + espacio.getCapacidad());
                        }
                    }
                }
            }
        }
    }

    // Getters y Setters
    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public ArrayList<Usuario> getListaUsuarios(){
        return usuarios;
    }
}