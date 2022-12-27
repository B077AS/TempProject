package Exceptions;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExceptionPanel {
	
	public ExceptionPanel(String message) {
		ExceptionHandler(message);
	}

	public void ExceptionHandler(String message) {
		JFrame f=new JFrame();
		JPanel p=new JPanel();
		JLabel l=new JLabel(message);
		JButton b=new JButton("OK");
		p.add(l);
		
		ButtonListener bl=new ButtonListener(f);
		b.addActionListener(bl);
		p.add(b);
		f.add(p);
		
		f.setSize(400,200);
		f.setTitle("Error");

		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}

}
