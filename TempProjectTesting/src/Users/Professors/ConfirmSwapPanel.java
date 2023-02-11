package Users.Professors;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Notifications.ProfessorNotification;
import Rooms.RoomDAO;
import Rooms.Rooms;
import Users.GeneralUser.UserGUI;
import Users.GeneralUser.Users;

public class ConfirmSwapPanel extends JPanel{
	
	private String[] startHours= {"From","9:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"};

	public ConfirmSwapPanel(Date firstDate, Date secondDate, Users User, UserGUI mainGUI, boolean skip) {


		setLayout (new GridBagLayout());
		setBackground(Color.white);
		GridBagConstraints c=new GridBagConstraints();

		JLabel firstDateLabel=new JLabel("Swappable date: "+firstDate.toString()+" ");
		firstDateLabel.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		firstDateLabel.setForeground(new Color(145,0,0));
		c.insets= new Insets (5,0,0,0);
		c.gridx=0;
		c.gridy=0;
		add(firstDateLabel, c);
		JLabel secondDateLabel;
		if(secondDate==null) {
			secondDateLabel=new JLabel("No preference specified");
			secondDateLabel.setFont(new Font("Comic Sans MS", Font.BOLD,17));
			secondDateLabel.setForeground(new Color(145,0,0));
		}
		else {
			secondDateLabel=new JLabel("Substitute date: "+secondDate.toString()+" ");
			secondDateLabel.setFont(new Font("Comic Sans MS", Font.BOLD,17));
			secondDateLabel.setForeground(new Color(145,0,0));
		}
		c.gridx=0;
		c.gridy=1;
		add(secondDateLabel, c);


		JComboBox<String> endTimeBox=new JComboBox<String>();
		endTimeBox.addItem("To");
		endTimeBox.setFocusable(false);
		endTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		endTimeBox.setForeground(new Color(145,0,0));
		endTimeBox.setBackground(Color.WHITE);
		c.insets= new Insets (0,5,0,0);
		c.gridx=2;
		c.gridy=0;
		add(endTimeBox, c);

		JComboBox<String> startTimeBox=new JComboBox<String>(startHours);
		ToActionListener sl=new ToActionListener(startTimeBox, endTimeBox);
		startTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		startTimeBox.setForeground(new Color(145,0,0));
		startTimeBox.setBackground(Color.WHITE);
		startTimeBox.addActionListener(sl);
		startTimeBox.setFocusable(false);
		
		c.gridx=1;
		c.gridy=0;
		add(startTimeBox, c);

		JComboBox<String> secondEndTimeBox=new JComboBox<String>();
		JComboBox<String> secondStartTimeBox=new JComboBox<String>(startHours);
		
		if(secondDate!=null) {
			secondEndTimeBox=new JComboBox<String>();
			secondEndTimeBox.addItem("To");
			secondEndTimeBox.setFocusable(false);
			secondEndTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			secondEndTimeBox.setForeground(new Color(145,0,0));
			secondEndTimeBox.setBackground(Color.WHITE);
			c.gridx=2;
			c.gridy=1;
			add(secondEndTimeBox, c);

			secondStartTimeBox=new JComboBox<String>(startHours);
			sl=new ToActionListener(secondStartTimeBox, secondEndTimeBox);
			secondStartTimeBox.addActionListener(sl);
			secondStartTimeBox.setFocusable(false);
			secondStartTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			secondStartTimeBox.setForeground(new Color(145,0,0));
			secondStartTimeBox.setBackground(Color.WHITE);
			c.gridx=1;
			c.gridy=1;
			add(secondStartTimeBox, c);


		}
		JComboBox<Rooms> possibleRooms=new JComboBox();
		try {
			RoomDAO dao=new RoomDAO();

			HashMap<String, Rooms> allRooms=dao.selectAllRooms();
			ArrayList<Rooms> roomsList=new ArrayList<Rooms>();
			for(HashMap.Entry<String, Rooms> entry : allRooms.entrySet()) {
				roomsList.add(entry.getValue());
			}
			Collections.sort(roomsList);


			possibleRooms=new JComboBox(roomsList.toArray());
			possibleRooms.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			possibleRooms.setForeground(new Color(145,0,0));
			possibleRooms.setBackground(Color.WHITE);
			c.gridx=3;
			c.gridy=0;
			add(possibleRooms, c);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JButton swapButton=new JButton("Send Swap Request");
		swapButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		swapButton.setForeground(Color.WHITE);
		swapButton.setBackground(new Color(145,0,0));
		
		swapButton.setOpaque(true);
		swapButton.setBorderPainted(false);
		swapButton.addActionListener(new SwapListener(firstDate, secondDate, User, mainGUI, possibleRooms, startTimeBox, endTimeBox, secondStartTimeBox, secondEndTimeBox, skip));
		c.gridx=4;
		c.gridy=0;
		add(swapButton, c);


	}

}

class SwapListener implements ActionListener{

