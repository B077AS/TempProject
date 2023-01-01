package Notifications;

import java.awt.event.*;
import javax.swing.*;

import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;
import Users.GeneralUser.UsersHeadPanel;
import Users.Students.StudentNotificationPanel;
import Users.Students.Students;
import Users.Students.StudentsGUI;

public class AcceptRejectFrame extends JFrame{
	
	public AcceptRejectFrame(String message, Users user, UsersGUI frame) {

		JFrame f=new JFrame();
		JPanel p=new JPanel();
		JLabel l=new JLabel(message);
		JButton b=new JButton("OK");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.removePanel();
				user.getNotifications().clear();
		    	frame.addSecondPanel(new StudentNotificationPanel(user, frame));
		    	StudentsGUI tempFrame=(StudentsGUI)frame;
		    	tempFrame.removeHeadPanel(new UsersHeadPanel(user.getName(), user.getLastName(), user.getEmail(), user, frame));
		    	tempFrame.revalidate();
		    	tempFrame.repaint();
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
