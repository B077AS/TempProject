package Groups;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import Exceptions.ExceptionFrame;
import Users.GeneralUser.UserGUI;
import Users.Students.Students;

public class UsersListFrame extends JFrame{
	
	
	public UsersListFrame(ArrayList<Students> studentsList, Students user, Group group, UserGUI studentsGUI) {
		
		
	JPanel mainPanel=new JPanel();
	mainPanel.setLayout (new GridBagLayout());
	GridBagConstraints c=new GridBagConstraints();
	
	
	JLabel title=new JLabel("List");
	c.gridx=0;
	c.gridy=0;
	mainPanel.add(title, c);
	
	JList<Students> list=new JList(studentsList.toArray());
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JScrollPane scroll = new JScrollPane(list);
	c.gridx=0;
	c.gridy=1;
	mainPanel.add(scroll, c);	
	
	
	JButton removeUser=new JButton("Remove");
	removeUser.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				user.removeStudent(group, list.getSelectedValue());
			} catch (Exception e1) {
				new ExceptionFrame("No User Selected!");
				return;
			}
			new ExceptionFrame("User Removed Succesfully!");
			dispose();
			studentsGUI.removePanel();
			studentsGUI.addSecondPanel(new GroupsPanel(user, studentsGUI));
			studentsGUI.revalidate();
			studentsGUI.repaint();
			
		}
		
	});
	c.gridx=0;
	c.gridy=2;
	mainPanel.add(removeUser, c);
	
	
	add(mainPanel);
	setSize(700,500);
	setTitle("List");

	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setVisible(true);
	}

}
