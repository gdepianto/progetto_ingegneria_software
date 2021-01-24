package controller;

import java.util.ArrayList;
import java.util.Date;

import database_layer.MapperLotto;
import model.Equipaggiamento;
import model.Lotto;
import model.NotaGusto;

public class ControllerLotto {
	private MapperLotto mapperLotto;
	
	
	public ControllerLotto(String pass,String dbName) {
		mapperLotto = new MapperLotto(pass,dbName);
		
	}
	
	
	public void inserisciLotto(int idRicetta,String commento,Date data,float quantita,Equipaggiamento equip,int valutazione) {
		Lotto lotto;
		if ( valutazione != -1) {
			lotto = new NotaGusto(idRicetta,commento,data,quantita,equip,valutazione);
		}
		else {
			lotto = new Lotto(idRicetta,commento,data,quantita,equip);
		}
		
		mapperLotto.insert(lotto);
	}
	
	public ArrayList<Lotto> getLotti(int idRicetta) {
		return mapperLotto.selectLotti(idRicetta);
	}
	
	
	public void rimuoviLotto(int idNota) {
		mapperLotto.delete(idNota);
	}
}
