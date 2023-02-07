package Groups;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Exceptions.ExceptionFrame;
import Users.GeneralUser.UserGUI;
import Users.Students.Students;

public class UsersListFrame extends JFrame{
	
	
	public UsersListFrame(ArrayList<Students> studentsList, Students user, Group group, UserGUI studentsGUI) {
		
		
	JPanel mainPanel=new JPanel();
	mainPanel.setLayout (new GridBagLayout());
	GridBagConstraints c=new GridBagConstraints();
	
	ImageIcon icon=new ImageIcon("Immagini/logo3.png");
	setIconImage(icon.getImage());
	mainPanel.setBackground(Color.white);
	
	JLabel title=new JLabel("List");
	title.setFont(new Font("Comic Sans MS", Font.BOLD,17));
	title.setForeground(new Color(145,0,0));
	c.gridx=0;
	c.gridy=0;
	c.insets = new Insets(0,0,10,0);
	mainPanel.add(title, c);
	
	JList<Students> list=new JList(studentsList.toArray());
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JScrollPane scroll = new JScrollPane(list);
	scroll.setBorder(new LineBorder(new Color(145,0,0),2));
	scroll.setPreferredSize(new Dimension(250, 200));
	c.gridx=0;
	c.gridy=1;
	mainPanel.add(scroll, c);	
	
	
	JButton removeUser=new JButton("Remove");
	removeUser.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
	removeUser.setForeground(Color.WHITE);
	removeUser.setBackground(new Color(145,0,0));	
	removeUser.setOpaque(true);
	removeUser.setBorderPainted(false);
	removeUser.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				if(list.getSelectedValue().getID().equals(user.getID())) {
					throw new IllegalArgumentException();
				}
			}catch(IllegalArgumentException e1) {
				new ExceptionFrame("You can't remove yourself");
				return;
			}
			
			
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
	setSize(600,400);
	setTitle("List");

	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setVisible(true);
	}

}
