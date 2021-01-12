package controller;

import database_layer.MapperEquipaggiamento;
import model.Equipaggiamento;

public class ControllerEquipaggiamento {
	
	private MapperEquipaggiamento mapperEquipaggiamento;
	
	public String aggiungiEquipaggiamento(String nome, float disponibilita) {
		Equipaggiamento equip = new Equipaggiamento(nome, disponibilita);
		return mapperEquipaggiamento.insert(equip);
	}
	
	public void aggiornaEquipaggiamento(String nome, float disponibilita) {
		Equipaggiamento equip = new Equipaggiamento(nome, disponibilita);
		mapperEquipaggiamento.update(equip);
	}

}
