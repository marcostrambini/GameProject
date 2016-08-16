package game.project.Test;

import game.project.DataSource.DataSource;

public class Test_sql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			DataSource ds = new DataSource();
			System.out.println(ds.getConnection());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
