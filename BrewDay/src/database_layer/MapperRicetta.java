package database_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Ricetta;

public class MapperRicetta {
	
	public void insert(Ricetta ricetta) {
		  Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:brewday.db");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO ricetta (nome, descrizione, tempo_preparazione) " +
	                        "VALUES ('"+ricetta.getNome()+"','"+ricetta.getDescrizione()+"',"+ricetta.getTempoPreparazione()+");"; 
	         stmt.executeUpdate(sql);

	         stmt.close();
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
	    Statement stmt = null;
	    
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:brewday.db");
	       c.setAutoCommit(false);
	       System.out.println("Opened database successfully");
	
	       stmt = c.createStatement();
	       String sql = "DELETE FROM ricetta WHERE ID="+id;
	       stmt.executeUpdate(sql);
	       c.commit();
	
	    stmt.close();
	    c.close();
	    } catch ( Exception e ) {
	       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	 }
	
	public void update (int id, String nome, String descrizione, int tempoPreparazione) {
		 Connection c = null;
		   Statement stmt = null;
		   
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:brewday.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      String sql = "UPDATE ricetta set nome = '"+nome+"', descrizione = '"+descrizione+
		    		       "', tempoPreparazione = "+tempoPreparazione+" where ID ="+id;
		      stmt.executeUpdate(sql);
		      c.commit();
		   
		      stmt.close();
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
		      c = DriverManager.getConnection("jdbc:sqlite:brewday.db");
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
