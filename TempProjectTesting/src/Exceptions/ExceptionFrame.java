package Exceptions;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExceptionFrame {
	
	public ExceptionFrame(String message) {
		ExceptionHandler(message);
	}

	public void ExceptionHandler(String message) {
		JFrame f=new JFrame();
		JPanel p=new JPanel();
		p.setBackground(Color.white);
		JLabel l=new JLabel(message);
		l.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		l.setForeground(new Color(145,0,0));
		//
		JButton b=new JButton("OK");
		b.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		b.setForeground(Color.white);
		b.setBackground(new Color(145,0,0));
		b.setOpaque(true);
		b.setBorderPainted(false);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		p.add(l);
		p.add(b);
		f.add(p);
		
		f.setSize(400,200);
		f.setTitle("Message");

		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}

} 
