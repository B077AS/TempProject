package Users.Students;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import DataBase.DBConnection;
import Groups.GroupsPanel;
import MyTimer.MyTimer;
import Users.Admin.ViewCoursesPanel;
import Users.GeneralUser.*;

public class FindSchedule extends JPanel{
	
	private String subject;
//	private String[][] schedule= new String[9][6];
	
/*	
	private String[] course;
	private String[] year = {"1","2","3"};
	private String[] day = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
	private JComboBox<String> courseSelect;
	private JComboBox<String> yearSelect;
	private JComboBox<String> daySelect;
	*/
	public FindSchedule() {
		
	/*	
		setLayout(new BorderLayout());
		add(tablePanel, BorderLayout.NORTH);

		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JTable table=tablePanel.getTable();

		schedule=new String[9][6];
		String[] columnsNames= {" ", "Monday", "Tuesday", "Thursday", "Wednesday", "Friday"};

		JTable newTable=new JTable(schedule, columnsNames);
		newTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		newTable.setDefaultEditor(Object.class, null);

		JButton selectSchedule=new JButton("Find Schedule");
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
						String sub;
						sub=checkString(result, 4);
						String room;
						room= checkString(result, 5);
						switch(result.getString(6)) {

						case "MONDAY":
							/*if(result.getString(2).equals("9:00")) {
								schedule[0][0]=result.getString(2)+" - "+result.getString(3);
								schedule[0][1]=subject+" - "+room;
							}else if(result.getString(2).equals("10:00")) {
								schedule[1][0]=result.getString(2)+" - "+result.getString(3);
								schedule[1][1]=subject+" - "+room;
							}else if(result.getString(2).equals("11:00")) {
								schedule[2][0]=result.getString(2)+" - "+result.getString(3);
								schedule[2][1]=subject+" - "+room;
							}else if(result.getString(2).equals("12:00")) {
								schedule[3][0]=result.getString(2)+" - "+result.getString(3);
								schedule[3][1]=subject+" - "+room;
							}else if(result.getString(2).equals("13:00")) {
								schedule[4][0]=result.getString(2)+" - "+result.getString(3);
								schedule[4][1]=" ";
							}else if(result.getString(2).equals("14:00")) {
								schedule[5][0]=result.getString(2)+" - "+result.getString(3);
								schedule[5][1]=subject+" - "+room;
							}else if(result.getString(2).equals("15:00")) {
								schedule[6][0]=result.getString(2)+" - "+result.getString(3);
								schedule[6][1]=subject+" - "+room;
							}else if(result.getString(2).equals("16:00")) {
								schedule[7][0]=result.getString(2)+" - "+result.getString(3);
								schedule[7][1]=subject+" - "+room;
							}else if(result.getString(2).equals("17:00")) {
								schedule[8][0]=result.getString(2)+" - "+result.getString(3);
								schedule[8][1]=subject+" - "+room;
							}
							
							table(result, sub, room, 1);
							break;

						case "TUESDAY":
							if(result.getString(2).equals("9:00")) {
								schedule[0][0]=result.getString(2)+" - "+result.getString(3);
								schedule[0][2]=subject+" - "+room;
							}else if(result.getString(2).equals("10:00")) {
								schedule[1][0]=result.getString(2)+" - "+result.getString(3);
								schedule[1][2]=subject+" - "+room;
							}else if(result.getString(2).equals("11:00")) {
								schedule[2][0]=result.getString(2)+" - "+result.getString(3);
								schedule[2][2]=subject+" - "+room;
							}else if(result.getString(2).equals("12:00")) {
								schedule[3][0]=result.getString(2)+" - "+result.getString(3);
								schedule[3][2]=subject+" - "+room;
							}else if(result.getString(2).equals("13:00")) {
								schedule[4][0]=result.getString(2)+" - "+result.getString(3);
								schedule[4][2]=" ";
							}else if(result.getString(2).equals("14:00")) {
								schedule[5][0]=result.getString(2)+" - "+result.getString(3);
								schedule[5][2]=subject+" - "+room;
							}else if(result.getString(2).equals("15:00")) {
								schedule[6][0]=result.getString(2)+" - "+result.getString(3);
								schedule[6][2]=subject+" - "+room;
							}else if(result.getString(2).equals("16:00")) {
								schedule[7][0]=result.getString(2)+" - "+result.getString(3);
								schedule[7][2]=subject+" - "+room;
							}else if(result.getString(2).equals("17:00")) {
								schedule[8][0]=result.getString(2)+" - "+result.getString(3);
								schedule[8][2]=subject+" - "+room;
							}
							break;

						case "THURSDAY":
							if(result.getString(2).equals("9:00")) {
								schedule[0][0]=result.getString(2)+" - "+result.getString(3);
								schedule[0][3]=subject+" - "+room;
							}else if(result.getString(2).equals("10:00")) {
								schedule[1][0]=result.getString(2)+" - "+result.getString(3);
								schedule[1][3]=subject+" - "+room;
							}else if(result.getString(2).equals("11:00")) {
								schedule[2][0]=result.getString(2)+" - "+result.getString(3);
								schedule[2][3]=subject+" - "+room;
							}else if(result.getString(2).equals("12:00")) {
								schedule[3][0]=result.getString(2)+" - "+result.getString(3);
								schedule[3][3]=subject+" - "+room;
							}else if(result.getString(2).equals("13:00")) {
								schedule[4][0]=result.getString(2)+" - "+result.getString(3);
								schedule[4][3]=" ";
							}else if(result.getString(2).equals("14:00")) {
								schedule[5][0]=result.getString(2)+" - "+result.getString(3);
								schedule[5][3]=subject+" - "+room;
							}else if(result.getString(2).equals("15:00")) {
								schedule[6][0]=result.getString(2)+" - "+result.getString(3);
								schedule[6][3]=subject+" - "+room;
							}else if(result.getString(2).equals("16:00")) {
								schedule[7][0]=result.getString(2)+" - "+result.getString(3);
								schedule[7][3]=subject+" - "+room;
							}else if(result.getString(2).equals("17:00")) {
								schedule[8][0]=result.getString(2)+" - "+result.getString(3);
								schedule[8][3]=subject+" - "+room;
							}
							break;

						case "WEDNESDAY":
							if(result.getString(2).equals("9:00")) {
								schedule[0][0]=result.getString(2)+" - "+result.getString(3);
								schedule[0][4]=subject+" - "+room;
							}else if(result.getString(2).equals("10:00")) {
								schedule[1][0]=result.getString(2)+" - "+result.getString(3);
								schedule[1][4]=subject+" - "+room;
							}else if(result.getString(2).equals("11:00")) {
								schedule[2][0]=result.getString(2)+" - "+result.getString(3);
								schedule[2][4]=subject+" - "+room;
							}else if(result.getString(2).equals("12:00")) {
								schedule[3][0]=result.getString(2)+" - "+result.getString(3);
								schedule[3][4]=subject+" - "+room;
							}else if(result.getString(2).equals("13:00")) {
								schedule[4][0]=result.getString(2)+" - "+result.getString(3);
								schedule[4][4]=" ";
							}else if(result.getString(2).equals("14:00")) {
								schedule[5][0]=result.getString(2)+" - "+result.getString(3);
								schedule[5][4]=subject+" - "+room;
							}else if(result.getString(2).equals("15:00")) {
								schedule[6][0]=result.getString(2)+" - "+result.getString(3);
								schedule[6][4]=subject+" - "+room;
							}else if(result.getString(2).equals("16:00")) {
								schedule[7][0]=result.getString(2)+" - "+result.getString(3);
								schedule[7][4]=subject+" - "+room;
							}else if(result.getString(2).equals("17:00")) {
								schedule[8][0]=result.getString(2)+" - "+result.getString(3);
								schedule[8][4]=subject+" - "+room;
							}
							break;

						case "FRIDAY":
							if(result.getString(2).equals("9:00")) {
								schedule[0][0]=result.getString(2)+" - "+result.getString(3);
								schedule[0][5]=subject+" - "+room;
							}else if(result.getString(2).equals("10:00")) {
								schedule[1][0]=result.getString(2)+" - "+result.getString(3);
								schedule[1][5]=subject+" - "+room;
							}else if(result.getString(2).equals("11:00")) {
								schedule[2][0]=result.getString(2)+" - "+result.getString(3);
								schedule[2][5]=subject+" - "+room;
							}else if(result.getString(2).equals("12:00")) {
								schedule[3][0]=result.getString(2)+" - "+result.getString(3);
								schedule[3][5]=subject+" - "+room;
							}else if(result.getString(2).equals("13:00")) {
								schedule[4][0]=result.getString(2)+" - "+result.getString(3);
								schedule[4][5]=" ";
							}else if(result.getString(2).equals("14:00")) {
								schedule[5][0]=result.getString(2)+" - "+result.getString(3);
								schedule[5][5]=subject+" - "+room;
							}else if(result.getString(2).equals("15:00")) {
								schedule[6][0]=result.getString(2)+" - "+result.getString(3);
								schedule[6][5]=subject+" - "+room;
							}else if(result.getString(2).equals("16:00")) {
								schedule[7][0]=result.getString(2)+" - "+result.getString(3);
								schedule[7][5]=subject+" - "+room;
							}else if(result.getString(2).equals("17:00")) {
								schedule[8][0]=result.getString(2)+" - "+result.getString(3);
								schedule[8][5]=subject+" - "+room;
							}
							break;
						}

					}

					remove(tablePanel);
					remove(buttonPanel);
					mainGUI.revalidate();
					mainGUI.repaint();
					JScrollPane listScroller = new JScrollPane(newTable);
					add(listScroller, BorderLayout.NORTH);
					mainGUI.revalidate();
					mainGUI.repaint();

					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
					return;
				}

			}

		}
		c.gridx=0;
		c.gridy=0;
		buttonPanel.add(selectSchedule, c);


		add(buttonPanel, BorderLayout.CENTER);*/
		
	}

	
	public String checkString(ResultSet s, int i) throws SQLException {
		
		if(s.getString(i)==null) {
			subject=" ";
		}
		else {
			subject=s.getString(i);
		}
		
		return subject;
		
	}
	
/*	
	public void table(ResultSet s, String sub, String room, int i) throws SQLException {
		
			
			for(int c=0; c<6;c++) {
				
				if(s.getString(2).equals(time[c])) {
					schedule[0][0]=result.getString(2)+" - "+result.getString(3);
					schedule[0][i]=sub+" - "+room;
			}
		
			if(s.getString(2).equals("9:00")) {
				schedule[0][0]=s.getString(2)+" - "+s.getString(3);
				schedule[0][i]=sub+" - "+room;
			}else if(s.getString(2).equals("10:00")) {
				
				schedule[1][0]=s.getString(2)+" - "+s.getString(3);
				schedule[1][i]=subject+" - "+room;
			}else if(s.getString(2).equals("11:00")) {
				
				schedule[2][0]=s.getString(2)+" - "+s.getString(3);
				schedule[2][i]=subject+" - "+room;
			}else if(s.getString(2).equals("12:00")) {
				
				schedule[3][0]=s.getString(2)+" - "+s.getString(3);
				schedule[3][i]=subject+" - "+room;
			}else if(s.getString(2).equals("13:00")) {
				
				schedule[4][0]=s.getString(2)+" - "+s.getString(3);
				schedule[4][i]=" ";
			}else if(s.getString(2).equals("14:00")) {
				
				schedule[5][0]=s.getString(2)+" - "+s.getString(3);
				schedule[5][i]=subject+" - "+room;
			}else if(s.getString(2).equals("15:00")) {
				
				schedule[6][0]=s.getString(2)+" - "+s.getString(3);
				schedule[6][i]=subject+" - "+room;
			}else if(s.getString(2).equals("16:00")) {
				
				schedule[7][0]=s.getString(2)+" - "+s.getString(3);
				schedule[7][i]=subject+" - "+room;
			}else if(s.getString(2).equals("17:00")) {
				
				schedule[8][0]=s.getString(2)+" - "+s.getString(3);
				schedule[8][i]=subject+" - "+room;
			}
			
		
			
		
	}
	
		*/	

}
