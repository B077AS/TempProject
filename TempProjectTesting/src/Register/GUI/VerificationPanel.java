package Register.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ENUM.UserType;
import Exceptions.ExceptionFrame;
import Exceptions.VerificationCodeEx;
import Login.LoginGUI;


public class VerificationPanel{
	public VerificationPanel(int number, RegisterGUI mainRegisterFrame, String name, String lastName, String idNumber, String email, char[] password, UserType type) {
		JFrame mainFrame=new JFrame();
		JPanel mainPanel=new JPanel();


		mainPanel.setLayout(new BorderLayout());
		JLabel info=new JLabel("Check your emails for the verification code", SwingConstants.CENTER);
		info.setFont(new Font("", Font.PLAIN, 20));
		mainPanel.add(info, BorderLayout.NORTH);

		JPanel secondPanel=new JPanel();
		mainPanel.add(secondPanel, BorderLayout.CENTER);

		JLabel l=new JLabel("Insert Code:");
		JTextField t=new JTextField(3);
		JButton b=new JButton("OK");
		secondPanel.add(l);
		secondPanel.add(t);
		ButtonListener bl=new ButtonListener(mainFrame, t, number, mainPanel, mainRegisterFrame, name, lastName, idNumber, email, password, type);
		b.addActionListener(bl);
		secondPanel.add(b);


		mainFrame.add(mainPanel);

		mainFrame.setSize(500,300);
		mainFrame.setTitle("Error");

		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
	}
}

class ButtonListener implements ActionListener{
	private JFrame frame;
	private JTextField code;
	private String number;
	private JPanel panel;
	private RegisterGUI mainRegisterFrame;
	private String name;
	private String lastName;
	private String idNumber;
	private String email;
	private char[] password;
	private UserType type;
	public ButtonListener(JFrame frame, JTextField code, int number, JPanel panel, RegisterGUI mainRegisterFrame, String name, String lastName, String idNumber, String email, char[] password, UserType type) {
		this.frame=frame;
		this.code=code;
		this.number=Integer.toString(number);
		this.panel=panel;
		this.mainRegisterFrame=mainRegisterFrame;
		this.name=name;
		this.lastName=lastName;
		this.idNumber=idNumber;
		this.email=email;
		this.password=password;
		this.type=type;

	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(this.number.equals(code.getText())) {
			VerificationSuccess v=new VerificationSuccess(mainRegisterFrame);
			this.frame.dispose();
			Registration r=new Registration(this.name, this.lastName, this.idNumber, this.email, this.password, this.type);

		}
		else {
			new ExceptionFrame("\u274C Wronge Code!");
		}

	}

}


class VerificationSuccess extends JFrame{

	public VerificationSuccess(RegisterGUI mainRegisterFrame){
		JPanel p=new JPanel();
		JLabel l=new JLabel ("Verification Successful! \u2714", SwingConstants.CENTER);
		l.setFont(new Font("", Font.PLAIN, 20));
		p.add(l);
		JButton b=new JButton("OK");

		add(p);
		FinalButton fb=new FinalButton(this, mainRegisterFrame);
		b.addActionListener(fb);
		p.add(b);

		setSize(300,200);
		setTitle("Success");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}

class FinalButton implements ActionListener{
	private VerificationSuccess f;
	private RegisterGUI mainRegisterFrame;
	public FinalButton(VerificationSuccess f, RegisterGUI mainRegisterFrame) {
		this.f=f;
		this.mainRegisterFrame=mainRegisterFrame;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		LoginGUI back=new LoginGUI();
		this.mainRegisterFrame.dispose();
		this.f.dispose();

	}
}