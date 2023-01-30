package Users.GeneralUser;

import java.awt.*;
import javax.swing.*;

public class UserGUI extends JFrame{
	
	protected Users user;
	protected JPanel secondPanel=new JPanel();
	protected JPanel northPanel;
	public UserGUI(String name, String lastName, String email, Users user) {
		this.user=user;
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setLayout(new BorderLayout());

		setSize(screenWidth,screenHeight);
		setTitle("UserProfile");
		setBackground(Color.WHITE);
		
	
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void removePanel() {
		this.remove(this.secondPanel);
		revalidate();
		repaint();
	}
	
	public void addSecondPanel(JPanel panel) {
		this.secondPanel=panel;
		add(this.secondPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	
	public void removeHeadPanel(JPanel panel) {
		//this.removePanel();
		this.remove(this.northPanel);
		revalidate();
		repaint();
		this.northPanel=panel;
		addFindPanel(this, user);
		add(this.northPanel, BorderLayout.NORTH);
		revalidate();
		repaint();
	}
	
	public void addFindPanel(UserGUI gui, Users user) {
		this.northPanel.add(new FindPanel(gui, user), BorderLayout.CENTER);
	}

	public Users getUser() {
		return user;
	}
	
/*	public JPanel mainPanel(String name, String lastName) {
		
		JPanel p= new JPanel();
		p.setLayout (new GridBagLayout());
		p.setSize(40,40);
		GridBagConstraints c=new GridBagConstraints();
		
		p.setBackground(new Color(145,0,0));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		JLabel Label=new JLabel("PROFILE");
		Label.setFont(new Font("Comic Sans MS", Font.BOLD,25));
		Label.setForeground(Color.white);
		c.insets= new Insets(0,10,250,0);
		c.gridx=0;
		c.gridy=0;
		
		p.add(Label, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		JLabel studentNameLabel=new JLabel("Name: ");
		studentNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		studentNameLabel.setForeground(Color.white);
		c.insets= new Insets(10,10,0,0);
		c.gridx=0;
		c.gridy=1;
		p.add(studentNameLabel, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JLabel studentName=new JLabel(name);
		studentName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		studentName.setForeground(new Color(217, 217, 217));
		c.gridx=1;
		c.gridy=1;
		p.add(studentName, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		JLabel studentLastNameLabel=new JLabel("Last Name: ");
		studentLastNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		studentLastNameLabel.setForeground(Color.white);
		c.insets= new Insets(10,10,0,0);
		c.gridx=0;
		c.gridy=2;
		p.add(studentLastNameLabel, c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JLabel studentLastName=new JLabel(lastName);
		studentLastName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		studentLastName.setForeground(new Color(217, 217, 217));
		c.gridx=1;
		c.gridy=2;
		p.add(studentLastName, c);
		
		return p;
	}*/
	

	public JPanel downPanel() {
		
		JPanel p= new JPanel();
		p.setLayout(new BorderLayout());
		p.setBackground(new Color(77, 77, 77));
		JLabel down = new JLabel("For any problem, please contact: findme.verify@outlook.com");
		down.setFont(new Font("Comic Sans MS", Font.PLAIN,12));
		down.setForeground(Color.white);
		p.add(down, BorderLayout.EAST);
		
		return p;
		
	}
	
	
}
