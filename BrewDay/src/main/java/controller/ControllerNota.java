package controller;

import java.util.Date;

import model.Equipaggiamento;
import model.Lotto;
import model.NotaGusto;

public class ControllerNota {

	
	
	public ControllerNota(String pass,String dbName) {
		
		
	}
	
	
	public void inserisciNota(int idRicetta,String commento,Date data,float quantita,Equipaggiamento equip,int valutazione) {
		Lotto lotto;
		if ( valutazione != -1) {
			lotto = new NotaGusto(idRicetta,commento,data,quantita,equip,valutazione);
		}
		else {
			lotto = new Lotto(idRicetta,commento,data,quantita,equip);
		}
		
	}
}
