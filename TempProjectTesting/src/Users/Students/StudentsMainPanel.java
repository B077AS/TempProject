package Users.Students;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Groups.GroupsPanel;
import MyTimer.MyTimer;
import Users.GeneralUser.*;

public class StudentsMainPanel extends JPanel{
		private boolean leapYear;
		private String[] yearsArray;
		private String[] allMonths= {"January", "February", "March", "April", "May", "June", "July", "August",
				"September", "November", "December"};
		private String[] startHours= {"From","9:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"};
		private JComboBox<String> monthsSelect;
		private JComboBox<String> daysSelect;
		public StudentsMainPanel(String name, String lastName, String email, StudentsGUI studentsGUI, Students user) {		
			setLayout (new GridBagLayout());
			GridBagConstraints c=new GridBagConstraints();

			MyTimer t=new MyTimer();
			this.leapYear=leapYearCheck(t.getYear());
			this.yearsArray=setYearsArray(t.getYear());

			c.anchor = GridBagConstraints.EAST;
			JLabel studentNameLabel=new JLabel("Student Name: ");
			c.gridx=0;
			c.gridy=0;
			add(studentNameLabel, c);
			c.anchor = GridBagConstraints.WEST;
			JLabel studentName=new JLabel(name);
			c.gridx=1;
			c.gridy=0;
			add(studentName, c);
			c.anchor = GridBagConstraints.EAST;
			JLabel studentLastNameLabel=new JLabel("Student Last Name: ");
			c.gridx=0;
			c.gridy=2;
			add(studentLastNameLabel, c);
			c.anchor = GridBagConstraints.WEST;
			JLabel studentLastName=new JLabel(lastName);
			c.gridx=1;
			c.gridy=2;
			add(studentLastName, c);
			c.anchor = GridBagConstraints.EAST;
			JLabel friendsLabel=new JLabel("Study Friends: ");
			c.gridx=0;
			c.gridy=3;
			add(friendsLabel, c);
			c.anchor = GridBagConstraints.WEST;
			ImageIcon friendsIcon=new ImageIcon("Files/users-icon.png");
			Image image = friendsIcon.getImage();
			Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
			friendsIcon = new ImageIcon(newimg);
			JButton groupsButton=new JButton(friendsIcon);
			groupsButton.addActionListener(new NewPanelListener(studentsGUI, new GroupsPanel(user, studentsGUI)));
			c.gridx=1;
			c.gridy=3;
			add(groupsButton, c);
			c.anchor = GridBagConstraints.EAST;
			JLabel findRoomLabel=new JLabel("Find Room: ");
			c.gridx=0;
			c.gridy=4;
			add(findRoomLabel, c);
			c.anchor = GridBagConstraints.WEST;
			JComboBox<String> yearsSelect=new JComboBox<String>(this.yearsArray);
			
			daysSelect=new JComboBox<String>();
			daysSelect.addItem("Day");
			c.gridx=3;
			c.gridy=4;
			add(daysSelect, c);
			
			monthsSelect=new JComboBox<String>();
			monthsSelect.addItem("Month");
			c.gridx=2;
			c.gridy=4;
			MonthComboBoxAL ml=new MonthComboBoxAL(this.monthsSelect, this.leapYear, this.daysSelect, this.monthsSelect);
			monthsSelect.addActionListener(ml);
			add(monthsSelect, c);
			
			YearComboBoxAL yl=new YearComboBoxAL(this.allMonths, this.monthsSelect, yearsSelect);
			yearsSelect.addActionListener(yl);
			c.gridx=1;
			c.gridy=4;
			add(yearsSelect, c);
			
			
			JComboBox<String> endTimeBox=new JComboBox<String>();
			endTimeBox.addItem("To");
			c.gridx=5;
			c.gridy=4;
			add(endTimeBox, c);
			
			JComboBox<String> startTimeBox=new JComboBox<String>(startHours);
			TimeSpanAL sl=new TimeSpanAL(startTimeBox, endTimeBox);
			startTimeBox.addActionListener(sl);
			c.gridx=4;
			c.gridy=4;
			add(startTimeBox, c);


			
			JButton searchButton=new JButton("Search");
			SearchButtonAL sbl=new SearchButtonAL(yearsSelect, monthsSelect, daysSelect, startTimeBox, endTimeBox, studentsGUI);
			searchButton.addActionListener(sbl);
			c.gridx=6;
			c.gridy=4;
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
