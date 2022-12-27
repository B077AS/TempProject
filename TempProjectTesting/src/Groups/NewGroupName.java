package Groups;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;

import Exceptions.NameGroupTakenEX;
import Users.Students.Students;
import Users.Students.StudentsGUI;

public class NewGroupName extends JFrame{

	public NewGroupName(Students user, StudentsGUI studentsGUI) {
		setSize(400,200);
		setTitle("Insert Group Name");

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel insertName=new JLabel("Insert Group Name");
		c.gridx=0;
		c.gridy=0;
		add(insertName, c);

		JTextField nameField=new JTextField(10);
		ConfirmListener confirm=new ConfirmListener(user, nameField, this, studentsGUI);
		nameField.addActionListener(confirm);
		c.gridx=1;
		c.gridy=0;
		add(nameField, c);

		JButton okButton=new JButton("OK");
		okButton.addActionListener(confirm);
		c.gridx=2;
		c.gridy=0;
		add(okButton, c);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
}


class ConfirmListener implements ActionListener{
	private Students user;
	private JTextField name;
	private JFrame frame;
	private StudentsGUI studentsGUI;

	public ConfirmListener(Students user, JTextField name, JFrame frame, StudentsGUI studentsGUI) {
		this.user=user;
		this.name=name;
		this.frame=frame;
		this.studentsGUI=studentsGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			HashMap<String, StudentsGroup> groups=this.user.getGroups();

			if(groups.containsKey(this.name.getText()) && groups.get(this.name.getText()).getAdmin().equals(this.user.getEmail())) {
				throw new IllegalArgumentException();
			}
			else {
				this.user.createGroup(this.name.getText());
				this.studentsGUI.removePanel();
				this.studentsGUI.addSecondPanel(new GroupsPanel(this.user, this.studentsGUI));
				this.studentsGUI.revalidate();
				this.studentsGUI.repaint();
				frame.dispose();
			} 
		}
		catch (Exception e1) {
			NameGroupTakenEX ex=new NameGroupTakenEX();
		}
	}
}
