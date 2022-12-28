package Groups;


import java.sql.*;
import java.util.HashMap;
import DataBase.DBConnection;
import Exceptions.ExceptionPanel;
import Users.Students.Students;

public class StudentsGroup {

	private String groupID;
	private String groupAdmin;
	private final int studentsLimit=25;
	private int studentsNumber;
	private HashMap<String, Students> studentsList;

	public StudentsGroup(String ID, String admin) {
		this.studentsList=new HashMap<String, Students>();
		this.groupID=ID;
		this.groupAdmin=admin;
		this.studentsNumber++;
	}


	public void addStudent(String ID, Students user){
		this.studentsList.put(ID, user);
		this.studentsNumber++;


	}

	public void addNewStudent(String emailOrID, StudentsGroup group){

		try {

			Connection conn=DBConnection.connect();
			String query="select * from users where User_Code=? or Email=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(emailOrID));
			preparedStmt.setString(2, emailOrID);
			ResultSet result=preparedStmt.executeQuery();

			if (result.next() == true) {

				query="insert into allgroups (Group_ID, Admin, Partecipant)"+"values (?, ?, ?)";
				preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, group.getID());
				preparedStmt.setString(2, group.getAdmin());
				preparedStmt.setString(3, emailOrID);
				preparedStmt.execute();

				conn.close();
			}


		} catch (Exception e) {
			new ExceptionPanel("\u274C User Already added to the Group!");
		}	


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
}
