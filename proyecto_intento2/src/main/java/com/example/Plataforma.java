package com.example;

import java.util.Scanner;

public class Plataforma {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Usuario.cargarUsuarios();
        Usuario.cargarEspacios();
        Usuario.cargarReservas();
        
        //verificacion de usuario
        System.out.println("Validacion de usuario");
        System.out.println("Ingrese su usuario");
        String user = sc.nextLine();
        System.out.println("Ingrese su contraseña");
        String password = sc.nextLine();
        boolean userExists = false;
        for(Usuario usuario: Usuario.usuarios){
            if (usuario.getUsuario().equals(user) && usuario.getContraseña().equals(password)) {
                System.out.println("Bienvenido al sistema de reserva de espacios de la Universidad");
                switch (usuario.getRol()) {
                    case "E":
                        Estudiante est = (Estudiante) usuario;
                        est.mostrarMenu(usuario);
                        break;

                    case "P":
                        Profesor prof = (Profesor) usuario;
                        prof.mostrarMenu(usuario);
                        break;

                    case "A":
                        Administrador admin = (Administrador) usuario;
                        admin.mostrarMenu(usuario);
                        break;
                    default:
                        break;
                }
                userExists = true;
            }
        }

        if (userExists == true) {
            System.out.println("Gracios por usar nuestro servicio");
        }else{
            System.out.println("El usuario no existe");
        }
    }
}
