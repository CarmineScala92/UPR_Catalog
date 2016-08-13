package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneProdotti.Articolo_Rivista;
import it.unisa.upr.data.gestioneProdotti.DBArticolo_Rivista;
import it.unisa.upr.data.gestioneProdotti.IArticolo_RivistaManager;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBArticolo_RivistaTest extends TestCase{
	private IArticolo_RivistaManager dbRiv;
	
	@Before
	public void setUp() {
		try{
			dbRiv = DBArticolo_Rivista.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbRiv = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testInserisciArticolo_Rivista() {
		Articolo_Rivista riv = new Articolo_Rivista(40, 2, "", "", "", "", 0, "", "articolo rivista", "", "", "Rivista", "", "", "", "");
		boolean done = dbRiv.inserisciArticolo_Rivista(riv);
		assertEquals(true, done);
	}
	
	@Test
	public void testModificaArticolo_Rivista() {
		Articolo_Rivista riv = new Articolo_Rivista(28, 2, "", "", "", "", 0, "", "articolo rivista", "", "", "Rivista", "", "", "", "");
		boolean done = dbRiv.modificaArticolo_Rivista(riv, "Numero_Volume", "10");
		assertEquals(true, done);
	}

	@Test
	public void testEliminaArticolo_Rivista() {
		Articolo_Rivista riv = new Articolo_Rivista(4, 2, "", "", "", "", 0, "", "articolo rivista", "", "", "Rivista", "", "", "", "");
		boolean done = dbRiv.eliminaArticolo_Rivista(riv);
		assertEquals(true, done);
	}
	
	@Test
	public void testVisualizzaDettagli() {
		Prodotto p = new Prodotto(2, 2, "", "", "", "", 1, "", "", "", "");
		Articolo_Rivista riv = dbRiv.visualizzaDettagli(p);
		System.out.println("visualizzaDettagli(): " + riv.getNome_Rivista());
		assertNotNull(riv);
	}
}