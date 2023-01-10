package Register.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

import DataBase.DBConnection;
import ENUM.*;
import Email.*;
import Exceptions.*;
import Login.*;
import Register.GUI.MainPanel;
import Login.PanelLogo;

public class RegisterGUI extends JFrame {

	public RegisterGUI() {
		openRegisterGUI();
	}

	public void openRegisterGUI() {
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;//finestra delle dimensioni dello schermo
		int screenWidth = screenSize.width;

		ImageIcon Icon=new ImageIcon("Immagini/background_v2.jpeg");
		Image image = Icon.getImage();
		Image newimg = image.getScaledInstance(screenWidth, screenHeight, java.awt.Image.SCALE_SMOOTH);

		Icon = new ImageIcon(newimg);

		JLabel ll= new JLabel(Icon);
		add(ll);


		setSize(screenWidth,screenHeight);
		JLayeredPane pane = this.getLayeredPane();


		PanelLogo panellogo=new PanelLogo();
		panellogo.setBounds(0, 0, 200, 200);

		MainPanel p=new MainPanel(this);
		p.setBounds(10, 20, screenWidth, screenHeight);
		pane.add(panellogo, new Integer(1));
		pane.add(p, new Integer(2));

		//setSize(screenWidth,screenHeight);
		setTitle("Register");

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

class MainPanel extends JPanel{
	private String[] emailTerminations= {"@universitadipavia.it", "@unipv.it"};//creo un vettore contenente gli unici domini accettati

	public MainPanel(RegisterGUI frame) {


		setLayout (new GridBagLayout());//crea una griglia cartesiana, uso le coordinate per mettere i componenti
		GridBagConstraints c=new GridBagConstraints();
		setBackground(new Color (0,0,0,0));
		
		
		c.anchor = GridBagConstraints.CENTER;
		JLabel introLabel=new JLabel("Compila qui sotto...");
		introLabel.setFont(new Font("Comic Sans MS", Font.BOLD,20));
		introLabel.setForeground(new Color(145,0,0));
		c.insets= new Insets (0,0,50,0);
		c.gridx=1;
		c.gridy=0;
		add(introLabel, c);
		
		
		

		c.anchor = GridBagConstraints.EAST;
		JLabel nameLabel=new JLabel("Name: ");
		nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		nameLabel.setForeground(new Color(145,0,0));
		c.insets= new Insets (0,0,20,0);
		c.gridx=0;
		c.gridy=1;
		add(nameLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JTextField nameField=new JTextField(20);
		c.gridx=1;
		c.gridy=1;
		add(nameField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel lastNameLabel=new JLabel("Last Name: ");
		lastNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		lastNameLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=2;
		add(lastNameLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JTextField lastNameField=new JTextField(20);
		c.gridx=1;
		c.gridy=2;
		add(lastNameField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel idNumber=new JLabel("Identification Number: ");
		idNumber.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		idNumber.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=3;
		add(idNumber, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JTextField idNumberField=new JTextField(20);
		c.gridx=1;
		c.gridy=3;
		add(idNumberField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel emailLabel=new JLabel("E-mail: ");
		emailLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		emailLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=4;
		add(emailLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JTextField emailField=new JTextField(20);
		c.gridx=1;
		c.gridy=4;
		add(emailField, c);
		//
		c.anchor = GridBagConstraints.WEST;
		JComboBox<String> emailSelect=new JComboBox<String>(this.emailTerminations);
		emailSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.revalidate();
				frame.repaint();
			}
		});
		emailSelect.setBackground(Color.WHITE);
		emailSelect.setForeground(new Color(145,0,0));
		c.gridx=2;
		c.gridy=4;
		add(emailSelect, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel passwordLabel=new JLabel("Password: ");
		passwordLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		passwordLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=5;
		add(passwordLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JPasswordField passwordField=new JPasswordField(20);
		c.gridx=1;
		c.gridy=5;
		add(passwordField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel confirmPasswordLabel=new JLabel("Confirm Password: ");
		confirmPasswordLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		confirmPasswordLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=6;
		add(confirmPasswordLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JPasswordField confirmPasswordField=new JPasswordField(20);
		c.gridx=1;
		c.gridy=6;
		add(confirmPasswordField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel userTypeField=new JLabel("Select User Type: ");
		userTypeField.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		userTypeField.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=7;
		add(userTypeField, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JComboBox<UserType> userTypeSelect= new JComboBox<UserType>(UserType.values());//menu a tendina a cui passo la enum
		userTypeSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.revalidate();
				frame.repaint();

			}});
		userTypeSelect.setBackground(Color.WHITE);
		userTypeSelect.setFont(new Font("Comic Sans MS", Font.PLAIN,10));
		userTypeSelect.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=7;
		add(userTypeSelect, c);
		//
		JButton registerButton=new JButton("Register");
		registerButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		registerButton.setForeground(Color.white);
		registerButton.setBackground(new Color(145,0,0));
		registerButton.setOpaque(true);
		registerButton.setBorderPainted(false);
	
		c.gridx=1;
		c.gridy=8;
		RegisterButtonListener rbl=new RegisterButtonListener(nameField, lastNameField, idNumberField,emailField, emailSelect, passwordField, confirmPasswordField, userTypeSelect, frame);
		registerButton.addActionListener(rbl);
		add(registerButton, c);
		//
		JButton backButton=new JButton("Back");
		c.gridx=1;
		c.gridy=9;
		backButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		backButton.setForeground(Color.white);
		backButton.setBackground(new Color(145,0,0));
		backButton.addActionListener(new ActionListener()
		backButton.setOpaque(true);
		backButton.setBorderPainted(false);{
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginGUI l=new LoginGUI();
				frame.dispose();
			}
		});
		add(backButton, c);

	}

}

class RegisterButtonListener implements ActionListener{//il bottone si occupera di tutti i controlli, la registrazione la fara la finestra del codice conferma
	private RegisterGUI frame;
	private JTextField name;
	private JTextField lastName;
	private  JTextField idNumber;
	private JTextField email;
	private JComboBox<String> emailTermination;
	private JPasswordField password;
	private JPasswordField confirmPassword;
	private JComboBox<UserType> userType;

	private UserType type;
	private String emailString;
	private char[] passwordString;

	public RegisterButtonListener(JTextField name, JTextField lastName, JTextField idNumber,JTextField email, JComboBox<String> emailTermination, JPasswordField password, JPasswordField confirmPassword, JComboBox<UserType> userType, RegisterGUI frame) {
		this.name=name;
		this.lastName=lastName;
		this.email=email;
		this.idNumber=idNumber;
		this.emailTermination=emailTermination;
		this.password=password;
		this.userType=userType;
		this.confirmPassword=confirmPassword;
		this.userType=userType;
		this.frame=frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.type=(UserType)this.userType.getSelectedItem();
		this.passwordString=this.password.getPassword();
		this.emailString=this.email.getText()+this.emailTermination.getSelectedItem();
		int number=new GenerateRandom().getRandom();

		try {
			EmailCheck(this.email.getText());
		} catch (IllegalArgumentException e1) {
			new ExceptionFrame("\u274C Email not Valid");
			return;
		}

		try {
			PasswordCheck(this.password.getPassword(), this.confirmPassword.getPassword());//controllo che le due password siano uguali (prima verifico la loro lunghezza e poi il loro contenuto)
		}catch(IllegalArgumentException e2) {
			new ExceptionFrame("\u274C Password Mismatch");
			return;
		}

		try {
			CheckDuplicate(this.emailString, this.idNumber.getText());
		} catch (Exception e1) {
			new ExceptionFrame("\u274C Email already registered");
			return;
		}


		try {
			TypeCheck((String)this.emailTermination.getSelectedItem(), this.type);
		}
		catch(IllegalArgumentException e4) {
			NotAllowedToRegisterEx na=new NotAllowedToRegisterEx(this.type);
			return;
		}

		EmailVerification(number, frame, this.name.getText(), this.lastName.getText(), this.idNumber.getText(),this.emailString, this.passwordString, this.type);//metodo di verifica mail, devo passare i paramtrei dell'utente che verranno poi passati al metodo di registrazione

	}

	public void EmailCheck(String email) throws IllegalArgumentException {
		for(int i=0; i<email.length(); i++) {
			if(email.charAt(i)=='@') {
				throw new IllegalArgumentException();
			}
		}
	}

	public void PasswordCheck(char[] password, char[] confirmPassword) throws IllegalArgumentException {
		if(password.length!=confirmPassword.length) {
			throw new IllegalArgumentException();
		}

		for(int i=0; i<password.length; i++) {
			if(password[i]!=(confirmPassword[i])) {
				throw new IllegalArgumentException();
			}
		}

	}

	public void CheckDuplicate(String email, String ID) throws Exception{
		Connection conn=DBConnection.connect();

		String query="select * from users where User_Code=? or Email=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, ID);
		preparedStmt.setString(2, email);
		ResultSet result=preparedStmt.executeQuery();
		if(result.next()==true) {
			throw new IllegalArgumentException();
		}
	}

	public void EmailVerification(int number, RegisterGUI mainRegisterFrame, String name, String lastName, String idNumber,String email, char[] password, UserType type){

		try {
			EmailTemplate eTemp=new EmailTemplate(this.emailString, "Verify your Account", "Verification Code: "+number);
			eTemp.start();
		} catch (Exception e) {
			return;
		}
		VerificationPanel v=new VerificationPanel(number, mainRegisterFrame, name, lastName, idNumber, email, password, type);
	}


	public void TypeCheck(String emailTermination, UserType type) throws IllegalArgumentException{
		if(emailTermination=="@universitadipavia.it" && type!=UserType.STUDENT) {
			throw new IllegalArgumentException();
		}
	}

}
