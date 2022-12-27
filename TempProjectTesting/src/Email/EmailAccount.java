package Email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EmailAccount {//le credenziali dell'account email che passo all'emailBuilder le tengo salvate in un file
							//questa classe la uso per prelevare le credenziali dal file
	
	private String email;
	private String password;
	
	public EmailAccount(String path) throws FileNotFoundException, IOException {
		
		File f=new File(path);
		FileReader r=new FileReader(f);
		BufferedReader b=new BufferedReader(r);
		String[] details;
		String account;
		account=b.readLine();
		details=account.split(",");
		
		this.email=details[0];
		this.password=details[1];
		
		r.close();
	}

	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}


}