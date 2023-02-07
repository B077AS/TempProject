package Users.Professors;

import java.awt.Color;
import java.awt.Dimension;
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
import MyTimer.MyTimer;
import Notifications.ProfNotificationDAO;
import Notifications.ProfessorNotification;
import Rooms.Rooms;
import Users.GeneralUser.Users;
import Users.GeneralUser.UserGUI;

public class SwapPanel extends JPanel{

	private Date firstDate;
	private Date secondDate;
	private JLabel calendarLabel;
	private boolean skip;


	public SwapPanel(Users user, UserGUI mainGUI, Date firstDate, Date secondDate) {
		this.firstDate=firstDate;
		this.secondDate=secondDate;

		setLayout (new GridBagLayout());
		setBackground(Color.white);
		GridBagConstraints c=new GridBagConstraints();

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.calendarLabel=new JLabel("(Requiered) Select a day to Swap");
		calendarLabel.setFont(new Font("Comic Sans MS", Font.BOLD,25));
		calendarLabel.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=0;
		c.insets= new Insets (0,0,100,0);
		add(calendarLabel, c);
		//
		SqlDateModel model= new SqlDateModel();

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		c.gridx=1;
		c.gridy=1;
		c.insets= new Insets(0,100,50,0);
		add(datePanel, c);
		//
		JButton firstDateButton=new JButton("Next");
		firstDateButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		firstDateButton.setForeground(Color.WHITE);
		firstDateButton.setBackground(new Color(145,0,0));		
		firstDateButton.setOpaque(true);
		firstDateButton.setBorderPainted(false);
		firstDateButton.addActionListener(new SaveDatesListener(this, datePanel, mainGUI, user));
		c.gridx=2;
		c.gridy=1;
		add(firstDateButton, c);
		//
		JButton skipSecondDate=new JButton("Skip");
		skipSecondDate.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		skipSecondDate.setForeground(Color.WHITE);
		skipSecondDate.setBackground(new Color(145,0,0));		
		skipSecondDate.setOpaque(true);
		skipSecondDate.setBorderPainted(false);

		skipSecondDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(firstDate!=null) {
					mainGUI.removePanel();
					mainGUI.addSecondPanel(new ConfirmSwapPanel(firstDate, null, user, mainGUI, true));
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
		c.insets= new Insets(50,100,0,0);
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
	private UserGUI mainGUI;
	private Users user;

	public SaveDatesListener(SwapPanel panel, JDatePanelImpl datePanel, UserGUI mainGUI, Users user) {
		this.panel=panel;
		this.datePanel=datePanel;
		this.mainGUI=mainGUI;
		this.user=user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MyTimer time=new MyTimer();

		Date currentDate=Date.valueOf(time.getDate().toLocalDate().toString());	

		try {
			if(currentDate.compareTo((Date)this.datePanel.getModel().getValue())>0) {
				throw new IllegalArgumentException();
			}
		}catch(Exception ex) {
			new ExceptionFrame("Invalid Date");
			return;
		}



		if(this.panel.getFirstDate()==null) {
			this.mainGUI.removePanel();
			SwapPanel swap=new SwapPanel(this.user, this.mainGUI, (Date)this.datePanel.getModel().getValue(), null);
			swap.setLabelText("(Optional) Select a preference");
			this.mainGUI.addSecondPanel(swap);
			this.mainGUI.revalidate();
			this.mainGUI.repaint();
		}
		else {
			if(this.panel.getFirstDate().compareTo((Date)this.datePanel.getModel().getValue())<0) {
				this.mainGUI.removePanel();
				this.mainGUI.addSecondPanel(new ConfirmSwapPanel(this.panel.getFirstDate(), (Date)this.datePanel.getModel().getValue(), this.user, this.mainGUI, false));
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



