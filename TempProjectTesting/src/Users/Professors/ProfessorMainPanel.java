package Users.Professors;

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

import Groups.GroupsPanel;
import Login.LoginGUI;
import Users.GeneralUser.FindPanel;
import Users.GeneralUser.NewPanelListener;
import Users.GeneralUser.UserGUI;

public class ProfessorMainPanel extends JPanel{

	public ProfessorMainPanel(String name, String lastName, String email, UserGUI mainGUI, Professor user) {
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
		JLabel PNameLabel=new JLabel("Name: ");
		PNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		PNameLabel.setForeground(Color.white);
		c.insets= new Insets(10,10,0,0);
		c.gridx=0;
		c.gridy=1;
		add(PNameLabel, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JLabel PName=new JLabel(name);
		PName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		PName.setForeground(new Color(217, 217, 217));
		c.gridx=1;
		c.gridy=1;
		add(PName, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		JLabel PLastNameLabel=new JLabel("Last Name: ");
		PLastNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		PLastNameLabel.setForeground(Color.white);
		c.insets= new Insets(10,10,0,0);
		c.gridx=0;
		c.gridy=2;
		add(PLastNameLabel, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JLabel PLastName=new JLabel(lastName);
		PLastName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		PLastName.setForeground(new Color(217, 217, 217));
		c.gridx=1;
		c.gridy=2;
		add(PLastName, c);
		
		//
		/*c.anchor = GridBagConstraints.EAST;
		JLabel swapLabel=new JLabel("Swap Schedule: ");
		swapLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		swapLabel.setForeground(Color.white);
		c.gridx=0;
		c.gridy=3;
		add(swapLabel, c);*/
		//
		c.anchor = GridBagConstraints.CENTER;
		ImageIcon swapIcon=new ImageIcon("Files/swap-icon.png");
		Image image = swapIcon.getImage();
		Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);  
		swapIcon = new ImageIcon(newimg);
		JButton swapButton=new JButton(swapIcon);
		swapButton.setBackground(Color.WHITE);
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
		c.gridx=0;
		c.gridy=4;
		c.insets= new Insets(0,0,20,0);
		add(swapButton, c);
		
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
		c.gridy=4;
		c.insets= new Insets(70,20,10,0);
		add(LogButton, c);
	}

}
