package controller;

import database_layer.MapperRicetta;
import model.Ricetta;

public class ControllerRicetta {
	private MapperRicetta mapperRicetta;
	private ControllerIngredienti controllerIngredienti;
	
	public ControllerRicetta (ControllerIngredienti contring) {
		this.mapperRicetta = new MapperRicetta();
		controllerIngredienti = contring;
	}
	

	public ControllerIngredienti getControllerIngredienti() {
		return controllerIngredienti;
	}


	public void setControllerIngredienti(ControllerIngredienti controllerIngredienti) {
		this.controllerIngredienti = controllerIngredienti;
	}


	public void aggiungiRicetta(String nome, String descrizione, int tempoPreparazione) {

		Ricetta ricetta = new Ricetta(nome, descrizione, tempoPreparazione);
		mapperRicetta.insert(ricetta);
	}
	
	public void rimuoviRicetta(int id) {
		mapperRicetta.delete(id);
		
	}
	
	public void aggiornaRicetta (int id, String nome, String descrizione, int tempoPreparazione) {
		mapperRicetta.update(id, nome, descrizione, tempoPreparazione);
		
	}
}
