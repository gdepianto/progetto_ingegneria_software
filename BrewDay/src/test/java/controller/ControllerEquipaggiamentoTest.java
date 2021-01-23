package controller;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import database_layer.MapperEquipaggiamento;
import database_layer.MapperIngrediente;
import database_layer.MapperRicetta;
import model.Equipaggiamento;
import view.BrewDayApplication;

public class ControllerEquipaggiamentoTest {
	
	@BeforeClass
	public static void setupDb() {
		BrewDayApplication.initializeDB("password","test.db");
	}
	
	@After
	public void resetDB() {
		MapperRicetta mapRicetta = new MapperRicetta("password","test.db");
		MapperIngrediente mapIngrediente = new MapperIngrediente("password","test.db");
		MapperEquipaggiamento mapEquipaggiamento = new MapperEquipaggiamento("password","test.db");
		mapRicetta.deleteAll();
		mapIngrediente.deleteAll();
		mapEquipaggiamento.delete();
		
	}
	
	@Test
	public void aggiungiEquipaggiamentoTest() {
    	ControllerEquipaggiamento controllerEquipaggiamento = new ControllerEquipaggiamento("password","test.db");
    	controllerEquipaggiamento.aggiungiEquipaggiamento("equip1", 20);
    	Equipaggiamento equipaggiamento = controllerEquipaggiamento.getEquipaggiamento();
    	boolean contr1 = equipaggiamento.getNome().equals("equip1");
    	boolean contr2 = equipaggiamento.getCapacita() == 20;
    	assertTrue(contr1 & contr2);
    		
    }
    
    @Test
    public void aggiornaEquipaggiamentoTest() {
    	ControllerEquipaggiamento controllerEquipaggiamento = new ControllerEquipaggiamento("password","test.db");
    	controllerEquipaggiamento.aggiornaEquipaggiamento("equip2", 15);
    	Equipaggiamento equipaggiamento = controllerEquipaggiamento.getEquipaggiamento();
    	boolean contr1 = equipaggiamento.getNome().equals("equip2");
    	boolean contr2 = equipaggiamento.getCapacita() == 15;
    	assertTrue(contr1 & contr2);
    }

}
