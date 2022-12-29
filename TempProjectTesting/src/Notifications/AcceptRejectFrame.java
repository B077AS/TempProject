package Notifications;

import java.awt.event.*;
import javax.swing.*;
import Users.Students.NotificationListPanel;
import Users.Students.Students;
import Users.Students.StudentsGUI;
import Users.Students.StudentsHeadPanel;

public class AcceptRejectFrame extends JFrame{
	
	public AcceptRejectFrame(String message, Students user, StudentsGUI frame) {

		JFrame f=new JFrame();
		JPanel p=new JPanel();
		JLabel l=new JLabel(message);
		JButton b=new JButton("OK");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.removePanel();
				user.getNotifications().clear();
		    	frame.addSecondPanel(new NotificationListPanel(user, frame));
		    	frame.removeHeadPanel(new StudentsHeadPanel(user.getName(), user.getLastName(), user.getEmail(), user, frame));
				frame.revalidate();
				frame.repaint();
				f.dispose();
			}
		});
		p.add(l);
		p.add(b);
		f.add(p);
		
		f.setSize(400,200);

		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}

}
