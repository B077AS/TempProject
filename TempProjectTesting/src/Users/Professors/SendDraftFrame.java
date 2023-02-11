package Users.Professors;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.SqlDateModel;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Notifications.AcceptRejectFrame;
import Notifications.ProfessorNotification;
import Rooms.RoomDAO;
import Rooms.Rooms;
import Users.GeneralUser.Users;
import Users.GeneralUser.UserGUI;

public class SendDraftFrame extends JFrame{
	private String[] startHours= {"From","9:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"};
	private String selectedRoom;

	public SendDraftFrame(ProfessorNotification notification, Users user, UserGUI frame) {
		JPanel secondPanel=new JPanel();
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		try {

			secondPanel.setLayout (new GridBagLayout());
			secondPanel.setBackground(Color.white);
			GridBagConstraints c=new GridBagConstraints();
			RoomDAO dao=new RoomDAO();

			HashMap<String, Rooms> allRooms=dao.selectAllRooms();
			ArrayList<Rooms> roomsList=new ArrayList<Rooms>();
			for(HashMap.Entry<String, Rooms> entry : allRooms.entrySet()) {
				roomsList.add(entry.getValue());
			}
			Collections.sort(roomsList);
			//
			JComboBox<String> roomsBox=new JComboBox(roomsList.toArray());
			roomsBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			roomsBox.setForeground(new Color(145,0,0));
			roomsBox.setBackground(Color.WHITE);

			JLabel label = new JLabel("Prepare your Draft Swap Proposal");
			label.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			label.setForeground(new Color(145,0,0));
			mainPanel.add(label, BorderLayout.NORTH);

			SqlDateModel model= new SqlDateModel();
			Properties p = new Properties();
			p.put("text.today", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");
			JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
			c.gridx=0;
			c.gridy=0;
			secondPanel.add(datePanel, c);

			JComboBox<String> endTimeBox=new JComboBox<String>();
			endTimeBox.addItem("To");
			endTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			endTimeBox.setForeground(new Color(145,0,0));
			endTimeBox.setBackground(Color.WHITE);
			endTimeBox.setFocusable(false);
			c.gridx=2;
			c.gridy=0;
			
			secondPanel.add(endTimeBox, c);

			JComboBox<String> startTimeBox=new JComboBox<String>(startHours);
			startTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			startTimeBox.setForeground(new Color(145,0,0));
			//startTimeBox.setFocusable(false);
			startTimeBox.setBackground(Color.WHITE);
			ToActionListener sl=new ToActionListener(startTimeBox, endTimeBox);
			startTimeBox.addActionListener(sl);
			startTimeBox.setFocusable(false);
			c.gridx=1;
			c.gridy=0;
			c.insets = new Insets(0,10,0,0);
			secondPanel.add(startTimeBox, c);

			c.gridx=3;
			c.gridy=0;
			secondPanel.add(roomsBox, c);

			JButton send=new JButton("Send");
			send.setFont(new Font("Comic Sans MS", Font.BOLD,17));
			send.setBackground(new Color(145,0,0));
			send.setForeground(Color.white);
			send.setOpaque(true);
			send.setBorderPainted(false);
			send.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Date receiverDate=null;
					selectedRoom=(String)roomsBox.getSelectedItem().toString();
					try {
					receiverDate=notification.getDate();
					}catch(Exception ex) {
						new ExceptionFrame("No Notification Selected");
						return;
					}
					Date draftDate=(Date)datePanel.getModel().getValue();

					try {

						if(receiverDate.compareTo(draftDate)>0) {
							throw new Exception();
						}
					}catch(Exception ex) {
						new ExceptionFrame("Not a valid Date!");
						return;
					}

					try {
						Connection conn=DBConnection.connect();
						LocalDate myDate = draftDate.toLocalDate();
						DayOfWeek dayOfWeek=myDate.getDayOfWeek();
						String query="select Schedule_ID from schedule where Start_Time=? and End_Time=? and Room=? and Day_Of_Week=?";
						PreparedStatement preparedStmt = conn.prepareStatement(query);
						preparedStmt.setString(1, startTimeBox.getSelectedItem().toString());
						preparedStmt.setString(2, endTimeBox.getSelectedItem().toString());
						preparedStmt.setString(3, selectedRoom);
						preparedStmt.setString(4, dayOfWeek.toString());
						ResultSet result=preparedStmt.executeQuery();
						result.next();
						String newSchedule=result.getString(1);

						query="insert into swap_notifications (Sender, Receiver, First_Date, New_Date, First_Schedule, New_Schedule, Accepted)"+"values(?, ?, ?, ?, ?, ?, ?)";
						preparedStmt = conn.prepareStatement(query);
						preparedStmt.setString(1, user.getID());
						preparedStmt.setString(2, notification.getSender());
						preparedStmt.setDate(3, receiverDate);
						preparedStmt.setDate(4, draftDate);
						preparedStmt.setString(5, notification.getScheduleID());
						preparedStmt.setString(6, newSchedule);
						preparedStmt.setString(7, "false");
						preparedStmt.execute();

						conn.close();

					} catch (Exception ea) {
						new ExceptionFrame("Error!");
						return;
					}
					notification.accept();
					new AcceptRejectFrame("Swap Proposal Sent!", user, frame);
					dispose();
				}
			});
			c.gridx=2;
			c.gridy=1;
			secondPanel.add(send, c);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		mainPanel.setBackground(Color.white);
		mainPanel.add(secondPanel, BorderLayout.CENTER);
		add(mainPanel);
		setSize(700,400);
		setTitle("Choose Room");
		
		ImageIcon icon=new ImageIcon("Immagini/logo4.png");
		setIconImage(icon.getImage());

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public String getRoom() {
		return this.selectedRoom;
	}
}
