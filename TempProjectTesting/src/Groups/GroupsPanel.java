package Groups;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.sql.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Users.GeneralUser.Users;
import Users.GeneralUser.UserGUI;
import Users.Students.Students;
import Users.Students.StudentsGUI;


public class GroupsPanel extends JPanel{
	private HashMap<String, Group> userSpecificGroups;

	public GroupsPanel(Users user, UserGUI studentsGUI) {
		this.userSpecificGroups=new HashMap<String, Group>();
		try {

			Connection conn=DBConnection.connect();

			String query="select * from (select * from allgroups where Group_ID in (select Group_ID from allgroups where Partecipant=?)) temp, users where temp.Partecipant=users.User_Code";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(user.getID()));
			ResultSet result=preparedStmt.executeQuery();
			while(result.next()) {
				if(this.userSpecificGroups.containsKey(result.getString(1))==false) {
					this.userSpecificGroups.put(result.getString(1), new Group(result.getString(1), result.getString(2)));
				}
				else {
					this.userSpecificGroups.get(result.getString(1)).addStudent(result.getString(1), new Students(result.getString(6), result.getString(7), result.getString(4), result.getString(8), result.getString(9),result.getString(2)));
				}			
			}

			conn.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setBackground (Color.white);
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		c.anchor= GridBagConstraints.PAGE_START;
		JLabel intro=new JLabel("Your groups: ");
		intro.setFont(new Font("Comic Sans MS", Font.BOLD,19));
		intro.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets(0,0,50,0);
		add(intro, c);
		

		JButton createGroup=new JButton("Add Group");
		createGroup.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		createGroup.setBackground(new Color(145,0,0));
		createGroup.setForeground(Color.white);
		createGroup.setOpaque(true);
		createGroup.setBorderPainted(false);
		createGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewGroupNameFrame group=new NewGroupNameFrame(user, studentsGUI);
			}
		});
		c.gridx=1;
		c.gridy=1;
		c.insets= new Insets(0,20,0,0);
		add(createGroup, c);

		List<Group> filteredGroups = new ArrayList<Group>();
		for(HashMap.Entry<String, Group> entry : this.userSpecificGroups.entrySet()) {
			filteredGroups.add(entry.getValue());
		}

		JList<Group> list=new JList(filteredGroups.toArray());
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200, 300));
		listScroller.setBorder(new LineBorder(new Color(145,0,0),2));
		c.gridx=0;
		c.gridy=1;
		add(listScroller, c);

		if(this.userSpecificGroups.isEmpty()==false) {
			JButton addUser=new JButton("Add User");
			addUser.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			addUser.setBackground(new Color(145,0,0));
			addUser.setForeground(Color.white);
			addUser.setOpaque(true);
			addUser.setBorderPainted(false);
			addUser.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UserAdderFrame add=new UserAdderFrame(list, studentsGUI, user);
				}
			});
			c.gridx=1;
			c.gridy=1;
			c.insets= new Insets(60,20,0,0);
			add(addUser, c);
		}
		
		boolean delete=false;
		for(HashMap.Entry<String, Group> group: userSpecificGroups.entrySet()) {
		if(group.getValue().getGroupAdmin().equals(user.getID())) {
			delete=true;
			break;
		}	
		}
		
		if(delete==true) {
			JButton deleteGroup=new JButton("Delete Group");
			deleteGroup.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			deleteGroup.setBackground(new Color(145,0,0));
			deleteGroup.setForeground(Color.white);
			deleteGroup.setOpaque(true);
			deleteGroup.setBorderPainted(false);
			deleteGroup.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Students student=(Students)user;
					try {
					student.removeGroup(list.getSelectedValue());
					} catch (Exception ex) {
						new ExceptionFrame("No Group Selected!");
						return;
					}
					studentsGUI.removePanel();
					studentsGUI.addSecondPanel(new GroupsPanel(user, studentsGUI));
					studentsGUI.revalidate();
					studentsGUI.repaint();
					new ExceptionFrame("Group Deleted Succesfully");
				}
			});
			c.gridx=1;
			c.gridy=1;
			c.insets= new Insets(120,20,0,0);
			add(deleteGroup, c);
			
			
			
			JButton removeUser=new JButton("Remove User");
			removeUser.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			removeUser.setBackground(new Color(145,0,0));
			removeUser.setForeground(Color.white);
			removeUser.setOpaque(true);
			removeUser.setBorderPainted(false);
			removeUser.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Students student=(Students)user;
					ArrayList<Students> partecipants=new ArrayList<Students>();
					GroupDAO dao=new GroupDAO();
					try {
					partecipants=dao.selectPartecipantsFromGroup(list.getSelectedValue());
					}catch(Exception ex) {
						new ExceptionFrame("No Group Selected!");
					}
					UsersListFrame listFrame=new UsersListFrame(partecipants, student, list.getSelectedValue(), studentsGUI);
				}
			});
			c.gridx=1;
			c.gridy=1;
			c.insets= new Insets(180,20,0,0);
			add(removeUser, c);
			
		}
		

	}

	public HashMap<String, Group> getUserSpecificGroups() {
		return userSpecificGroups;
	}
}
