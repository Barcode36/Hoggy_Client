package utiles;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class GoogleMail {

    public static String login;
    public static String password;
    public static String nom;
    public static String prenom;

    public static void main() {
        // Sender's email ID needs to be mentioned
      String from = "falloufalllive";
        String pass = "Hpenvydv6";

        // Recipient's email ID needs to be mentioned.
        String to = "falloufalllive@hotmail.fr";

        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("Bonjour Mr/MMe ;" + prenom + "  " + nom + "\nVotre Login de Connection est :" + login + "\nVOTRE Mot De Passe est: " + password+
                    "Vous Allez devoir Changer De Login Et de Mot de passe AU prochaine Session\n\nMERCI");

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
