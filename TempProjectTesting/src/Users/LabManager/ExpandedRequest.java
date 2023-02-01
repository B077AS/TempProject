package Users.LabManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Notifications.LabNotification;

public class ExpandedRequest extends JFrame{
	
	public ExpandedRequest(LabNotification notification) {
		JPanel container=new JPanel();
		container.setBackground(Color.white);
		JPanel header=new JPanel();
		header.setBackground(Color.white);
		
		header.setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		JLabel groupName=new JLabel("Group Name: "+notification.getGroupID());
		groupName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		groupName.setForeground(new Color(145,0,0));
		c.gridy=0;
		header.add(groupName, c);
		
		JLabel room=new JLabel("Room: "+notification.getRoom());
		room.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		room.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=1;
		header.add(room, c);
		
		JLabel date=new JLabel("Date: "+notification.getDate());
		date.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		date.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=2;
		header.add(date, c);
		
		JLabel fromTo=new JLabel("From: "+notification.getStartTime()+" to: "+notification.getEndTime());
		fromTo.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		fromTo.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=3;
		header.add(fromTo, c);
		
		container.add(header, BorderLayout.NORTH);
		add(container);
		
		JTextArea reason=new JTextArea(20, 60);
		reason.setText(notification.getReason());
		reason.setBorder(new LineBorder(new Color(145,0,0),2));
		reason.setLineWrap(true);
		reason.setWrapStyleWord(true);
		reason.setEditable(false);

		JScrollPane scrollpane = new JScrollPane(reason);
		container.add(scrollpane, BorderLayout.CENTER);
		
		
		setSize(700,500);
		setTitle("Write your Request");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
