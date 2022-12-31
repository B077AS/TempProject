package Users.Students;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Notifications.JoinGroupNotification;
import Users.Admin.ViewCoursesPanel;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;
import Notifications.AcceptRejectFrame;

public class NotificationListPanel extends JPanel{

	public NotificationListPanel(Users user, UsersGUI frame) {

		try {

			Connection conn=DBConnection.connect();
			String query="select * from group_notifications where Receiver=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(user.getID()));
			ResultSet result=preparedStmt.executeQuery();

			while(result.next()) {
				user.loadNotifications(new JoinGroupNotification(result.getString(1), result.getString(2), result.getString(3)));
			}
			
			conn.close();

		} catch (Exception e) {
		}
		JList<JoinGroupNotification> list=new JList(user.getNotifications().toArray());
		JScrollPane listScroller = new JScrollPane(list);
		add(listScroller);
		
		JButton accept=new JButton("Accept");
		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JoinGroupNotification notification=list.getSelectedValue();
				try {
				Connection conn=DBConnection.connect();
				String query="delete from group_notifications where Sender=? and Receiver=? and Group_ID=?";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, Integer.parseInt(notification.getSender()));
				preparedStmt.setInt(2, Integer.parseInt(notification.getReceiver()));
				preparedStmt.setString(3, notification.getGroupID());
				preparedStmt.execute();
				
				conn.close();
				
				} catch (Exception ea) {
					new ExceptionFrame("\u274C No Invite Selected!");
					return;
				}
				notification.accept();
				new AcceptRejectFrame("Invite Accepted!", user, frame);
			}
			
		});
		JButton reject=new JButton("Reject");
		reject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JoinGroupNotification notification=list.getSelectedValue();
				try {
				Connection conn=DBConnection.connect();
				String query="delete from group_notifications where Sender=? and Receiver=? and Group_ID=?";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, Integer.parseInt(notification.getSender()));
				preparedStmt.setInt(2, Integer.parseInt(notification.getReceiver()));
				preparedStmt.setString(3, notification.getGroupID());
				preparedStmt.execute();
				
				
				conn.close();
				
				} catch (Exception ea) {
					new ExceptionFrame("\u274C No Invite Selected!");
					return;
				}
				new AcceptRejectFrame("Invite Rejected!", user, frame);			
			}
			
		});
		
		//setBackground(new Color(0,0,0,0));
		
		add(accept);
		add(reject);
	}

}
