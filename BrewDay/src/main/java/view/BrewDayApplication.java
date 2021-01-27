package view;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import controller.ControllerEquipaggiamento;
import controller.ControllerIngredienti;
import controller.ControllerLotto;
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
	     String sqlIng = "CREATE TABLE IF NOT EXISTS ingrediente " + 
       		  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
       		  "nome VARCHAR(45) UNIQUE NOT NULL, " +
       		  "unitaMisura VARCHAR(45) NOT NULL, " +
       		  "disponibilita FLOAT NOT NULL)";
	     String sqlRic = "CREATE TABLE IF NOT EXISTS ricetta " + 
	        	  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
	       		  "nome VARCHAR(45) NOT NULL, " +
	       		  "descrizione TEXT(1000) NOT NULL, " +
	       		  "tempo_preparazione INT NOT NULL)";
	     String sqlEquip = "CREATE TABLE IF NOT EXISTS equipaggiamento " + 
	        	  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
	       		  "nome VARCHAR(45) NOT NULL, " +
	       		  "capacita FLOAT NOT NULL)";
	     String sqlQuant = "CREATE TABLE IF NOT EXISTS quantita " + 
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
	     String sqlLot = "CREATE TABLE IF NOT EXISTS lotto ("+
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
       		     "ON UPDATE NO ACTION )";
	     
		 try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
	                 pass);
	         
	         stmt = c.createStatement();
	         stmt.executeUpdate(sqlIng);
	         stmt.executeUpdate(sqlRic);
	         stmt.executeUpdate(sqlEquip);
	         stmt.executeUpdate(sqlQuant);
	         stmt.executeUpdate(sqlLot);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      
	     

	}
	
	public static void resetDB(String pass, String dbName) {
		Connection c = null;
	    
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
	    		   pass);
	       c.setAutoCommit(false);
	       String sql = "DELETE FROM ingrediente";
	       PreparedStatement pstmt = c.prepareStatement( sql );
	         
	         
	       pstmt.executeUpdate();
	         
	     
	       pstmt.close();
	       c.commit();
	       sql = "DELETE FROM lotto";
	       pstmt = c.prepareStatement( sql );
	     

	       pstmt.executeUpdate();	         
	       pstmt.close();
	       
	       c.commit();
	       
	       sql = "DELETE FROM ricetta";
	       pstmt = c.prepareStatement( sql );

		   pstmt.executeUpdate();
		   
		   String sql2 = "DELETE FROM quantita";
		   PreparedStatement pstmt2 = c.prepareStatement(sql2);
		
		   pstmt2.executeUpdate();		         
		     
		   pstmt2.close();
		   pstmt.close();
	      
	       c.commit();
	       sql = "DELETE FROM equipaggiamento";
	       pstmt = c.prepareStatement( sql );
	         
	         
	       pstmt.executeUpdate();
	         

	       pstmt.close();
	       c.commit();
	       
	       c.close();
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
		
		ControllerLotto controllNota = new ControllerLotto(password,dbName);     
		
		ControllerEquipaggiamento controllerEq = new ControllerEquipaggiamento(password,dbName);
		
		ControllerIngredienti control1 = new ControllerIngredienti(password,dbName);
		
		ControllerRicetta controlRic = new ControllerRicetta(control1,controllerEq,controllNota,password,dbName);
		
		VisualizzazioneRicetta finestraVisRicetta = new VisualizzazioneRicetta(controlRic);
		finestraVisRicetta.open();

		
	}
	
	
}
		
	
	


