package game.project.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DataSourceMongo {
	
	
	
	private String db_driver_firebird = "org.firebirdsql.jdbc.FBDriver";
	private String userName = "SYSDBA";
	private String password = "masterkey";
	private String ipServer = "192.168.100.100";
	private String pathDbs = ":d:/Colate/Archivio/";
	private String urlGeneric = "jdbc:firebirdsql:";
	//"jdbc:firebirdsql:192.168.100.100:d:/Colate/Archivio/test.fdb", "SYSDBA", "masterkey"
	
	
	public DataSourceMongo() throws ClassNotFoundException {
		Class.forName(db_driver_firebird);
		System.out.println("caricato driver firebird");
 }

	
	
	public Connection getConnection(String dbName){
		String url = urlGeneric+ipServer+pathDbs+dbName;
		  try {
			  
			  return DriverManager.getConnection( url, userName, password );
		} catch (SQLException e) {
			System.out.println("non riesco a creare la connessione");
			e.printStackTrace();
			return null;
		}
	}

}
