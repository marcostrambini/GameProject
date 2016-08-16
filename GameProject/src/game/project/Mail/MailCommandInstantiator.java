package game.project.Mail;

public class MailCommandInstantiator {

	   private static MailCommand instance;

	   public static MailCommand getInstance() {
	      if (instance == null) {
	         String smtp = "10.0.112.54";
	         String user = "verona@eu.nlmk.com";
	         String password = "PupRon11";
	         instance = new MailCommand(smtp, user, password);
	      }
	      return instance;
	   }
	}