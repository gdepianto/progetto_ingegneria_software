package model;

public class Quantita {
	private Ricetta ricetta;
	private Ingrediente ingrediente;
	private float quantitaNecessaria;	
	
	public Ricetta getRicetta() {
		return ricetta;
	}
	
	public void setRicetta(Ricetta ricetta) {
		this.ricetta = ricetta;
	}
	
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	
	public float getQuantitaNecessaria() {
		return quantitaNecessaria;
	}
	
	public void setQuantitaNecessaria(float quantitaNecessaria) {
		this.quantitaNecessaria = quantitaNecessaria;
	}
}
