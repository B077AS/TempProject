package Rooms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookingSuccessful extends JFrame{
	
	public BookingSuccessful() {
		JFrame f=new JFrame();
		f.setLayout(new BorderLayout());
		JPanel p=new JPanel();
		p.setBackground(Color.white);
		
		JLabel l=new JLabel("Booking Successful!");
		l.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		l.setForeground(new Color(145,0,0));
		
		p.add(l);
		//
		JButton b=new JButton("OK");
		b.setFont(new Font("Comic Sans MS", Font.PLAIN,10));
		b.setForeground(Color.WHITE);
		b.setBackground(new Color(145,0,0));		
		b.setOpaque(true);
		b.setBorderPainted(false);
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		
	
		p.add(b);
		f.add(p,BorderLayout.CENTER);
		
		f.setSize(400,200);
		f.setTitle("Booking");

		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}

}
