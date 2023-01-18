package Groups;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.sql.*;
import DataBase.DBConnection;
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

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JButton createGroup=new JButton("Add Group");
		createGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewGroupNameFrame group=new NewGroupNameFrame(user, studentsGUI);
			}
		});
		c.gridx=0;
		c.gridy=0;
		add(createGroup, c);

		List<Group> filteredGroups = new ArrayList<Group>();
		for(HashMap.Entry<String, Group> entry : this.userSpecificGroups.entrySet()) {
			filteredGroups.add(entry.getValue());
		}

		JList<Group> list=new JList(filteredGroups.toArray());
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200, 300));
		c.gridx=0;
		c.gridy=1;
		add(listScroller, c);

		if(this.userSpecificGroups.isEmpty()==false) {
			JButton addUser=new JButton("Add User");
			addUser.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UserAdderFrame add=new UserAdderFrame(list, studentsGUI, user);
				}
			});
			c.gridx=0;
			c.gridy=2;
			add(addUser, c);
		}

	}

	public HashMap<String, Group> getUserSpecificGroups() {
		return userSpecificGroups;
	}
}
