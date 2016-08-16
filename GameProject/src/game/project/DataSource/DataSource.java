package game.project.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.project.Tools.Tools;

public class DataSource {
	
	 //Utente per la connessione al DB Sql Server
	 
	   private String db_driver_sqlServer = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	 
	 
	   
	    private String portNumber = "1433";
	    private String serverName= "10.0.112.91";
	    private String mirrorName= "10.0.112.91";
	    private String databaseName= "LMGP";
	    private String selectMethod = "cursor";
	    private String userName = "SERVIZIO";
	    private String password = "Verona11";
	    

	    private String url = "jdbc:sqlserver://"
	            +serverName+":"
	            +portNumber+";"
	            +"databaseName="
	            +databaseName+";";

	    
	    public DataSource() throws ClassNotFoundException {
         Class.forName( db_driver_sqlServer );
         System.out.println("driver sql caricato per DataSourceChimica");
   }
	    
	 
	   /**
	    * ritorna la connessione al DB_CHIMICA 
	    * @return
	    */
	   public Connection getConnection(){
	 
	       try {
	           return DriverManager.getConnection( url, userName, password );
	       } catch (SQLException e) {
	           System.out.println("non riesco a creare la connessione");
	           e.printStackTrace();
	           return null;
	       }
	   }
	   
	   /* metodi di inserimento */
	   public boolean insLogin(String user, String nickname, String password, String email, String data, String state) {
			Connection con = null;
			PreparedStatement pstm = null;
			boolean result = false;
			
			try {
				con = getConnection();
				pstm = con.prepareStatement(MyQuery.qInsLogin);
				
			//001_USER,001_NICKNAME, 001_PWD, 001_EMAIL, 001_DATA, 001_STATE)" 
				pstm.setString(1, user);
				pstm.setString(2, nickname);
				pstm.setString(3, password);
				pstm.setString(4, email);
				pstm.setString(5, data);
				pstm.setString(6, state);
				
				pstm.execute();	
				result = true;
									
				  } catch (SQLException e) {
						result = false;
						e.printStackTrace();
					}finally{
						try {
							pstm.close();
							
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			return result;
			
		}
	   

}
