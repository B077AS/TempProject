package Exceptions;

public class VerificationCodeEx implements ExHandler{


		public VerificationCodeEx() {
			ExceptionHandler("\u274C Wrong Code");
			
		}
	@Override
	public void ExceptionHandler(String message) {
		ExceptionFrame ex=new ExceptionFrame(message);
		
	}

}
