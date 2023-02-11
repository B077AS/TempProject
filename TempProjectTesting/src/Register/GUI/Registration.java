package Register.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import DataBase.DBConnection;
import ENUM.UserType;
import Email.EmailTemplate;
import Users.GeneralUser.UsersDAO;
import Users.Students.Students;

public class Registration {


	private String name;
	private String lastName;
	private String idNumber;
	private String email;
	private String emailTermination;
	private char[] password;
	private char[] confirmPassword;
	private String userType;
	private String emailString;
	private String passwordString;


	public Registration(String name, String lastName, String idNumber, String email, String emailTermination, char[] password, char[] confirmPassword, String userType) {
		this.name=name;
		this.lastName=lastName;
		this.idNumber=idNumber;
		this.email=email;
		this.emailTermination=emailTermination;
		this.password=password;
		this.confirmPassword=confirmPassword;
		this.userType=userType;
		this.emailString=email+emailTermination;
		this.passwordString=new String(password);
	}

	public void register() {
		UsersDAO dao=new UsersDAO();
		dao.insertUser(new Students(name, lastName, idNumber, emailString, passwordString, userType));
	}

	public void namesCheck() throws IllegalArgumentException{
		Pattern p = Pattern.compile("((?=.*\\d)).{1,}");
		Matcher m = p.matcher(name);
		Matcher m1 = p.matcher(lastName);
		if(m.matches()==true || m1.matches()==true || name.isEmpty()==true || lastName.isEmpty()==true) {
			throw new IllegalArgumentException();
		}
	}

	public void emailCheck() throws IllegalArgumentException {
		if(email.isEmpty()==true) {
			throw new IllegalArgumentException();
		}

		for(int i=0; i<email.length(); i++) {
			if(email.charAt(i)=='@') {
				throw new IllegalArgumentException();
			}
		}
	}


	public void passwordCheck() throws IllegalArgumentException {
		if(password.length!=confirmPassword.length) {
			throw new IllegalArgumentException();
		}

		for(int i=0; i<password.length; i++) {
			if(password[i]!=(confirmPassword[i])) {
				throw new IllegalArgumentException();
			}
		}

		Pattern p = Pattern.compile("((?=.*\\d)).{6,}");
		Matcher m = p.matcher(passwordString);
		if(m.matches()==false || passwordString.isEmpty()==true) {
			throw new IllegalArgumentException();
		}

	}

	public void checkDuplicate() throws Exception{
		Connection conn=DBConnection.connect();

		String query="select * from users where User_Code=? or Email=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, idNumber);
		preparedStmt.setString(2, emailString);
		ResultSet result=preparedStmt.executeQuery();
		
		if(result.next()==true) {
			throw new IllegalArgumentException();
		}
	}

	public void emailVerification(int number){
		try {
			EmailTemplate eTemp=new EmailTemplate(this.emailString, "Verify your Account", "Verification Code: "+number);
			eTemp.start();
		} catch (Exception e) {
			return;
		}
	}
	
	public void TypeCheck() throws IllegalArgumentException{
		if(emailTermination=="@universitadipavia.it" && !userType.equals(UserType.STUDENT.toString())) {
			throw new IllegalArgumentException();
		}
		
		if(emailTermination=="@unipv.it" && userType.equals(UserType.STUDENT.toString())) {
			throw new IllegalArgumentException();
		}
	}
}

