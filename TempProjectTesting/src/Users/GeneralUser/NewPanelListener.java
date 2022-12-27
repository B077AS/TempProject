package Users.GeneralUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import Users.Admin.AddRoomPanel;

public class NewPanelListener implements ActionListener{
	
	private UsersGUI frame;
	private JPanel panel;
	
	public NewPanelListener(UsersGUI frame, JPanel panel) {
		this.frame=frame;
		this.panel=panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.removePanel();
		this.frame.addSecondPanel(this.panel);
		this.frame.revalidate();
		this.frame.repaint();
		
	}

}
