package Users.Students;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import DataBase.DBConnection;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;
import Users.GeneralUser.UsersHeadPanel;

public class NotificationRightPanel extends JPanel{
	
	
	public NotificationRightPanel(Users user, UsersGUI main) {
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JButton notificationsButton=user.checkNotifications();
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
				main.removeHeadPanel(new UsersHeadPanel(user.getName(), user.getLastName(), user.getEmail(), user, main));
			}

		});
		c.gridx=0;
		c.gridy=1;
		add(refreshButton, c);
		
	}
	
	

}
