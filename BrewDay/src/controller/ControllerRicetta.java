package controller;

import database_layer.MapperRicetta;
import model.Ricetta;

public class ControllerRicetta {
	private MapperRicetta mapperRicetta;
	
	public ControllerRicetta () {
		this.mapperRicetta = new MapperRicetta();
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
