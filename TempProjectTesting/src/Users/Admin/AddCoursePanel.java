package Users.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import DataBase.DBConnection;
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
		AddCourseLinstener addListener=new AddCourseLinstener(courseFullNameField, yearsSelect, courseCodeFiled);
		addButton.addActionListener(addListener);
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

class AddCourseLinstener implements ActionListener{
	
	private JTextField courseFullNameField;
	private JComboBox<String> yearsSelect;
	private JTextField courseCodeFiled;
	
	
	public AddCourseLinstener(JTextField courseFullNameField, JComboBox<String> yearsSelect, JTextField courseCodeFiled) {
		
		this.courseFullNameField=courseFullNameField;
		this.yearsSelect=yearsSelect;
		this.courseCodeFiled=courseCodeFiled;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Connection conn=DBConnection.connect();
		
		try {
			String query="SELECT * FROM courses WHERE Course_ID=?";
			PreparedStatement check=conn.prepareStatement(query);
			check.setString(1, this.courseCodeFiled.getText());
			
			ResultSet checkResult=check.executeQuery();
			
			if(checkResult.next()==false) {
			
			query="insert into courses (Course_ID, Name, Year)"+" values (?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			preparedStmt.setString(1, this.courseCodeFiled.getText());
			preparedStmt.setString(2, this.courseFullNameField.getText());
			preparedStmt.setInt(3, Integer.parseInt((String)this.yearsSelect.getSelectedItem()));
			
			
			preparedStmt.execute();
			conn.close();
			}
			else {
				//TODO eccezione, un corso con lo stesso codice esiste gia!
				conn.close();
			}
		} catch (SQLException e1) {
			System.out.println("errore query");
			e1.printStackTrace();
		}
		
	}
	
}
