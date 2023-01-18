package Users.LabManager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Users.GeneralUser.UserGUI;
import Users.Professors.SwapPanel;
import Users.Professors.Professor;

public class LabManagerMainPanel extends JPanel{

	public LabManagerMainPanel(String name, String lastName, String email, UserGUI mainGUI, Lab_Manager user) {
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		c.anchor = GridBagConstraints.EAST;
		JLabel studentNameLabel=new JLabel("LabManager Name: ");
		c.gridx=0;
		c.gridy=0;
		add(studentNameLabel, c);
		c.anchor = GridBagConstraints.WEST;
		JLabel studentName=new JLabel(name);
		c.gridx=1;
		c.gridy=0;
		add(studentName, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel studentLastNameLabel=new JLabel("LabManager Last Name: ");
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