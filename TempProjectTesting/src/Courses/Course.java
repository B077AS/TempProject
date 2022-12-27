package Courses;

public class Course {
	
	private String ID;
	private String fullName;
	private int year;
	
	public Course(String ID, String fullName, int year) {
		this.ID=ID;
		this.fullName=fullName;
		this.year=year;
		
	}

	public String getID() {
		return ID;
	}

	public String getFullName() {
		return fullName;
	}

	public int getYear() {
		return year;
	}

}
