package Users.Professors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import MyLoader.RoomLoader;
import Notifications.AcceptRejectFrame;
import Notifications.Notification;
import Notifications.ProfNotificationDAO;
import Notifications.ProfSwapNotificationDAO;
import Notifications.ProfessorNotification;
import Notifications.ProfessorSwapDraft;
import Rooms.Rooms;
import Users.GeneralUser.Users;
import Users.GeneralUser.UserGUI;

public class ProfessorNotificationPanel extends JPanel{

	private JLabel notificationDetails;

	public ProfessorNotificationPanel(Users user, UserGUI frame) {
		user.getNotifications().clear();
		setLayout (new GridBagLayout());
		setBackground(Color.white);
		GridBagConstraints c=new GridBagConstraints();

		JPanel swapRequestsContainer=new JPanel();
		swapRequestsContainer.setLayout (new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();

		JPanel draftRequestsContainer=new JPanel();
		draftRequestsContainer.setLayout (new GridBagLayout());
		GridBagConstraints c2=new GridBagConstraints();


		JPanel reminderContainer=new JPanel();
		reminderContainer.setLayout (new GridBagLayout());
		GridBagConstraints c3=new GridBagConstraints();

		JTabbedPane tabbedPane = new JTabbedPane();
		//tabbedPane.setPreferredSize(new Dimension(500, 300));
		ProfNotificationDAO dao=new ProfNotificationDAO();
		ProfSwapNotificationDAO swapDao=new ProfSwapNotificationDAO();

			List<Notification> listNotify=dao.getNotifications(new ProfessorNotification(null, null,  user.getID(), null, null, null));
			for(int i=0; i<listNotify.size(); i++) {
				user.loadNotifications(listNotify.get(i));
			}


			List<Notification> listSwap=swapDao.getNotifications(new ProfessorSwapDraft(user.getID(), user.getID(), null, null, null, null, null));
			for(int i=0; i<listSwap.size(); i++) {
				user.loadNotifications(listSwap.get(i));
			}

		ArrayList profNotifications=new ArrayList();
		ArrayList swapNotAcceptedNotifications=new ArrayList();
		ArrayList swapAcceptedNotifications=new ArrayList();

		for(int i=0; i<user.getNotifications().size(); i++) {
			try {
				ProfessorNotification swap=(ProfessorNotification)user.getNotifications().get(i);

				profNotifications.add(swap);
			}
			catch(Exception selectType) {
				ProfessorSwapDraft draft=(ProfessorSwapDraft)user.getNotifications().get(i);
				if(draft.isAccepted()==false && !draft.getSender().equals(user.getID())) {
					swapNotAcceptedNotifications.add(draft);
				}
				else if(draft.isAccepted()==true){
					swapAcceptedNotifications.add(draft);
				}
			}
		}

		JList<ProfessorNotification> list=new JList(profNotifications.toArray());
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setBorder(new LineBorder(new Color(145,0,0),2));
		//listScroller.setPreferredSize(new Dimension(250, 200));
		listScroller.setForeground(new Color(145,0,0));
		listScroller.setBackground(new Color(145,0,0));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==1){
					if(list.getSelectedValue().getNewDate()==null) {
						notificationDetails.setText("Notification Deatils: Swap "+list.getSelectedValue().getDate());
					}
					else {
						notificationDetails.setText("Notification Deatils: Swap "+list.getSelectedValue().getDate()+" into "+list.getSelectedValue().getNewDate()+" between "+list.getSelectedValue().getNewTo()+"-"+list.getSelectedValue().getNewFrom());	
					}
				}
			}
		});
		c1.gridx=0;
		c1.gridy=0;
		swapRequestsContainer.add(listScroller, c1);

		c1.gridx=0;
		c1.gridy=1;
		notificationDetails=new JLabel("Notification Details");
		notificationDetails.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		notificationDetails.setForeground(new Color(145,0,0));
		swapRequestsContainer.add(this.notificationDetails, c1);



		JButton acceptSwap=new JButton("Accept");
		acceptSwap.setFont(new Font("Comic Sans MS", Font.PLAIN,12));
		acceptSwap.setForeground(Color.WHITE);
		acceptSwap.setBackground(new Color(145,0,0));
		acceptSwap.setOpaque(true);
		acceptSwap.setBorderPainted(false);
		acceptSwap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProfessorNotification notification=list.getSelectedValue();
				if(notification==null) {
					new ExceptionFrame("No Notification Selected!");
					return;
				}
				RoomChooser choose=new RoomChooser(notification, user, frame);
				frame.removePanel();
				frame.addSecondPanel(new ProfessorNotificationPanel(user, frame));
				frame.revalidate();
				frame.repaint();
			}

		});
		c1.gridx=1;
		c1.gridy=0;
		c1.insets = new Insets(0,10,40,0);
		swapRequestsContainer.add(acceptSwap, c1);

		JButton draft=new JButton("Send Swap-Draft");
		draft.setFont(new Font("Comic Sans MS", Font.PLAIN,12));
		draft.setForeground(Color.WHITE);
		draft.setBackground(new Color(145,0,0));
		draft.setOpaque(true);
		draft.setBorderPainted(false);
		draft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProfessorNotification notification=list.getSelectedValue();
				SendDraftFrame draftFrame=new SendDraftFrame(notification, user, frame);
				frame.removePanel();
				frame.addSecondPanel(new ProfessorNotificationPanel(user, frame));
				frame.revalidate();
				frame.repaint();
			}

		});
		c1.gridx=1;
		c1.gridy=0;
		c1.insets = new Insets(30,15,0,0);
		swapRequestsContainer.add(draft, c1);

		c.gridx=0;
		c.gridy=0;
		tabbedPane.addTab("Swap Requests", swapRequestsContainer);
		tabbedPane.setForeground((new Color(145,0,0)));
		tabbedPane.setBackground(Color.WHITE);
		add(tabbedPane, c);

		JList<ProfessorSwapDraft> listDraft=new JList(swapNotAcceptedNotifications.toArray());
		JScrollPane listDraftScroller = new JScrollPane(listDraft);
		listDraft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDraftScroller.setBorder(new LineBorder(new Color(145,0,0),2));
		//listScroller.setPreferredSize(new Dimension(250, 200));
		listDraftScroller.setForeground(new Color(145,0,0));
		listDraftScroller.setBackground(new Color(145,0,0));
		c2.gridx=0;
		c2.gridy=0;
		draftRequestsContainer.add(listDraftScroller);


		JButton acceptDraft=new JButton("Accept Draft");
		acceptDraft.setForeground(Color.WHITE);
		acceptDraft.setBackground(new Color(145,0,0));
		acceptDraft.setFont(new Font("Comic Sans MS", Font.PLAIN,12));
		acceptDraft.setOpaque(true);
		acceptDraft.setBorderPainted(false);
		acceptDraft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					ProfessorSwapDraft swap=listDraft.getSelectedValue();
					swap.setReceiver(user.getID());
					swapDao.acceptSwap(swap);
					swapDao.deleteSwap(swap);
					dao.deleteNotificationNoNewDate(new ProfessorNotification(swap.getFirstSchedule(), swap.getFirstDate(), swap.getReceiver(), null, null, null));
				} catch (Exception ea) {
					new ExceptionFrame("No Notification Selected");
					return;
				}
				new AcceptRejectFrame("Swap Request Accepted!", user, frame);
			}

		});
		c2.gridx=1;
		c2.gridy=0;
		c2.insets = new Insets(0,10,40,0);
		draftRequestsContainer.add(acceptDraft, c2);


		JButton reject=new JButton("Reject Draft");
		reject.setForeground(Color.WHITE);
		reject.setBackground(new Color(145,0,0));
		reject.setFont(new Font("Comic Sans MS", Font.PLAIN,12));
		reject.setOpaque(true);
		reject.setBorderPainted(false);
		
		reject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					ProfessorSwapDraft swap=listDraft.getSelectedValue();
					swapDao.deleteSwap(swap);

					frame.removePanel();
					frame.addSecondPanel(new ProfessorNotificationPanel(user, frame));
					frame.revalidate();
					frame.repaint();
				}

				catch (Exception ea) {
					ea.printStackTrace();
					new ExceptionFrame("No Notification Selected!");
					return;
				}

			}

		});
		c2.gridx=1;
		c2.gridy=0;
		c2.insets = new Insets(30,15,0,0);
		draftRequestsContainer.add(reject, c2);


		JList<ProfessorSwapDraft> listReminders=new JList(swapAcceptedNotifications.toArray());
		JScrollPane listRemindersScroller = new JScrollPane(listReminders);
		listReminders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listRemindersScroller.setBorder(new LineBorder(new Color(145,0,0),2));
		//listScroller.setPreferredSize(new Dimension(250, 200));
		listRemindersScroller.setForeground(new Color(145,0,0));
		listRemindersScroller.setBackground(new Color(145,0,0));
		c3.gridx=0;
		c3.gridy=0;
		reminderContainer.add(listRemindersScroller);


		tabbedPane.addTab("Draft Requests", draftRequestsContainer);
		tabbedPane.addTab("Reminders", reminderContainer);
	}
}

class SendDraftListener implements ActionListener{
	private JList<ProfessorNotification> list;

	public SendDraftListener(JList<ProfessorNotification> list) {
		this.list=list;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.list.getSelectedValue();

	}

}

