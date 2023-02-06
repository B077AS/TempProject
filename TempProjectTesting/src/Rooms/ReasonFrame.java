package Rooms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Notifications.LabNotification;

public class ReasonFrame extends JFrame{
	
	public LabNotification notification;

	public ReasonFrame(LabNotification notification) {
		this.notification=notification;
		
		JPanel mainPanel=new JPanel();
		JPanel headerPanel=new JPanel();
		JPanel secondPanel=new JPanel();
		JPanel footerPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());

		headerPanel.setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		headerPanel.setBackground(new Color(145,0,0));
		
		footerPanel.setLayout (new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();
		//footerPanel.setBackground(new Color(145,0,0));
		mainPanel.setBackground(new Color(145,0,0));
		secondPanel.setBackground(new Color(145,0,0));

		JLabel info=new JLabel("Explain why you are requesting to book this room");
		info.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		info.setForeground(Color.white);
		
		c.gridx=0;
		c.gridy=0;
		headerPanel.add(info, c);
		
		mainPanel.add(headerPanel, BorderLayout.NORTH);

		JTextArea reason=new JTextArea(20, 60);
		reason.setDocument(new LimitTextArea(2000));
		reason.setLineWrap(true);
		reason.setWrapStyleWord(true);

		JScrollPane scrollpane = new JScrollPane(reason);
		secondPanel.add(scrollpane, BorderLayout.NORTH);
		
		mainPanel.add(secondPanel, BorderLayout.CENTER);
		
		JButton send=new JButton("Send");
		send.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		send.setBackground(Color.white);
		send.setForeground(new Color(145,0,0));
		send.setOpaque(true);
		send.setBorderPainted(false);
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendRequest(notification, reason.getText());
				new ExceptionFrame("Booking Request sent Successfully");
				dispose();
			}
			
		});
		c1.gridx=0;
		c1.gridy=0;
		footerPanel.add(send, c);
		
		secondPanel.add(footerPanel, BorderLayout.CENTER);


		add(mainPanel);
		setSize(700,460);
		setTitle("Write your Request");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	public void sendRequest(LabNotification notification, String reason) {
		try {
			String bookingID=notification.getGroupID()+"-"+notification.getDate()+"-"+notification.getStartTime().split(":")[0]+"-"+notification.getEndTime().split(":")[0];
			
			Connection conn=DBConnection.connect();
			String query="insert into lab_booking (Booking_ID, Group_ID, Date, Start_Time, End_Time, Room, Reason, Locked)"+"values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt=conn.prepareStatement(query);
			preparedStmt.setString(1, bookingID);
			preparedStmt.setString(2, notification.getGroupID());
			preparedStmt.setDate(3, notification.getDate());
			preparedStmt.setString(4, notification.getStartTime());
			preparedStmt.setString(5, notification.getEndTime());
			preparedStmt.setString(6, notification.getRoom());
			preparedStmt.setString(7, reason);
			preparedStmt.setString(8, "false");
			preparedStmt.execute();
			conn.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			new ExceptionFrame("Error!");
			return;
		}
	}

}


class LimitTextArea extends PlainDocument{
	
	private int limit;
	
	public LimitTextArea(int limit) {
		this.limit=limit;
	}
	
	public void insertString(int offset, String string, AttributeSet set) throws BadLocationException{
		
		if(string==null) {
			return;
		}else if(getLength()+string.length() <= limit) {
			super.insertString(offset, string, set);
		}
		
	}
}
