package Courses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;

public class CourseDAO {


	public void insertCourse(Course course) {


		Connection conn=DBConnection.connect();
		try {
			String query="SELECT * FROM courses WHERE Course_ID=?";
			PreparedStatement check=conn.prepareStatement(query);
			check.setString(1, course.getID());

			ResultSet checkResult=check.executeQuery();

			if(checkResult.next()==false) {

				query="insert into courses (Course_ID, Name, Year)"+" values (?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, course.getID());
				preparedStmt.setString(2, course.getFullName());
				preparedStmt.setInt(3, course.getYear());


				preparedStmt.execute();
				conn.close();
			}
			else {
				new ExceptionFrame("A Course with the same Code is already present!");
				conn.close();
				return;
			}
		} catch (SQLException e1) {
			System.out.println("errore query");
			e1.printStackTrace();
		}
	}


}
