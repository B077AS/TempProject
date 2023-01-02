package Users.Professors;

import java.awt.Image;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import DataBase.DBConnection;
import Login.*;
import MyTimer.MyTimer;
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
		boolean profRequests=false;
		boolean swapRequests=false;
		boolean reminders=false;;
		deleteOldNotifications();
		try {

			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications where Sender!=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, this.ID);
			ResultSet result=statement.executeQuery();

			profRequests=result.next();

			query="select * from swap_notifications where Receiver=? and Accepted='false'";
			statement = conn.prepareStatement(query);
			statement.setString(1, this.ID);
			result=statement.executeQuery();

			swapRequests=result.next();

			query="select * from swap_notifications where Receiver=? or Sender=? and Accepted='true'";
			statement = conn.prepareStatement(query);
			statement.setString(1, this.ID);
			statement.setString(2, this.ID);
			result=statement.executeQuery();

			reminders=result.next();


			if (profRequests==true || swapRequests==true) {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-active.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				return new JButton(notificationIcon);
			}
			else if(profRequests==false && swapRequests==false && reminders==false){
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-inactive.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				return new JButton(notificationIcon);
			}
			else {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-reminder.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				return new JButton(notificationIcon);

			}


		} catch (Exception e) {
			e.printStackTrace();
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

	public void deleteOldNotifications() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now=LocalDate.now();
		dtf.format(now);
		
		try {

			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications";
			Statement statement = conn.createStatement();
			ResultSet result=statement.executeQuery(query);
			
			while(result.next()) {
				if(result.getDate(4)!=null && now.compareTo((result.getDate(4)).toLocalDate())>0) {
					query="delete from prof_notifications where Schedule_ID=? and Date=? and Sender=? and New_Date=? and New_From=? and New_To=?";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1, result.getString(1));
					preparedStmt.setDate(2, result.getDate(2));
					preparedStmt.setString(3, result.getString(3));
					preparedStmt.setDate(4, result.getDate(4));
					preparedStmt.setString(5, result.getString(5));
					preparedStmt.setString(6, result.getString(6));
					preparedStmt.executeUpdate();

					}
					else if (result.getDate(4)==null && now.compareTo((result.getDate(2)).toLocalDate())>0){
						query="delete from prof_notifications where Schedule_ID=? and Date=? and Sender=? and New_Date is NULL";
						PreparedStatement preparedStmt = conn.prepareStatement(query);
						preparedStmt.setString(1, result.getString(1));
						preparedStmt.setDate(2, result.getDate(2));
						preparedStmt.setString(3, result.getString(3));
						preparedStmt.executeUpdate();
						conn.close();
					}
				}
			
			query="select * from swap_notifications";
			statement = conn.createStatement();
			result=statement.executeQuery(query);
			

			while(result.next()) {
				if(now.compareTo((result.getDate(4)).toLocalDate())>0) {
					query="delete from swap_notifications where Sender=? and Receiver=? and First_Date=? and New_Date=? and First_Schedule=? and New_Schedule=? and Accepted=?";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1, result.getString(1));
					preparedStmt.setString(2, result.getString(2));
					preparedStmt.setDate(3, result.getDate(3));
					preparedStmt.setDate(4, result.getDate(4));
					preparedStmt.setString(5, result.getString(5));
					preparedStmt.setString(6, result.getString(6));
					preparedStmt.setString(7, result.getString(7));
					preparedStmt.executeUpdate();
					}
				}
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
