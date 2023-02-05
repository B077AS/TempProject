package Users.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;
import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.SqlDateModel;
import Exceptions.ExceptionFrame;
import Users.GeneralUser.UserGUI;

public class EditSemesterPanel extends JPanel{

	public EditSemesterPanel(UserGUI frame) {

		setBackground(Color.white);
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel startSemester=new JLabel("Select the Beginning of the Semester");
		startSemester.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		startSemester.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets(0,0,10,30);
		add(startSemester, c);
		SqlDateModel modelStart= new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl startCalendar = new JDatePanelImpl(modelStart, p);
		c.gridx=0;
		c.gridy=1;
		add(startCalendar, c);
		JLabel endSemester=new JLabel("Select the End of the Semester");
		endSemester.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		endSemester.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=0;
		add(endSemester, c);
		SqlDateModel modelEnd= new SqlDateModel();
		JDatePanelImpl endCalendar = new JDatePanelImpl(modelEnd, p);
		c.gridx=1;
		c.gridy=1;
		add(endCalendar, c);
		JButton updateSemesterButton=new JButton("Update");
		updateSemesterButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		updateSemesterButton.setForeground(Color.white);
		updateSemesterButton.setBackground(new Color(145,0,0));
		updateSemesterButton.setOpaque(true);
		updateSemesterButton.setBorderPainted(false);
		updateSemesterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date startDate=(Date)startCalendar.getModel().getValue();
				Date endDate=(Date)endCalendar.getModel().getValue();
				
				Admin user=(Admin)frame.getUser();
				user.updateSemester(startDate, endDate);
				
				new ExceptionFrame("Semester Updated");
				
			}});
		c.gridx=0;
		c.gridy=2;
		c.insets= new Insets(30,300,0,0);
		add(updateSemesterButton, c);

	}
}