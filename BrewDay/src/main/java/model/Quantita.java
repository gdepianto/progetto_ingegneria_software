package model;

public class Quantita {
	private Ingrediente ingrediente;
	private float quantitaNecessaria;	
	
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
