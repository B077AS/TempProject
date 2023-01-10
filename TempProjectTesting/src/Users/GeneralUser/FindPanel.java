package Users.GeneralUser;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MyTimer.MyTimer;
import Users.Students.StudentsGUI;

public class FindPanel extends JPanel{
	private boolean leapYear;
	private String[] yearsArray;
	private String[] allMonths= {"January", "February", "March", "April", "May", "June", "July", "August",
			"September", "November", "December"};
	private String[] startHours= {"From","9:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"};
	private JComboBox<String> monthsSelect;
	private JComboBox<String> daysSelect;
	
	public FindPanel(UsersGUI userMainGUI, Users user) {
		
	
		
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		setBackground(Color.white);
		
		MyTimer t=new MyTimer();
		this.leapYear=leapYearCheck(t.getYear());
		this.yearsArray=setYearsArray(t.getYear());
		
		c.anchor = GridBagConstraints.PAGE_START;
		
		JLabel findRoomLabel=new JLabel("Find a free room: ");
		findRoomLabel.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		findRoomLabel.setForeground(new Color(145,0,0));
		c.insets= new Insets (5,0,0,0);
		c.gridx=0;
		c.gridy=0;
		add(findRoomLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JComboBox<String> yearsSelect=new JComboBox<String>(this.yearsArray);
		yearsSelect.setFocusable(false);
		//
		daysSelect=new JComboBox<String>();
		daysSelect.addItem("Day");
		daysSelect.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		daysSelect.setForeground(new Color(145,0,0));
		daysSelect.setFocusable(false);
		daysSelect.setBackground(Color.WHITE);
		c.insets= new Insets (0,20,0,0);
		c.gridx=3;
		//c.gridy=3;
		c.gridy=0;
		add(daysSelect, c);
		//
		monthsSelect=new JComboBox<String>();
		monthsSelect.addItem("Month");
		monthsSelect.setFocusable(false);
		monthsSelect.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		monthsSelect.setForeground(new Color(145,0,0));
		monthsSelect.setFocusable(false);
		monthsSelect.setBackground(Color.WHITE);
		
		c.gridx=2;
		c.gridy=0;
		//c.gridy=2;
		//
		MonthComboBoxAL ml=new MonthComboBoxAL(this.monthsSelect, this.leapYear, this.daysSelect, this.monthsSelect);
		monthsSelect.addActionListener(ml);
		add(monthsSelect, c);
		
		YearComboBoxAL yl=new YearComboBoxAL(this.allMonths, this.monthsSelect, yearsSelect);
		yearsSelect.addActionListener(yl);
		c.gridx=1;
		c.gridy=0;
		//c.gridy=1;
		yearsSelect.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		yearsSelect.setForeground(new Color(145,0,0));
		yearsSelect.setFocusable(false);
		yearsSelect.setBackground(Color.WHITE);
		add(yearsSelect, c);
		
		
		JComboBox<String> endTimeBox=new JComboBox<String>();
		endTimeBox.addItem("To");
		endTimeBox.setFocusable(false);
		endTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		endTimeBox.setForeground(new Color(145,0,0));
		endTimeBox.setFocusable(false);
		endTimeBox.setBackground(Color.WHITE);
		c.gridx=5;
		c.gridy=0;
		add(endTimeBox, c);
		
		JComboBox<String> startTimeBox=new JComboBox<String>(startHours);
		TimeSpanAL sl=new TimeSpanAL(startTimeBox, endTimeBox);
		startTimeBox.addActionListener(sl);
		startTimeBox.setFocusable(false);
		startTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		startTimeBox.setForeground(new Color(145,0,0));
		startTimeBox.setBackground(Color.WHITE);
		c.gridx=4;
		c.gridy=0;
		add(startTimeBox, c);


		
		JButton searchButton=new JButton("Search");
		searchButton.setFocusable(false);
		searchButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		searchButton.setForeground(Color.white);
		searchButton.setBackground(new Color(145,0,0));
		SearchButtonAL sbl=new SearchButtonAL(yearsSelect, monthsSelect, daysSelect, startTimeBox, endTimeBox, userMainGUI, user);
		searchButton.addActionListener(sbl);
		c.gridx=6;
		c.gridy=0;
		add(searchButton, c);		
	}
	
	public boolean leapYearCheck(int y) {
		boolean leapYear;
		if(y%4==0) {
			leapYear=true;
			return leapYear;
		}
		else {
			leapYear=false;
			return leapYear;
		}
	}

	public String[] setYearsArray(int y) {
		int nextYear=y+1;
		int currentYear=y;
		String[] years= {"Year", Integer.toString(y), Integer.toString(nextYear)};
		return years;
	}

}