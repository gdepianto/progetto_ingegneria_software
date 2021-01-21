package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import View.BrewDayApplication;
import database_layer.MapperEquipaggiamento;
import database_layer.MapperIngrediente;
import database_layer.MapperRicetta;
import model.Ingrediente;

public class ControllerTest {

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
	public void addIngredientiTest() {
		ControllerIngredienti contrIng = new ControllerIngredienti("password","test.db");
		contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		contrIng.aggiungiIngrediente("Luppolo",13,"Grammi");
		contrIng.aggiungiIngrediente("Acqua",50,"Litri");
		ArrayList<Ingrediente> listaIng = contrIng.getIngredienti();
		boolean contr1 = listaIng.stream().anyMatch(i -> (i.getNome().equals("Malto") && i.getDisponibilita() == 15.6f && i.getUnitaMisura().equals("Grammi")));
		boolean contr2 = listaIng.stream().anyMatch(i -> (i.getNome().equals("Luppolo") && i.getDisponibilita() == 13 && i.getUnitaMisura().equals("Grammi")));
		boolean contr3 = listaIng.stream().anyMatch(i -> (i.getNome().equals("Acqua") && i.getDisponibilita() == 50 && i.getUnitaMisura().equals("Litri")));
		assertTrue(contr1 && contr2 && contr3);
	}

	@Test
	public void removeIngredientiTest() {
		ControllerIngredienti contrIng = new ControllerIngredienti("password","test.db");
		contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		contrIng.aggiungiIngrediente("Luppolo",13,"Grammi");
		contrIng.aggiungiIngrediente("Acqua",50,"Litri");
		ArrayList<Ingrediente> listaIng = contrIng.getIngredienti();
		for(Ingrediente ing : listaIng) {
			contrIng.rimuoviIngrediente(ing.getIdIngrediente());
		}
		listaIng = contrIng.getIngredienti();
		assertTrue(listaIng.isEmpty());
	}
	
	@Test
	public void addIngredientAlreadyExistTest() {
		ControllerIngredienti contrIng = new ControllerIngredienti("password","test.db");
		contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		String resp = contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		assertEquals("Errore: ingrediente con questo nome già esistente",resp);
	}
	
	@Test
	public void updateIngredienteTest() {
		ControllerIngredienti contrIng = new ControllerIngredienti("password","test.db");
		contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		Ingrediente ing = contrIng.getIngredienti().get(0);
		contrIng.aggiornaIngrediente(ing.getIdIngrediente(), "Malto",2, "litri");
		ing = contrIng.getIngredienti().get(0);
		assertEquals("litri",ing.getUnitaMisura());
	}
}
