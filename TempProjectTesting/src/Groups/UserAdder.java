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
import Login.Login;
import Users.GeneralUser.Users;
import Users.Students.Students;
import Users.Students.StudentsGUI;

public class UserAdder extends JFrame{
	
	public UserAdder(JList<String> list, HashMap<String, StudentsGroup> groups, StudentsGUI studentsGUI, Students user) {
		setSize(400,200);
		setTitle("Insert User Email");

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel insertName=new JLabel("Insert email");
		c.gridx=0;
		c.gridy=0;
		add(insertName, c);

		JTextField nameField=new JTextField(25);
		c.gridx=1;
		c.gridy=0;
		add(nameField, c);

		JButton okButton=new JButton("OK");
		OkListener listener=new OkListener(list, groups, nameField, this, studentsGUI, user);
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
	
	private JList<String> list;
	private HashMap<String, StudentsGroup> groups;
	private JTextField email;
	private JFrame frame;
	private StudentsGUI studentsGUI;
	private Students user;
	
	public OkListener(JList<String> list, HashMap<String, StudentsGroup> groups, JTextField email, JFrame frame, StudentsGUI studentsGUI, Students user) {
		this.list=list;
		this.groups=groups;
		this.email=email;
		this.frame=frame;
		this.user=user;
		this.studentsGUI=studentsGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String[] groupInfo=this.list.getSelectedValue().split(" ");
	
		try {
			this.groups.get(groupInfo[0]).addNewStudent(this.email.getText(), this.groups.get(groupInfo[0]).getAdmin(), this.groups.get(groupInfo[0]), this.groups);
			this.studentsGUI.removePanel();
			this.studentsGUI.addSecondPanel(new GroupsPanel(this.user, this.studentsGUI));
			this.studentsGUI.revalidate();
			this.studentsGUI.repaint();
			this.frame.dispose();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
