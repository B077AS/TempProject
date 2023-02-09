package Users.Students;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import Exceptions.ExceptionFrame;
import Rooms.Booking;
import Rooms.RoomsBookingDAO;
import Rooms.SoloBookingDAO;
import Users.GeneralUser.UserGUI;
import Users.LabManager.LabBookingDAO;

public class BookingsPanel extends JPanel{


	public BookingsPanel(UserGUI mainGUI) {
		setLayout (new GridBagLayout());
		setBackground(Color.white);
		GridBagConstraints c=new GridBagConstraints();

		JPanel soloPanel=new JPanel();
		JPanel groupPanel=new JPanel();

		JTabbedPane tabbedPane = new JTabbedPane();


		SoloBookingDAO soloDAO=new SoloBookingDAO();
		ArrayList<Booking> bookings=soloDAO.selectBookingsFromUser(mainGUI.getUser());

		JList<Booking> soloList=new JList(bookings.toArray());
		soloList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(soloList);
		scroll.setBorder(new LineBorder(new Color(145,0,0),2));
		soloPanel.add(scroll);

		JButton cancelBooking=new JButton("Cancel");
		cancelBooking.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		cancelBooking.setForeground(Color.white);
		cancelBooking.setBackground(new Color(145,0,0));
		cancelBooking.setOpaque(true);
		cancelBooking.setBorderPainted(false);
		cancelBooking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					soloDAO.deleteBooking(soloList.getSelectedValue());
				}catch(Exception ex) {
					new ExceptionFrame("No Booking Selected");
					return;
				}
				mainGUI.removePanel();
				mainGUI.addSecondPanel(new BookingsPanel(mainGUI));
				mainGUI.revalidate();
				mainGUI.repaint();
				new ExceptionFrame("Booking Cancelled Succesfully");
			}

		});
		soloPanel.add(cancelBooking);


		RoomsBookingDAO groupsDAO=new RoomsBookingDAO();
		ArrayList<Booking> groupBookings=groupsDAO.selectBookingsFromUser(mainGUI.getUser());
		
		LabBookingDAO labDAO=new LabBookingDAO();
		ArrayList<Booking> labBookings=labDAO.selectBookingsFromUser(mainGUI.getUser());
		
		groupBookings.addAll(labBookings);
		
		JList<Booking> groupsList=new JList(groupBookings.toArray());
		soloList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollGroups = new JScrollPane(groupsList);
		scrollGroups.setBorder(new LineBorder(new Color(145,0,0),2));
		groupPanel.add(scrollGroups);

		JButton cancelGroupBooking=new JButton("Cancel");
		cancelGroupBooking.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		cancelGroupBooking.setForeground(Color.white);
		cancelGroupBooking.setBackground(new Color(145,0,0));
		cancelGroupBooking.setOpaque(true);
		cancelGroupBooking.setBorderPainted(false);
		cancelGroupBooking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(groupsList.getSelectedValue().getPeopleID().split("-")[1].equals(mainGUI.getUser().getID())){

					}else {
						throw new IllegalArgumentException();
					}
				}catch(IllegalArgumentException ex1) {
					new ExceptionFrame("You are not the Admin of this Group");
					return;
				}catch(NullPointerException ex2) {
					new ExceptionFrame("No Booking Selected");
					return;
				}
				groupsDAO.deleteBooking(groupsList.getSelectedValue());
				labDAO.deleteBooking(groupsList.getSelectedValue());
				mainGUI.removePanel();
				mainGUI.addSecondPanel(new BookingsPanel(mainGUI));
				mainGUI.revalidate();
				mainGUI.repaint();
				new ExceptionFrame("Booking Cancelled Succesfully");
			}

		});
		groupPanel.add(cancelGroupBooking);



		tabbedPane.addTab("Solo Bookings", soloPanel);
		tabbedPane.addTab("Group Bookings", groupPanel);
		add(tabbedPane);

	}

}
