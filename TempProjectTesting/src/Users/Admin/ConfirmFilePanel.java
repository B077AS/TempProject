package Users.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;

import javax.swing.*;

import CSVConverter.CSVtoDB;
import DataBase.DBConnection;
import Users.GeneralUser.NewPanelListener;
import Users.GeneralUser.UserGUI;

public class ConfirmFilePanel extends JPanel{
	
	public ConfirmFilePanel(String path, UserGUI frame, JTable table) {
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		c.anchor = GridBagConstraints.WEST;
		JLabel pathLabelInfo=new JLabel("Selected file: ");
		c.gridx=0;
		c.gridy=0;
		add(pathLabelInfo, c);
		
		c.anchor = GridBagConstraints.EAST;
		JLabel pathLabel=new JLabel(path);
		c.gridx=1;
		c.gridy=0;
		add(pathLabel, c);
		
		c.anchor = GridBagConstraints.WEST;
		JLabel changeInfo=new JLabel("Change Info: ");
		c.gridx=0;
		c.gridy=2;
		add(changeInfo, c);
		JButton changeFile=new JButton("Change");
		changeFile.addActionListener(new NewPanelListener(frame, new ViewCoursesPanel(frame)));
		c.gridx=1;
		c.gridy=2;
		add(changeFile, c);
		
		int row=table.getSelectedRow();
		String code=(String) table.getValueAt(row, 0);
		JLabel selectedReplace=new JLabel("Selected Course to be Updated: ");
		c.gridx=0;
		c.gridy=1;
		add(selectedReplace, c);
		
		c.anchor = GridBagConstraints.WEST;
		JLabel codeLabel=new JLabel(code);
		c.gridx=1;
		c.gridy=1;
		add(codeLabel, c);
		
		JLabel addSchedule=new JLabel("Add Schedule: ");
		c.gridx=0;
		c.gridy=3;
		add(addSchedule, c);
		JButton addScheduleButton=new JButton("Add");
		addScheduleButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	CSVtoDB converter=new CSVtoDB(code, path);
		    }
		});
		c.gridx=1;
		c.gridy=3;
		add(addScheduleButton, c);
		
		JLabel updateSchedule=new JLabel("Update Schedule: ");
		c.gridx=0;
		c.gridy=4;
		add(updateSchedule, c);
		JButton updateButton=new JButton("Update");
		UpdateLinstener update=new UpdateLinstener(code, path);
		updateButton.addActionListener(update);
		c.gridx=1;
		c.gridy=4;
		add(updateButton, c);
		
	}
}

class UpdateLinstener implements ActionListener{
	
	private String code;
	private String path;
	
	public UpdateLinstener(String code, String path) {
		this.code=code;
		this.path=path;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection conn=DBConnection.connect();
		try {
			
		String query="delete from schedule where Course_ID=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, this.code);
		preparedStmt.execute();
		
		CSVtoDB converter=new CSVtoDB(this.code, this.path);
		
		conn.close();
			
		}catch (Exception e1) {
		System.out.println("errore query");
		e1.printStackTrace();	
	}
		
	}
	
}

