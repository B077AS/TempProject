package Groups;


import java.sql.*;
import java.util.HashMap;
import DataBase.DBConnection;
import Email.EmailTemplate;
import Exceptions.ExceptionFrame;
import Notifications.GroupNotificationDAO;
import Users.GeneralUser.UsersDAO;
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

		UsersDAO daoUser=new UsersDAO();
		Students student;

		try {
			student=(Students)daoUser.checkUser(new Students(null, null, null, emailOrID, null, null));
		}
		catch(Exception e){
			new ExceptionFrame("Invalid User!");
			return;
		}

		try {
		GroupDAO daoGroup=new GroupDAO();
		daoGroup.check(group, student);
		}
		catch(Exception e){
			new ExceptionFrame("User already part of the Group!");
			return;
		}

		try {
			
			GroupNotificationDAO notifyDAO=new GroupNotificationDAO();
			notifyDAO.insertGroup(group, student);
			//EmailTemplate eTemp=new EmailTemplate(student.getEmail(), "Notification", "You have been invited by "+group.getGroupAdmin()+" to join the Group: "+group.getGroupID());
			//eTemp.start();

		} catch (Exception e) {
			new ExceptionFrame("User Already invited to the Group!");
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
