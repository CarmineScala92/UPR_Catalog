package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneProdotti.Altro;
import it.unisa.upr.data.gestioneProdotti.DBAltro;
import it.unisa.upr.data.gestioneProdotti.IAltroManager;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBAltroTest extends TestCase{
	private IAltroManager dbAltro;
	
	@Before
	public void setUp() {
		try{
			dbAltro = DBAltro.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbAltro = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testVisualizzaDettagliAltro() {
		Prodotto p = new Prodotto(13, 1, "", "", "", "", 0, "", "", "", "");
		Altro a = dbAltro.visualizzaDettagliAltro(p);
		System.out.println("visualizzaDettagliAltro(): nome_volume = " + a.getNome_Volume());
		assertNotNull(a);
	}
	
	@Test
	public void testInserisciAltro() {
		Altro a = new Altro(34, 12, "", "", "", "", 0, "", "altro", "", "", "", "", "", "", "");
		boolean done = dbAltro.inserisciAltro(a);
		assertEquals(true, done);
	}

	@Test
	public void testModificaAltro() {
		Altro a = new Altro(27, 27, "", "", "", "", 1, "", "", "", "", "", "", "", "", "");
		boolean done = dbAltro.modificaAltro(a, "Nome_Volume", "Ciao mondo");
		assertEquals(true, done);
	}
	
	@Test
	public void testEliminaAltro() {
		Altro a = new Altro(14, 27, "", "", "", "", 1, "", "altro", "", "", "", "", "", "", "");
		boolean done = dbAltro.eliminaAltro(a);
		assertEquals(true, done);
	}	
}