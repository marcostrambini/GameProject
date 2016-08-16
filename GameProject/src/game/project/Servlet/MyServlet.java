package game.project.Servlet;

import game.project.DataSource.DataSource;
import game.project.Mail.MailGameProject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.Tools.RandomString;
import com.project.Tools.Tools;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono nel metodo get");
		HttpSession session = request.getSession();
		String act = request.getParameter("act");
		switch(act){
		
		
		
		case "registrazione":
		String name = request.getParameter("name").trim();
		String nickname = request.getParameter("nickname").trim();
		String email = request.getParameter("email").trim();
		
		System.out.println("Parametri arrivati alla servlet:\n"+name+"\n"+nickname+"\n"+email);
		
		RandomString rs = new RandomString(8);
		String password = rs.nextString();
		boolean esito = false;
			try {
				DataSource ds = new DataSource();
				String data = Tools.getDateGregorianFormat("yyyyMMddHHmmss"); //time stamp
				esito = ds.insLogin(name, nickname, password, email, data, "A");
				if(esito){
					MailGameProject mgp = new MailGameProject();
					mgp.invioMail(email, name, nickname, password);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		
		
			
			
		break;
			
		}
	}

}
