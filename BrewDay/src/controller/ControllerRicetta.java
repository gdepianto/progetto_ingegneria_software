package controller;

import java.util.ArrayList;

import database_layer.MapperRicetta;
import model.Equipaggiamento;
import model.Quantita;
import model.Ricetta;

public class ControllerRicetta {
	private MapperRicetta mapperRicetta;
	private ControllerIngredienti controllerIngredienti;
	private ControllerEquipaggiamento controllerEquipaggiamento;
	
	public ControllerRicetta (ControllerIngredienti contring,ControllerEquipaggiamento contrEq) {
		this.mapperRicetta = new MapperRicetta();
		controllerIngredienti = contring;
		controllerEquipaggiamento = contrEq;
	}
	

	public ControllerIngredienti getControllerIngredienti() {
		return controllerIngredienti;
	}


	public void setControllerIngredienti(ControllerIngredienti controllerIngredienti) {
		this.controllerIngredienti = controllerIngredienti;
	}
	
	public ArrayList<Ricetta> getRicette(){
		return mapperRicetta.getRicette();
	}


	public String aggiungiRicetta(String nome, String descrizione, int tempoPreparazione,ArrayList<Quantita> listaQuantita) {
		Equipaggiamento equip = controllerEquipaggiamento.getEquipaggiamento();
		for(Quantita q: listaQuantita) {
			q.setQuantitaNecessaria(q.getQuantitaNecessaria()/equip.getCapacita());
		}
		
		Ricetta ricetta = new Ricetta(nome, descrizione, tempoPreparazione,listaQuantita);
		return mapperRicetta.insert(ricetta);
	}
	
	public void rimuoviRicetta(int id) {
		mapperRicetta.delete(id);
		
	}
	
	public void aggiornaRicetta (int id, String nome, String descrizione, int tempoPreparazione,ArrayList<Quantita> listaQuantita) {
		mapperRicetta.update(id, nome, descrizione, tempoPreparazione,listaQuantita);
		
	}
}
