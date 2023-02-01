package Users.LabManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Login.LoginGUI;
import Users.GeneralUser.UserGUI;
import Users.Professors.SwapPanel;
import Users.Professors.Professor;

public class LabManagerMainPanel extends JPanel{

	public LabManagerMainPanel(String name, String lastName, String email, UserGUI mainGUI, Lab_Manager user) {
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
		JLabel LNameLabel=new JLabel("Name: ");
		LNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		LNameLabel.setForeground(Color.white);
		c.insets= new Insets(10,10,0,0);
		c.gridx=0;
		c.gridy=1;
		add(LNameLabel, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JLabel LName=new JLabel(name);
		LName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		LName.setForeground(new Color(217, 217, 217));
		c.gridx=1;
		c.gridy=1;
		add(LName, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		JLabel LLastNameLabel=new JLabel("Last Name: ");
		LLastNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		LLastNameLabel.setForeground(Color.white);
		c.insets= new Insets(10,10,0,0);
		c.gridx=0;
		c.gridy=2;
		add(LLastNameLabel, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JLabel LLastName=new JLabel(lastName);
		LLastName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		LLastName.setForeground(new Color(217, 217, 217));
		c.gridx=1;
		c.gridy=2;
		add(LLastName, c);
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
				mainGUI.dispose();
			}
		});
		
		c.gridx=0;
		c.gridy=3;
		c.insets= new Insets(50,0,10,7);
		add(LogButton, c);
		
	}

}