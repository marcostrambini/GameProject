package game.project.Mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailCommandAuthenticator extends Authenticator {

   private String user;
   private String password;

   public MailCommandAuthenticator(String user, String password) {
      this.user = user;
      this.password = password;
   }

   @Override
   public PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(this.user, this.password);
   }
}