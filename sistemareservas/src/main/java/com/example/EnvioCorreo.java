package com.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import io.github.cdimascio.dotenv.*;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class EnvioCorreo {

     public static void enviarCorreo(String destinatario, String asunto, String mensaje) {
        Dotenv dot = Dotenv.load();

        String host = dot.get("MAIL_HOST");
        String port = dot.get("MAIL_PORT");
        String user = dot.get("MAIL_USER");
        String pass = dot.get("MAIL_PASS");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);

        Session sesion = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message mes = new MimeMessage(sesion);
            mes.setFrom(new InternetAddress("correo del remitente", "Sistema de Reserva"));
            mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Correo del destinatario"));
            mes.setSubject(asunto);
            mes.setText(mensaje);
            Transport.send(mes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
