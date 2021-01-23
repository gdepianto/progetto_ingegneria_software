package database_layer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.Lotto;
import model.NotaGusto;

public class MapperLotto {
	private String pass;
	private String dbName;

	public MapperLotto(String pass,String dbName) {
		this.pass = pass;
		this.dbName = dbName;
	}
	
	public void insert(Lotto lotto) {
		
		Connection c = null;
	     
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",pass);
	         c.setAutoCommit(false);
	         
	         String sql = "INSERT INTO lotto (commento, data, valutazione, ricetta_id, quantitaProdotta," +
	        		 	  "nomeEquipaggiamento, capacitaEquipaggiamento)" +
                          "VALUES (?,?,?,?,?,?,?)";
	         PreparedStatement pstmt = c.prepareStatement( sql );
	         pstmt.setString(1, lotto.getCommento());
	         pstmt.setDate(2, (Date) lotto.getData());
	         if (lotto instanceof NotaGusto) {
	 			NotaGusto lotto2 = (NotaGusto) lotto;
	 			pstmt.setInt(3, lotto2.getValutazione());
	 		} else 
	         pstmt.setNull(3, java.sql.Types.NULL);
	         pstmt.setInt(4, lotto.getRicetta());
	         pstmt.setFloat(5, lotto.getQuantitaProdotta());
	         pstmt.setString(6, lotto.getEquipaggiamento().getNome());
	         pstmt.setFloat(7, lotto.getEquipaggiamento().getCapacita());
	         
	         
	         
	      } catch ( Exception e ) {
	    	  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	  System.exit(0);
	      }
	}
	
	public void delete (int id) {
		Connection c = null;
	    
	    try {
	       Class.forName("org.sqlite.JDBC");
	       c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
	    		   pass);
	       c.setAutoCommit(false);
	       String sql = "DELETE FROM lotto WHERE ID=?";
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
	 }
}
