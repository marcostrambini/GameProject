package game.project.Mail;

import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class HtmlEmailSender {
 
    public void sendHtmlEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException,
            MessagingException {
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
 
    }
 
    /**
     * Test the send html e-mail method
     *
     */
    public static void main(String[] args) {
        // SMTP server information
        String host = "10.0.112.54";
        String port = "25";
        String mailFrom = "verona@eu.nlmk.com";
        String password = "PupRon11";
 
        // outgoing message information
        String mailTo = "m.strambini@eu.nlmk.com";
        String subject = "Hello my friend";
 
        String msg = "<table border=\"1\">"+
        "<tr><td>Marco</td><td>Strambini</td></tr>"+
        "<tr><td>Nicola</td><td>Ferraro</td></tr>"+
        "</table>";
        
        
        
        // message contains HTML markups
        String message = "<i>Greetings!</i><br>";
        message += "<b>Wish you a nice day!</b><br>";
        message += "<font color=red>Duke</font>";
 
        HtmlEmailSender mailer = new HtmlEmailSender();
 
        try {
            mailer.sendHtmlEmail(host, port, mailFrom, password, mailTo,
                    subject, msg);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }
}