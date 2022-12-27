package Exceptions;

public class NameGroupTakenEX implements ExHandler{
	
	public NameGroupTakenEX() {
		ExceptionHandler("A Group with the same Name already exists!");
	}

	@Override
	public void ExceptionHandler(String message) {
		ExceptionPanel ex=new ExceptionPanel(message);
	}

}
