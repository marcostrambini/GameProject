package game.project.Mail;

import javax.mail.MessagingException;

public class MailGameProject {

	public boolean invioMail(String destinatario, String name, String nickname, String password){
		boolean result = false;
		MailCommand mailCommand = MailCommandInstantiator.getInstance();

		String body = "Dioboia!\nBenvenuto "+name+"!\nIl tuo nickname per l'accesso al fantastico gioco creato da Mago e Loca è: "+nickname;
		body += "\nUtilizza questa password al primo accesso: "+password;
		
		try {

			mailCommand.init();

			mailCommand.setFrom("verona@eu.nlmk.com");

			mailCommand.addTo(destinatario);


			mailCommand.setSubject("Password GameProject");
			mailCommand.setBody(body);        



			System.out.println("Tentativo di invio in corso.."); 
			mailCommand.execute();
			System.out.println("Invio completato");
			result = true;

		} 
		catch (MessagingException ex) {
			ex.printStackTrace();
		}	
		return result;
	}

}
