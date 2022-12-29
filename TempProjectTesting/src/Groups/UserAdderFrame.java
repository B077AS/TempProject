package Groups;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import Exceptions.ExceptionFrame;
import Login.Login;
import Users.GeneralUser.Users;
import Users.Students.Students;
import Users.Students.StudentsGUI;

public class UserAdderFrame extends JFrame{
	
	public UserAdderFrame(JList<Group> list, StudentsGUI studentsGUI, Students user) {
		setSize(400,200);
		setTitle("Add User");

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel insertName=new JLabel("Insert Email or ID");
		c.gridx=0;
		c.gridy=0;
		add(insertName, c);

		JTextField nameField=new JTextField(20);
		c.gridx=1;
		c.gridy=0;
		add(nameField, c);

		JButton okButton=new JButton("OK");
		OkListener listener=new OkListener(list, nameField, this, studentsGUI, user);
		okButton.addActionListener(listener);
		c.gridx=2;
		c.gridy=0;
		add(okButton, c);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
}

class OkListener implements ActionListener{
	
	private JList<Group> list;
	private JTextField emailOrID;
	private JFrame frame;
	private StudentsGUI studentsGUI;
	private Students user;
	
	public OkListener(JList<Group> list, JTextField emailOrID, JFrame frame, StudentsGUI studentsGUI, Students user) {
		this.list=list;
		this.emailOrID=emailOrID;
		this.frame=frame;
		this.user=user;
		this.studentsGUI=studentsGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.emailOrID.getText().equals(this.user.getID()) || this.emailOrID.getText().equals(this.user.getEmail())) {
			new ExceptionFrame("\u274C You are already part of this Group!");
			return;
		}
	
		try {
			this.list.getSelectedValue().addNewStudent(this.emailOrID.getText(), this.list.getSelectedValue());
			this.studentsGUI.removePanel();
			this.studentsGUI.addSecondPanel(new GroupsPanel(this.user, this.studentsGUI));
			this.studentsGUI.revalidate();
			this.studentsGUI.repaint();
			this.frame.dispose();
			
		} catch (Exception e1) {
			new ExceptionFrame("\u274C No Groupd Selected!");
			return;
		}
	}
	
}
