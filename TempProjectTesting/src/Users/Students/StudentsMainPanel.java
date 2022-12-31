package Users.Students;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Groups.GroupsPanel;
import MyTimer.MyTimer;
import Users.Admin.ViewCoursesPanel;
import Users.GeneralUser.*;

public class StudentsMainPanel extends JPanel{
		public StudentsMainPanel(String name, String lastName, String email, UsersGUI studentsGUI, Users user) {		
			setLayout (new GridBagLayout());
			GridBagConstraints c=new GridBagConstraints();

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
			groupsButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					studentsGUI.removePanel();
					studentsGUI.addSecondPanel(new GroupsPanel(user, studentsGUI));
					studentsGUI.revalidate();
					studentsGUI.repaint();
				}
			});
			c.gridx=1;
			c.gridy=3;
			add(groupsButton, c);
			c.anchor = GridBagConstraints.EAST;
		}
}
