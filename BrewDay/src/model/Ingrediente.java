package model;

import java.util.ArrayList;

public class Ingrediente {
	private int idIngrediente;
	private String nome;
	private float disponibilita;
	private String unitaMisura;
	

	public Ingrediente (String nome, float disponibilita, String unitaMisura) {
		this.idIngrediente = -1;
		this.nome = nome;
		this.disponibilita = disponibilita;
		this.unitaMisura  = unitaMisura;
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
	
	public String getUnitaMisura() {
		return unitaMisura;
	}
	
	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	@Override
	public String toString() {
		return "Ingrediente [idIngrediente=" + idIngrediente + ", nome=" + nome + ", disponibilita=" + disponibilita
				+ ", unitaMisura=" + unitaMisura + "]";
	}

}
