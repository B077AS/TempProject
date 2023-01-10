package Users.Students;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

public class StudentNotificationPanel extends JPanel{

	public StudentNotificationPanel(Users user, UsersGUI frame) {
		
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		setBackground(new Color(0,0,0,0));
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
		
		c.anchor = GridBagConstraints.CENTER;
		JLabel notLabel= new JLabel("Check your notifications:");
		notLabel.setFont(new Font("Comic Sans MS", Font.BOLD,25));
		notLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets (0,0,100,0);
		add(notLabel,c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JList<JoinGroupNotification> list=new JList(user.getNotifications().toArray());
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 200));
		c.gridx=0;
		c.gridy=1;
		c.insets= new Insets (0,0,100,0);
		add(listScroller,c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JButton accept=new JButton("Accept");
		accept.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		accept.setForeground(Color.WHITE);
		accept.setBackground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=1;
		c.insets= new Insets (0,20,0,0);
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
		add(accept,c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JButton reject=new JButton("Reject");
		reject.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		reject.setForeground(Color.WHITE);
		reject.setBackground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=1;
		c.insets= new Insets (50,20,0,0);
		
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
		
		
		add(reject,c);
	}

}