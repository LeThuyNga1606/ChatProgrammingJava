package resources.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    public void SendEmail(String emailTo, String newPass) {
        // Set up the mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Set up the username and password for authenticating the account
        String username = "pqtuan19@clc.fitus.edu.vn";
        String password = "Tommy@142@";

        // Create a session with the mail server properties
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailTo));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("lethuynga1662002@gmail.com"));
            message.setSubject("Test Email from Java Program");
            message.setText("Your new password is: " + newPass);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}