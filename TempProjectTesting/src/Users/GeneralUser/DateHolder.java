package Users.GeneralUser;

public class DateHolder {
	private static int day;
	private static int month;
	private static int year;
	
	public static void DateHolder(int day, int month, int year) {
		DateHolder.day=day;
		DateHolder.month=month;
		DateHolder.year=year;
		
	}

	public static int getDay() {
		return day;
	}

	public static int getMonth() {
		return month;
	}

	public static int getYear() {
		return year;
	}
	


}
