package Users.Students;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import DataBase.DBConnection;
import Exceptions.ExceptionPanel;
import Login.*;
import Users.GeneralUser.Users;
import Groups.StudentsGroup;


public class Students extends Users{
	
	private HashMap<String, StudentsGroup> groups;

	public Students(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		this.groups=new HashMap<String, StudentsGroup>();
	}

	@Override
	public void GUI(LoginGUI frame) {
		StudentsGUI gui=new StudentsGUI(name, lastName, email, this);
		frame.dispose();
	}	

	public void createGroup(String groupName){
		try {

			Connection conn=DBConnection.connect();
			
			String query="insert into allgroups (Group_ID, Admin, Partecipant)"+"values (?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, groupName);
			preparedStmt.setString(2, this.ID);
			preparedStmt.setString(3, this.ID);
			
			preparedStmt.execute();
			conn.close();
			
			
		} catch (Exception e) {
			new ExceptionPanel("\u274C Group Name too Long");
			return;
		}	
	}

	public HashMap<String, StudentsGroup> getGroups(){
		return this.groups;
	}
	
}
