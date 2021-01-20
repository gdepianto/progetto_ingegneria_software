package database_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Equipaggiamento;

public class MapperEquipaggiamento {
	private String pass;
	
	public MapperEquipaggiamento(String pass) {
		this.pass = pass;
	}

	public String insert(Equipaggiamento equip) {
		  Connection c = null;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
	                 pass);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         String sql = "INSERT INTO equipaggiamento (nome, capacita) " +
                
	              "VALUES (?,?);"; 
	         PreparedStatement pstmt = c.prepareStatement( sql );
	         pstmt.setString(1, equip.getNome());
	         pstmt.setFloat(2, equip.getCapacita());
	         
	         	    
	         pstmt.executeUpdate();

	         pstmt.close();
	         
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	      return "Ok";
	     
	    	  
	}

	public void delete () {
		Connection c = null;
	    
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
	    		   pass);
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
	
	public Equipaggiamento getEquipaggiamento() {
		Connection c = null;
		Statement stmt = null;
		
		Equipaggiamento equip = null;
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
		    		  pass);
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM equipaggiamento;" );
	          
		      String nome = rs.getString("nome");
		      float capacita = rs.getFloat("capacita");
		      equip = new Equipaggiamento(nome, capacita);
		      
		  rs.close();
	      stmt.close();
	      c.close(); 
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		System.out.println("Operation done successfully");
		return equip;
	}  
}	

	
	