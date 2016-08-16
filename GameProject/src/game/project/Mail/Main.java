package game.project.Mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class Main {

	public static void main(String[] args) {
		MailCommand mailCommand = MailCommandInstantiator.getInstance();
	
		String dest = "m.strambini@eu.nlmk.com";
		String nuovaPassword = "test";
		String body = "Ciao utente di seguito la tua password di test:\n\n ";
		try {
			
		     mailCommand.init();
	     
		     mailCommand.setFrom("verona@eu.nlmk.com");
		     
             mailCommand.addTo(dest);
	    	 
		     	 
		     mailCommand.setSubject("Recupero password GameProject");
		     mailCommand.setBody(body + nuovaPassword);        
		     
		     
		   
		     System.out.println("Tentativo di invio in corso.."); 
		     mailCommand.execute();
		     System.out.println("Invio completato");
		   
	
		} 
		catch (MessagingException ex) {
		    ex.printStackTrace();
		}	
	}

}
