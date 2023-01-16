package Users.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Users.GeneralUser.UsersGUI;

public class AddCoursePanel extends JPanel{
	
	public AddCoursePanel(UsersGUI frame) {
		String[] years=new String[]{"1", "2", "3", "4", "5"};
		
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		c.anchor = GridBagConstraints.EAST;
		JLabel courseFullName=new JLabel("Course Full Name: ");
		c.gridx=0;
		c.gridy=0;
		add(courseFullName, c);
		c.anchor = GridBagConstraints.WEST;
		JTextField courseFullNameField=new JTextField(20);
		c.gridx=1;
		c.gridy=0;
		add(courseFullNameField, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel courseYears=new JLabel("Course Year: ");
		c.gridx=0;
		c.gridy=1;
		add(courseYears, c);
		c.anchor = GridBagConstraints.WEST;
		JComboBox<String> yearsSelect=new JComboBox<String>(years);
		c.gridx=1;
		c.gridy=1;
		add(yearsSelect, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel courseCode=new JLabel("Course Code: ");
		c.gridx=0;
		c.gridy=2;
		add(courseCode, c);
		c.anchor = GridBagConstraints.WEST;
		JTextField courseCodeFiled=new JTextField(20);
		c.gridx=1;
		c.gridy=2;
		add(courseCodeFiled, c);
		c.anchor = GridBagConstraints.CENTER;
		JButton addButton=new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String courseFullName=courseFullNameField.getText();
				String years=(String)yearsSelect.getSelectedItem();
				String courseCode=courseCodeFiled.getText();
				
				Admin user=(Admin)frame.getUser();
				user.addCourse(courseCode, courseFullName, years);
				
			}
			
		});
		c.gridx=1;
		c.gridy=3;
		add(addButton, c);
		JButton back=new JButton("Back");
		back.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	frame.removePanel();
		    	frame.addSecondPanel(new ViewCoursesPanel(frame));
				frame.revalidate();
				frame.repaint();
		    }
		});
		c.gridx=1;
		c.gridy=4;
		add(back, c);
		
		
		
	}

}