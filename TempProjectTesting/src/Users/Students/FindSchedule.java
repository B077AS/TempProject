package Users.Students;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Finder.FinderDB;
import Finder.FinderMainPanel;
import Groups.GroupsPanel;
import MyTimer.MyTimer;
import Users.Admin.ViewCoursesPanel;
import Users.GeneralUser.*;

public class FindSchedule extends JPanel{
	
	private String[] course;
	private String[] year = {"1","2","3"};
	private String[] day = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
	private JComboBox<String> courseSelect;
	private JComboBox<String> yearSelect;
	private JComboBox<String> daySelect;
	
	public FindSchedule(UsersGUI userMainGUI, Users user) {
		
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		
		JLabel scheduleLabel=new JLabel("Trova il tuo orario di oggi: ");
		scheduleLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		scheduleLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		add(scheduleLabel, c);
		//
		daySelect=new JComboBox<String>(this.day);
		daySelect.addItem("GIORNO");
		daySelect.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		daySelect.setForeground(new Color(145,0,0));
		daySelect.setFocusable(false);
		daySelect.setBackground(Color.WHITE);
		c.insets= new Insets (0,20,0,0);
		c.gridx=3;
		c.gridy=0;
		add(daySelect, c);
		//
		courseSelect=new JComboBox<String>();
		courseSelect.addItem("CORSO");
		courseSelect.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		courseSelect.setForeground(new Color(145,0,0));
		courseSelect.setFocusable(false);
		courseSelect.setBackground(Color.WHITE);
		c.insets= new Insets (0,20,0,0);
		c.gridx=1;
		c.gridy=0;
		add(courseSelect, c);
		//
		yearSelect=new JComboBox<String>(this.year);
		yearSelect.addItem("ANNO");
		yearSelect.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		yearSelect.setForeground(new Color(145,0,0));
		yearSelect.setFocusable(false);
		yearSelect.setBackground(Color.WHITE);
		c.insets= new Insets (0,20,0,0);
		c.gridx=2;
		c.gridy=0;
		add(yearSelect, c);
		//
		JButton searchButton=new JButton("Cerca");
		searchButton.setFocusable(false);
		searchButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		searchButton.setForeground(Color.white);
		searchButton.setBackground(new Color(145,0,0));
		SearchB sbl=new SearchB(yearSelect,courseSelect, daySelect, userMainGUI, user);
		searchButton.addActionListener(sbl);
		c.gridx=6;
		c.gridy=0;
		add(searchButton, c);		
		
	}
	

}


class SearchB implements ActionListener {
	
	private JComboBox<String> yearSelect;
	private JComboBox<String> courseSelect;
	private JComboBox<String> daySelect;
	private UsersGUI userMainGUI;
	private Users user;
	
	public SearchB (JComboBox<String> yearSelect, JComboBox<String> courseSelect, JComboBox<String> daySelect,UsersGUI userMainGUI, Users user) {
		
		this.yearSelect=yearSelect;
		this.courseSelect=courseSelect;
		this.daySelect=daySelect;
		this.userMainGUI=userMainGUI;
		this.user=user;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		int year=Integer.parseInt((String)this.yearSelect.getSelectedItem());
		String course=(String)this.courseSelect.getSelectedItem();
		int day=Integer.parseInt((String)this.daySelect.getSelectedItem());

	/*	FinderDAO f=new FinderDAO(year, course, day);

		this.userMainGUI.removePanel();
		this.userMainGUI.addSecondPanel(new FinderMainPanel(f.getFreeRooms(), f.getFreeRooms(), this.userMainGUI, this.user));
		this.userMainGUI.revalidate();
		this.userMainGUI.repaint();*/
		}
		catch(Exception ex) {
			ex.printStackTrace();
			new ExceptionFrame("\u274C Not Valid Parameters!");
			return;
		}
	}
}
