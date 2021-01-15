package View;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Shell;

import controller.ControllerEquipaggiamento;
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
	         String sql = "CREATE TABLE IF NOT EXISTS ingrediente " + 
	        		  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
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
		        String sql = "CREATE TABLE IF NOT EXISTS ricetta " + 
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
			        String sql = "CREATE TABLE IF NOT EXISTS equipaggiamento " + 
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
				        String sql = "CREATE TABLE IF NOT EXISTS quantita " + 
				       		  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
				       		  "id_ricetta INTEGER NOT NULL, " +
				       		  "id_ingrediente INTEGER NOT NULL," +
				       		  "quantita_necessaria REAL NOT NULL," +
				       	      "FOREIGN KEY (id_ingrediente) "+
				       	      "REFERENCES ingrediente(id) "+
				       	      "ON DELETE NO ACTION "+
				       	      "ON UPDATE NO ACTION,"+
				              "FOREIGN KEY (id_ricetta) "+
				              "REFERENCES ricetta(id) "+
				              "ON DELETE NO ACTION "+
				              "ON UPDATE NO ACTION)";
				        stmt.executeUpdate(sql);
				        stmt.close();
				        c.close();
				     } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				     }
				     System.out.println("Table created successfully");

			     
		ControllerEquipaggiamento controllerEq = new ControllerEquipaggiamento();
		CreaEquipaggiamento finestraCreaEq = new CreaEquipaggiamento(controllerEq,pass);
		finestraCreaEq.open();
			    
	}
	
	public static void startApplication(String pass) {
		password = pass;
		
		     
		
		ControllerEquipaggiamento controllerEq = new ControllerEquipaggiamento();
		
		ControllerIngredienti control1 = new ControllerIngredienti();
		
		ControllerRicetta controlRic = new ControllerRicetta(control1,controllerEq);
		
		VisualizzazioneRicetta finestraVisRicetta = new VisualizzazioneRicetta(controlRic);
		finestraVisRicetta.open();

		
	}
	
	
}
		
	
	


