package Users.GeneralUser;

import java.awt.event.*;
import javax.swing.*;

public class YearComboBoxAL implements ActionListener{
	private String[] months;
	private JComboBox<String> monthsSelect;
	private JComboBox<String> yearSelect;
	public YearComboBoxAL(String[] months, JComboBox<String> monthsSelect, JComboBox<String> yearSelect) {
		this.months=months;
		this.monthsSelect=monthsSelect;
		this.yearSelect=yearSelect;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.yearSelect.removeItem("Year");
		DefaultComboBoxModel<String> df=new DefaultComboBoxModel<String>(months);
		monthsSelect.setModel(df);
	}

}