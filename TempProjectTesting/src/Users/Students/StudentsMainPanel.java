package Users.Students;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import DataBase.DBConnection;
import Groups.GroupsPanel;
import Login.LoginGUI;
import MyTimer.MyTimer;
import Users.Admin.ViewCoursesPanel;
import Users.GeneralUser.*;

public class StudentsMainPanel extends JPanel{
		public StudentsMainPanel(String name, String lastName, String email, UserGUI mainGUI, Users user) {		
			setLayout (new GridBagLayout());
			GridBagConstraints c=new GridBagConstraints();
			
			setBackground(new Color(145,0,0));
			
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			JLabel Label=new JLabel("PROFILE");
			Label.setFont(new Font("Comic Sans MS", Font.BOLD,25));
			Label.setForeground(Color.white);
			c.insets= new Insets(0,10,0,0);
			c.gridx=0;
			c.gridy=0;
			add(Label, c);
			
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			JLabel studentNameLabel=new JLabel("Name: ");
			studentNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentNameLabel.setForeground(Color.white);
			c.insets= new Insets(10,10,0,0);
			c.gridx=0;
			c.gridy=1;
			add(studentNameLabel, c);
			//
			c.anchor = GridBagConstraints.FIRST_LINE_END;
			JLabel studentName=new JLabel(name);
			studentName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentName.setForeground(new Color(217, 217, 217));
			c.gridx=1;
			c.gridy=1;
			add(studentName, c);
			//
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			JLabel studentLastNameLabel=new JLabel("Last Name: ");
			studentLastNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentLastNameLabel.setForeground(Color.white);
			c.insets= new Insets(10,10,0,0);
			c.gridx=0;
			c.gridy=2;
			add(studentLastNameLabel, c);
			//
			c.anchor = GridBagConstraints.FIRST_LINE_END;
			JLabel studentLastName=new JLabel(lastName);
			studentLastName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentLastName.setForeground(new Color(217, 217, 217));
			c.gridx=1;
			c.gridy=2;
			add(studentLastName, c);
			//
		
			c.anchor = GridBagConstraints.PAGE_START;
			ImageIcon friendsIcon=new ImageIcon("Files/users-icon.png");
			Image image = friendsIcon.getImage();
			Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);  
			friendsIcon = new ImageIcon(newimg);
			JButton groupsButton=new JButton(friendsIcon);
			groupsButton.setBackground(Color.WHITE);
			//
			groupsButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					mainGUI.removePanel();
					mainGUI.addSecondPanel(new GroupsPanel(user, mainGUI));
					mainGUI.revalidate();
					mainGUI.repaint();
				}
			});
			c.insets= new Insets(30,0,35,10);
			c.gridx=0;
			c.gridy=4;
			add(groupsButton, c);
			//
			c.anchor = GridBagConstraints.EAST;
			ImageIcon calendarIcon=new ImageIcon("Files/calendar-icon.png");
			Image calendar = calendarIcon.getImage();
			Image temp = calendar.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);  
			calendarIcon = new ImageIcon(temp);
			JButton scheduleButton=new JButton(calendarIcon);
			scheduleButton.setBackground(Color.WHITE);
			scheduleButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					mainGUI.removePanel();
					ViewCoursesPanel view=new ViewCoursesPanel(mainGUI);
					view.remove(view.getButtonsPanel());

					FindSchedule findSchedulePanel=new FindSchedule(view, mainGUI);

					mainGUI.addSecondPanel(findSchedulePanel);
					mainGUI.revalidate();
					mainGUI.repaint();
				}

			});
			c.gridx=1;
			c.gridy=4;
			c.insets= new Insets(0,0,5,0);
			add(scheduleButton, c);
			
			JButton LogButton=new JButton("Sign out");
			LogButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
			LogButton.setBackground(Color.white);
			LogButton.setForeground(new Color(145,0,0));
			LogButton.setOpaque(true);
			LogButton.setBorderPainted(false);
			LogButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					LoginGUI l=new LoginGUI();
					mainGUI.dispose();
				}
			});
			
			c.gridx=0;
			c.gridy=5;
			c.insets= new Insets(0,0,5,0);
			add(LogButton, c);
			
		}
		
}
