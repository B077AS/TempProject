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

		MainPanel p=new MainPanel(this);
		add(p);

		setSize(screenWidth,screenHeight);
		setTitle("FindMe");

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}

class MainPanel extends JPanel{
	public MainPanel(LoginGUI mainGUI) {
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
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterGUI register=new RegisterGUI();
				mainGUI.dispose();

			}
		});
		add(registerButton, c);

	}

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