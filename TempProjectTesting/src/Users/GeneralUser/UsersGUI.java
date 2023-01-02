package Users.GeneralUser;

import java.awt.*;
import javax.swing.*;

public class UsersGUI extends JFrame{
	
	protected Users user;
	protected JPanel secondPanel=new JPanel();
	protected JPanel northPanel;
	public UsersGUI(String name, String lastName, String email, Users user) {
		this.user=user;
		Toolkit kit = Toolkit.getDefaultToolkit();


		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setLayout(new BorderLayout());
		
/*
		ImageIcon Icon=new ImageIcon("Immagini/background_v2.jpeg");
		Image image = Icon.getImage();
		Image newimg = image.getScaledInstance(screenWidth, screenHeight, java.awt.Image.SCALE_SMOOTH);
	
		Icon = new ImageIcon(newimg);
		
		JLabel ll= new JLabel(Icon);
		add(ll);

		*/
		

		setSize(screenWidth,screenHeight);
		setTitle("UserProfile");
		setBackground(Color.white);

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
		secondPanel.setBackground(new Color(0,0,0,0));
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
		add(this.northPanel, BorderLayout.NORTH);
		revalidate();
		repaint();
	}
	
}
