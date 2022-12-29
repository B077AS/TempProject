package Users.GeneralUser;

import java.awt.event.*;
import javax.swing.*;

import Exceptions.ExceptionFrame;
import Finder.FinderDB;
import Finder.FinderMainPanel;
import Users.Students.StudentsGUI;

public class SearchButtonAL implements ActionListener{

	private JComboBox<String> yearSelect;
	private JComboBox<String> monthSelect;
	private JComboBox<String> daySelect;
	private JComboBox<String> startTimeBox;
	private JComboBox<String> endTimeBox;
	private UsersGUI userMainGUI;

	public SearchButtonAL(JComboBox<String> yearSelect, JComboBox<String> monthSelect, JComboBox<String> daySelect, JComboBox<String> startTimeBox, JComboBox<String> endTimeBox, UsersGUI userMainGUI) {
		this.yearSelect=yearSelect;
		this.monthSelect=monthSelect;
		this.daySelect=daySelect;
		this.startTimeBox=startTimeBox;
		this.endTimeBox=endTimeBox;
		this.userMainGUI=userMainGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		int year=Integer.parseInt((String)this.yearSelect.getSelectedItem());
		String month=(String)this.monthSelect.getSelectedItem();
		int day=Integer.parseInt((String)this.daySelect.getSelectedItem());
		String start=(String)this.startTimeBox.getSelectedItem();
		String end=(String)this.endTimeBox.getSelectedItem();

		FinderDB f=new FinderDB(year, month, day, start, end);

		this.userMainGUI.removePanel();
		this.userMainGUI.addSecondPanel(new FinderMainPanel(f.getFreeRooms(), f.getFreeRooms()));
		this.userMainGUI.revalidate();
		this.userMainGUI.repaint();
		}
		catch(Exception ex) {
			new ExceptionFrame("\u274C Not Valid Parameters!");
			return;
		}
	}

}