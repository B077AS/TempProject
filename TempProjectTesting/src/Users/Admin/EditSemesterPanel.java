package Users.Admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;
import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.SqlDateModel;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Users.GeneralUser.UserGUI;

public class EditSemesterPanel extends JPanel{

	public EditSemesterPanel(UserGUI frame) {


		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel startSemester=new JLabel("Select the Beginning of the Semester");
		c.gridx=0;
		c.gridy=0;
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
		c.gridx=1;
		c.gridy=0;
		add(endSemester, c);
		SqlDateModel modelEnd= new SqlDateModel();
		JDatePanelImpl endCalendar = new JDatePanelImpl(modelEnd, p);
		c.gridx=1;
		c.gridy=1;
		add(endCalendar, c);
		JButton updateSemesterButton=new JButton("Upadte");
		updateSemesterButton.addActionListener(new UpdateActionListener(startCalendar, endCalendar));
		c.gridx=2;
		c.gridy=1;
		add(updateSemesterButton, c);

	}


	public void updateCourse() {
		Connection conn=DBConnection.connect();

		try {

			conn.close();
		} catch (SQLException e1) {
			System.out.println("errore query");
			e1.printStackTrace();
		}
	}

}


class UpdateActionListener implements ActionListener{

	private JDatePanelImpl start;
	private JDatePanelImpl end;

	public UpdateActionListener(JDatePanelImpl start, JDatePanelImpl end) {
		this.start=start;
		this.end=end;

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Date startDate=(Date)this.start.getModel().getValue();
		Date endDate=(Date)this.end.getModel().getValue();
		
		Connection conn=DBConnection.connect();
		try {
			String query="delete from semester";
			Statement statement=conn.prepareStatement(query);
			statement.execute(query);
			
			PreparedStatement preparedStmt=conn.prepareStatement(query);
			query="insert into semester (Start_Date, End_Date)"+"values (?, ?)";
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setDate(1, startDate);
			preparedStmt.setDate(2, endDate);
			preparedStmt.execute();
				
			conn.close();

		}catch (Exception e1) {
			System.out.println("errore query");
			e1.printStackTrace();
		}
	}
}