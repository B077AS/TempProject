package Rooms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookingSuccessful extends JFrame{
	
	public BookingSuccessful() {
		JFrame f=new JFrame();
		JPanel p=new JPanel();
		JLabel l=new JLabel("Booking Successful!");
		JButton b=new JButton("OK");
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
		f.setTitle("Booking");

		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}

}
