package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneProdotti.DBMonografia;
import it.unisa.upr.data.gestioneProdotti.IMonografiaManager;
import it.unisa.upr.data.gestioneProdotti.Monografia;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBMonografiaTest extends TestCase{
private IMonografiaManager dbMono;
	
	@Before
	public void setUp() {
		try{
			dbMono = DBMonografia.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbMono = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testInserisciMonografia() {
		Monografia m = new Monografia(36, 1, "", "", "", "", 1, "", "monografia", "", "", "Ciao Monografia", "", "", "", "", "", "");
		boolean done = dbMono.inserisciMonografia(m);
		assertEquals(true, done);
	}
	
	@Test
	public void testModificaMonografia() {
		Monografia m = new Monografia(9, 1, "", "", "", "", 1, "", "monografia", "", "", "Ciao Monografia", "", "", "", "", "", "");
		boolean done = dbMono.modificaMonografia(m, "Citta_Editore", "Palermo");
		assertEquals(true, done);
	}
	
	@Test
	public void testEliminaMonografia() {
		Monografia m = new Monografia(10, 1, "", "", "", "", 1, "", "monografia", "", "", "Ciao Monografia", "", "", "", "", "", "");
		boolean done = dbMono.eliminaMonografia(m);
		assertEquals(true, done);
	}

	@Test
	public void testVisualizzaDettagliMonografia() {
		Prodotto p = new Prodotto(23, 1, "", "", "", "", 1, "", "", "", "");
		Monografia m = dbMono.visualizzaDettagliMonografia(p);
		System.out.println("visualizzaDettagliMonografia(): "+ m.getEditore());
		assertNotNull(m);
	}
}