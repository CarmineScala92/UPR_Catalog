package it.unisa.upr.junittest;

import java.sql.Date;

import it.unisa.upr.data.gestioneProdotti.DBProceeding;
import it.unisa.upr.data.gestioneProdotti.IProceedingManager;
import it.unisa.upr.data.gestioneProdotti.Proceeding;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBProceedingTest extends TestCase{
	private IProceedingManager dbProc;
	
	@Before
	public void setUp() {
		try{
			dbProc = DBProceeding.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbProc = null;
		System.gc();	//per deallocare
	}
	
	@Test
	//togliere numero_volume e autori_Volume dal costruttore in DBProceeding
	public void testInserisciProceeding() {
		Proceeding p = new Proceeding(32, 1, "", "", "", "", 1, "", "proceeding", "", "", "", "", "", "", "", "", "", "", "", new Date(1972,2,12), "", "", "", "", "");
		boolean done = dbProc.inserisciProceeding(p);
		assertEquals(true, done);
	}
	
	@Test
	public void testModificaProceeding() {
		Proceeding p = new Proceeding(11, 1, "", "", "", "", 1, "", "proceeding", "", "", "", "", "", "", "", "", "", "", "", null, "", "", "", "", "");
		boolean done = dbProc.modificaProceeding(p, "Nome_Congresso", "Proceeding statali");
		assertEquals(true, done);
	}
	
	@Test
	public void testEliminaProceeding() {
		Proceeding p = new Proceeding(25, 1, "", "", "", "", 1, "", "proceeding", "", "", "", "", "", "", "", "", "", "", "", null, "", "", "", "", "");
		boolean done = dbProc.eliminaProceeding(p);
		assertEquals(true, done);
	}

	@Test
	public void testVisualizzaDettagliProceeding() {
		Prodotto p = new Prodotto(26, 1, "", "", "", "", 1, "", "proceeding", "", "");
		Proceeding b = dbProc.visualizzaDettagliProceeding(p);
		System.out.println("visualizzaDettagliProceeding(): "+ b.getEditore());
		assertNotNull(b);
	}
}