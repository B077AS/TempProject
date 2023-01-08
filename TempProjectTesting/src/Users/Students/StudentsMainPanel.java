package Users.Students;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import DataBase.DBConnection;
import Groups.GroupsPanel;
import MyTimer.MyTimer;
import Users.Admin.ViewCoursesPanel;
import Users.GeneralUser.*;

public class StudentsMainPanel extends JPanel{
		public StudentsMainPanel(String name, String lastName, String email, UsersGUI mainGUI, Users user) {		
			setLayout (new GridBagLayout());
			GridBagConstraints c=new GridBagConstraints();
			
			setBackground(new Color(0,0,0,0));
			
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			JLabel Label=new JLabel("PROFILO");
			Label.setFont(new Font("Comic Sans MS", Font.PLAIN,25));
			Label.setForeground(new Color(145,0,0));
			c.insets= new Insets(0,10,0,0);
			c.gridx=0;
			c.gridy=0;
			add(Label, c);
			
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			JLabel studentNameLabel=new JLabel("Name: ");
			studentNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentNameLabel.setForeground(new Color(145,0,0));
			c.insets= new Insets(10,10,0,0);
			c.gridx=0;
			c.gridy=1;
			add(studentNameLabel, c);
			//
			c.anchor = GridBagConstraints.FIRST_LINE_END;
			JLabel studentName=new JLabel(name);
			studentName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentName.setForeground(Color.black);
			c.gridx=1;
			c.gridy=1;
			add(studentName, c);
			//
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			JLabel studentLastNameLabel=new JLabel("Last Name: ");
			studentLastNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentLastNameLabel.setForeground(new Color(145,0,0));
			c.insets= new Insets(10,10,0,0);
			c.gridx=0;
			c.gridy=2;
			add(studentLastNameLabel, c);
			//
			c.anchor = GridBagConstraints.FIRST_LINE_END;
			JLabel studentLastName=new JLabel(lastName);
			studentLastName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentLastName.setForeground(Color.black);
			c.gridx=1;
			c.gridy=2;
			add(studentLastName, c);
			//
		/*	c.anchor = GridBagConstraints.EAST;
			JLabel friendsLabel=new JLabel("Study Friends: ");
			friendsLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			friendsLabel.setForeground(new Color(145,0,0));
			c.gridx=0;
			c.gridy=3;
			add(friendsLabel, c);*/
			//
			c.anchor = GridBagConstraints.PAGE_START;
			ImageIcon friendsIcon=new ImageIcon("Files/users-icon.png");
			Image image = friendsIcon.getImage();
			Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);  
			friendsIcon = new ImageIcon(newimg);
			JButton groupsButton=new JButton(friendsIcon);
			groupsButton.setBackground(Color.WHITE);
			//
			groupsButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					mainGUI.removePanel();
					mainGUI.addSecondPanel(new GroupsPanel(user, mainGUI));
					mainGUI.revalidate();
					mainGUI.repaint();
				}
			});
			c.insets= new Insets(30,0,0,10);
			c.gridx=0;
			c.gridy=4;
			add(groupsButton, c);
			//
			c.anchor = GridBagConstraints.EAST;
			ImageIcon calendarIcon=new ImageIcon("Files/calendar-icon.png");
			Image calendar = calendarIcon.getImage();
			Image temp = calendar.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);  
			calendarIcon = new ImageIcon(temp);
			JButton scheduleButton=new JButton(calendarIcon);
			scheduleButton.setBackground(Color.WHITE);
			scheduleButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					mainGUI.removePanel();
					ViewCoursesPanel view=new ViewCoursesPanel(mainGUI);
					view.remove(view.getButtonsPanel());

					FindSchedulePanel findSchedulePanel=new FindSchedulePanel(view, mainGUI);

					mainGUI.addSecondPanel(findSchedulePanel);
					mainGUI.revalidate();
					mainGUI.repaint();
				}

			});
			c.gridx=1;
			c.gridy=4;
			add(scheduleButton, c);
			/*JLabel scheduleLabel=new JLabel("Find Schedule: ");
			c.gridx=0;
			c.gridy=4;
			add(scheduleLabel, c);*/
		}
}

class FindSchedulePanel extends JPanel{


	public FindSchedulePanel(ViewCoursesPanel tablePanel, UsersGUI mainGUI) {
		setLayout(new BorderLayout());
		add(tablePanel, BorderLayout.NORTH);

		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JTable table=tablePanel.getTable();

		String[][] schedule=new String[9][6];
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
					FindSchedule f= new FindSchedule();

					while(result.next()) {
						String subject;
						subject= f.checkString(result, 4);
						String room;
						room= f.checkString(result, 5);
						switch(result.getString(6)) {

						case "MONDAY":
							if(result.getString(2).equals("9:00")) {
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
							
							//f.table(result, subject, room, 1);
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

		});
		c.gridx=0;
		c.gridy=0;
		buttonPanel.add(selectSchedule, c);


		add(buttonPanel, BorderLayout.CENTER);
	}
}