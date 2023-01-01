package Users.Professors;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdatepicker.impl.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import MyLoader.RoomLoader;
import Rooms.Rooms;
import Rooms.Rooms.*;
import Users.GeneralUser.TimeSpanAL;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;
import org.jdatepicker.*;
import java.time.*;

public class SwapPanel extends JPanel{

	private Date firstDate;
	private Date secondDate;
	private JLabel calendarLabel;


	public SwapPanel(Users user, UsersGUI mainGUI, Date firstDate, Date secondDate) {
		this.firstDate=firstDate;
		this.secondDate=secondDate;

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		this.calendarLabel=new JLabel("Please Select a day to Swap");
		c.gridx=0;
		c.gridy=0;
		add(calendarLabel, c);
		SqlDateModel model= new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		c.gridx=0;
		c.gridy=1;
		add(datePanel, c);
		JButton firstDateButton=new JButton("Next");
		firstDateButton.addActionListener(new SaveDatesListener(this, datePanel, mainGUI, user));
		c.gridx=1;
		c.gridy=1;
		add(firstDateButton, c);
		JButton skipSecondDate=new JButton("Skip");
		skipSecondDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(firstDate!=null) {
					mainGUI.removePanel();
					mainGUI.addSecondPanel(new ConfirmSwapPanel(firstDate, null, user, mainGUI));
					mainGUI.revalidate();
					mainGUI.repaint();
				}
				else {
					new ExceptionFrame("You can't skip this step");
					return;
				}

			}

		});
		c.gridx=2;
		c.gridy=1;
		add(skipSecondDate, c);


	}

	public Date getFirstDate() {
		return firstDate;
	}


	public Date getSecondDate() {
		return secondDate;
	}

	public void setLabelText(String text) {
		this.calendarLabel.setText(text);
	}


}


class SaveDatesListener implements ActionListener{

	private SwapPanel panel;
	private JDatePanelImpl datePanel;
	private UsersGUI mainGUI;
	private Users user;

	public SaveDatesListener(SwapPanel panel, JDatePanelImpl datePanel, UsersGUI mainGUI, Users user) {
		this.panel=panel;
		this.datePanel=datePanel;
		this.mainGUI=mainGUI;
		this.user=user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.panel.getFirstDate()==null) {
			this.mainGUI.removePanel();
			SwapPanel swap=new SwapPanel(this.user, this.mainGUI, (Date)this.datePanel.getModel().getValue(), null);
			swap.setLabelText("Select a preference");
			this.mainGUI.addSecondPanel(swap);
			this.mainGUI.revalidate();
			this.mainGUI.repaint();
		}
		else {
			if(this.panel.getFirstDate().compareTo((Date)this.datePanel.getModel().getValue())<0) {
				this.mainGUI.removePanel();
				this.mainGUI.addSecondPanel(new ConfirmSwapPanel(this.panel.getFirstDate(), (Date)this.datePanel.getModel().getValue(), this.user, this.mainGUI));
				this.mainGUI.revalidate();
				this.mainGUI.repaint();
			}
			else {
				new ExceptionFrame("Not a Valid Date");
				return;
			}
		}

	}

}



class ConfirmSwapPanel extends JPanel{

	private String[] startHours= {"From","9:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"};

	public ConfirmSwapPanel(Date firstDate, Date secondDate, Users User, UsersGUI mainGUI) {


		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel firstDateLabel=new JLabel(firstDate.toString()+" ");
		c.gridx=0;
		c.gridy=0;
		add(firstDateLabel, c);
		JLabel secondDateLabel;
		if(secondDate==null) {
			secondDateLabel=new JLabel("No proposal Date");
		}
		else {
			secondDateLabel=new JLabel(secondDate.toString()+" ");
		}
		c.gridx=0;
		c.gridy=1;
		add(secondDateLabel, c);


		JComboBox<String> endTimeBox=new JComboBox<String>();
		endTimeBox.addItem("To");
		endTimeBox.setFocusable(false);
		c.gridx=2;
		c.gridy=0;
		add(endTimeBox, c);

		JComboBox<String> startTimeBox=new JComboBox<String>(startHours);
		TimeSpanAL sl=new TimeSpanAL(startTimeBox, endTimeBox);
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
			c.gridx=2;
			c.gridy=1;
			add(secondEndTimeBox, c);

			secondStartTimeBox=new JComboBox<String>(startHours);
			sl=new TimeSpanAL(secondStartTimeBox, secondEndTimeBox);
			secondStartTimeBox.addActionListener(sl);
			secondStartTimeBox.setFocusable(false);
			c.gridx=1;
			c.gridy=1;
			add(secondStartTimeBox, c);


		}
		JComboBox<Rooms> possibleRooms=new JComboBox();
		RoomLoader loadRooms;
		try {
			loadRooms = new RoomLoader();

			HashMap<String, Rooms> allRooms=loadRooms.getRooms();
			ArrayList<Rooms> roomsList=new ArrayList<Rooms>();
			for(HashMap.Entry<String, Rooms> entry : allRooms.entrySet()) {
				roomsList.add(entry.getValue());
			}
			Collections.sort(roomsList);


			possibleRooms=new JComboBox(roomsList.toArray());
			c.gridx=3;
			c.gridy=0;
			add(possibleRooms, c);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JButton swapButton=new JButton("Send Swap Request");
		swapButton.addActionListener(new SwapListener(firstDate, secondDate, User, mainGUI, possibleRooms, startTimeBox, endTimeBox, secondStartTimeBox, secondEndTimeBox));
		c.gridx=4;
		c.gridy=1;
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
	private UsersGUI mainGUI;
	
	
	public SwapListener(Date firstDate, Date secondDate, Users user, UsersGUI mainGUI, JComboBox<Rooms> possibleRooms, JComboBox<String> startTimeBox, JComboBox<String> endTimeBox, JComboBox<String> secondEndTimeBox, JComboBox<String> secondStartTimeBox) {
		this.possibleRooms=possibleRooms;
		this.startTimeBox=startTimeBox;
		this.endTimeBox=endTimeBox;
		this.secondEndTimeBox=secondEndTimeBox;
		this.secondStartTimeBox=secondStartTimeBox;
		this.firstDate=firstDate;
		this.secondDate=secondDate;
		this.user=user;
		this.mainGUI=mainGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		LocalDate myDate =this.firstDate.toLocalDate();
		DayOfWeek dayOfWeek=myDate.getDayOfWeek();
		
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
			String scheduleID=result.getString(1);
			
			
			query="insert into prof_notifications (Schedule_ID, Date, Sender, New_Date, New_From, New_To)"+"values (?, ?, ?, ?, ?, ?)";
			preparedStmt = conn.prepareStatement(query);
			Date DoW;
			try {
				DoW =Date.valueOf(this.secondDate.toLocalDate());
			}catch(Exception ex) {
				DoW=null;
			}
			String from=(String)this.secondEndTimeBox.getSelectedItem();
			if(from.equals("From")){
				from=null;
			}
			preparedStmt.setString(1, scheduleID);
			preparedStmt.setDate(2, Date.valueOf(myDate));
			preparedStmt.setString(3, user.getID());
			preparedStmt.setDate(4, DoW);
			preparedStmt.setString(5, from);
			preparedStmt.setString(6, (String)this.secondStartTimeBox.getSelectedItem());
			
			
			preparedStmt.execute();
			conn.close();


		} catch (Exception e1) {
			new ExceptionFrame("Not a valid Schedule");
			return;
		}

	}

}
