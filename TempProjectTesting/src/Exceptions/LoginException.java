package Exceptions;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginException implements ExHandler {

	public LoginException() {
		ExceptionHandler("\u274C Wrong Email or Password");
	}
	
	@Override
	public void ExceptionHandler(String message) {
		ExceptionPanel ex=new ExceptionPanel(message);
	}

}