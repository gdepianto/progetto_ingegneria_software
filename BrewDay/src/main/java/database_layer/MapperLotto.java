package database_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.Equipaggiamento;
import model.Ingrediente;
import model.Lotto;
import model.NotaGusto;
import model.Quantita;
import model.Ricetta;

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
	
	
	public ArrayList<Lotto> selectNote(int idRicetta){
		Connection c = null;
		Statement stmt = null;
		ArrayList<Lotto> listaLotti = new ArrayList<Lotto>();
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:"+dbName,"",
		    		  pass);
		      c.setAutoCommit(false);

		      
		    
	         String sql = "SELECT * "+
	        		 	  "FROM lotto "+
	        		 	  "WHERE ricetta_id = ?";
	         PreparedStatement pstmt = c.prepareStatement( sql );
	         pstmt.setInt(1, idRicetta);
	         
	         ResultSet rs2 = pstmt.executeQuery();
	         
	         while (rs2.next()) {
	        	 Lotto l;
	        	 int idNota = rs2.getInt("id");
	        	 String commento = rs2.getString("commento");
	        	 Date data = rs2.getDate("data");
	        	 int ricetta_id = rs2.getInt("ricetta_id");
	        	 float quantitaProdotta = rs2.getFloat("quantitaProdotta");
	        	 String nomeEquip = rs2.getString("nomeEquipaggiamento");
	        	 float capacitaEquip = rs2.getFloat("capacitaEquipaggiamento");
	        	 int valutazione = rs2.getInt("valutazione");
	        	 if(rs2.wasNull()) {
	        		 l = new Lotto(idRicetta,commento,data,quantitaProdotta,new Equipaggiamento(nomeEquip,capacitaEquip));
	        		 l.setIdLotto(idNota);
	        	 }
	        	 else {
	        		 l = new NotaGusto(idRicetta,commento,data,quantitaProdotta,new Equipaggiamento(nomeEquip,capacitaEquip),valutazione);
	        		 l.setIdLotto(idNota);
	        	 }
	        	 
	        	 listaLotti.add(l);
	         }
	    
	        
	         rs2.close();
	         pstmt.close();
		      
		     
		      stmt.close();
		      c.close();
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		return listaLotti;
	}
}
