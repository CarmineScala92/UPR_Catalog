package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneProdotti.Brevetto;
import it.unisa.upr.data.gestioneProdotti.DBBrevetto;
import it.unisa.upr.data.gestioneProdotti.IBrevettoManager;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBBrevettoTest extends TestCase{
	private IBrevettoManager dbBrev;
	
	@Before
	public void setUp() {
		try{
			dbBrev = DBBrevetto.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbBrev = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testInserisciBrevetto() {
		Brevetto b = new Brevetto(36, 1, "", "", "", "", 1, "", "brevetto", "", "", "Proprieta brevetto", "", "", "", "");
		boolean done = dbBrev.inserisciBrevetto(b);
		assertEquals(true, done);
	}
	
	@Test
	public void testModificaBrevetto() {
		Brevetto b = new Brevetto(5, 1, "", "", "", "", 1, "", "brevetto", "", "", "Ciao brevetto", "", "", "", "");
		boolean done = dbBrev.modificaBrevetto(b, "Numero_Brevetto", "2");
		assertEquals(true, done);
	}
	
	@Test
	public void testEliminaBrevetto() {
		Brevetto b = new Brevetto(6, 1, "", "", "", "", 1, "", "brevetto", "", "", "Ciao brevetto", "", "", "", "");
		boolean done = dbBrev.eliminaBrevetto(b);
		assertEquals(true, done);
	}

	@Test
	public void testVisualizzaDettagli() {
		Prodotto p = new Prodotto(19, 1, "", "", "", "", 1, "", "", "", "");
		Brevetto b = dbBrev.visualizzaDettagli(p);
		System.out.println("visualizzaDettagli(): "+ b.getProprieta());
		assertNotNull(b);
	}
}