package model;

import java.util.Date;

public class NotaGusto extends Lotto{
	private int valutazione;
	
	public NotaGusto(int idRicetta, String commento, Date data, float quantitaProdotta, Equipaggiamento equipaggiamento,int val) {
		super(idRicetta,commento, data, quantitaProdotta, equipaggiamento);
		this.valutazione=val;
	}

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	
}
