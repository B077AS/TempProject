package Users.GeneralUser;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.swing.*;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Finder.FinderDB;
import Finder.FinderMainPanel;
import MyTimer.DateHolder;
import MyTimer.Months;
import MyTimer.MyTimer;
import Users.Students.StudentsGUI;

public class SearchButtonAL implements ActionListener{

	private JComboBox<String> yearSelect;
	private JComboBox<String> monthSelect;
	private JComboBox<String> daySelect;
	private JComboBox<String> startTimeBox;
	private JComboBox<String> endTimeBox;
	private UserGUI userMainGUI;
	private Users user;

	public SearchButtonAL(JComboBox<String> yearSelect, JComboBox<String> monthSelect, JComboBox<String> daySelect, JComboBox<String> startTimeBox, JComboBox<String> endTimeBox, UserGUI userMainGUI, Users user) {
		this.yearSelect=yearSelect;
		this.monthSelect=monthSelect;
		this.daySelect=daySelect;
		this.startTimeBox=startTimeBox;
		this.endTimeBox=endTimeBox;
		this.userMainGUI=userMainGUI;
		this.user=user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		int year=Integer.parseInt((String)this.yearSelect.getSelectedItem());
		String month=(String)this.monthSelect.getSelectedItem();
		int day=Integer.parseInt((String)this.daySelect.getSelectedItem());
		String start=(String)this.startTimeBox.getSelectedItem();
		String end=(String)this.endTimeBox.getSelectedItem();
		
		try {
		}catch(IllegalArgumentException ex) {
			new ExceptionFrame("Invalide Date");
			return;
		}

		
		FinderDB f=new FinderDB(year, month, day, start, end);

		this.userMainGUI.removePanel();
		this.userMainGUI.addSecondPanel(new FinderMainPanel(f.getFreeRooms(), f.getFreeRooms(), this.userMainGUI, this.user));
		this.userMainGUI.revalidate();
		this.userMainGUI.repaint();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			new ExceptionFrame("Not Valid Parameters!");
			return;
		}
	}

}