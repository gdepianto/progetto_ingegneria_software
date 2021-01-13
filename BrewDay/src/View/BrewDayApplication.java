package View;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Shell;

import controller.ControllerIngredienti;
import controller.ControllerRicetta;
import controller.SecurityController;
import model.Ingrediente;

public class BrewDayApplication {
	public static String password;
	
	
	public static void main(String[] args) {
		
	      SecurityController securityController = new SecurityController();
	      File f = new File("security.bd");
	      if(!f.exists() && !f.isDirectory()) { 
	          CreaPassword finestraCreaPassword = new CreaPassword(securityController);
	          finestraCreaPassword.open();
	      }
	      else {
	    	  Accedi finestraAccedi = new Accedi(securityController);
	    	  finestraAccedi.open();
	      }
	  	      
	     
	  }	
	
	public static void initialize(String pass) {
		 password = pass;
		 Connection c = null;
	     Statement stmt = null;
		 try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
	                 BrewDayApplication.password);
	         System.out.println("Opened database successfully");
	         
	         stmt = c.createStatement();
	         String sql = "CREATE TABLE IF NOT EXISTS ingrediente" + 
	        		  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
	        		  "nome VARCHAR(45) UNIQUE NOT NULL, " +
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
		        c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
		                 BrewDayApplication.password);
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
		     
		     try {
			        Class.forName("org.sqlite.JDBC");
			        c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
			                 BrewDayApplication.password);
			        System.out.println("Opened database successfully");
			
			        stmt = c.createStatement();
			        String sql = "CREATE TABLE IF NOT EXISTS equipaggiamento" + 
			       		  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
			       		  "nome VARCHAR(45) NOT NULL, " +
			       		  "capacita FLOAT NOT NULL)";
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
				        c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
				                 BrewDayApplication.password);
				        System.out.println("Opened database successfully");
				
				        stmt = c.createStatement();
				        String sql = "CREATE TABLE IF NOT EXISTS quantita" + 
				       		  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
				       		  "ricetta NOT NULL, " +
				       		  "ingrediente NOT NULL)" +
				       		  "INDEX fk_ingrediente_has_ricetta_ricetta1_idx (ricetta_id ASC) VISIBLE," +
				       	      "INDEX fk_ingrediente_has_ricetta_ingrediente_idx (ingrediente_id ASC) VISIBLE,"+
				       	      "CONSTRAINT fk_ingrediente_has_ricetta_ingrediente"+
				       	      "FOREIGN KEY (ingrediente)"+
				       	      "REFERENCES brewDay.ingrediente (id)"+
				       	      "ON DELETE NO ACTION"+
				       	      "ON UPDATE NO ACTION"+
				       	      "CONSTRAINT fk_ingrediente_has_ricetta_ricetta1"+
				              "FOREIGN KEY ricetta)"+
				              "REFERENCES brewDay.ricetta (id)"+
				              "ON DELETE NO ACTION"+
				              "ON UPDATE NO ACTION)";
				        stmt.executeUpdate(sql);
				        stmt.close();
				        c.close();
				     } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				     }
				     System.out.println("Table created successfully");
			     
			     
			     startApplication(pass);
	}
	
	public static void startApplication(String pass) {
		password = pass;
		
		     
		ControllerIngredienti control1 = new ControllerIngredienti();
		Visualizzazione_Ingrediente finestra = new Visualizzazione_Ingrediente(control1);
		finestra.open();
		
	}
	
	
}
		
	
	


