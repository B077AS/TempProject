package Finder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;

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
		SwingUtilities.updateComponentTreeUI(frame);
		this.allFilters=new LinkedList<FilterCheckBox>();
		this.freeRooms=freeRooms;
		this.freeRoomsBackUp=freeRoomsBackUp;
		removeAll();

		setBackground(new Color(0,0,0,0));
		setLayout (new GridBagLayout());
	
		GridBagConstraints c=new GridBagConstraints();
	/*	
		c.anchor = GridBagConstraints.PAGE_START;
		JLabel notLabel= new JLabel("This is free room:");
		notLabel.setFont(new Font("Comic Sans MS", Font.BOLD,25));
		notLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets (0,0,100,0);
		add(notLabel,c);*/

		JList<Booking> list=new JList(freeRooms.toArray());
		list.setForeground(Color.black);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setBorder(new LineBorder(new Color(145,0,0),2));
		listScroller.setPreferredSize(new Dimension(250, 200));
		listScroller.setForeground(new Color(145,0,0));
		listScroller.setBackground(new Color(145,0,0));
		c.insets = new Insets(0,0,20,0);
		c.gridx=1;
		c.gridy=0;
		
		this.scroll=listScroller;
		add(listScroller, c);

		c.anchor = GridBagConstraints.CENTER;
		FilterSeatsNumber filterSeats100=new FilterSeatsNumber();
		filterSeats100.setLabel("100 seats");
		filterSeats100.setSeats(100);
		filterSeats100.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		filterSeats100.setForeground(new Color(145,0,0));
		this.allFilters.add(filterSeats100);
		c.gridx=0;
		c.gridy=1;
		add(filterSeats100, c);


		FilterSeatsNumber filterSeats50=new FilterSeatsNumber();
		filterSeats50.setLabel("50 Seats");
		filterSeats50.setSeats(50);
		this.allFilters.add(filterSeats50);
		filterSeats50.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		filterSeats50.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=1;
		add(filterSeats50, c);

		FilterRoomType filterLab=new FilterRoomType();
		filterLab.setLabel("Only Labs");
		filterLab.setIdentifier("LAB");
		this.allFilters.add(filterLab);
		filterLab.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		filterLab.setForeground(new Color(145,0,0));
		c.gridx=2;
		c.gridy=1;
		add(filterLab, c);


		c.anchor = GridBagConstraints.CENTER;
		JButton filterButton=new JButton("Filter");
		FilterListener filterL= new FilterListener(this, this.allFilters, frame, user);
		filterButton.addActionListener(filterL);
		filterButton.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		filterButton.setForeground(Color.white);
		filterButton.setBackground(new Color(145,0,0));
		filterButton.setOpaque(true);
		filterButton.setBorderPainted(false);
	
		c.gridx=3;
		c.gridy=1;
		c.insets = new Insets(0,20,15,0);
		add(filterButton, c);

		c.anchor = GridBagConstraints.CENTER;
		JButton bookButton=new JButton("Book");
		BookListener bookListen=new BookListener(list, frame, user);
		bookButton.addActionListener(bookListen);
		bookButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		bookButton.setForeground(Color.white);
		bookButton.setBackground(new Color(145,0,0));
		bookButton.setOpaque(true);
		bookButton.setBorderPainted(false);
	
		c.gridx=1;
		c.gridy=2;
		c.insets = new Insets(20,0,0,0);
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

