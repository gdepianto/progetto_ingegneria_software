package database_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Equipaggiamento;

public class MapperEquipaggiamento {
	private String pass;
	private String dbName;
	
	public MapperEquipaggiamento(String pass,String dbName) {
		this.pass = pass;
		this.dbName = dbName;
	}

	public String insert(Equipaggiamento equip) {
		  Connection c = null;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
	                 pass);
	         c.setAutoCommit(false);
	         
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
	      return "Ok";
	     
	    	  
	}

	public void delete () {
		Connection c = null;
	    
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
	    		   pass);
	       c.setAutoCommit(false);
	       String sql = "DELETE FROM equipaggiamento";
	       PreparedStatement pstmt = c.prepareStatement( sql );
	         
	         
	       pstmt.executeUpdate();
	         

	       pstmt.close();
	       c.commit();
	
	       
	       c.close();
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
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
		      c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
		    		  pass);
		      c.setAutoCommit(false);

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
		return equip;
	}  
}	

	
	