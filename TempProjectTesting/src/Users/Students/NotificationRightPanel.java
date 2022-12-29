package Users.Students;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import DataBase.DBConnection;

public class NotificationRightPanel extends JPanel{
	
	
	public NotificationRightPanel(Students user, StudentsGUI main) {
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JButton notificationsButton=checkNotifications(user);
		notificationsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				user.getNotifications().clear();
				main.removePanel();
				main.addSecondPanel(new NotificationListPanel(user, main));
				main.revalidate();
				main.repaint();
				
			}
			
		});
		c.gridx=0;
		c.gridy=0;
		add(notificationsButton, c);

		ImageIcon refreshIcon=new ImageIcon("Files/refresh-icon.png");
		Image image2 = refreshIcon.getImage();
		Image newimg2 = image2.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
		refreshIcon = new ImageIcon(newimg2);

		
		NotificationRightPanel temp=this;
		JButton refreshButton=new JButton(refreshIcon);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.removeHeadPanel(new StudentsHeadPanel(user.getName(), user.getLastName(), user.getEmail(), user, main));
			}

		});
		c.gridx=0;
		c.gridy=1;
		add(refreshButton, c);
		
	}
	
	public JButton checkNotifications(Students user) {

		try {

			Connection conn=DBConnection.connect();
			String query="select * from group_notifications where Receiver=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(user.getID()));
			ResultSet result=preparedStmt.executeQuery();

			if (result.next() == true) {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-active.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				return new JButton(notificationIcon);
			}
			else {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-inactive.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				return new JButton(notificationIcon);
			}


		} catch (Exception e) {
		}
		return null;	

	}

}
