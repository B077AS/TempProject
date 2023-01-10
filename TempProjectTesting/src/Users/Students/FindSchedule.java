package Users.Students;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import DataBase.DBConnection;
import Groups.GroupsPanel;
import MyTimer.MyTimer;
import Users.Admin.ViewCoursesPanel;
import Users.GeneralUser.*;

public class FindSchedule extends JPanel{
	
	private String subject;

	public FindSchedule() {
		
	
	}

	
	public String checkString(ResultSet s, int i) throws SQLException {
		
		if(s.getString(i)==null) {
			subject=" ";
		}
		else {
			subject=s.getString(i);
		}
		
		return subject;
		
	}
	
/*	
	public void table(ResultSet s, String sub, String room, int i) throws SQLException {
		
			
			for(int c=0; c<6;c++) {
				
				if(s.getString(2).equals(time[c])) {
					schedule[0][0]=result.getString(2)+" - "+result.getString(3);
					schedule[0][i]=sub+" - "+room;
			}
		
			if(s.getString(2).equals("9:00")) {
				schedule[0][0]=s.getString(2)+" - "+s.getString(3);
				schedule[0][i]=sub+" - "+room;
			}else if(s.getString(2).equals("10:00")) {
				
				schedule[1][0]=s.getString(2)+" - "+s.getString(3);
				schedule[1][i]=subject+" - "+room;
			}else if(s.getString(2).equals("11:00")) {
				
				schedule[2][0]=s.getString(2)+" - "+s.getString(3);
				schedule[2][i]=subject+" - "+room;
			}else if(s.getString(2).equals("12:00")) {
				
				schedule[3][0]=s.getString(2)+" - "+s.getString(3);
				schedule[3][i]=subject+" - "+room;
			}else if(s.getString(2).equals("13:00")) {
				
				schedule[4][0]=s.getString(2)+" - "+s.getString(3);
				schedule[4][i]=" ";
			}else if(s.getString(2).equals("14:00")) {
				
				schedule[5][0]=s.getString(2)+" - "+s.getString(3);
				schedule[5][i]=subject+" - "+room;
			}else if(s.getString(2).equals("15:00")) {
				
				schedule[6][0]=s.getString(2)+" - "+s.getString(3);
				schedule[6][i]=subject+" - "+room;
			}else if(s.getString(2).equals("16:00")) {
				
				schedule[7][0]=s.getString(2)+" - "+s.getString(3);
				schedule[7][i]=subject+" - "+room;
			}else if(s.getString(2).equals("17:00")) {
				
				schedule[8][0]=s.getString(2)+" - "+s.getString(3);
				schedule[8][i]=subject+" - "+room;
			}
			
		
			
		
	}
	
		*/	

}
