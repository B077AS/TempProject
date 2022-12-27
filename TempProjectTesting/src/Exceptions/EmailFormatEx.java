package Exceptions;

import java.awt.event.*;
import javax.swing.*;

public class EmailFormatEx implements ExHandler {
	public EmailFormatEx() {
		ExceptionHandler("\u274C Email not Valid");
		
	}
	@Override
	public void ExceptionHandler(String message) {
		ExceptionPanel ex=new ExceptionPanel(message);
		
	}
}

class ButtonListener implements ActionListener{
	private JFrame frame;
	
	public ButtonListener(JFrame frame) {
		this.frame=frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.dispose();
		
	}
	
}

