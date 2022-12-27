package Users.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Users.GeneralUser.UsersGUI;

public class UpdateSchedulePanel extends JPanel{

	public UpdateSchedulePanel(UsersGUI frame, JTable table) {
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel selectFile=new JLabel("Select File to Import: ");
		c.gridx=0;
		c.gridy=0;
		add(selectFile, c);
		JButton browseButton=new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	BrowseFrame browseFrame=new BrowseFrame(frame, table);
		    }
		});
		c.gridx=1;
		c.gridy=0;
		add(browseButton, c);
	}

}


class BrowseFrame extends JFrame{

	public BrowseFrame(UsersGUI frame, JTable table) {
		JFileChooser choose=new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
		choose.setFileFilter(filter);
		choose.setMultiSelectionEnabled(false);
		FileChooseListner chooseListener=new FileChooseListner(choose, this, frame, table);
		choose.addActionListener(chooseListener);
		add(choose);

		setSize(500,500);
		setTitle("Choose File");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
}

class FileChooseListner implements ActionListener{

	private JFileChooser choose;
	private JFrame thisFrame;
	private UsersGUI frame;
	private JTable table;

	public FileChooseListner(JFileChooser choose, JFrame thisFrame, UsersGUI frame, JTable table) {
		this.choose=choose;
		this.thisFrame=thisFrame;
		this.frame=frame;
		this.table=table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String path;
		if (e.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION)) {
			File selectedFile = this.choose.getSelectedFile();
			path=selectedFile.getAbsolutePath();
			this.frame.removePanel();
			this.frame.addSecondPanel(new ConfirmFilePanel(path, this.frame, this.table));
			this.frame.revalidate();
			this.frame.repaint();
			this.thisFrame.dispose();
		} else if (e.getActionCommand().equals(javax.swing.JFileChooser.CANCEL_SELECTION)) {
			this.thisFrame.dispose();
		}
	}

}

