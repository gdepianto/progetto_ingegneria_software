package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BrewDayApplication {

	public static void main(String[] args) {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.xerial.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:brewday.db");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE IF NOT EXISTS brewDay.ingrediente" + 
	        		  "(id INT NOT NULL," +
	        		  "nome VARCHAR(45) NOT NULL, " +
	        		  "disponibilita FLOAT NOT NULL," +
	        		  "PRIMARY KEY (id))";
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
		
	}

}
