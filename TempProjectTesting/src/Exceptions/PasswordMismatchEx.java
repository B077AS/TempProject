package Exceptions;

public class PasswordMismatchEx implements ExHandler{
	
	public PasswordMismatchEx() {
		ExceptionHandler("\u274C Password Mismatch");
	}
	@Override
	public void ExceptionHandler(String message) {
		ExceptionPanel ex=new ExceptionPanel(message);
	}

}
