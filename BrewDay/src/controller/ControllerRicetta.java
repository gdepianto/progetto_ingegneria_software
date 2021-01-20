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
	private String pass;
	
	public ControllerRicetta (ControllerIngredienti contring,ControllerEquipaggiamento contrEq,String pass) {
		this.pass = pass;
		this.mapperRicetta = new MapperRicetta(this.pass);
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
		ArrayList<Ricetta>  ricette=mapperRicetta.getRicette();
		for(Ricetta r : ricette) {
			Equipaggiamento equip = getControllerEquipaggiamento().getEquipaggiamento();
			for(Quantita q: r.getIngredienti()) {
				q.setQuantitaNecessaria(q.getQuantitaNecessaria()*equip.getCapacita());
			}
		}
		return ricette;
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
		Equipaggiamento equip = controllerEquipaggiamento.getEquipaggiamento();
		for(Quantita q: listaQuantita) {
			q.setQuantitaNecessaria(q.getQuantitaNecessaria()/equip.getCapacita());
		}
		mapperRicetta.update(id, nome, descrizione, tempoPreparazione,listaQuantita);
		
	}


	public ControllerEquipaggiamento getControllerEquipaggiamento() {
		return controllerEquipaggiamento;
	}
}
