import java.sql.*;

public class TestDB {
	public static void main(String[] args) {
		String url="jdbc.mysql://localhost:3306/test";
		String username="root";
		String password="mypassword";
		String schema="test";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url, username, password);
			Statement st=conn.createStatement();
			
			ResultSet result=st.executeQuery("select * from gay");
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
