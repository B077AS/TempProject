package Users.GeneralUser;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		
		//setBackground(new Color(0,0,0,0));
		
		MyTimer t=new MyTimer();
		this.leapYear=leapYearCheck(t.getYear());
		this.yearsArray=setYearsArray(t.getYear());
		
		JLabel findRoomLabel=new JLabel("Find Room: ");
		c.gridx=0;
		c.gridy=0;
		add(findRoomLabel, c);
		c.anchor = GridBagConstraints.WEST;
		JComboBox<String> yearsSelect=new JComboBox<String>(this.yearsArray);
		yearsSelect.setFocusable(false);
		daysSelect=new JComboBox<String>();
		daysSelect.addItem("Day");
		daysSelect.setFocusable(false);
		c.gridx=3;
		c.gridy=0;
		add(daysSelect, c);
		
		monthsSelect=new JComboBox<String>();
		monthsSelect.addItem("Month");
		monthsSelect.setFocusable(false);
		c.gridx=2;
		c.gridy=0;
		MonthComboBoxAL ml=new MonthComboBoxAL(this.monthsSelect, this.leapYear, this.daysSelect, this.monthsSelect);
		monthsSelect.addActionListener(ml);
		add(monthsSelect, c);
		
		YearComboBoxAL yl=new YearComboBoxAL(this.allMonths, this.monthsSelect, yearsSelect);
		yearsSelect.addActionListener(yl);
		c.gridx=1;
		c.gridy=0;
		add(yearsSelect, c);
		
		
		JComboBox<String> endTimeBox=new JComboBox<String>();
		endTimeBox.addItem("To");
		endTimeBox.setFocusable(false);
		c.gridx=5;
		c.gridy=0;
		add(endTimeBox, c);
		
		JComboBox<String> startTimeBox=new JComboBox<String>(startHours);
		TimeSpanAL sl=new TimeSpanAL(startTimeBox, endTimeBox);
		startTimeBox.addActionListener(sl);
		startTimeBox.setFocusable(false);
		c.gridx=4;
		c.gridy=0;
		add(startTimeBox, c);


		
		JButton searchButton=new JButton("Search");
		searchButton.setFocusable(false);
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