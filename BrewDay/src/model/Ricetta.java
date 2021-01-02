package model;

import java.util.ArrayList;

public class Ricetta {
	private int idRicetta;
	private String nome;
	private String descrizione;
	private int tempoPreparazione;
	private ArrayList<Quantita> ingredienti;

	public int getIdRicetta() {
		return idRicetta;
	}
	
	public void setIdRicetta(int idRicetta) {
		this.idRicetta = idRicetta;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public int getTempoPreparazione() {
		return tempoPreparazione;
	}
	
	public void setTempoPreparazione(int tempoPreparazione) {
		this.tempoPreparazione = tempoPreparazione;
	}
	
	public ArrayList<Quantita> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(ArrayList<Quantita> ingredienti) {
		this.ingredienti = ingredienti;
	}
}
