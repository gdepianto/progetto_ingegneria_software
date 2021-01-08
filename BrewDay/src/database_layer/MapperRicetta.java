package database_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import View.BrewDayApplication;
import model.Ricetta;

public class MapperRicetta {
	
	public void insert(Ricetta ricetta) {
		  Connection c = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
		    		  BrewDayApplication.password);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         String sql = "INSERT INTO ricetta (nome, descrizione, tempo_preparazione) " +
                     "VALUES (?,?,?);"; 
	         PreparedStatement pstmt = c.prepareStatement( sql );
		     pstmt.setString(1, ricetta.getNome());
		     pstmt.setString(2,ricetta.getDescrizione());
		     pstmt.setInt(3, ricetta.getTempoPreparazione());
		     
		         
		         
		    
		     pstmt.executeUpdate();
		         

		     
		     pstmt.close();

	         
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	   
	}
	
	public void delete (int id) {
		Connection c = null;
	    
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
		    		  BrewDayApplication.password);
	       c.setAutoCommit(false);
	       String sql = "DELETE FROM ricetta WHERE ID=?";
	       System.out.println("Opened database successfully");
	       PreparedStatement pstmt = c.prepareStatement( sql );
		
		   pstmt.setInt(1, id);
		     
		         
		         
		    
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
	
	public void update (int id, String nome, String descrizione, int tempoPreparazione) {
		 Connection c = null;
		   
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
		    		  BrewDayApplication.password);
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		      String sql = "UPDATE ricetta set nome = ?, descrizione = ?"+
	    		       ", tempoPreparazione = ? where ID =?";
		      PreparedStatement pstmt = c.prepareStatement( sql );
			  pstmt.setString(1, nome);
			  pstmt.setString(2,descrizione);
			  pstmt.setInt(3, tempoPreparazione);
			  pstmt.setInt(4, id);
			     
			         
			         
			    
			  pstmt.executeUpdate();
			         

			     
			  pstmt.close();
		      
		      
		     
		      c.commit();
		   
		      c.close();
	   }  catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
	   }	
	  }
	
	public ArrayList<Ricetta> getRicette() {
		Connection c = null;
		Statement stmt = null;
		ArrayList<Ricetta> listaRicette = new ArrayList<Ricetta>();
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
		    		  BrewDayApplication.password);
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM ricetta;" );
		      
		      while ( rs.next() ) {
		    	 int id = rs.getInt("id");
		         String nome = rs.getString("nome");
		         String descrizione = rs.getString("descrizione");
		         int tempo_preparazione  = rs.getInt("tempo_preparazione");
		         Ricetta ricetta = new Ricetta(nome, descrizione, tempo_preparazione);
		         ricetta.setIdRicetta(id);
		         listaRicette.add(ricetta);
		         
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		System.out.println("Operation done successfully");
		return listaRicette;
	}

}
