package Groups;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DataBase.DBConnection;

public class GroupDAO {
	
	public String check(String emailOrID,Group group) throws Exception  {
		
		Connection conn=DBConnection.connect();  //DAO users
		String query="select User_Code, Email from users where User_Code=? or Email=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, Integer.parseInt(emailOrID));
		preparedStmt.setString(2, emailOrID);
		ResultSet result=preparedStmt.executeQuery();
		
		

		if (result.next() == true) {  // DAO Notifications
			
			String email= result.getString(2);
			query="insert into group_notifications (Sender, Receiver, Group_ID)"+"values (?, ?, ?)";
			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, group.getGroupAdmin());
			preparedStmt.setString(2, result.getString(1));
			preparedStmt.setString(3, group.getGroupID());
			preparedStmt.execute();
		
			return email;
		}
		
		conn.close();
		return null; 
	}
}