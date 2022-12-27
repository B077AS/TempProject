package Finder;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.*;
import Finder.Filter.FilterCheckBox;
import Finder.Filter.FilterRoomType;
import Finder.Filter.FilterSeatsNumber;

public class FinderMainPanel extends JPanel{

	private JScrollPane scroll;
	private LinkedList<FreeForBooking> freeRooms;
	private LinkedList<FreeForBooking> freeRoomsBackUp;
	private LinkedList<FilterCheckBox> allFilters;

	public FinderMainPanel(LinkedList<FreeForBooking> freeRooms, LinkedList<FreeForBooking> freeRoomsBackUp) {
		this.allFilters=new LinkedList<FilterCheckBox>();
		this.freeRooms=freeRooms;
		this.freeRoomsBackUp=freeRoomsBackUp;
		removeAll();
		String[] freeRoomsArray=new String[freeRooms.size()];
		for(int i=0; i<freeRooms.size(); i++) {
			FreeForBooking freeRoom=freeRooms.get(i);
			freeRoomsArray[i]=freeRoom.toString();
		}

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JList<String> list=new JList<String>(freeRoomsArray);
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
		FilterListener filterL= new FilterListener(this, this.allFilters);
		filterButton.addActionListener(filterL);
		c.gridx=4;
		c.gridy=0;
		add(filterButton, c);

		c.anchor = GridBagConstraints.CENTER;
		JButton bookButton=new JButton("Book");
		BookListener bookListen=new BookListener(list);
		bookButton.addActionListener(bookListen);
		c.gridx=0;
		c.gridy=1;
		add(bookButton, c);

		revalidate();
		repaint();
	}

	public LinkedList<FreeForBooking> getFreeRooms(){
		return this.freeRoomsBackUp;
	}

}

class BookListener implements ActionListener{

	private JList<String> scroll;

	public BookListener(JList<String> scroll) {
		this.scroll=scroll;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}

class FilterListener implements ActionListener{
	private FinderMainPanel panel;
	private LinkedList<FreeForBooking> freeRooms;
	private LinkedList<FreeForBooking> filteredRooms;
	private HashMap<String, LinkedList<FreeForBooking>> allFiltered;
	private LinkedList<FilterCheckBox> allFilters;
	public FilterListener(FinderMainPanel panel, LinkedList<FilterCheckBox> allFilters) {
		this.filteredRooms=new LinkedList<FreeForBooking>();
		this.allFiltered=new HashMap<String, LinkedList<FreeForBooking>>();
		this.panel=panel;
		this.allFilters=allFilters;
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

		for(HashMap.Entry<String, LinkedList<FreeForBooking>> list: this.allFiltered.entrySet()) {
			this.filteredRooms.addAll(list.getValue());
		}
		this.panel.removeAll();
		this.panel.revalidate();
		this.panel.repaint();


		this.panel.add(new FinderMainPanel(this.filteredRooms, this.panel.getFreeRooms()));

		this.panel.revalidate();
		this.panel.repaint();



	}
}

