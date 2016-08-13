package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneProdotti.Articolo_Libro;
import it.unisa.upr.data.gestioneProdotti.DBArticolo_Libro;
import it.unisa.upr.data.gestioneProdotti.IArticolo_LibroManager;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBArticolo_LibroTest extends TestCase{
	private IArticolo_LibroManager dbLibro;
	
	@Before
	public void setUp() {
		try{
			dbLibro = DBArticolo_Libro.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbLibro = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testInserisciArticolo_Libro() {
		Articolo_Libro l = new Articolo_Libro(36, 1, "", "", "", "", 1, "", "articolo_libro", "", "", "Ciao libro", "", "", "", "", "", "", "", "");
		boolean done = dbLibro.inserisciArticolo_Libro(l);
		assertEquals(true, done);
	}
	
	@Test
	public void testModificaArticolo_Libro() {
		Articolo_Libro l = new Articolo_Libro(15, 1, "", "", "", "", 1, "", "articolo_libro", "", "", "Ciao libro", "", "", "", "", "", "", "", "");
		boolean done = dbLibro.modificaArticoloLibro(l, "Nome_Volume", "Teorie della comunicazione");
		assertEquals(true, done);
	}
	
	@Test
	public void testEliminaArticolo_Libro() {
		Articolo_Libro l = new Articolo_Libro(16, 1, "", "", "", "", 1, "", "articolo_libro", "", "", "Ciao libro", "", "", "", "", "", "", "", "");
		boolean done = dbLibro.eliminaArticoloLibro(l);
		assertEquals(true, done);
	}

	@Test
	public void testVisualizzaDettagli() {
		Prodotto p = new Prodotto(1, 1, "", "", "", "", 1, "", "", "", "");
		Articolo_Libro l = dbLibro.visualizzaDettagli(p);
		System.out.println("visualizzaDettagli(): "+ l.getNome_Volume());
		assertNotNull(l);
	}
}