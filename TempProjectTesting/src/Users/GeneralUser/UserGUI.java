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

	public JLabel image() {
		
		ImageIcon Icon=new ImageIcon("Immagini/owl_v2.png");
		Image image = Icon.getImage();
		Image newimg = image.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
	
		Icon = new ImageIcon(newimg);	
		JLabel ll= new JLabel(Icon);
		ll.setBounds(20, 270, 500, 500);
		
		return ll;
		
	}
	
	
}
