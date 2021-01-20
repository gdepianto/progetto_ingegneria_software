package controller;

import java.util.ArrayList;

import database_layer.MapperIngrediente;
import model.Ingrediente;

public class ControllerIngredienti {
	private MapperIngrediente mapperIngrediente;
	private String pass;
	
	public ControllerIngredienti (String pass) {
		this.pass = pass;
		this.mapperIngrediente = new MapperIngrediente(this.pass);
		
	}
	
	public String aggiungiIngrediente (String nome, float disponibilita, String unitaMisura) {
		Ingrediente ing = new Ingrediente(nome, disponibilita, unitaMisura);
		return mapperIngrediente.insert(ing);	
	}
	
	public ArrayList<Ingrediente> getIngredienti(){
		return mapperIngrediente.getIngredienti();
	}
	
    public void rimuoviIngrediente (int id) {

		mapperIngrediente.delete(id);

	}
    
    public void aggiornaIngrediente (int id,String nome, float quantita,String unitaMisura) {
		
		mapperIngrediente.update(id, nome, quantita, unitaMisura);;
	}
}
