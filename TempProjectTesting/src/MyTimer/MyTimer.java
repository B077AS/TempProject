package MyTimer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class MyTimer {
	private int day;
	private int month;
	private int year;
	private String date;
	
	public MyTimer() {
		this.date=getJavaDate().toString();
		String[] splitted;
		splitted=this.date.split("-");
		this.day=Integer.parseInt(splitted[2]);
		this.month=Integer.parseInt(splitted[1]);
		this.year=Integer.parseInt(splitted[0]);
		
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
	
	public LocalDate getJavaDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		dtf.format(now);
		return now.toLocalDate();
	}
	
	public Date getSqlDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		dtf.format(now);
		return Date.valueOf(now.toLocalDate());
	}
	
}
