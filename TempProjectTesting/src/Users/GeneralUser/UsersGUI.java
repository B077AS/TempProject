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

		setSize(screenWidth,screenHeight);
		setTitle("UserProfile");

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
	
	public void addFindPanel(UsersGUI gui, Users user) {
		this.northPanel.add(new FindPanel(gui, user), BorderLayout.CENTER);
	}
	
}
