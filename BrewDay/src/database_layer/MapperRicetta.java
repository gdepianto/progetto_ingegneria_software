package database_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import View.BrewDayApplication;
import model.Quantita;
import model.Ricetta;

public class MapperRicetta {
	
	public String insert(Ricetta ricetta) {
		  Connection c = null;
		  int count = -1;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:brewday.db","",
		    		  BrewDayApplication.password);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         String sqlCheck = "SELECT COUNT(*) FROM ricetta WHERE nome = ?";
	         PreparedStatement checkStatement = c.prepareStatement(sqlCheck);
	         checkStatement.setString(1, ricetta.getNome());
	         ResultSet rs = checkStatement.executeQuery();
	         rs.next();
	         count = rs.getInt(1);
	         checkStatement.close();
	         if(count == 0) {
		         String sql = "INSERT INTO ricetta (nome, descrizione, tempo_preparazione) " +
	                     "VALUES (?,?,?);"; 
		         PreparedStatement pstmt = c.prepareStatement( sql,Statement.RETURN_GENERATED_KEYS );
		         pstmt.setString(1, ricetta.getNome());
		         pstmt.setString(2, ricetta.getDescrizione());
		         pstmt.setInt(3, ricetta.getTempoPreparazione());
		         pstmt.executeUpdate();
		         c.commit();
		         int idRicetta=-1;
			     try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			                idRicetta= (int) generatedKeys.getLong(1);
			            }
			            else {
			               count = -1;
			            }
			      }
			     
			     pstmt.close();
			     if(idRicetta != -1) {
				     String sqlInsertQuantita = "INSERT INTO quantita (id_ricetta, id_ingrediente, quantita_necessaria) " +
		                     "VALUES ";
				     int contQ = 1;
				     for(Quantita q : ricetta.getIngredienti()) {
				    	 sqlInsertQuantita+=" (?,?,?),";
				     }
				     sqlInsertQuantita = sqlInsertQuantita.substring(0, sqlInsertQuantita.length() - 1);
				     sqlInsertQuantita += ";";
				     PreparedStatement statementQuantita = c.prepareStatement( sqlInsertQuantita );
				     for(Quantita q : ricetta.getIngredienti()) {
				    	 statementQuantita.setInt(contQ, idRicetta);
				    	 contQ++;
				    	 statementQuantita.setInt(contQ, q.getIngrediente().getIdIngrediente());
				    	 contQ++;
				    	 statementQuantita.setFloat(contQ, q.getQuantitaNecessaria());
				    	 contQ++;
				     }
				     statementQuantita.executeUpdate();
				     statementQuantita.close();
			     }
	        }   
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Record created successfully");
	      if(count == 0)
	    	  return "Ok";
	      else if(count == -1)
	    	  return "Errore: problemi al database";
	      else
	    	  return "Errore: ricetta con questo nome già esistente";
	   
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
