package Email;

import java.util.Random;

public class GenerateRandom {//codice di verifica lo genero con un random tra 1000 e 9999
	private int number;
	
	public GenerateRandom() {
		Random r=new Random();
		this.number=r.nextInt(1000,9999);
		
	}
	
	public int getRandom() {
		return this.number;
	}

}