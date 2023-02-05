package Groups;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
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


	public int countPartecipant(Group group) {
		try {
			Connection conn=DBConnection.connect();

			String query ="select count(Partecipant) from allgroups where Group_ID=? and Admin=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, group.getGroupID());
			preparedStmt.setString(2, group.getGroupAdmin());
			ResultSet result=preparedStmt.executeQuery();
			result.next();

			int newPartecipants=result.getInt(1);
			return newPartecipants;
		} catch (Exception e) {
		}
		return 0;
	}

}