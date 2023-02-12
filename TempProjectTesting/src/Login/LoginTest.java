package Login;

import org.junit.Test;
import Users.GeneralUser.UsersDAO;
import Users.Students.Students;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoginTest {
	private final String emailOrID = "william.solari01@universitadipavia.it";
	private final char[] password = "password1".toCharArray();
	private final LoginGUI frame = new LoginGUI();

	@Test
	public void testLogin() {
		Login login = new Login(emailOrID, password, frame);
		login.login();

		UsersDAO dao = new UsersDAO();
		dao.selectUser(new Students(null, null, null, emailOrID, String.valueOf(password), null));
		//assertEquals(emailOrID, dao.getUser().getID());
		assertEquals(emailOrID, dao.getUser().getEmail());
		assertEquals("password1", dao.getUser().getPassword());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFieldCheckWithEmptyInput() {
		Login login = new Login("", new char[0], frame);
		login.fieldCheck();
		
		fail("Expected an IllegalArgumentException to be thrown");
	}
	
	@Test
	public void testFieldCheckWithValidInput() {
		Login login = new Login(emailOrID, password, frame);
		login.fieldCheck();
	}
}