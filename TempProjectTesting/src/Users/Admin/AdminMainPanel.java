package Users.Admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Login.LoginGUI;
import Users.GeneralUser.NewPanelListener;
import Users.GeneralUser.UserGUI;
import Users.GeneralUser.Users;

public class AdminMainPanel extends JPanel{
	
	public AdminMainPanel(String name, String lastName, String email, UserGUI frame) {
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
		JLabel adminNameLabel=new JLabel("Name: ");
		adminNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		adminNameLabel.setForeground(Color.white);
		c.insets= new Insets(10,10,0,0);
		c.gridx=0;
		c.gridy=1;
		add(adminNameLabel, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JLabel adminName=new JLabel(name);
		adminName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		adminName.setForeground(new Color(217, 217, 217));
		c.gridx=1;
		c.gridy=1;
		add(adminName, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		JLabel adminLastNameLabel=new JLabel("Last Name: ");
		adminLastNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		adminLastNameLabel.setForeground(Color.white);
		c.insets= new Insets(10,10,0,0);
		c.gridx=0;
		c.gridy=2;
		add(adminLastNameLabel, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JLabel adminLastName=new JLabel(lastName);
		adminLastName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		adminLastName.setForeground(new Color(217, 217, 217));
		c.gridx=1;
		c.gridy=2;
		
		add(adminLastName, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel editCourses=new JLabel("Edit Available Courses: ");
		editCourses.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		editCourses.setForeground(Color.white);
		c.gridx=2;
		c.gridy=1;
		c.insets= new Insets(0,100,0,0);
		add(editCourses, c);
		//
		c.anchor = GridBagConstraints.WEST;
		JButton editCoursesButton=new JButton("Edit Courses");
		editCoursesButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		editCoursesButton.setBackground(Color.white);
		editCoursesButton.setForeground(new Color(145,0,0));
		editCoursesButton.setOpaque(true);
		editCoursesButton.setBorderPainted(false);
		editCoursesButton.addActionListener(new NewPanelListener(frame, new ViewCoursesPanel(frame)));
		c.gridx=2;
		c.gridy=2;
		add(editCoursesButton, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel addRoom=new JLabel("Edit Current Rooms: ");
		addRoom.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		addRoom.setForeground(Color.white);
		c.gridx=3;
		c.gridy=1;
		add(addRoom, c);
		//
		c.anchor = GridBagConstraints.WEST;
		JButton addRoomButton=new JButton("Edit Rooms");
		addRoomButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		addRoomButton.setBackground(Color.white);
		addRoomButton.setForeground(new Color(145,0,0));
		addRoomButton.setOpaque(true);
		addRoomButton.setBorderPainted(false);
		addRoomButton.addActionListener(new NewPanelListener(frame, new AddRoomPanel(frame)));
		c.gridx=3;
		c.gridy=2;
		add(addRoomButton, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel editSemesterLabel=new JLabel("Edit Semester: ");
		editSemesterLabel.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		editSemesterLabel.setForeground(Color.white);
		c.gridx=4;
		c.gridy=1;
		add(editSemesterLabel, c);
		//
		c.anchor = GridBagConstraints.WEST;
		JButton editSemesterButton=new JButton("Edit Semester");
		editSemesterButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		editSemesterButton.setBackground(Color.white);
		editSemesterButton.setForeground(new Color(145,0,0));
		editSemesterButton.setOpaque(true);
		editSemesterButton.setBorderPainted(false);
		editSemesterButton.addActionListener(new NewPanelListener(frame, new EditSemesterPanel(frame)));
		c.gridx=4;
		c.gridy=2;
		add(editSemesterButton, c);
		//
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
				frame.dispose();
			}
		});
		
		c.gridx=0;
		c.gridy=4;
		c.insets= new Insets(30,10,10,0);
		add(LogButton, c);
		
	}

}