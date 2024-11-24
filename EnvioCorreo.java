import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import io.github.cdimascio.dotenv.*;
import java.util.Properties;

public class EnvioCorreo {
        Dotenv dot = Dotenv.load();

        String host = dot.get("MAIL_HOST");
        String port = dot.get("MAIL_PORT");
        String user = dot.get("MAIL_USER");
        String pass = dot.get("MAIL_PASS");
        System.out.println(host);

        Properties prop = new Properties();
        prop.put("mail.smtp.host",host);
        prop.put("mail.smtp.port",port);
        prop.put("mail.smtp.auth",true);
        prop.put("mail.smtp.mail.starttls.enable",true);

        Session sesion = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getpPasswordAuthentication(){
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message mes = new MimeMessage(sesion);
            mes.setFrom(new InternetAddress(user,"APP DE RESERVAS"));
            mes.setRecipients(Message.RecipientType.TO,InternetAddress.parse("jminaca@espol.edu.ec")); //cambiar lo que hay dentro de internetaddress.parse poner el atributo de la clase estudiante o administrador.
            mes.setSubject(pass);  //modificar el pass por el tema que se requiere
            mes.setText(pass);     //el texto que se colocara por correo
            Transport.send(mes);     //envio del mensaje

        } catch (Exception e) {
            System.out.println(e.getMessage());
        
        }
