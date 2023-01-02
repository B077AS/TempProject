package Users.Students;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Groups.GroupsPanel;
import MyTimer.MyTimer;
import Users.Admin.ViewCoursesPanel;
import Users.GeneralUser.*;

public class StudentsMainPanel extends JPanel{
		public StudentsMainPanel(String name, String lastName, String email, UsersGUI mainGUI, Users user) {		
			setLayout (new GridBagLayout());
			GridBagConstraints c=new GridBagConstraints();
			
			setBackground(new Color(0,0,0,0));
			
			c.anchor = GridBagConstraints.EAST;
			JLabel studentNameLabel=new JLabel("Student Name: ");
			studentNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentNameLabel.setForeground(new Color(145,0,0));
			c.insets= new Insets(10,0,0,0);
			c.gridx=0;
			c.gridy=0;
			add(studentNameLabel, c);
			//
			c.anchor = GridBagConstraints.WEST;
			JLabel studentName=new JLabel(name);
			studentName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentName.setForeground(Color.black);
			c.gridx=1;
			c.gridy=0;
			add(studentName, c);
			//
			c.anchor = GridBagConstraints.EAST;
			JLabel studentLastNameLabel=new JLabel("Student Last Name: ");
			studentLastNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentLastNameLabel.setForeground(new Color(145,0,0));
			c.gridx=0;
			c.gridy=2;
			add(studentLastNameLabel, c);
			//
			c.anchor = GridBagConstraints.WEST;
			JLabel studentLastName=new JLabel(lastName);
			studentLastName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentLastName.setForeground(Color.black);
			c.gridx=1;
			c.gridy=2;
			add(studentLastName, c);
			//
			c.anchor = GridBagConstraints.EAST;
			JLabel friendsLabel=new JLabel("Study Friends: ");
			friendsLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			friendsLabel.setForeground(new Color(145,0,0));
			c.gridx=0;
			c.gridy=3;
			add(friendsLabel, c);
			//
			c.anchor = GridBagConstraints.WEST;
			ImageIcon friendsIcon=new ImageIcon("Files/users-icon.png");
			Image image = friendsIcon.getImage();
			Image newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);  
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
			c.gridx=1;
			c.gridy=3;
			add(groupsButton, c);
			c.anchor = GridBagConstraints.EAST;
		}
}
