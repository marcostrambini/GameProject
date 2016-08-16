package game.project.Mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailCommand extends AbstractMailCommand {

   private static final Logger logger = 
        Logger.getLogger(MailCommand.class.getName());
   private String smtp;
   private String user = "verona@eu.nlmk.com";
   private String password;
   private String from;
   private String body;
   private boolean debugEnabled;
   private Session session;
   private MimeMessage message;
   private List<BodyPart> attachments;

   public MailCommand() {
   }

   public MailCommand(String smtp, String user, String password) {
      this.smtp = smtp;
      this.user = user;
      this.password = password;
      this.debugEnabled = false;
      this.mailcap();
   }

   public void addAttachment(File file) 
    throws IOException, MessagingException {
      String name = file.getName();
      BodyPart messageBodyPart = null;
      messageBodyPart = new MimeBodyPart();
      DataSource source = new FileDataSource(file);
      messageBodyPart.setDataHandler(new DataHandler(source));
      messageBodyPart.setFileName(name);
      this.attachments.add(messageBodyPart);
   }

   public void init() throws MessagingException {
	 //  initializeBase();
         initialize(); //potevo usare il metodo con “base” se non mi interessa l’invio autenticato
   }

   private void initialize() throws MessagingException {
      MailCommandAuthenticator authenticator = new MailCommandAuthenticator(this.user, this.password);
      Properties props = System.getProperties();
      props.put("mail.smtp.host", this.smtp);
      props.put("mail.smtp.auth", "true");
      this.session = Session.getInstance(props, authenticator);
      this.session.setDebug(this.debugEnabled);
      this.attachments = new ArrayList<BodyPart>();
      this.message = new MimeMessage(this.session);
   }

   private void initializeBase() throws MessagingException {
      Properties props = System.getProperties();
      props.put("mail.smtp.host", this.smtp);
      this.session = Session.getInstance(props, null);
      this.session.setDebug(this.debugEnabled);
      this.attachments = new ArrayList<BodyPart>();
      this.message = new MimeMessage(this.session);
   }

   private void initialize(Session session) throws MessagingException {
      Properties props = System.getProperties();
      props.put("mail.smtp.host", this.smtp);
      props.put("mail.smtp.auth", "true");
      this.session = session;
      this.session.setDebug(this.debugEnabled);
      this.attachments = new ArrayList<BodyPart>();
      this.message = new MimeMessage(this.session);
   }

   public void setFrom(String from) throws MessagingException {
      this.from = from;
      this.message.setFrom(new InternetAddress(this.from));
   }

   public void addTo(String to) throws MessagingException {
      this.message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
   }

   public void addCc(String cc) throws MessagingException {
      if (isNotEmpty(cc)) {
         this.message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
      }
   }

   public void addBcc(String bcc) throws AddressException, MessagingException {
      if (isNotEmpty(bcc)) {
         this.message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
      }
   }

   public void setSubject(String subject) throws MessagingException {
      this.message.setSubject(subject);
   }

   public void setBody(String body) {
      this.body = body;
   }

   private boolean isNotEmpty(String value) {
      return value != null && value.trim().length() > 0;
   }

   public void execute() {
      try {
         Multipart multipart = new MimeMultipart();
         BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText(this.body);
         multipart.addBodyPart(messageBodyPart);
         for (Iterator<BodyPart> it = this.attachments.iterator(); it.hasNext();) {
            BodyPart bodyPart = it.next();
            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);
         }
         if (this.attachments.isEmpty()) {
            message.setContent(multipart);
         }
         Transport tr = session.getTransport("smtp");
         logger.log(Level.FINEST, "Connect to host " + smtp + " from " + from + " password 'secret'");
         tr.connect();
         tr.sendMessage(message, message.getAllRecipients());
         logger.log(Level.FINEST, "Mail sent successfully");
         tr.close();
      } catch (MessagingException ex) {
         logger.log(Level.SEVERE, "", ex);
      }
   }
}