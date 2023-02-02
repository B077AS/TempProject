package Users.Admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Users.GeneralUser.UserGUI;

import java.sql.*;

public class AddRoomPanel extends JPanel{

	public AddRoomPanel(UserGUI frame) {

		String[] yesNoArray=new String[]{"yes", "no"};
		String[] roomTypeArray=new String[]{"SMALL", "BIG", "LAB"};
	
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		setBackground(Color.white);
		
		
		c.anchor = GridBagConstraints.CENTER;
		JLabel roomCode=new JLabel("Room Code: ");
		roomCode.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		roomCode.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets (0,0,20,0);
		add(roomCode, c);
		//
		JTextField roomCodeField=new JTextField(5);
		c.gridx=1;
		c.gridy=0;
		add(roomCodeField, c);
		//
		JLabel roomSeats=new JLabel("Room Seats: ");
		roomSeats.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		roomSeats.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=1;
		add(roomSeats, c);
		//
		JTextField roomSeatsField=new JTextField(5);
		c.gridx=1;
		c.gridy=1;
		add(roomSeatsField, c);
		//
		JLabel lim=new JLabel("Equipped with LIM: ");
		lim.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		lim.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=2;
		add(lim, c);
		//
		JComboBox<String> yesNoLIM=new JComboBox<String>(yesNoArray);
		yesNoLIM.setBackground(Color.WHITE);
		yesNoLIM.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=2;
		add(yesNoLIM, c);
		//
		JLabel outlets=new JLabel("Equipped with Outlets: ");
		outlets.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		outlets.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=3;
		add(outlets, c);
		//
		JComboBox<String> yesNoOutlets=new JComboBox<String>(yesNoArray);
		yesNoOutlets.setBackground(Color.WHITE);
		yesNoOutlets.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=3;
		add(yesNoOutlets, c);
		//
		JLabel disabledAccess=new JLabel("Disabled Access: ");
		disabledAccess.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		disabledAccess.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=4;
		add(disabledAccess, c);
		JComboBox<String> yesNoDisabledAccess=new JComboBox<String>(yesNoArray);
		yesNoDisabledAccess.setBackground(Color.WHITE);
		yesNoDisabledAccess.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=4;
		add(yesNoDisabledAccess, c);
		//
		JLabel roomType=new JLabel("Room Type: ");
		roomType.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		roomType.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=5;
		add(roomType, c);
		//
		JComboBox<String> typeBox=new JComboBox<String>(roomTypeArray);
		typeBox.setBackground(Color.WHITE);
		typeBox.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=5;
		add(typeBox, c);
		//
		JButton confirm=new JButton("Add");
		confirm.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		confirm.setForeground(Color.white);
		confirm.setBackground(new Color(145,0,0));
		confirm.setOpaque(true);
		confirm.setBorderPainted(false);
	
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String code=roomCodeField.getText();
				String type=(String)typeBox.getSelectedItem();
				String seatsNumber=roomSeatsField.getText();
				String limString=(String)yesNoLIM.getSelectedItem();
				String outlets=(String)yesNoOutlets.getSelectedItem();
				String disabled=(String)yesNoDisabledAccess.getSelectedItem();
				
				Admin user=(Admin)frame.getUser();
				user.addRoom(code, type, seatsNumber, limString, outlets, disabled);
				
			}
			
		});
		c.gridx=0;
		c.gridy=6;
		add(confirm, c);
	/*	JButton back=new JButton("Back");
		back.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	frame.removePanel();
       
		    }
		});
		c.gridx=13;
		c.gridy=0;
		add(back, c);*/
	}
}