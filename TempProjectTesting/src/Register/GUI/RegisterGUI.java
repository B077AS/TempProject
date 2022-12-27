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

public class RegisterGUI extends JFrame {

	public RegisterGUI() {
		openRegisterGUI();
	}

	public void openRegisterGUI() {
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;//finestra delle dimensioni dello schermo
		int screenWidth = screenSize.width;

		MainPanel p=new MainPanel(this);
		add(p);

		setSize(screenWidth,screenHeight);
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

		c.anchor = GridBagConstraints.EAST;
		JLabel nameLabel=new JLabel("Name: ");
		c.gridx=0;
		c.gridy=0;
		add(nameLabel, c);
		c.anchor = GridBagConstraints.CENTER;
		JTextField nameField=new JTextField(20);
		c.gridx=1;
		c.gridy=0;
		add(nameField, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel lastNameLabel=new JLabel("Last Name: ");
		c.gridx=0;
		c.gridy=1;
		add(lastNameLabel, c);
		c.anchor = GridBagConstraints.CENTER;
		JTextField lastNameField=new JTextField(20);
		c.gridx=1;
		c.gridy=1;
		add(lastNameField, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel idNumber=new JLabel("Identification Number: ");
		c.gridx=0;
		c.gridy=2;
		add(idNumber, c);
		c.anchor = GridBagConstraints.CENTER;
		JTextField idNumberField=new JTextField(20);
		c.gridx=1;
		c.gridy=2;
		add(idNumberField, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel emailLabel=new JLabel("E-mail: ");
		c.gridx=0;
		c.gridy=3;
		add(emailLabel, c);
		c.anchor = GridBagConstraints.CENTER;
		JTextField emailField=new JTextField(20);
		c.gridx=1;
		c.gridy=3;
		add(emailField, c);
		c.anchor = GridBagConstraints.WEST;
		JComboBox<String> emailSelect=new JComboBox<String>(this.emailTerminations);
		c.gridx=2;
		c.gridy=3;
		add(emailSelect, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel passwordLabel=new JLabel("Password: ");
		c.gridx=0;
		c.gridy=4;
		add(passwordLabel, c);
		c.anchor = GridBagConstraints.CENTER;
		JPasswordField passwordField=new JPasswordField(20);
		c.gridx=1;
		c.gridy=4;
		add(passwordField, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel confirmPasswordLabel=new JLabel("Confirm Password: ");
		c.gridx=0;
		c.gridy=5;
		add(confirmPasswordLabel, c);
		c.anchor = GridBagConstraints.CENTER;
		JPasswordField confirmPasswordField=new JPasswordField(20);
		c.gridx=1;
		c.gridy=5;
		add(confirmPasswordField, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel userTypeField=new JLabel("Select User Type: ");
		c.gridx=0;
		c.gridy=6;
		add(userTypeField, c);
		c.anchor = GridBagConstraints.CENTER;
		JComboBox<UserType> userTypeSelect= new JComboBox<UserType>(UserType.values());//menu a tendina a cui passo la enum
		c.gridx=1;
		c.gridy=6;
		add(userTypeSelect, c);
		JButton registerButton=new JButton("Register");
		c.gridx=1;
		c.gridy=7;
		RegisterButtonListener rbl=new RegisterButtonListener(nameField, lastNameField, idNumberField,emailField, emailSelect, passwordField, confirmPasswordField, userTypeSelect, frame);
		registerButton.addActionListener(rbl);
		add(registerButton, c);
		JButton backButton=new JButton("Back");
		c.gridx=1;
		c.gridy=8;
		backButton.addActionListener(new ActionListener() {
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
			EmailFormatEx ex=new EmailFormatEx();
			return;
		}

		try {
			PasswordCheck(this.password.getPassword(), this.confirmPassword.getPassword());//controllo che le due password siano uguali (prima verifico la loro lunghezza e poi il loro contenuto)
		}catch(IllegalArgumentException e2) {
			PasswordMismatchEx pm=new PasswordMismatchEx();
			return;
		}

		try {
			CheckDuplicate(this.emailString, this.idNumber.getText());
		} catch (Exception e1) {
			DuplicateAccountEx daex=new DuplicateAccountEx();
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
		EmailBuilder eb=new EmailBuilder(this.emailString, number);
		VerificationPanel v=new VerificationPanel(number, mainRegisterFrame, name, lastName, idNumber, email, password, type);
	}

	public void TypeCheck(String emailTermination, UserType type) throws IllegalArgumentException{
		if(emailTermination=="@universitadipavia.it" && type!=UserType.STUDENT) {
			throw new IllegalArgumentException();
		}
	}

}
