package database_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import View.BrewDayApplication;
import model.Equipaggiamento;

public class MapperEquipaggiamento {
	public String insert(Equipaggiamento equip) {
		  Connection c = null;
		  int count = -1;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
	                 BrewDayApplication.password);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         String sqlCheck = "SELECT COUNT(*) FROM equipaggiamento";
	         PreparedStatement checkStatement = c.prepareStatement( sqlCheck );
	         checkStatement.setString(1, equip.getNome());
	         ResultSet rs = checkStatement.executeQuery();
	         rs.next();
	         count = rs.getInt(1);
	         checkStatement.close();
	         if(count == 0) {
		         String sql = "INSERT INTO equipaggiamento (nome, capacita) " +
	                     "VALUES (?,?);"; 
		         PreparedStatement pstmt = c.prepareStatement( sql );
		         pstmt.setString(1, equip.getNome());
		         pstmt.setFloat(2, equip.getCapacita());
		         
		         	    
		         pstmt.executeUpdate();
		         
	
		         pstmt.close();
	         }
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	      if(count == 0)
	    	  return "Ok";
	      else if(count == -1)
	    	  return "Errore: problemi al database";
	      else
	    	  return "Hai già inserito il tuo equipaggiamento";
	    	  
	}

	public void delete () {
		Connection c = null;
	    
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
	    		   BrewDayApplication.password);
	       c.setAutoCommit(false);
	       String sql = "DELETE FROM equipaggiamento";
	       System.out.println("Opened database successfully");
	       PreparedStatement pstmt = c.prepareStatement( sql );
	         
	         
	       pstmt.executeUpdate();
	         

	       pstmt.close();
	       c.commit();
	
	       
	       c.close();
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	 }

	public void update(Equipaggiamento equip) {
		delete();
		insert(equip);
    }
}	

	
	