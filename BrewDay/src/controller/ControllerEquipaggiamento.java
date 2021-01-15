package controller;

import database_layer.MapperEquipaggiamento;
import model.Equipaggiamento;

public class ControllerEquipaggiamento {
	private MapperEquipaggiamento mapperEquipaggiamento;
	
	public ControllerEquipaggiamento() {
		mapperEquipaggiamento = new MapperEquipaggiamento();
	}

	public String aggiungiEquipaggiamento(String nome, float capacita) {
		Equipaggiamento equip = new Equipaggiamento(nome, capacita);
		return mapperEquipaggiamento.insert(equip);
	}
	
	public void aggiornaEquipaggiamento(String nome, float capacita) {
		Equipaggiamento equip = new Equipaggiamento(nome, capacita);
		mapperEquipaggiamento.update(equip);
	}
	
	public Equipaggiamento getEquipaggiamento() {
		return mapperEquipaggiamento.getEquipaggiamento();
	}

}
