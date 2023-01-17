package Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DataBase.DBConnection;

public class ProfNotificationDAO {
	
	
	public boolean checkNotification(ProfessorNotification notification) {
		boolean resultFull=false;
		
		try {

			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications where Sender!=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, notification.getSender());
			ResultSet result=statement.executeQuery();

			resultFull=result.next();
			return resultFull;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultFull;
		
	}

}
