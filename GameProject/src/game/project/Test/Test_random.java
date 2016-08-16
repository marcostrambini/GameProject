package game.project.Test;

import com.project.Tools.RandomString;

public class Test_random {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RandomString rs = new RandomString(8);
		String password = rs.nextString();
		System.out.println(password);
		
	}

}
