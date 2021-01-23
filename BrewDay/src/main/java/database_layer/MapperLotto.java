package database_layer;

import java.sql.Connection;
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
	
	public String insert(Lotto lotto) {
		if (lotto instanceof NotaGusto) {
			lotto = (NotaGusto) lotto;
		}
		
		Connection c = null;
	     
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",pass);
	         c.setAutoCommit(false);
	         
	         String sql = "INSERT INTO lotto (commento, data, valutazione, ricetta_id, quantitaProdotta," +
	        		 	  "nomeEquipaggiamento, capacitaEquipaggiamento)" +
                          "VALUES (?,?,?)";
	         PreparedStatement pstmt = c.prepareStatement( sql );
	         pstmt.setString(1, lotto.getCommento());
	         pstmt.setDate(2, lotto.getData());
	         if (lotto instanceof NotaGusto) {
	 			NotaGusto lotto2 = (NotaGusto) lotto;
	 			pstmt.setInt(3, lotto2.getValutazione());
	 		} else 
	         pstmt.setNull(3, java.sql.Types.NULL);
	         pstmt.setInt(4, lotto.getRicetta());
	         pstmt.setFloat(5, lotto.getQuantitaProdotta());
	         
	         
	         
	      } catch ( Exception e ) {
	    	  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	  System.exit(0);
	      }
	}
}
