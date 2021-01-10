package database_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import View.BrewDayApplication;
import model.Ingrediente;

public class MapperIngrediente {
	public String insert(Ingrediente ing) {
		  Connection c = null;
		  int count = -1;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
	                 BrewDayApplication.password);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         String sqlCheck = "SELECT COUNT(*) FROM ingrediente WHERE nome = ?";
	         PreparedStatement checkStatement = c.prepareStatement( sqlCheck );
	         checkStatement.setString(1, ing.getNome());
	         ResultSet rs = checkStatement.executeQuery();
	         rs.next();
	         count = rs.getInt(1);
	         checkStatement.close();
	         if(count == 0) {
		         String sql = "INSERT INTO ingrediente (nome,disponibilita, unitaMisura) " +
	                     "VALUES (?,?,?);"; 
		         PreparedStatement pstmt = c.prepareStatement( sql );
		         pstmt.setString(1, ing.getNome());
		         pstmt.setFloat(2, ing.getDisponibilita());
		         pstmt.setString(3,ing.getUnitaMisura());
		         
		         
		    
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
	    	  return "Errore: ingrediente con questo nome già esistente";
	    	  
	}
	
	public ArrayList<Ingrediente> getIngredienti() {
		Connection c = null;
		Statement stmt = null;
		ArrayList<Ingrediente> listaIngredienti = new ArrayList<Ingrediente>();
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
		    		  BrewDayApplication.password);
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM ingrediente;" );
		      
		      while ( rs.next() ) {
		    	 int id = rs.getInt("id");
		         String nome = rs.getString("nome");
		         String unitaMisura = rs.getString("unitaMisura");
		         float quantita  = rs.getFloat("disponibilita");
		         Ingrediente ing = new Ingrediente(nome,quantita,unitaMisura);
		         ing.setIdIngrediente(id);
		         listaIngredienti.add(ing);
		         
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		System.out.println("Operation done successfully");
		return listaIngredienti;
	}
	
	
	public void delete (int id) {
		Connection c = null;
	    
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
	    		   BrewDayApplication.password);
	       c.setAutoCommit(false);
	       String sql = "DELETE FROM ingrediente WHERE ID=?";
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

	public void update (int id, String nome, float disponibilita, String unitaMisura) {
	 Connection c = null;
	   
	   try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
	    		  BrewDayApplication.password);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      String sql = "UPDATE INGREDIENTE set nome = ?, disponibilita =?, unitaMisura=? where ID =?";
	      PreparedStatement pstmt = c.prepareStatement( sql );
	      pstmt.setString(1, nome);
	      pstmt.setFloat(2,disponibilita);
	      pstmt.setString(3, unitaMisura);
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
}