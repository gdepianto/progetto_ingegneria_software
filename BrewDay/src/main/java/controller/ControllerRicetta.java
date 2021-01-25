package controller;

import java.util.ArrayList;
import java.util.Iterator;

import database_layer.MapperRicetta;
import model.Equipaggiamento;
import model.Quantita;
import model.Ricetta;

public class ControllerRicetta {
	private MapperRicetta mapperRicetta;
	private ControllerIngredienti controllerIngredienti;
	private ControllerEquipaggiamento controllerEquipaggiamento;
	private ControllerLotto controllerNota;
	
	
	public ControllerRicetta (ControllerIngredienti contring,ControllerEquipaggiamento contrEq,ControllerLotto contrNota,String pass,String dbName) {
		this.mapperRicetta = new MapperRicetta(pass,dbName);
		controllerIngredienti = contring;
		controllerEquipaggiamento = contrEq;
		controllerNota = contrNota;
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
	
	
	public Ricetta getBirraDelGiorno() {
		Ricetta ric = mapperRicetta.selectBirraDelGiorno(controllerEquipaggiamento.getEquipaggiamento().getCapacita());
		Equipaggiamento equip = getControllerEquipaggiamento().getEquipaggiamento();
		for(Quantita q: ric.getIngredienti()) {
			q.setQuantitaNecessaria(q.getQuantitaNecessaria()*equip.getCapacita());
		}
		return ric;
		
	}

	public ArrayList<Ricetta> getRicetteScarsaDisponibilita() {
		ArrayList<Ricetta> listaRicetta = getRicette();
		boolean contr = false;
		for(Iterator<Ricetta> iterator = listaRicetta.iterator();iterator.hasNext();) {
			contr = false;
			Ricetta r = iterator.next();
			for(Iterator<Quantita> iterator1 = r.getIngredienti().iterator();iterator1.hasNext();) {
				Quantita q = iterator1.next();
				if(q.getQuantitaNecessaria() > q.getIngrediente().getDisponibilita()) {
					contr = true;
				}
				else {
					//r.getIngredienti().remove(q);
					iterator1.remove();
				}
			}
			if(!contr) {
				//listaRicetta.remove(r);
				iterator.remove();
			}
		}
		return listaRicetta;
	}

	public boolean aggiornaDisponibilità(Ricetta r,float molt) {
		boolean contr = true;
		ArrayList<Quantita> newListaQuantita = new ArrayList<Quantita>();
		for(Quantita q : r.getIngredienti()) {
			if(q.getIngrediente().getDisponibilita() < (q.getQuantitaNecessaria()*molt)) {
				contr = false;
			}
			else {
				Quantita tmpq = new Quantita(q);
				tmpq.getIngrediente().setDisponibilita(q.getIngrediente().getDisponibilita()-(q.getQuantitaNecessaria()*molt));
				newListaQuantita.add(tmpq);
			}
		}
		if(contr) {
			r.setIngredienti(newListaQuantita);
			for(Quantita q : r.getIngredienti()) {
				controllerIngredienti.aggiornaIngrediente(q.getIngrediente().getIdIngrediente(), q.getIngrediente().getNome(), q.getIngrediente().getDisponibilita(), q.getIngrediente().getUnitaMisura());
			}
		}
		return contr;
	}
	
	
	public ControllerEquipaggiamento getControllerEquipaggiamento() {
		return controllerEquipaggiamento;
	}
	
	public ControllerLotto getControllerLotto() {
		return controllerNota;
	}
}
