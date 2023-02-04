package Groups;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBConnection;
import Users.Students.Students;

public class GroupDAO {
	
	public void insertGroup(Group group) throws Exception {
		Connection conn=DBConnection.connect();
		
		String query="insert into allgroups (Group_ID, Admin, Partecipant)"+"values (?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setString(1, group.getGroupID());
		preparedStmt.setString(2, group.getGroupAdmin());
		preparedStmt.setString(3, group.getGroupAdmin());
		
		preparedStmt.execute();
		conn.close();
	}
	
	
	public void check(Group group, Students student) throws Exception {
		Connection conn=DBConnection.connect();
		String query="select * from allgroups where Group_ID=? and Admin=? and Partecipant=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, group.getGroupID());
		preparedStmt.setString(2, group.getGroupAdmin());
		preparedStmt.setString(3, student.getID());
		ResultSet result=preparedStmt.executeQuery();
		if(result.next()==true) {
			conn.close();
			throw new Exception();
		}
		conn.close();
	}
}