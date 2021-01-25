package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import database_layer.MapperEquipaggiamento;
import database_layer.MapperIngrediente;
import database_layer.MapperLotto;
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
		MapperLotto mapLotto = new MapperLotto("password","test.db");
		mapRicetta.deleteAll();
		mapIngrediente.deleteAll();
		mapEquipaggiamento.delete();
		mapLotto.deleteAll();
	}

	@Test
	public void AggiuntaRicettaTest() {
		ControllerLotto contrNota = new ControllerLotto("password","test.db");
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
		contrEquip.aggiungiEquipaggiamento("Equip", 70f);
		ControllerRicetta contrRic = new ControllerRicetta(contrIng,contrEquip,contrNota,"password","test.db");
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
		ControllerLotto contrNota = new ControllerLotto("password","test.db");
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
		ControllerRicetta contrRic = new ControllerRicetta(contrIng,contrEquip,contrNota,"password","test.db");
		contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		String resp = contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		assertEquals("Errore: ricetta con questo nome gia esistente",resp);
	}
	
	@Test
	public void birraDelGiornoTest() {
		ControllerLotto contrNota = new ControllerLotto("password","test.db");
		ControllerEquipaggiamento contrEquip = new ControllerEquipaggiamento("password","test.db");
		ControllerIngredienti contrIng = new ControllerIngredienti("password","test.db");
		contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		contrIng.aggiungiIngrediente("Luppolo",13,"Grammi");
		contrIng.aggiungiIngrediente("Acqua",50,"Litri");
		ArrayList<Ingrediente> listaIng = contrIng.getIngredienti();
		ArrayList<Quantita> listaQuantita = new ArrayList<Quantita>();
		Quantita q1 = new Quantita();
		q1.setIngrediente(listaIng.get(2));
		q1.setQuantitaNecessaria(11);
		Quantita q2 = new Quantita();
		q2.setIngrediente(listaIng.get(1));
		q2.setQuantitaNecessaria(11);
		Quantita q3 = new Quantita();
		q3.setIngrediente(listaIng.get(0));
		q3.setQuantitaNecessaria(40);
		listaQuantita.add(q1);
		listaQuantita.add(q2);
		listaQuantita.add(q3);
		ArrayList<Quantita> listaQuantita2 = new ArrayList<Quantita>();
		q1 = new Quantita();
		q1.setIngrediente(listaIng.get(2));
		q1.setQuantitaNecessaria(13);
		q2 = new Quantita();
		q2.setIngrediente(listaIng.get(1));
		q2.setQuantitaNecessaria(12);
		q3 = new Quantita();
		q3.setIngrediente(listaIng.get(0));
		q3.setQuantitaNecessaria(42);
		listaQuantita2.add(q1);
		listaQuantita2.add(q2);
		listaQuantita2.add(q3);
		contrEquip.aggiungiEquipaggiamento("Equip", 30);
		ControllerRicetta contrRic = new ControllerRicetta(contrIng,contrEquip,contrNota,"password","test.db");
		contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		contrRic.aggiungiRicetta("Ricetta2", "Preparare e poi assaggiare", 23, listaQuantita2);
		Ricetta ricDelGiorno = contrRic.getBirraDelGiorno();
		assertEquals("Ricetta2",ricDelGiorno.getNome());
	}
	
	@Test
	public void quantitaCambioEquipaggiamentoTest() {
		ControllerLotto contrNota = new ControllerLotto("password","test.db");
		ControllerIngredienti contrIng = new ControllerIngredienti("password","test.db");
		contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		contrIng.aggiungiIngrediente("Luppolo",13,"Grammi");
		contrIng.aggiungiIngrediente("Acqua",50,"Litri");
		ArrayList<Ingrediente> listaIng = contrIng.getIngredienti();
		ArrayList<Quantita> listaQuantita = new ArrayList<Quantita>();
		Quantita q1 = new Quantita();
		q1.setIngrediente(listaIng.get(2));
		q1.setQuantitaNecessaria(20);
		Quantita q2 = new Quantita();
		q2.setIngrediente(listaIng.get(1));
		q2.setQuantitaNecessaria(30);
		Quantita q3 = new Quantita();
		q3.setIngrediente(listaIng.get(0));
		q3.setQuantitaNecessaria(40);
		listaQuantita.add(q1);
		listaQuantita.add(q2);
		listaQuantita.add(q3);
		ControllerEquipaggiamento contrEquip = new ControllerEquipaggiamento("password","test.db");
		contrEquip.aggiungiEquipaggiamento("Equip", 70);
		ControllerRicetta contrRic = new ControllerRicetta(contrIng,contrEquip,contrNota,"password","test.db");
		contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		contrEquip.aggiornaEquipaggiamento("Equip2", 40);
		
		Ricetta ric = contrRic.getRicette().get(0);
	
		
		boolean contr1 = ric.getIngredienti().stream().anyMatch(i -> (i.getIngrediente().getNome().equals("Malto") && i.getQuantitaNecessaria() == (20f/70f)*40f && i.getIngrediente().getUnitaMisura().equals("Grammi")));
		boolean contr2 = ric.getIngredienti().stream().anyMatch(i -> (i.getIngrediente().getNome().equals("Luppolo") && i.getQuantitaNecessaria() == (30f/70f)*40f && i.getIngrediente().getUnitaMisura().equals("Grammi")));
		boolean contr3 = ric.getIngredienti().stream().anyMatch(i -> (i.getIngrediente().getNome().equals("Acqua") && i.getQuantitaNecessaria() == (40f/70f)*40f && i.getIngrediente().getUnitaMisura().equals("Litri")));
		
		assertTrue(contr1 && contr2 && contr3);
	}
	
	@Test
	public void updateRicettaTest() {
		ControllerLotto contrNota = new ControllerLotto("password","test.db");
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
		ControllerRicetta contrRic = new ControllerRicetta(contrIng,contrEquip,contrNota,"password","test.db");
		contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		Ricetta ric = contrRic.getRicette().get(0);
		int id = ric.getIdRicetta();
		
		
		listaQuantita = new ArrayList<Quantita>();
		q1 = new Quantita();
		q1.setIngrediente(listaIng.get(0));
		q1.setQuantitaNecessaria(15);
		q2 = new Quantita();
		q2.setIngrediente(listaIng.get(1));
		q2.setQuantitaNecessaria(25);
		q3 = new Quantita();
		q3.setIngrediente(listaIng.get(2));
		q3.setQuantitaNecessaria(35);
		listaQuantita.add(q1);
		listaQuantita.add(q2);
		listaQuantita.add(q3);
		contrRic.aggiornaRicetta(id, "RicettaUpdated", "Preparare, assaggiare e servire", 24, listaQuantita);
		ric = contrRic.getRicette().get(0);
		boolean contr1 = ric.getIngredienti().stream().anyMatch(i -> (i.getIngrediente().getNome().equals("Malto") && i.getIngrediente().getDisponibilita() == 15.6f && i.getIngrediente().getUnitaMisura().equals("Grammi")));
		boolean contr2 = ric.getIngredienti().stream().anyMatch(i -> (i.getIngrediente().getNome().equals("Luppolo") && i.getIngrediente().getDisponibilita() == 13 && i.getIngrediente().getUnitaMisura().equals("Grammi")));
		boolean contr3 = ric.getIngredienti().stream().anyMatch(i -> (i.getIngrediente().getNome().equals("Acqua") && i.getIngrediente().getDisponibilita() == 50 && i.getIngrediente().getUnitaMisura().equals("Litri")));
		
		boolean contr4 = ric.getNome().equals("RicettaUpdated");
		boolean contr5 = ric.getDescrizione().equals("Preparare, assaggiare e servire");
		boolean contr6 = ric.getTempoPreparazione() == 24;
		
		assertTrue(contr1 && contr2 && contr3 && contr4 && contr5 && contr6);
	}
	
	@Test
	public void deleteRicetta() {
		ControllerLotto contrNota = new ControllerLotto("password","test.db");
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
		ControllerRicetta contrRic = new ControllerRicetta(contrIng,contrEquip,contrNota,"password","test.db");
		contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		Ricetta ric = contrRic.getRicette().get(0);
		contrRic.rimuoviRicetta(ric.getIdRicetta());
		
		ArrayList<Ricetta> listaRicette = contrRic.getRicette();
		assertTrue(listaRicette.isEmpty());
		
	}
	
	
	@Test
	public void aggiornaDisponibilita() {
		ControllerLotto contrNota = new ControllerLotto("password","test.db");
		ControllerIngredienti contrIng = new ControllerIngredienti("password","test.db");
		contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		contrIng.aggiungiIngrediente("Luppolo",13,"Grammi");
		contrIng.aggiungiIngrediente("Acqua",50,"Litri");
		ArrayList<Ingrediente> listaIng = contrIng.getIngredienti();
		ArrayList<Quantita> listaQuantita = new ArrayList<Quantita>();
		Quantita q1 = new Quantita();
		q1.setIngrediente(listaIng.get(2));
		q1.setQuantitaNecessaria(15);
		Quantita q2 = new Quantita();
		q2.setIngrediente(listaIng.get(1));
		q2.setQuantitaNecessaria(10);
		Quantita q3 = new Quantita();
		q3.setIngrediente(listaIng.get(0));
		q3.setQuantitaNecessaria(40);
		listaQuantita.add(q1);
		listaQuantita.add(q2);
		listaQuantita.add(q3);
		ControllerEquipaggiamento contrEquip = new ControllerEquipaggiamento("password","test.db");
		contrEquip.aggiungiEquipaggiamento("Equip", 70);
		ControllerRicetta contrRic = new ControllerRicetta(contrIng,contrEquip,contrNota,"password","test.db");
		contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		Ricetta ric = contrRic.getRicette().get(0);
		boolean contr1 = contrRic.aggiornaDisponibilità(ric, 1);
		ArrayList<Ingrediente> listaIngredienti = contrIng.getIngredienti();
		Ingrediente ing1 = listaIngredienti.get(2);
		Ingrediente ing2 = listaIngredienti.get(1);
		Ingrediente ing3 = listaIngredienti.get(0);
		
		assertTrue(contr1 && Math.abs(ing1.getDisponibilita()-0.6) < 0.0001 && ing2.getDisponibilita() == 3 && ing3.getDisponibilita() == 10);
		
	}
	
	@Test
	public void provaRicetteScarsaDisponibilitaTest() {
		ControllerLotto contrNota = new ControllerLotto("password","test.db");
		ControllerEquipaggiamento contrEquip = new ControllerEquipaggiamento("password","test.db");
		ControllerIngredienti contrIng = new ControllerIngredienti("password","test.db");
		contrIng.aggiungiIngrediente("Malto",15.6f,"Grammi");
		contrIng.aggiungiIngrediente("Luppolo",13,"Grammi");
		contrIng.aggiungiIngrediente("Acqua",50,"Litri");
		ArrayList<Ingrediente> listaIng = contrIng.getIngredienti();
		ArrayList<Quantita> listaQuantita = new ArrayList<Quantita>();
		Quantita q1 = new Quantita();
		q1.setIngrediente(listaIng.get(2));
		q1.setQuantitaNecessaria(1);
		Quantita q2 = new Quantita();
		q2.setIngrediente(listaIng.get(1));
		q2.setQuantitaNecessaria(1);
		Quantita q3 = new Quantita();
		q3.setIngrediente(listaIng.get(0));
		q3.setQuantitaNecessaria(1);
		listaQuantita.add(q1);
		listaQuantita.add(q2);
		listaQuantita.add(q3);
		ArrayList<Quantita> listaQuantita2 = new ArrayList<Quantita>();
		q1 = new Quantita();
		q1.setIngrediente(listaIng.get(2));
		q1.setQuantitaNecessaria(80);
		q2 = new Quantita();
		q2.setIngrediente(listaIng.get(1));
		q2.setQuantitaNecessaria(80);
		q3 = new Quantita();
		q3.setIngrediente(listaIng.get(0));
		q3.setQuantitaNecessaria(80);
		listaQuantita2.add(q1);
		listaQuantita2.add(q2);
		listaQuantita2.add(q3);
		contrEquip.aggiungiEquipaggiamento("Equip", 30);
		ControllerRicetta contrRic = new ControllerRicetta(contrIng,contrEquip,contrNota,"password","test.db");
		contrRic.aggiungiRicetta("Ricetta1", "Preparare e poi assaggiare", 23, listaQuantita);
		contrRic.aggiungiRicetta("Ricetta2", "Preparare e poi assaggiare", 23, listaQuantita2);
		ArrayList<Ricetta> listaRicetteScarsaDisponibilita = contrRic.getRicetteScarsaDisponibilita();
		
		boolean contr1 = listaRicetteScarsaDisponibilita.size() == 1;
		boolean contr2 = listaRicetteScarsaDisponibilita.get(0).getNome().equals("Ricetta2");
	
		assertTrue(contr1 && contr2);
		
		
	}
	

}
