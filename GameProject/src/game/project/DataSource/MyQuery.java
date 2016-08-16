package game.project.DataSource;

public class MyQuery {
	
	
	public static String qInsLogin = " INSERT INTO [LMGP].[dbo].[001_LOGINS] " +
									 " ([001_USER], [001_NICKNAME], [001_PWD], [001_EMAIL], [001_DATA], [001_STATE])" +
									 " VALUES(?,?,?,?,?,?)";
 

}
