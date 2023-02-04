package Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataBase.DBConnection;
import Groups.Group;
import Users.GeneralUser.Users;

public class GroupNotificationDAO {

	public void insertGroup(Group group, Users user) throws Exception {

		Connection conn=DBConnection.connect();
		String query="insert into group_notifications (Sender, Receiver, Group_ID)"+"values (?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, group.getGroupAdmin());
		preparedStmt.setString(2, user.getID());
		preparedStmt.setString(3, group.getGroupAdmin());
		preparedStmt.execute();
		conn.close();

	}

}
