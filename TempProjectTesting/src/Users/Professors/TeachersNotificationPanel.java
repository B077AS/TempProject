package Users.Professors;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Notifications.AcceptRejectFrame;
import Notifications.ProfessorNotification;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;

public class TeachersNotificationPanel extends JPanel{

	public TeachersNotificationPanel(Users user, UsersGUI frame) {
		try {

			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications";
			Statement statement = conn.prepareStatement(query);
			ResultSet result=statement.executeQuery(query);

			while(result.next()) {
				user.loadNotifications(new ProfessorNotification(result.getString(1), result.getString(2), result.getString(3)));
			}

			conn.close();

		} catch (Exception e) {
		}
		JList<ProfessorNotification> list=new JList(user.getNotifications().toArray());
		JScrollPane listScroller = new JScrollPane(list);
		add(listScroller);

		JButton accept=new JButton("Accept");
		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ProfessorNotification notification=list.getSelectedValue();
				try {
					Connection conn=DBConnection.connect();
					String query="delete from prof_notifications where Schedule_ID=? and Date=? and Sender=?";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1, notification.getScheduleID());
					preparedStmt.setString(2, notification.getSender());
					preparedStmt.setDate(3, Date.valueOf(notification.getDate()));
					preparedStmt.execute();

					conn.close();

				} catch (Exception ea) {
					new ExceptionFrame("\u274C No Notification Selected!");
					return;
				}
				notification.accept();
				new AcceptRejectFrame("Swap Request Accepted!", user, frame);
			}

		});
		add(accept);
	}
}

