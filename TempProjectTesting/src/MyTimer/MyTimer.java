package MyTimer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyTimer {
	private int day;
	private int month;
	private int year;
	private String date;
	
	public MyTimer() {
		this.date=getTime();
		String[] splitted;
		splitted=this.date.split("-");
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
	
	public String getStringDate() {
		return this.date;
	}
	
	public LocalDateTime getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		return now;
	}
	
}
