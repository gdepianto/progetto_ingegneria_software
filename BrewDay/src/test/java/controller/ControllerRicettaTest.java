package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import database_layer.MapperEquipaggiamento;
import database_layer.MapperIngrediente;
import database_layer.MapperRicetta;
import model.Ingrediente;
import model.Quantita;
import model.Ricetta;
import view.BrewDayApplication;

public class ControllerRicettaTest {

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
	public void AggiuntaRicettaTest() {
		ControllerIngredienti contrIng = new ControllerIngredienti("password","test.db");
		contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		contrIng.aggiungiIngrediente("Luppolo",13,"Grammi");
		contrIng.aggiungiIngrediente("Acqua",50,"Litri");
		ArrayList<Ingrediente> listaIng = contrIng.getIngredienti();
		ArrayList<Quantita> listaQuantita = new ArrayList<Quantita>();
		Quantita q1 = new Quantita();
		q1.setIngrediente(listaIng.get(0));
		q1.setQuantitaNecessaria(20);
		Quantita q2 = new Quantita();
		q2.setIngrediente(listaIng.get(1));
		q2.setQuantitaNecessaria(30);
		Quantita q3 = new Quantita();
		q3.setIngrediente(listaIng.get(2));
		q3.setQuantitaNecessaria(40);
		listaQuantita.add(q1);
		listaQuantita.add(q2);
		listaQuantita.add(q3);
		ControllerEquipaggiamento contrEquip = new ControllerEquipaggiamento("password","test.db");
		contrEquip.aggiungiEquipaggiamento("Equip", 70);
		ControllerRicetta contrRic = new ControllerRicetta(contrIng,contrEquip,"password","test.db");
		contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		
		Ricetta ric = contrRic.getRicette().get(0);
		
		boolean contr1 = ric.getIngredienti().stream().anyMatch(i -> (i.getIngrediente().getNome().equals("Malto") && i.getIngrediente().getDisponibilita() == 15.6f && i.getIngrediente().getUnitaMisura().equals("Grammi")));
		boolean contr2 = ric.getIngredienti().stream().anyMatch(i -> (i.getIngrediente().getNome().equals("Luppolo") && i.getIngrediente().getDisponibilita() == 13 && i.getIngrediente().getUnitaMisura().equals("Grammi")));
		boolean contr3 = ric.getIngredienti().stream().anyMatch(i -> (i.getIngrediente().getNome().equals("Acqua") && i.getIngrediente().getDisponibilita() == 50 && i.getIngrediente().getUnitaMisura().equals("Litri")));
		
		boolean contr4 = ric.getNome().equals("Ricetta1");
		boolean contr5 = ric.getDescrizione().equals("Preparare e poi assaggiare");
		boolean contr6 = ric.getTempoPreparazione() == 23;
		
		assertTrue(contr1 && contr2 && contr3 && contr4 && contr5 && contr6);
		
	}
	@Test
	public void AggiuntaRicettaNomeEsistenteTest() {
		ControllerEquipaggiamento contrEquip = new ControllerEquipaggiamento("password","test.db");
		ControllerIngredienti contrIng = new ControllerIngredienti("password","test.db");
		contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		contrIng.aggiungiIngrediente("Luppolo",13,"Grammi");
		contrIng.aggiungiIngrediente("Acqua",50,"Litri");
		ArrayList<Ingrediente> listaIng = contrIng.getIngredienti();
		ArrayList<Quantita> listaQuantita = new ArrayList<Quantita>();
		Quantita q1 = new Quantita();
		q1.setIngrediente(listaIng.get(0));
		q1.setQuantitaNecessaria(20);
		Quantita q2 = new Quantita();
		q2.setIngrediente(listaIng.get(1));
		q2.setQuantitaNecessaria(30);
		Quantita q3 = new Quantita();
		q3.setIngrediente(listaIng.get(2));
		q3.setQuantitaNecessaria(40);
		listaQuantita.add(q1);
		listaQuantita.add(q2);
		listaQuantita.add(q3);
		contrEquip.aggiungiEquipaggiamento("Equip", 70);
		ControllerRicetta contrRic = new ControllerRicetta(contrIng,contrEquip,"password","test.db");
		contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		String resp = contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		assertEquals("Errore: ricetta con questo nome gia esistente",resp);
	}
	
		

}
