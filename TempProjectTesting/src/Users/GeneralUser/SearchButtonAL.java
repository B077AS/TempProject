package Users.GeneralUser;

import java.awt.event.*;
import javax.swing.*;
import Finder.FinderDB;
import Finder.FinderMainPanel;
import Users.Students.StudentsGUI;

public class SearchButtonAL implements ActionListener{

	private JComboBox<String> yearSelect;
	private JComboBox<String> monthSelect;
	private JComboBox<String> daySelect;
	private JComboBox<String> startTimeBox;
	private JComboBox<String> endTimeBox;
	private StudentsGUI studentsGUI;

	public SearchButtonAL(JComboBox<String> yearSelect, JComboBox<String> monthSelect, JComboBox<String> daySelect, JComboBox<String> startTimeBox, JComboBox<String> endTimeBox, StudentsGUI studentsGUI) {
		this.yearSelect=yearSelect;
		this.monthSelect=monthSelect;
		this.daySelect=daySelect;
		this.startTimeBox=startTimeBox;
		this.endTimeBox=endTimeBox;
		this.studentsGUI=studentsGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int year=Integer.parseInt((String)this.yearSelect.getSelectedItem());
		String month=(String)this.monthSelect.getSelectedItem();
		int day=Integer.parseInt((String)this.daySelect.getSelectedItem());
		String start=(String)this.startTimeBox.getSelectedItem();
		String end=(String)this.endTimeBox.getSelectedItem();

		FinderDB f=new FinderDB(year, month, day, start, end);

		this.studentsGUI.removePanel();
		this.studentsGUI.addSecondPanel(new FinderMainPanel(f.getFreeRooms(), f.getFreeRooms()));
		this.studentsGUI.revalidate();
		this.studentsGUI.repaint();
	}

}