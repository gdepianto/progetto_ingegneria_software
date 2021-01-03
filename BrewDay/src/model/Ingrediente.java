package model;

import java.util.ArrayList;

public class Ingrediente {
	private int idIngrediente;
	private String nome;
	private float disponibilita;
	private ArrayList<Quantita> ricette = null;
	

	public Ingrediente (String nome, float disponibilita) {
		this.idIngrediente = -1;
		this.nome = nome;
		this.disponibilita = disponibilita;
	}
	
	public int getIdIngrediente() {	
		return idIngrediente;
	}
	
	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getDisponibilita() {
		return disponibilita;
	}
	
	public void setDisponibilita(float disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	public ArrayList<Quantita> getRicette() {
		return ricette;
	}

	public void setRicette(ArrayList<Quantita> ricette) {
		this.ricette = ricette;
	}
}
