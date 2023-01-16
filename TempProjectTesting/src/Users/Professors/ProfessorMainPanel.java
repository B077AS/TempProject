package Users.Professors;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Groups.GroupsPanel;
import Users.GeneralUser.FindPanel;
import Users.GeneralUser.NewPanelListener;
import Users.GeneralUser.UsersGUI;

public class ProfessorMainPanel extends JPanel{

	public ProfessorMainPanel(String name, String lastName, String email, UsersGUI mainGUI, Professor user) {
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
		c.anchor = GridBagConstraints.EAST;
		JLabel swapLabel=new JLabel("Swap Schedule: ");
		c.gridx=0;
		c.gridy=3;
		add(swapLabel, c);
		c.anchor = GridBagConstraints.WEST;
		ImageIcon swapIcon=new ImageIcon("Files/swap-icon.png");
		Image image = swapIcon.getImage();
		Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
		swapIcon = new ImageIcon(newimg);
		JButton swapButton=new JButton(swapIcon);
		swapButton.setFocusPainted(false);
		swapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainGUI.removePanel();
				mainGUI.addSecondPanel(new SwapPanel(user, mainGUI, null, null));
				mainGUI.revalidate();
				mainGUI.repaint();
				
			}
			
		});
		c.gridx=1;
		c.gridy=3;
		add(swapButton, c);
	}

}
