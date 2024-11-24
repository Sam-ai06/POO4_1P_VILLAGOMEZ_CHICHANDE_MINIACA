import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import io.github.cdimascio.dotenv.*;
import java.util.Properties;

public class EnvioCorreo {
   public static void enviarCorreo(String destinatario, String asunto, String mensaje) {
        Dotenv dot = Dotenv.load();
        String host = dot.get("MAIL_HOST");
        String port = dot.get("MAIL_PORT");
        String user = dot.get("MAIL_USER");
        String pass = dot.get("MAIL_PASS");

        // Configuración de propiedades para el correo
        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);

        // Crear sesión
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        // Crear el mensaje de correo
        try {
            Message mes = new MimeMessage(session);
            mes.setFrom(new InternetAddress(user, "APP DE RESERVAS"));
            mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mes.setSubject(asunto);
            mes.setText(mensaje);
            Transport.send(mes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
