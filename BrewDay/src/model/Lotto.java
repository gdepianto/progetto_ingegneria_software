package model;

import java.util.Date;

public class Lotto {
	private int idLotto;
	private String commento;
	private Date data;
	private float quantitaProdotta;
	private Equipaggiamento equipaggiamento;
	

	public int getIdLotto() {
		return idLotto;
	}
	
	public void setIdLotto(int idLotto) {
		this.idLotto = idLotto;
	}
	
	public String getCommento() {
		return commento;
	}
	
	public void setCommento(String commento) {
		this.commento = commento;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public float getQuantitaProdotta() {
		return quantitaProdotta;
	}

	public void setQuantitaProdotta(float quantitaProdotta) {
		this.quantitaProdotta = quantitaProdotta;
	}

	public Equipaggiamento getEquipaggiamento() {
		return equipaggiamento;
	}

	public void setEquipaggiamento(Equipaggiamento equipaggiamento) {
		this.equipaggiamento = equipaggiamento;
	}
	
	
}
