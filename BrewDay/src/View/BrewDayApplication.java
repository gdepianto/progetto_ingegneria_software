package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import controller.ControllerIngredienti;
import controller.ControllerRicetta;
import model.Ingrediente;

public class BrewDayApplication {

	public static void main(String[] args) {
		Connection c = null;
	      Statement stmt = null;
	  	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:brewday.db");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE IF NOT EXISTS ingrediente" + 
	        		  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
	        		  "nome VARCHAR(45) NOT NULL, " +
	        		  "unitaMisura VARCHAR(45) NOT NULL, " +
	        		  "disponibilita FLOAT NOT NULL)";
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	      
	      try {
		        Class.forName("org.sqlite.JDBC");
		        c = DriverManager.getConnection("jdbc:sqlite:brewday.db");
		        System.out.println("Opened database successfully");
		
		        stmt = c.createStatement();
		        String sql = "CREATE TABLE IF NOT EXISTS ricetta" + 
		       		  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
		       		  "nome VARCHAR(45) NOT NULL, " +
		       		  "descrizione TEXT(1000) NOT NULL, " +
		       		  "tempo_preparazione INT NOT NULL)";
		        stmt.executeUpdate(sql);
		        stmt.close();
		        c.close();
		     } catch ( Exception e ) {
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		        System.exit(0);
		     }
		     System.out.println("Table created successfully");
		     
		      ControllerIngredienti control1 = new ControllerIngredienti();
		      Visualizzazione_Ingrediente finestra = new Visualizzazione_Ingrediente(control1);
		      finestra.open();
		      Aggiunta_Ingrediente finestraAgg = new Aggiunta_Ingrediente(control1);
		      finestraAgg.open();
	  }	
}
		
	
	


