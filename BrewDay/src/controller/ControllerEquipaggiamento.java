package controller;

import database_layer.MapperEquipaggiamento;
import model.Equipaggiamento;

public class ControllerEquipaggiamento {
	private String pass;
	private MapperEquipaggiamento mapperEquipaggiamento;
	
	public ControllerEquipaggiamento(String pass) {
		this.pass = pass;
		mapperEquipaggiamento = new MapperEquipaggiamento(this.pass);
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
