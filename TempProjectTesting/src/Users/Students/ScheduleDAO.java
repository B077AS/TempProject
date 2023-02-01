package Users.Students;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;

import DataBase.DBConnection;
import Users.Admin.ViewCoursesPanel;
import Users.GeneralUser.UserGUI;

public class ScheduleDAO {
	public ScheduleDAO() {
		
	}
	
	public void sDAO(String code, String[][] schedule) throws Exception {
		
		Connection conn=DBConnection.connect();

		String query="select * from schedule where Course_ID=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setString(1, code);
		ResultSet result=preparedStmt.executeQuery();
	
		while(result.next()) {
			String subject;
			subject= checkString(result, 4);
			String room;
			room= checkString(result, 5);
			switch(result.getString(6)) {

			case "MONDAY":
				reconstruct(result.getString(2), result.getString(3), subject, room, 1, schedule);
				break;

			case "TUESDAY":
				reconstruct(result.getString(2), result.getString(3), subject, room, 2, schedule);
				break;

			case "THURSDAY":
				reconstruct(result.getString(2), result.getString(3), subject, room, 3, schedule);
				break;

			case "WEDNESDAY":
				reconstruct(result.getString(2), result.getString(3), subject, room, 4, schedule);
				break;

			case "FRIDAY":
				reconstruct(result.getString(2), result.getString(3), subject, room, 5, schedule);
				break;
			}

		}

		conn.close();
		

}
	
	
public String checkString(ResultSet s, int i) throws Exception {
		
		String subject;
		if(s.getString(i)==null) {
			subject=" ";
		}
		else {
			subject=s.getString(i);
		}
		
		return subject;
		
	}


public void reconstruct(String start, String end, String subject, String room, int dayIdentifier, String[][] schedule) {
	if(start.equals("9:00")) {
		schedule[0][0]=start+" - "+end;
		schedule[0][dayIdentifier]=subject+" - "+room;
	}else if(start.equals("10:00")) {
		schedule[1][0]=start+" - "+end;
		schedule[1][dayIdentifier]=subject+" - "+room;
	}else if(start.equals("11:00")) {
		schedule[2][0]=start+" - "+end;
		schedule[2][dayIdentifier]=subject+" - "+room;
	}else if(start.equals("12:00")) {
		schedule[3][0]=start+" - "+end;
		schedule[3][dayIdentifier]=subject+" - "+room;
	}else if(start.equals("13:00")) {
		schedule[4][0]=start+" - "+end;
		schedule[4][dayIdentifier]=" ";
	}else if(start.equals("14:00")) {
		schedule[5][0]=start+" - "+end;
		schedule[5][dayIdentifier]=subject+" - "+room;
	}else if(start.equals("15:00")) {
		schedule[6][0]=start+" - "+end;
		schedule[6][dayIdentifier]=subject+" - "+room;
	}else if(start.equals("16:00")) {
		schedule[7][0]=start+" - "+end;
		schedule[7][dayIdentifier]=subject+" - "+room;
	}else if(start.equals("17:00")) {
		schedule[8][0]=start+" - "+end;
		schedule[8][dayIdentifier]=subject+" - "+room;
	}
}


}
