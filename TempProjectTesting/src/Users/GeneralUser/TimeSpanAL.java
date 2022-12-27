package Users.GeneralUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class TimeSpanAL implements ActionListener{

	private JComboBox<String> startTimeBox;
	private JComboBox<String> endTimeBox;
	public TimeSpanAL(JComboBox<String> startTimeBox, JComboBox<String> endTimeBox) {
		this.startTimeBox=startTimeBox;
		this.endTimeBox=endTimeBox;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.startTimeBox.removeItem("From");
		String timeString=(String)this.startTimeBox.getSelectedItem();
		String[] temp=timeString.split(":");
		int timeFrom=Integer.parseInt(temp[0]);
		int[] timeTo;
		String[] timeEnd;
		int count=0;
		if(timeFrom<13) {
			timeTo=new int[(18-timeFrom-1)];
		}
		else {
			timeTo=new int[(18-timeFrom)];
		}
		for(int i=timeFrom; i<18; i++) {
			if(i!=13) {
				timeTo[count]=i;
				count++;
			}
		}
		timeEnd=new String[timeTo.length];
		for(int i=0; i<timeTo.length; i++) {
			timeEnd[i]=(Integer.toString(timeTo[i]+1)+":00");
		}
		DefaultComboBoxModel<String> df=new DefaultComboBoxModel<String>(timeEnd);
		endTimeBox.setModel(df);
	}
	
}