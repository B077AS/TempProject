package Users.Professors;

import java.awt.Image;
import java.sql.*;
import javax.swing.*;
import DataBase.DBConnection;
import Login.*;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;

public class Teachers extends Users{

	public Teachers(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		TeachersGUI gui=new TeachersGUI(this.name, this.lastName, this.email, this);
		frame.dispose();
		
	}

	@Override
	public JButton checkNotifications() {
		try {

			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications";
			Statement statement = conn.createStatement();
			ResultSet result=statement.executeQuery(query);

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

	@Override
	public JPanel getMainPanel(UsersGUI gui) {
		// TODO Auto-generated method stub
		return new TeachersMainPanel(name, lastName, email, gui, this);
	}

	@Override
	public JPanel book(Object[] objects, UsersGUI frame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel notificationPanel(Users user, UsersGUI frame) {
		return new TeachersNotificationPanel(user, frame);
	}
}
