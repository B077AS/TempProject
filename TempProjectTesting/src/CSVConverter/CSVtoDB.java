package CSVConverter;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;

import DataBase.DBConnection;

public class CSVtoDB {

	public CSVtoDB(String code, String path) {
		try {
			converter(code, path);
		} catch (IOException e) {
			System.out.println("errore");
		}
	}

	public void converter(String code, String path) throws IOException {
		File f = new File(path);
		FileReader reader=new FileReader(f);
		BufferedReader br=new BufferedReader(reader);
		String buff="";
		LinkedList<String> list=new LinkedList<String>();
		int n=0;
		while((buff=br.readLine())!=null) {
			list.add(n, buff);
			n++;
		}
		br.close();
		daySplitter(list, n, "MONDAY", 1, code);
		daySplitter(list, n, "TUESDAY", 2, code);
		daySplitter(list, n, "WEDNESDAY", 3, code);
		daySplitter(list, n, "THURSDAY", 4, code);
		daySplitter(list, n, "FRIDAY", 5, code);
	}


	public void daySplitter(LinkedList<String> list, int n, String dayOfWeek, int dayOfWeekIdentifier, String code) throws IOException {
		String line;
		String[] temp;
		String[] temp2;
		String[] temp3;

		String query;

		String start="";
		String end="";
		String subject="";
		String room="";
		String day="";
		String courseID="";
		String scheduleID="";

		try {

			Connection conn=DBConnection.connect();
			for(int i=1; i<n; i++) {

				line=list.get(i);
				temp=line.split("-");
				if(temp[0].equals("13")){

				}
				else {
					start=temp[0];
					temp2=temp[1].split(",");
					end=temp2[0];
					temp=line.split(",");
					if(temp[0].equals("13:00-14:00")) {
						temp=new String[6];
						temp[1]="";
						subject=null;
						room=null;
						day=dayOfWeek;
						courseID=code;
					}
					else {
						if(temp.length==1 || temp.length==dayOfWeekIdentifier || temp.length==4 || temp.length==3 || temp.length==2 || temp[dayOfWeekIdentifier].equals("")) {//cambio
							subject=null;
							room=null;
							day=dayOfWeek;
							courseID=code;
						}
						else {

							temp2=temp[dayOfWeekIdentifier].split("-");//cambio
							char[] ctemp=new char[temp2[0].length()-2];
							for(int t=0; t<(ctemp.length); t++) {
								ctemp[t]=temp2[0].charAt(t);
							}
							temp2[0]=String.valueOf(ctemp);
							subject=temp2[0];
						}
						temp=line.split(",");
						if(temp.length==1 || temp.length==dayOfWeekIdentifier || temp.length==4 || temp.length==3 || temp.length==2 || temp[dayOfWeekIdentifier].equals("")) {//cambio
						}
						else {
							temp2=line.split(",");
							temp3=temp2[dayOfWeekIdentifier].split("- ");//cambio
							temp=temp3[1].split(" ");
							if(temp[0].equals("")) {
								temp=temp3[1].split(" ");
								room=temp[2];
								day=dayOfWeek;
								courseID=code;
							}
							else {
								room=temp[0];
								day=dayOfWeek;
								courseID=code;
							}
						}
					}
				}
				if(room!=null) {
					scheduleID=start.split(":")[0]+"-"+end.split(":")[0]+"-"+day.charAt(0)+day.charAt(1)+"-"+room+"-"+courseID;
				}
				else {
					scheduleID=start.split(":")[0]+"-"+end.split(":")[0]+"-"+day.charAt(0)+day.charAt(1)+"-"+courseID;
				}
				query="insert into schedule (Schedule_ID, Start_Time, End_Time, Subject, Room, Day_Of_Week, Course_ID)"+" values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, scheduleID);
				preparedStmt.setString(2, start);
				preparedStmt.setString(3, end);
				preparedStmt.setString(4, subject);
				preparedStmt.setString(5, room);
				preparedStmt.setString(6, day);
				preparedStmt.setString(7, courseID);

				preparedStmt.execute();
			}
			conn.close();

		} catch (Exception e1) {
			System.out.println("errore query");
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conn=DBConnection.connect();
		try {
			String query="delete from schedule where Course_ID='E-ENG-SCS2'";
			Statement statement=conn.prepareStatement(query);
			statement.execute(query);
			conn.close();
			
		}catch (Exception e1) {
			System.out.println("errore query");
			e1.printStackTrace();
		}
	
	}
}