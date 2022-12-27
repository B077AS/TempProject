package Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import Login.Login;
import Users.GeneralUser.Users;
import Users.Students.Students;

public class StudentsGroup {

	private String groupID;
	private String groupAdmin;
	private final int studentsLimit=25;
	private int studentsNumber;
	private HashMap<String, Students> studentsList;

	public StudentsGroup(String ID, String admin) {
		this.groupID=ID;
		this.groupAdmin=admin;
		this.studentsNumber=1;
		this.studentsList=new HashMap<String, Students>();
	}


	public void addStudent(String email) throws IOException {
		Login l=new Login();
		/*HashMap<String, Users> allStudents=l.getUsers();
		if(allStudents.containsKey(email)) {
			Students student=(Students)allStudents.get(email);
			this.studentsList.put(email, student);
			this.studentsNumber=this.studentsNumber+1;
		}*/
	}
	
	public void addNewStudent(String email, String admin, StudentsGroup group,  HashMap<String, StudentsGroup> groups) throws IOException {
		/*Login l=new Login();
		HashMap<String, Users> allStudents=l.getUsers();
		if(allStudents.containsKey(email)) {
			Students student=(Students)allStudents.get(email);
			student.addGroup(group, groups);
		}*/
	}

	public HashMap<String, Students> getStudentsList(){
		return this.studentsList;
	}
	
	public int getNumberOfStudents() {
		return this.studentsNumber;
	}
	
	public String getAdmin() {
		return this.groupAdmin;
	}
	
	public String getID() {
		return this.groupID;
	}
	
	@Override
	public String toString() {
		return this.groupID+" users: "+this.studentsNumber;
	}
	
	public String writeToFile() {
		StringBuffer buff=new StringBuffer();
		for(HashMap.Entry<String, Students> student: this.studentsList.entrySet()) {
			buff.append(","+student.getValue().getEmail());
		}
		return this.groupID+","+this.groupAdmin+"-ADMIN"+buff;
	}
	

}
