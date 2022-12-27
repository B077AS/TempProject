package Exceptions;

import javax.swing.*;

public class DuplicateAccountEx implements ExHandler{

	public DuplicateAccountEx() {
		ExceptionHandler("\u274C Email already registered");
	}
	@Override
	public void ExceptionHandler(String message) {
		ExceptionPanel ex=new ExceptionPanel(message);
	}

}
