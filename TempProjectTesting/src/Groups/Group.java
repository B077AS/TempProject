package Groups;


import java.sql.*;
import java.util.HashMap;
import DataBase.DBConnection;
import Email.EmailTemplate;
import Exceptions.ExceptionFrame;
import Users.Students.Students;

public class Group {

	private String groupID;
	private String groupAdmin;
	private final int studentsLimit=25;
	private int studentsNumber;
	private HashMap<String, Students> studentsList;

	public Group(String ID, String admin) {
		this.studentsList=new HashMap<String, Students>();
		this.groupID=ID;
		this.groupAdmin=admin;
		this.studentsNumber++;
	}


	public void addStudent(String ID, Students user){
		this.studentsList.put(ID, user);
		this.studentsNumber++;


	}

	public void addNewStudent(String emailOrID, Group group){

		try {

			Connection conn=DBConnection.connect();
			String query="select User_Code, Email from users where User_Code=? or Email=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(emailOrID));
			preparedStmt.setString(2, emailOrID);
			ResultSet result=preparedStmt.executeQuery();

			if (result.next() == true) {

				query="insert into group_notifications (Sender, Receiver, Group_ID)"+"values (?, ?, ?)";
				preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, group.getGroupAdmin());
				preparedStmt.setString(2, result.getString(1));
				preparedStmt.setString(3, group.getGroupAdmin());
				preparedStmt.execute();
				EmailTemplate eTemp=new EmailTemplate(result.getString(2), "Notification", "You have been invited by "+group.getGroupAdmin()+" to join the Group: "+group.getGroupID());
				eTemp.start(); // da aggiungere parte mancante
			}
			
			conn.close();

		} catch (Exception e) {
			new ExceptionFrame("\u274C User Already invited to the Group!");
			e.printStackTrace();
			return;
		}	


	}

	public HashMap<String, Students> getStudentsList(){
		return this.studentsList;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getGroupAdmin() {
		return groupAdmin;
	}

	public void setGroupAdmin(String groupAdmin) {
		this.groupAdmin = groupAdmin;
	}

	public int getStudentsNumber() {
		return studentsNumber;
	}

	public void setStudentsNumber(int studentsNumber) {
		this.studentsNumber = studentsNumber;
	}

	public int getStudentsLimit() {
		return studentsLimit;
	}

	@Override
	public String toString() {
		return this.groupID+" users: "+this.studentsNumber;
	}
}
