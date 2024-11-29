import java.util.*;
import java.io.*;

public class plataforma{
    public static void main(String[] args) {
        ArrayList<String> listaUsuario = ManejoArchivo.LeeFichero("espacios.txt");
        ArrayList<String> listaEstudiantes = ManejoArchivo.LeeFichero("estudianes.txt");
        ArrayList<String> listaProfesores = ManejoArchivo.LeeFichero("profesores.txt");
        ArrayList<String> listaAdministradores = ManejoArchivo.LeeFichero("administradores.txt");
        //verificacion de usuario
        boolean usuarioEsxistente = false;
        String correo = "";
        String rol = "";
        String[] datos = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su usuario");
        String user = sc.nextLine();
        System.out.println("Ingrese su contraseña");
        String password = sc.nextLine();
        ArrayList<String> listaUser = plataforma.LeeFichero("usuarios.txt");
        for(String listUser: listaUsuario){
            String[] partesU = listUser.split(" | ");
            if(partesU[4].equals(user) && partesU[5].equals(password)){
                usuarioEsxistente = true;
                correo = partesU[6];
                rol = partesU[7];
                datos = {partesU[0],partesU[1],partesU[2],partesU[3],partesU[4],partesU[5],partesU[6],partesU[7]};
        }
            
        //funciones
        if(usuarioEsxistente){
            System.out.println("Bienvenido al sistema de reserva de espacio");
            switch (rol) {
                case "E":
                    String matricula = "";
                    String carrera = "";
                    for(String listEst: listaEstudiantes){
                        String[] partesE = listEst.split(" | ");
                        if(partesE[0].equals(datos[0])){
                            matricula = partesE[4];
                            carrera = partesE[5];
                        }
                    }
                    Estudiante est = new Estudiante(datos, matricula, carrera);
                    est.mostrarMenu(est);
                    break;
                case "P":
                    String facultad = "";
                    ArrayList<String> materias = new ArrayList<>();
                    for(String listProf: listaProfesores){
                        String[] partesP = listProf.split(" | ");
                        if(partesP[0].equals(datos[0])){
                            facultad = partesP[4];
                            String[] listMaterias = partesP[5].split(", ");
                            for(String m: listMaterias){
                                materias.add(m);
                            }
                        }
                        
                    }
                    Profesor prof = new Profesor(datos, facultad, materias);
                    prof.mostrarMenu(prof);
                    break;
                case "A":
                    String cargo = "";
                    for(String listAdmin: listaAdministradores){
                        String[] partesA = listAdmin.split(" | ");
                        if(partesA[0].equals(datos[0])){
                            cargo = partesA[4];
                        }
                    }
                    Administrador admin = new Administrador(datos, cargo);
                    admin.mostrarMenu(admin);
                break;
            }
        }else{
            System.out.println("El usuario ingresado no existe");
        }
    }
    }
}
