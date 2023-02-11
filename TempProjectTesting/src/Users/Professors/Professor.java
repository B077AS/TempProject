package Users.Professors;

import java.awt.*;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Login.*;
import MyTimer.DateHolder;
import MyTimer.MyTimer;
import Notifications.ProfNotificationDAO;
import Notifications.ProfSwapNotificationDAO;
import Notifications.ProfessorNotification;
import Notifications.ProfessorSwapDraft;
import Rooms.Booking;
import Rooms.BookingSuccessful;
import Rooms.RoomsBookingDAO;
import Rooms.SoloBookingDAO;
import Users.GeneralUser.Users;
import Users.GeneralUser.UserGUI;

public class Professor extends Users{

	public Professor(String name, String lastName, String ID, String email, String password, String type) {
		super(name, lastName, ID, email, password, type);
		// TODO Auto-generated constructor stub
	}
	
	
	public void swapSchedule(ProfessorNotification notification) throws Exception{
		ProfNotificationDAO profDAO=new ProfNotificationDAO();
		profDAO.insertNotification(notification);
	}
	
	

	@Override
	public void GUI(LoginGUI frame) {
		ProfessorGUI gui=new ProfessorGUI(this.name, this.lastName, this.email, this);
		frame.dispose();

	}

	@Override
	public JButton checkNotifications() {
		ProfNotificationDAO daoProfNotification=new ProfNotificationDAO();
		ProfSwapNotificationDAO daoProfSwap=new ProfSwapNotificationDAO();
		boolean profRequests=daoProfNotification.checkNotification(new ProfessorNotification(null, null, this.ID, null, null, null));
		boolean swapRequests=daoProfSwap.checkNotification(new ProfessorSwapDraft(null, this.ID, null, null, null, null, "false"));
		boolean reminders=daoProfSwap.checkNotification(new ProfessorSwapDraft(null, this.ID, null, null, null, null, "true"));
		deleteOldNotifications();
		
			if (profRequests==true || swapRequests==true) {
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-active.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				JButton button= new JButton(notificationIcon);
				button.setBackground(Color.white);
				return button;
			}
			else if(profRequests==false && swapRequests==false && reminders==false){
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-inactive.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				JButton button= new JButton(notificationIcon);
				button.setBackground(Color.white);
				return button;
			}
			else {
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-reminder.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				JButton button= new JButton(notificationIcon);
				button.setBackground(Color.white);
				return button;

			}

	}

	@Override
	public JPanel getMainPanel(UserGUI gui) {
		// TODO Auto-generated method stub
		return new ProfessorMainPanel(name, lastName, email, gui, this);
	}

	@Override
	public JPanel book(Object[] objects, UserGUI frame) {
		int year=DateHolder.getYear();
		int month=DateHolder.getMonth();
		int day=DateHolder.getDay();

		JPanel bookPanel=new JPanel();
		bookPanel.setBackground(Color.white);
		bookPanel.setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		Booking booking=(Booking)objects[0];

		JLabel book= new JLabel ("Booking Summary :");
		book.setFont(new Font("Comic Sans MS", Font.BOLD,25));
		book.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets(0,0,50,0);
		bookPanel.add(book, c);
		//
		JLabel bookingSummary=new JLabel("Room: "+booking.getRoom()+" from: "+booking.getStartTime()+" to "+booking.getEndTime());
		bookingSummary.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		bookingSummary.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=1;
		bookPanel.add(bookingSummary, c);


		MyTimer time=new MyTimer();

		LocalDate now=time.getJavaDate();
		Date date=DateHolder.getDate();
		LocalDate bookingDate=date.toLocalDate();

		JButton claimButton=new JButton("Claim Room");
		claimButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		claimButton.setForeground(Color.WHITE);
		claimButton.setBackground(new Color(145,0,0));	
		claimButton.setOpaque(true);
		claimButton.setBorderPainted(false);
		claimButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SoloBookingDAO soloDAO=new SoloBookingDAO();
					RoomsBookingDAO roomsDAO=new RoomsBookingDAO();
					boolean firstCheck=soloDAO.checkFromDateAndTime(booking);
					boolean secondCheck=roomsDAO.checkFromDateAndTime(booking);
					
					Connection conn=DBConnection.connect();
					
					String bookingID=ID+"-"+booking.getRoom().getCode()+"-"+booking.getStartTime().split(":")[0]+"-"+booking.getEndTime().split(":")[0]+"-"+date;
					
					if(firstCheck==true && bookingDate.compareTo(now.plusDays(3))>0 || secondCheck==true && bookingDate.compareTo(now.plusDays(3))>0) {
						
						String query="delete from rooms_booking where rooms_booking.Date=? and rooms_booking.Room=? and rooms_booking.Start_Time=? and rooms_booking.End_Time=?";
						PreparedStatement preparedStmt = conn.prepareStatement(query);
						preparedStmt.setDate(1, date);
						preparedStmt.setString(2, booking.getRoom().getCode());
						preparedStmt.setString(3, booking.getStartTime());
						preparedStmt.setString(4, booking.getEndTime());
						preparedStmt.execute();
						
						query="delete from solo_booking where solo_booking.Date=? and solo_booking.Room=? and solo_booking.Start_Time=? and solo_booking.End_Time=?";
						preparedStmt = conn.prepareStatement(query);
						preparedStmt.setDate(1, date);
						preparedStmt.setString(2, booking.getRoom().getCode());
						preparedStmt.setString(3, booking.getStartTime());
						preparedStmt.setString(4, booking.getEndTime());
						preparedStmt.execute();

						conn.close();
						
						soloDAO.insert(new Booking(booking.getStartTime(), booking.getEndTime(), date, booking.getRoom(), bookingID, ID, "true"));
						new BookingSuccessful();
					}
					
					else if(firstCheck==false && secondCheck==false) {
						System.out.println("ciao2");
						soloDAO.insert(new Booking(booking.getStartTime(), booking.getEndTime(), date, booking.getRoom(), bookingID, ID, "true"));
						new BookingSuccessful();
					
					}
					else {
						throw new Exception();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					new ExceptionFrame("Not Allowed to Overbook!");
					return;
				}

			}

		});
		c.gridx=0;
		c.gridy=2;
		bookPanel.add(claimButton, c);

		return bookPanel;
	}
	

	@Override
	public JPanel notificationPanel(Users user, UserGUI frame) {
		return new ProfessorNotificationPanel(user, frame);
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
