package Groups;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.*;
import Users.Students.Students;
import Users.Students.StudentsGUI;

public class GroupsPanel extends JPanel{
	private HashMap<String, StudentsGroup> groups;

	public GroupsPanel(Students user, StudentsGUI studentsGUI) {

		try {
			user.loadGroups();
		} catch (IOException e1) {
			System.out.println("errore caricamento gruppi");
		}

		this.groups=user.getGroups();
		LinkedList<String> filteredGroups=new LinkedList<String>();

		int i=0;
		for(HashMap.Entry<String, StudentsGroup> group: groups.entrySet()) {
			if(group.getValue().getAdmin().equals(user.getEmail()) || group.getValue().getStudentsList().containsKey(user.getEmail())==false) {
				filteredGroups.add(i, group.getValue().toString());
				i++;
			}
		}
		String[] groupsList=new String[filteredGroups.size()];
		i=0;
		for(String group: filteredGroups) {
			groupsList[i]=group;
			i++;
		}

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JButton createGroup=new JButton("Add Group");
		createGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewGroupName group=new NewGroupName(user, studentsGUI);
			}
		});
		c.gridx=0;
		c.gridy=0;
		add(createGroup, c);

		JList<String> list=new JList<String>(groupsList);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200, 300));
		c.gridx=0;
		c.gridy=1;
		add(listScroller, c);

		if(user.getGroups().isEmpty()==false) {
			JButton addUser=new JButton("Add User");
			addUser.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UserAdder add=new UserAdder(list, getGroups(), studentsGUI, user);
				}
			});
			c.gridx=0;
			c.gridy=2;
			add(addUser, c);
		}

	}
	public HashMap<String, StudentsGroup> getGroups(){
		return this.groups;
	}

}
