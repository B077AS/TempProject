package Finder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import Finder.Filter.FilterCheckBox;
import Finder.Filter.FilterRoomType;
import Finder.Filter.FilterSeatsNumber;
import Rooms.Booking;
import Rooms.ConfirmBookigPanel;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;
import Users.Students.StudentsGUI;


public class FinderMainPanel extends JPanel{

	private JScrollPane scroll;
	private List<Booking> freeRooms;
	private List<Booking> freeRoomsBackUp;
	private LinkedList<FilterCheckBox> allFilters;

	public FinderMainPanel(List<Booking> freeRooms, List<Booking> freeRoomsBackUp, UsersGUI frame, Users user) {
		this.allFilters=new LinkedList<FilterCheckBox>();
		this.freeRooms=freeRooms;
		this.freeRoomsBackUp=freeRoomsBackUp;
		removeAll();

		setLayout (new GridBagLayout());
	
		GridBagConstraints c=new GridBagConstraints();

		JList<Booking> list=new JList(freeRooms.toArray());
		JScrollPane listScroller = new JScrollPane(list);
		c.gridx=0;
		c.gridy=0;
		this.scroll=listScroller;
		add(listScroller, c);

		c.anchor = GridBagConstraints.NORTH;
		FilterSeatsNumber filterSeats100=new FilterSeatsNumber();
		filterSeats100.setLabel("100 seats");
		filterSeats100.setSeats(100);
		this.allFilters.add(filterSeats100);
		c.gridx=1;
		c.gridy=0;
		add(filterSeats100, c);


		FilterSeatsNumber filterSeats50=new FilterSeatsNumber();
		filterSeats50.setLabel("50 Seats");
		filterSeats50.setSeats(50);
		this.allFilters.add(filterSeats50);
		c.gridx=2;
		c.gridy=0;
		add(filterSeats50, c);

		FilterRoomType filterLab=new FilterRoomType();
		filterLab.setLabel("Only Labs");
		filterLab.setIdentifier("LAB");
		this.allFilters.add(filterLab);
		c.gridx=3;
		c.gridy=0;
		add(filterLab, c);


		c.anchor = GridBagConstraints.NORTH;
		JButton filterButton=new JButton("Filter");
		FilterListener filterL= new FilterListener(this, this.allFilters, frame, user);
		filterButton.addActionListener(filterL);
		c.gridx=4;
		c.gridy=0;
		add(filterButton, c);

		c.anchor = GridBagConstraints.CENTER;
		JButton bookButton=new JButton("Book");
		BookListener bookListen=new BookListener(list, frame, user);
		bookButton.addActionListener(bookListen);
		c.gridx=0;
		c.gridy=1;
		add(bookButton, c);

		revalidate();
		repaint();
	}

	public List<Booking> getFreeRooms(){
		return this.freeRoomsBackUp;
	}

}

class BookListener implements ActionListener{

	private JList<Booking> scroll;
	private UsersGUI frame;
	private Users user;


	public BookListener(JList<Booking> list, UsersGUI frame, Users user) {
		this.scroll=list;
		this.frame=frame;
		this.user=user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.removePanel();
		this.frame.addSecondPanel(new ConfirmBookigPanel(this.scroll, user, frame));
		this.frame.revalidate();
		this.frame.repaint();
	}

}

class FilterListener implements ActionListener{
	private FinderMainPanel panel;
	private List<Booking> freeRooms;
	private List<Booking> filteredRooms;
	private HashMap<String, List<Booking>> allFiltered;
	private LinkedList<FilterCheckBox> allFilters;
	private UsersGUI frame;
	private Users user;
	public FilterListener(FinderMainPanel panel, LinkedList<FilterCheckBox> allFilters, UsersGUI frame, Users user) {
		this.filteredRooms=new ArrayList<Booking>();
		this.allFiltered=new HashMap<String, List<Booking>>();
		this.panel=panel;
		this.allFilters=allFilters;
		this.frame=frame;
		this.user=user;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.freeRooms=this.panel.getFreeRooms();
		this.allFiltered.clear();
		
		for(FilterCheckBox singleFilter: allFilters) {
			if(singleFilter.isSelected()==true) {
			this.allFiltered.put(singleFilter.getLabel(), singleFilter.filter(freeRooms, singleFilter));
			}
		}

		for(HashMap.Entry<String, List<Booking>> list: this.allFiltered.entrySet()) {
			this.filteredRooms.addAll(list.getValue());
		}
		this.panel.removeAll();
		this.panel.revalidate();
		this.panel.repaint();


		this.panel.add(new FinderMainPanel(this.filteredRooms, this.panel.getFreeRooms(), this.frame, this.user));

		this.panel.revalidate();
		this.panel.repaint();



	}
}

