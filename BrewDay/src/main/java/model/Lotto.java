package model;


import java.util.Date;

public class Lotto {
	private int idRicetta;
	private int idLotto;
	private String commento;
	private  Date data;
	private float quantitaProdotta;
	private Equipaggiamento equipaggiamento;
	

	public Lotto(int idRicetta, String commento, Date data, float quantitaProdotta, Equipaggiamento equipaggiamento) {
		this.idRicetta = idRicetta;
		this.commento = commento;
		this.data = data;
		this.quantitaProdotta = quantitaProdotta;
		this.equipaggiamento = equipaggiamento;
	}

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
	
	public int getRicetta() {
		return this.idRicetta;
	}
	
	
}
