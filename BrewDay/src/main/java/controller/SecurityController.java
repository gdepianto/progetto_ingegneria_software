package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import security_layer.PasswordUtils;

public class SecurityController {

		public void savePassword(String password) {
			String salt = PasswordUtils.getSalt(30);
			String securePassword = PasswordUtils.generateSecurePassword(password, salt);
			
			String saveString =  securePassword.substring(0, 2) + salt + securePassword.substring(2);
			FileWriter myWriter = null;
		    try {
		    	myWriter = new FileWriter("security.bd");
				myWriter.write(saveString);
				
			} catch (Exception e) {
			}
		    finally {
		    	try {
		    		if(myWriter != null)
		    			myWriter.close();
				} catch (IOException e) {
				}
		    }
		    
		}
		
		public boolean verifyPassword(String password) {
			String storedHash= "";
			
			try {
		      File myObj = new File("security.bd");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        storedHash += data;
		        
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		    }
			
			String storedPassword = storedHash.substring(0,2)+storedHash.substring(32);
			String storedSalt = storedHash.substring(2,32);
			
			return PasswordUtils.verifyUserPassword(password, storedPassword, storedSalt);
		}
		
	
}
