package view;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import controller.ControllerEquipaggiamento;
import controller.ControllerIngredienti;
import controller.ControllerNota;
import controller.ControllerRicetta;
import controller.SecurityController;

public class BrewDayApplication {
	public static String password;
	public static String dbName = "brewday.db";
	static String table = "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ";
	
	
	
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
	
	public static void initializeDB(String pass,String dbName) {
		Connection c = null;
	     Statement stmt = null;
		 try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
	                 pass);
	         
	         stmt = c.createStatement();
	         String sql = "CREATE TABLE IF NOT EXISTS ingrediente " + 
	        		  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
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
	      
	      try {
		        Class.forName("org.sqlite.JDBC");
		        c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
		                 BrewDayApplication.password);
		
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

		     
		     try {

			        Class.forName("org.sqlite.JDBC");
			        c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
			                 pass);
			
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
			     
			     
			     try {
				        Class.forName("org.sqlite.JDBC");
				        c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
				                 pass);
				
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
			     
			     
			     try {
				        Class.forName("org.sqlite.JDBC");
				        c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
				                 BrewDayApplication.password);
				
				        stmt = c.createStatement();
				        String sql = "CREATE TABLE IF NOT EXISTS lotto ("+
				        		  "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
				        		  "commento TEXT(2000) NOT NULL, " +
				        		  "data DATE NOT NULL, "+
				        		  "valutazione INT NULL, "+
				        		  "ricetta_id INT NOT NULL, "+ 
				        		  "quantitaProdotta FLOAT NOT NULL, "+
				        		  "nomeEquipaggiamento VARCHAR NOT NULL, "+
				        		  "capacitaEquipaggiamento FLOAT NOT NULL, "+
				        		  "FOREIGN KEY (ricetta_id) "+
				        		     "REFERENCES ricetta (id) "+
				        		     "ON DELETE NO ACTION "+
				        		     "ON UPDATE NO ACTION, "+
				        		  "FOREIGN KEY (nomeEquipaggiamento) "+
				        		     "REFERENCES equipaggiamento (nome) "+
				        		     "ON DELETE NO ACTION "+
				        		     "ON UPDATE NO ACTION, "+
				        		  "FOREIGN KEY (capacitaEquipaggiamento) "+
				        		     "REFERENCES equipaggiamento (capacita) "+
				        		     "ON DELETE NO ACTION "+
				        		     "ON UPDATE NO ACTION) ";
				        stmt.executeUpdate(sql);
				        stmt.close();
				        c.close();
				        System.out.println("Tabello lotto creata");
				     } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				     }
			     
				     

	}
	
	public static void initialize(String pass) {
		password = pass;
		initializeDB(pass,dbName); 
			     
		ControllerEquipaggiamento controllerEq = new ControllerEquipaggiamento(pass,dbName);
		CreaEquipaggiamento finestraCreaEq = new CreaEquipaggiamento(controllerEq);
		finestraCreaEq.open();
			    
	}
	
	public static void startApplication(String pass) {
		password = pass;
		
		     
		
		ControllerEquipaggiamento controllerEq = new ControllerEquipaggiamento(password,dbName);
		
		ControllerIngredienti control1 = new ControllerIngredienti(password,dbName);
		
		ControllerNota controlNota = new ControllerNota();
		
		ControllerRicetta controlRic = new ControllerRicetta(control1,controllerEq,controlNota,password,dbName);
		
		VisualizzazioneRicetta finestraVisRicetta = new VisualizzazioneRicetta(controlRic);
		finestraVisRicetta.open();

		
	}
	
	
}
		
	
	


