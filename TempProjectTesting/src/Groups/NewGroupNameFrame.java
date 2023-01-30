package Groups;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;

import Users.GeneralUser.Users;
import Users.GeneralUser.UserGUI;
import Users.Students.Students;
import Users.Students.StudentsGUI;

public class NewGroupNameFrame extends JFrame{

	public NewGroupNameFrame(Users user, UserGUI studentsGUI) {
		setSize(400,200);
		setTitle("Insert Group Name");

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel insertName=new JLabel("Insert Group Name");
		insertName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		insertName.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets(0,0,10,0);
		add(insertName, c);

		JTextField nameField=new JTextField(10);
		ConfirmListener confirm=new ConfirmListener(user, nameField, this, studentsGUI);
		nameField.addActionListener(confirm);
		c.gridx=0;
		c.gridy=1;
		add(nameField, c);

		JButton okButton=new JButton("OK");
		okButton.addActionListener(confirm);
		okButton.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		okButton.setBackground(new Color(145,0,0));
		okButton.setForeground(Color.white);
		okButton.setOpaque(true);
		okButton.setBorderPainted(false);
		c.gridx=0;
		c.gridy=2;
		c.insets= new Insets(10,0,0,0);
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
	private UserGUI studentsGUI;

	public ConfirmListener(Users user, JTextField name, JFrame frame, UserGUI studentsGUI) {
		this.user=(Students)user;
		this.name=name;
		this.frame=frame;
		this.studentsGUI=studentsGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
				this.user.createGroup(this.name.getText());
				this.studentsGUI.removePanel();
				this.studentsGUI.addSecondPanel(new GroupsPanel(this.user, this.studentsGUI));
				this.studentsGUI.revalidate();
				this.studentsGUI.repaint();
				frame.dispose();
	}
}