	private JComboBox<Rooms> possibleRooms;
	private JComboBox<String> startTimeBox;
	private JComboBox<String> endTimeBox;
	private JComboBox<String> secondEndTimeBox;
	private JComboBox<String> secondStartTimeBox;
	private Date firstDate;
	private Date secondDate;
	private Users user;
	private UserGUI mainGUI;
	private boolean skip;
		
		
	public SwapListener(Date firstDate, Date secondDate, Users user, UserGUI mainGUI, JComboBox<Rooms> possibleRooms, JComboBox<String> startTimeBox, JComboBox<String> endTimeBox, JComboBox<String> secondEndTimeBox, JComboBox<String> secondStartTimeBox, boolean skip) {
		this.possibleRooms=possibleRooms;
		this.startTimeBox=startTimeBox;
		this.endTimeBox=endTimeBox;
		this.secondEndTimeBox=secondEndTimeBox;
		this.secondStartTimeBox=secondStartTimeBox;
		this.firstDate=firstDate;
		this.secondDate=secondDate;
		this.user=user;
		this.mainGUI=mainGUI;
		this.skip=skip;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		LocalDate myDate =this.firstDate.toLocalDate();
		DayOfWeek dayOfWeek=myDate.getDayOfWeek();
		Date newMyDate=Date.valueOf(myDate);
		String scheduleID="";
			
		Connection conn=DBConnection.connect();
		try {
				
			String query="select Schedule_ID from schedule where Start_Time=? and End_Time=? and Day_Of_Week=? and Room=?";
			PreparedStatement preparedStmt=conn.prepareStatement(query);
			preparedStmt.setString(1, (String)this.startTimeBox.getSelectedItem());
			preparedStmt.setString(2, (String)this.endTimeBox.getSelectedItem());
			preparedStmt.setString(3, dayOfWeek.toString());
			preparedStmt.setString(4, (String)this.possibleRooms.getSelectedItem().toString());
			ResultSet result=preparedStmt.executeQuery();
			result.next();
			scheduleID=result.getString(1);
			conn.close();
				
		} catch (Exception e1) {
			e1.printStackTrace();
			new ExceptionFrame("Not a valid Schedule");
			return;
		}
				
			Date DoW;
			try {
				DoW =Date.valueOf(this.secondDate.toLocalDate());
			}catch(Exception ex) {
				DoW=null;
			}

			String from=(String)this.secondStartTimeBox.getSelectedItem();
			String to=(String)this.secondEndTimeBox.getSelectedItem();
				
			if(this.skip==true) {
				from=null;
				to=null;
			}
				
			if(from!=null && from.equals("To")) {
				new ExceptionFrame("Fill all the fiels before continuing!");
				return;
			}
				
			ProfessorNotification notification=new ProfessorNotification(scheduleID, newMyDate, user.getID(), DoW, from, to);
			try {
			Professor prof=(Professor)user;
			prof.swapSchedule(notification);
			}catch(Exception ex) {
				ex.printStackTrace();
				new ExceptionFrame("A Swap Request already present");
			}
			
			new ExceptionFrame("Swap Request sent Successfully");
			this.mainGUI.removePanel();
	}

}

class ToActionListener implements ActionListener{

	private JComboBox<String> startTimeBox;
	private JComboBox<String> endTimeBox;
	public ToActionListener(JComboBox<String> startTimeBox, JComboBox<String> endTimeBox) {
		this.startTimeBox=startTimeBox;
		this.endTimeBox=endTimeBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.startTimeBox.removeItem("From");
		String timeString=(String)this.startTimeBox.getSelectedItem();
		String[] temp=timeString.split(":");
		int timeFrom=Integer.parseInt(temp[0]);
		int timeEnd=timeFrom+1;
		String[] timeEndArray=new String[1];
		timeEndArray[0]=Integer.toString(timeEnd)+":00";
		DefaultComboBoxModel<String> df=new DefaultComboBoxModel<String>(timeEndArray);
		endTimeBox.setModel(df);
	}
		
}



