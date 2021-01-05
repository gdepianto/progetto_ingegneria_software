package controller;

import java.util.ArrayList;

import database_layer.MapperIngrediente;
import model.Ingrediente;

public class ControllerIngredienti {
	private MapperIngrediente mapperIngrediente;
	
	public ControllerIngredienti () {
		this.mapperIngrediente = new MapperIngrediente();
		
	}
	
	public void aggiungiIngrediente (String nome, float disponibilita, String unitaMisura) {
		Ingrediente ing = new Ingrediente(nome, disponibilita, unitaMisura);
		mapperIngrediente.insert(ing);
		
	}
	
	public ArrayList<Ingrediente> getIngredienti(){
		return mapperIngrediente.getIngredienti();
	}
	
    public void rimuoviIngrediente (int id) {
    	
    	
		
	}
    
    public void aggiornaIngrediente (int id, float quantita) {
		
	}
}
