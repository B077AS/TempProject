package Notifications;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
			conn.close();
			return resultFull;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultFull;

	}

	public List<Notification> getNotifications(ProfessorNotification notification) {
		List<Notification> list=new ArrayList();
		try {
			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications where Sender!=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, notification.getSender());
			ResultSet result=statement.executeQuery();
			while(result.next()) {
				list.add(new ProfessorNotification(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6)));
			}
			conn.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
