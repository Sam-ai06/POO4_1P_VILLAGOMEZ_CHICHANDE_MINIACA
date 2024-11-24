import java.util.*;
import java.io.*;

public class plataforma{
    public static void main(String[] args) {
        //verificacion de usuario
        boolean usuarioEsxistente = false;
        String correo = "";
        String rol = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su usuario");
        String user = sc.nextLine();
        System.out.println("Ingrese su contraseña");
        String password = sc.nextLine();
        ArrayList<String> listaUser = plataforma.LeeFichero("usuarios.txt");
        for(String LU: listaUser){
            String[] partes = LU.split(" | ");
            if(partes[4].equals(user) && partes[5].equals(password)){
                usuarioEsxistente = true;
                correo = partes[6];
                rol = partes[7];
        }
            
        //funciones
        if(usuarioEsxistente){
            System.out.println("Bienvenido al sistema de reserva de espacio");
            switch (rol) {
                case "E":               
                    
                    break;
                case "P":
                    
                    break;
                case "A":

                break;
            }
        }else{
            System.out.println("El usuario ingresado no existe");
        }
    }
}
