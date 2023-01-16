package MyTimer;

import java.util.HashMap;

public class Months {

	private static HashMap<String, Integer> months=new HashMap<String, Integer>(){{
	put("January", 1);
	put("February", 2);
	put("March", 3);
	put("April", 4);
	put("May", 5);
	put("June", 6);
	put("July", 7);
	put("August", 8);
	put("September", 9);
	put("October", 10);
	put("November", 11);
	put("December", 12);
	}};


	public static void Months() {
	}

	public static HashMap<String, Integer> getMonths() {
		return months;
	}

}


