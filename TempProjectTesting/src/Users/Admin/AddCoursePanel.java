package Users.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Users.GeneralUser.UserGUI;

public class AddCoursePanel extends JPanel{
	
	public AddCoursePanel(UserGUI frame) {
		String[] years=new String[]{"1", "2", "3", "4", "5"};
		setBackground(Color.white);
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		c.anchor = GridBagConstraints.EAST;
		JLabel courseFullName=new JLabel("Course Full Name: ");
		courseFullName.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		courseFullName.setForeground(new Color(145,0,0));	
		c.gridx=0;
		c.gridy=0;
		c.insets  =new Insets(20,0,0,0);
		add(courseFullName, c);
		//
		c.anchor = GridBagConstraints.WEST;
		JTextField courseFullNameField=new JTextField(20);
		c.gridx=1;
		c.gridy=0;
		add(courseFullNameField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel courseYears=new JLabel("Course Year: ");
		courseYears.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		courseYears.setForeground(new Color(145,0,0));	
		c.gridx=0;
		c.gridy=1;
		add(courseYears, c);
		//
		c.anchor = GridBagConstraints.WEST;
		JComboBox<String> yearsSelect=new JComboBox<String>(years);
		yearsSelect.setBackground(Color.WHITE);
		yearsSelect.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=1;
		add(yearsSelect, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel courseCode=new JLabel("Course Code: ");
		courseCode.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		courseCode.setForeground(new Color(145,0,0));	
		c.gridx=0;
		c.gridy=2;
		add(courseCode, c);
		//
		c.anchor = GridBagConstraints.WEST;
		JTextField courseCodeFiled=new JTextField(20);
		c.gridx=1;
		c.gridy=2;
		add(courseCodeFiled, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JButton addButton=new JButton("Add");
		addButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		addButton.setForeground(Color.white);
		addButton.setBackground(new Color(145,0,0));
		addButton.setOpaque(true);
		addButton.setBorderPainted(false);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String courseFullName=courseFullNameField.getText();
				String years=(String)yearsSelect.getSelectedItem();
				String courseCode=courseCodeFiled.getText();
				
				Admin user=(Admin)frame.getUser();
				user.addCourse(courseCode, courseFullName, years);
				
				frame.removePanel();
				frame.addSecondPanel(new ViewCoursesPanel(frame));
				frame.revalidate();
				frame.repaint();
				
				new ExceptionFrame("Course Added Successfully!");
				
			}
			
		});
		c.gridx=1;
		c.gridy=3;
		add(addButton, c);
		//
		JButton back=new JButton("Back");
		back.setFont(new Font("Comic Sans MS", Font.PLAIN,13));
		back.setForeground(Color.white);
		back.setBackground(new Color(145,0,0));
		back.setOpaque(true);
		back.setBorderPainted(false);
		back.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	frame.removePanel();
		    	frame.addSecondPanel(new ViewCoursesPanel(frame));
				frame.revalidate();
				frame.repaint();
		    }
		});
		c.gridx=2;
		c.gridy=4;
		add(back, c);
		
		
		
	}

}