package Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Register.GUI.RegisterGUI;


public class LoginGUI extends JFrame{

	public LoginGUI() {
		openRegisterGUI();
	}

	public void openRegisterGUI() {
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

	/*	MainPanel p=new MainPanel(this);
		add(p);

		setSize(screenWidth,screenHeight);*/
		setTitle("FindMe");

		ImageIcon Icon=new ImageIcon("Immagini/background_v2.jpeg");
		Image image = Icon.getImage();
		Image newimg = image.getScaledInstance(screenWidth, screenHeight, java.awt.Image.SCALE_SMOOTH);
	
		Icon = new ImageIcon(newimg);
		
		JLabel ll= new JLabel(Icon);
		add(ll);

		
		setSize(screenWidth,screenHeight);
		JLayeredPane pane = this.getLayeredPane();

	
		PanelLogo panel=new PanelLogo();
		panel.setBounds(0, 0, 200, 200);
		MainPanel p=new MainPanel(this);
		p.setBounds(10, 120, screenWidth, screenHeight);
		pane.add(panel, new Integer(1));
		pane.add(p, new Integer(2));
	
		
		
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}

class MainPanel extends JPanel{
/*	public MainPanel(LoginGUI mainGUI) {
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		c.anchor = GridBagConstraints.EAST;
		JLabel emailLabel=new JLabel("Email/ID: ");
		c.gridx=0;
		c.gridy=0;
		add(emailLabel, c);
		c.anchor = GridBagConstraints.CENTER;
		JTextField emailField=new JTextField(25);
		c.gridx=1;
		c.gridy=0;
		add(emailField, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel passwordLabel=new JLabel("Password: ");
		c.gridx=0;
		c.gridy=1;
		add(passwordLabel, c);
		c.anchor = GridBagConstraints.CENTER;
		JPasswordField passwordField=new JPasswordField(25);
		LoginListener login=new LoginListener(emailField, passwordField, mainGUI);
		c.gridx=1;
		c.gridy=1;
		passwordField.addActionListener(login);
		add(passwordField, c);
		JButton loginButton=new JButton("Login");
		c.gridx=1;
		c.gridy=2;
		loginButton.addActionListener(login);
		add(loginButton, c);
		JButton registerButton=new JButton("Register");
		c.gridx=1;
		c.gridy=3;
		
		//
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterGUI register=new RegisterGUI();
				mainGUI.dispose();

			}
		});
		add(registerButton, c);

	}*/
	
	public MainPanel(LoginGUI mainGUI) {

		setLayout (new GridBagLayout());
		setSize(40,40);
		GridBagConstraints c=new GridBagConstraints();
		setBackground(new Color (0,0,0,0));
		

		c.anchor = GridBagConstraints.PAGE_START;
		JLabel intro= new JLabel("WELCOME TO FindMe");
		intro.setFont(new Font("Comic Sans MS", Font.BOLD,30));
		intro.setForeground(new Color(145,0,0));
		c.weightx= 0.5;
		c.weighty=0;
		c.insets= new Insets (0,0,200,0);
		c.gridx=0;
		c.gridy=1;
		add(intro,c);
		
		c.anchor = GridBagConstraints.PAGE_START;
		JLabel intro_acc= new JLabel("Log in your account");
		intro_acc.setFont(new Font("Comic Sans MS", Font.BOLD,25));
		intro_acc.setForeground(new Color(145,0,0));
	//	c.weightx= 0.5;
		//c.weighty=0;
		c.insets= new Insets (100,0,50,0);
		c.gridx=0;
		c.gridy=1;
		add(intro_acc,c);
		
		c.anchor = GridBagConstraints.CENTER;
		JLabel emailLabel= new JLabel("E-mail/ID number");
		emailLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		emailLabel.setForeground(new Color(145,0,0));
		c.weightx= 0.1;
		c.weighty=0.1;
		c.insets= new Insets (0,50,300,500);
		c.gridx=0;
		c.gridy=1;
		add(emailLabel,c);
		
		
		c.anchor = GridBagConstraints.CENTER;
		JTextField emailField=new JTextField(25);
		c.insets= new Insets (0,10,300,0);
		c.gridx=0;
		c.gridy=1;
		add(emailField,c);
		
		c.anchor = GridBagConstraints.CENTER;
		JLabel passwordLabel= new JLabel("Password");
		passwordLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		passwordLabel.setForeground(new Color(145,0,0));
		
	//	c.weightx= 0.1;
	//	c.weighty=0.1;
		c.insets= new Insets (0,80,250,500);
		c.gridx=0;
		c.gridy=1;
		add(passwordLabel,c);
		
		c.anchor = GridBagConstraints.CENTER;
		JPasswordField passwordField=new JPasswordField(25);
		c.insets= new Insets (0,10,250,0);
		LoginListener login=new LoginListener(emailField, passwordField, mainGUI);
		c.gridx=0;
		c.gridy=1;
		passwordField.addActionListener(login);
		add(passwordField,c);
		
		c.anchor = GridBagConstraints.CENTER;
		JButton loginButton= new JButton("Login");
		loginButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(new Color(145,0,0));
		
		loginButton.setOpaque(true);
		loginButton.setBorderPainted(false);
		//servono per il mac
		
		c.weightx= 0.5;
		c.weighty=0;
		c.insets= new Insets (0,0,50,0);
		c.gridx=0;
		c.gridy=1;
		loginButton.addActionListener(login);
		add(loginButton,c);
		
		c.anchor = GridBagConstraints.CENTER;
		JLabel intro_no= new JLabel("I don't have an account: ");
		intro_no.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		intro_no.setForeground(new Color(145,0,0));
	//	c.weightx= 0.1;
	//	c.weighty=0.1;
		c.insets= new Insets (200,0,0,100);
		c.gridx=0;
		c.gridy=1;
		add(intro_no,c);
		
		
		
		c.anchor = GridBagConstraints.CENTER;
		JButton registerButton= new JButton("Register");
		registerButton.setFont(new Font("Comic Sans MS", Font.PLAIN,10));
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(new Color(145,0,0));
		registerButton.setOpaque(true);
		registerButton.setBorderPainted(false);
		//servono per il mac
		
		//c.weightx= 0.5;
	//	c.weighty=0;
		c.insets= new Insets (200,200,0,0);
		c.gridx=0;
		c.gridy=1;
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterGUI register=new RegisterGUI();
				mainGUI.dispose();

			}
		});
		
		add(registerButton,c);
		
		


}


class LoginListener implements ActionListener{

	private LoginGUI frame;
	private JTextField email;
	private JPasswordField password;

	public LoginListener(JTextField email, JPasswordField password, LoginGUI frame) {
		this.email=email;
		this.password=password;
		this.frame=frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Login l=new Login(this.email.getText(), this.password.getPassword(), this.frame);

	}

}
}