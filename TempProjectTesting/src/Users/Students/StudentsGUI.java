package Users.Students;

import java.awt.*;
import javax.swing.*;
import Users.GeneralUser.*;

public class StudentsGUI extends UserGUI{
	

	public StudentsGUI(String name, String lastName, String email, Students user) {
		super(name, lastName, email, user);
		
		
		UsersHeadPanel head=new UsersHeadPanel(name, lastName, email, user, this);
		northPanel=head;
		
		addFindPanel(this, user);
		this.add(northPanel, BorderLayout.NORTH);
		this.add(downPanel(), BorderLayout.SOUTH);
		this.setBackground(Color.white);
		
		JLayeredPane pane = this.getLayeredPane();

		ImageIcon Icon=new ImageIcon("Immagini/owl_v2.png");
		Image image = Icon.getImage();
		Image newimg = image.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
	
		Icon = new ImageIcon(newimg);
		
		
		JLabel ll= new JLabel(Icon);
		ll.setBounds(20, 270, 500, 500);
		pane.add(ll, new Integer(1));
		
	}
	
	
	
}

