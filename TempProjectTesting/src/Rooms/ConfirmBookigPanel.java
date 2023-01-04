package Rooms;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import MyTimer.DateHolder;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;

public class ConfirmBookigPanel extends JPanel{

	private JList<Booking> roomsList; 
	private List<Booking> possibleBookings;
	private JLabel countSeats;
	private boolean maxed;

	public ConfirmBookigPanel(JList<Booking> roomsList, Users user, UsersGUI frame) {
		this.possibleBookings=new ArrayList<Booking>();
		this.roomsList=roomsList;

		Booking selectedBooking =this.roomsList.getSelectedValue();
		for(int i = 0; i< this.roomsList.getModel().getSize();i++){
			if(this.roomsList.getModel().getElementAt(i).getRoom().getCode().equals(selectedBooking.getRoom().getCode())) {
				this.possibleBookings.add(this.roomsList.getModel().getElementAt(i));
			}
		}
		Rooms room=selectedBooking.getRoom();
		String[][] roomDetails=new String[1][5];
		String[] columnsNames=new String[]{"Room Code", "Max Capacity", "Equipped with LIM", "Equipped with Outlets", "Disabled-Friendly"};
		roomDetails[0][0]=room.getCode();
		roomDetails[0][1]=Integer.toString(room.getSeats());
		roomDetails[0][2]=Boolean.toString(room.isLIM());
		roomDetails[0][3]=Boolean.toString(room.isOutlets());
		roomDetails[0][4]=Boolean.toString(room.isDisabledAccess());

		setLayout (new BorderLayout());

		JPanel lowerPanel=new JPanel();
		lowerPanel.setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JTable table=new JTable(roomDetails, columnsNames);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setFocusable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultEditor(Object.class, null);

		JScrollPane fullTable=new JScrollPane(table);
		Dimension d = table.getPreferredSize();
		fullTable.setPreferredSize(new Dimension(d.width,table.getRowHeight()*(4)));
		fullTable.setBorder(BorderFactory.createEmptyBorder());
		add(fullTable, BorderLayout.NORTH);

		this.countSeats=new JLabel("Select to see Bookable Seats");
		c.gridx=1;
		c.gridy=2;
		lowerPanel.add(this.countSeats, c);
		JLabel timeSlot=new JLabel("Select your preferred Time-Slot: ");
		c.gridx=0;
		c.gridy=1;
		lowerPanel.add(timeSlot, c);
		JList<Booking> list=new JList(this.possibleBookings.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() ==1){
					try {
						Connection conn=DBConnection.connect();
						String query="select\r\n"
								+ "(select count(*) from allgroups where Group_ID in (select Group_ID from rooms_booking where rooms_booking.Date=? and Room=? and Start_Time=? and End_Time=?))\r\n"
								+ "+(select count(*) from solo_booking where solo_booking.Date=? and Room=? and Start_Time=? and End_Time=?) as sum";
						PreparedStatement preparedStmt = conn.prepareStatement(query);
						preparedStmt.setDate(1, Date.valueOf(DateHolder.getYear()+"-"+DateHolder.getMonth()+"-"+DateHolder.getDay()));
						preparedStmt.setString(2, list.getSelectedValue().getRoom().getCode());
						preparedStmt.setString(3, list.getSelectedValue().getStartTime());
						preparedStmt.setString(4, list.getSelectedValue().getEndTime());
						preparedStmt.setDate(5, Date.valueOf(DateHolder.getYear()+"-"+DateHolder.getMonth()+"-"+DateHolder.getDay()));
						preparedStmt.setString(6, list.getSelectedValue().getRoom().getCode());
						preparedStmt.setString(7, list.getSelectedValue().getStartTime());
						preparedStmt.setString(8, list.getSelectedValue().getEndTime());
						ResultSet result=preparedStmt.executeQuery();
						result.next();
						countSeats.setText("Bookable Seats: "+(list.getSelectedValue().getRoom().getOccupiedSeats()-Integer.parseInt(result.getString(1))));
						if(list.getSelectedValue().getRoom().getOccupiedSeats()-Integer.parseInt(result.getString(1))==0){
							maxed=true;
						}
						else {
							maxed=false;
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});		
		JScrollPane listScroller = new JScrollPane(list);
		c.gridx=1;
		c.gridy=1;
		lowerPanel.add(listScroller, c);
		JButton bookingButton=new JButton("Proceed with Booking");
		bookingButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(maxed==false) {
				frame.removePanel();
				frame.addSecondPanel(user.book(list.getSelectedValues(), frame));
				frame.revalidate();
				frame.repaint();
				}
				else {
					new ExceptionFrame("Room is FULL!");
				}
			}

		});
		c.gridx=2;
		c.gridy=1;
		lowerPanel.add(bookingButton, c);
		JButton backButton=new JButton("Back");
		c.gridx=3;
		c.gridy=1;
		lowerPanel.add(backButton, c);

		add(lowerPanel, BorderLayout.CENTER);

	}
}
