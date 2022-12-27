package MyTimer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyTimer {
	private int day;
	private int month;
	private int year;
	
	public MyTimer() {
		String temp=getTime();
		String[] splitted;
		splitted=temp.split("-");
		this.day=Integer.parseInt(splitted[2]);
		this.month=Integer.parseInt(splitted[1]);
		this.year=Integer.parseInt(splitted[0]);
		
		
	}

	public String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);

	}
	public static void main(String[] args) {
	}
	
	public int getDay() {
		return this.day;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getYear() {
		return this.year;
	}

}
