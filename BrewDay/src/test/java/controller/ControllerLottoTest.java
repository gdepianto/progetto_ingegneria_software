package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import database_layer.MapperEquipaggiamento;
import database_layer.MapperIngrediente;
import database_layer.MapperLotto;
import database_layer.MapperRicetta;
import model.Equipaggiamento;
import model.Lotto;
import model.NotaGusto;
import view.BrewDayApplication;

public class ControllerLottoTest {

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
	public void aggiuntaLottoTest(){
		ControllerLotto contrLotto = new ControllerLotto("password","test.db");
		contrLotto.inserisciLotto(1, "Ciao", new Date(), 34, new Equipaggiamento("Equip",234), -1);
		ArrayList<Lotto> listaLotti = contrLotto.getLotti(1);
		assertEquals("Ciao",listaLotti.get(0).getCommento());
	}
	
	@Test
	public void aggiuntaNotaGustoTest() {
		ControllerLotto contrLotto = new ControllerLotto("password","test.db");
		contrLotto.inserisciLotto(1, "Ciao", new Date(), 34, new Equipaggiamento("Equip",234), 5);
		ArrayList<Lotto> listaLotti = contrLotto.getLotti(1);
		boolean contr1 = listaLotti.get(0) instanceof NotaGusto;
		boolean contr2 = listaLotti.get(0).getCommento().equals("Ciao");
		boolean contr3 = ((NotaGusto)listaLotti.get(0)).getValutazione() == 5;
		assertTrue(contr1 && contr2 && contr3);
	}

	@Test
	public void rimuoviNotaTest() {
		ControllerLotto contrLotto = new ControllerLotto("password","test.db");
		contrLotto.inserisciLotto(1, "Ciao", new Date(), 34, new Equipaggiamento("Equip",234), -1);
		int id = contrLotto.getLotti(1).get(0).getIdLotto();
		contrLotto.rimuoviLotto(id);
		assertEquals(0,contrLotto.getLotti(1).size());
	}
}
