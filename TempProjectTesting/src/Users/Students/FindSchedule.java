package Users.Students;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;

import DataBase.DBConnection;
import Groups.GroupsPanel;
import MyTimer.MyTimer;
import Users.Admin.ViewCoursesPanel;
import Users.GeneralUser.*;

public class FindSchedule extends JPanel{
	


	public FindSchedule(ViewCoursesPanel tablePanel, UserGUI mainGUI) {
		
		setLayout(new BorderLayout());
		add(tablePanel, BorderLayout.NORTH);

		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JTable table=tablePanel.getTable();

		String[][] schedule=new String[9][6];
		String[] columnsNames= {" ", "Monday", "Tuesday", "Thursday", "Wednesday", "Friday"};

		JTable newTable=new JTable(schedule, columnsNames);
		newTable.setBackground(Color.white);
		newTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		newTable.setDefaultEditor(Object.class, null);

		JButton selectSchedule=new JButton("Find Schedule");
		selectSchedule.setFont(new Font("Comic Sans MS", Font.PLAIN,13));
		selectSchedule.setForeground(Color.WHITE);
		selectSchedule.setBackground(new Color(145,0,0));
		selectSchedule.setOpaque(true);
		selectSchedule.setBorderPainted(false);
		selectSchedule.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				String code=(String) table.getValueAt(row, 0);


				try {
					Connection conn=DBConnection.connect();

					String query="select * from schedule where Course_ID=?";
					PreparedStatement preparedStmt = conn.prepareStatement(query);

					preparedStmt.setString(1, code);
					ResultSet result=preparedStmt.executeQuery();
				
					while(result.next()) {
						String subject;
						subject= checkString(result, 4);
						String room;
						room= checkString(result, 5);
						switch(result.getString(6)) {

						case "MONDAY":
							reconstruct(result.getString(2), result.getString(3), subject, room, 1, schedule);
							break;

						case "TUESDAY":
							reconstruct(result.getString(2), result.getString(3), subject, room, 2, schedule);
							break;

						case "THURSDAY":
							reconstruct(result.getString(2), result.getString(3), subject, room, 3, schedule);
							break;

						case "WEDNESDAY":
							reconstruct(result.getString(2), result.getString(3), subject, room, 4, schedule);
							break;

						case "FRIDAY":
							reconstruct(result.getString(2), result.getString(3), subject, room, 5, schedule);
							break;
						}
					

					}
					
					remove(tablePanel);
					remove(buttonPanel);
					mainGUI.revalidate();
					mainGUI.repaint();
					JScrollPane listScroller = new JScrollPane(newTable);
					listScroller.setBorder(new LineBorder(new Color(145,0,0),2));
					listScroller.setForeground(new Color(145,0,0));
					listScroller.setBackground(new Color(145,0,0));
					add(listScroller, BorderLayout.NORTH);
					mainGUI.revalidate();
					mainGUI.repaint();
					conn.close();
					

				} catch (Exception e1) {
					e1.printStackTrace();
					return;
				}

			}

		});
		c.gridx=0;
		c.gridy=0;
		buttonPanel.add(selectSchedule, c);


		add(buttonPanel, BorderLayout.CENTER);
	}


	
	public String checkString(ResultSet s, int i) throws SQLException {
		
		String subject;
		if(s.getString(i)==null) {
			subject=" ";
		}
		else {
			subject=s.getString(i);
		}
		
		return subject;
		
	}
	
	public void reconstruct(String start, String end, String subject, String room, int dayIdentifier, String[][] schedule) {
		if(start.equals("9:00")) {
			schedule[0][0]=start+" - "+end;
			schedule[0][dayIdentifier]=subject+" - "+room;
		}else if(start.equals("10:00")) {
			schedule[1][0]=start+" - "+end;
			schedule[1][dayIdentifier]=subject+" - "+room;
		}else if(start.equals("11:00")) {
			schedule[2][0]=start+" - "+end;
			schedule[2][dayIdentifier]=subject+" - "+room;
		}else if(start.equals("12:00")) {
			schedule[3][0]=start+" - "+end;
			schedule[3][dayIdentifier]=subject+" - "+room;
		}else if(start.equals("13:00")) {
			schedule[4][0]=start+" - "+end;
			schedule[4][dayIdentifier]=" ";
		}else if(start.equals("14:00")) {
			schedule[5][0]=start+" - "+end;
			schedule[5][dayIdentifier]=subject+" - "+room;
		}else if(start.equals("15:00")) {
			schedule[6][0]=start+" - "+end;
			schedule[6][dayIdentifier]=subject+" - "+room;
		}else if(start.equals("16:00")) {
			schedule[7][0]=start+" - "+end;
			schedule[7][dayIdentifier]=subject+" - "+room;
		}else if(start.equals("17:00")) {
			schedule[8][0]=start+" - "+end;
			schedule[8][dayIdentifier]=subject+" - "+room;
		}
	}
	
		

}
