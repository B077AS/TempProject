package Users.Professors;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Users.GeneralUser.FindPanel;
import Users.GeneralUser.NewPanelListener;
import Users.GeneralUser.UsersGUI;

public class TeachersMainPanel extends JPanel{

	public TeachersMainPanel(String name, String lastName, String email, UsersGUI teachersGUI, Teachers user) {
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		c.anchor = GridBagConstraints.EAST;
		JLabel studentNameLabel=new JLabel("Professor Name: ");
		c.gridx=0;
		c.gridy=0;
		add(studentNameLabel, c);
		c.anchor = GridBagConstraints.WEST;
		JLabel studentName=new JLabel(name);
		c.gridx=1;
		c.gridy=0;
		add(studentName, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel studentLastNameLabel=new JLabel("Professor Last Name: ");
		c.gridx=0;
		c.gridy=2;
		add(studentLastNameLabel, c);
		c.anchor = GridBagConstraints.WEST;
		JLabel studentLastName=new JLabel(lastName);
		c.gridx=1;
		c.gridy=2;
		add(studentLastName, c);
	}

}
