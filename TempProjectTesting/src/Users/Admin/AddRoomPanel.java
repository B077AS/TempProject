package Users.Admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Users.GeneralUser.UsersGUI;

import java.sql.*;

public class AddRoomPanel extends JPanel{

	public AddRoomPanel(UsersGUI frame) {

		String[] yesNoArray=new String[]{"yes", "no"};
		String[] roomTypeArray=new String[]{"SMALL", "BIG", "LAB"};
	
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel roomCode=new JLabel("Room Code: ");
		c.gridx=0;
		c.gridy=0;
		add(roomCode, c);
		JTextField roomCodeField=new JTextField(5);
		c.gridx=1;
		c.gridy=0;
		add(roomCodeField, c);
		JLabel roomSeats=new JLabel("Room Seats: ");
		c.gridx=2;
		c.gridy=0;
		add(roomSeats, c);
		JTextField roomSeatsField=new JTextField(5);
		c.gridx=3;
		c.gridy=0;
		add(roomSeatsField, c);
		JLabel lim=new JLabel("Equipped with LIM: ");
		c.gridx=4;
		c.gridy=0;
		add(lim, c);
		JComboBox<String> yesNoLIM=new JComboBox<String>(yesNoArray);
		c.gridx=5;
		c.gridy=0;
		add(yesNoLIM, c);
		JLabel outlets=new JLabel("Equipped with Outlets: ");
		c.gridx=6;
		c.gridy=0;
		add(outlets, c);
		JComboBox<String> yesNoOutlets=new JComboBox<String>(yesNoArray);
		c.gridx=7;
		c.gridy=0;
		add(yesNoOutlets, c);
		JLabel disabledAccess=new JLabel("Disabled Access: ");
		c.gridx=8;
		c.gridy=0;
		add(disabledAccess, c);
		JComboBox<String> yesNoDisabledAccess=new JComboBox<String>(yesNoArray);
		c.gridx=9;
		c.gridy=0;
		add(yesNoDisabledAccess, c);
		JLabel roomType=new JLabel("Room Type: ");
		c.gridx=10;
		c.gridy=0;
		add(roomType, c);
		JComboBox<String> typeBox=new JComboBox<String>(roomTypeArray);
		c.gridx=11;
		c.gridy=0;
		add(typeBox, c);
		JButton confirm=new JButton("Add");
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
		c.gridx=12;
		c.gridy=0;
		add(confirm, c);
		JButton back=new JButton("Back");
		back.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	frame.removePanel();
       
		    }
		});
		c.gridx=13;
		c.gridy=0;
		add(back, c);
	}
}