package it.unisa.upr.junittest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unisa.upr.data.gestioneProdotti.Curatela;
import it.unisa.upr.data.gestioneProdotti.DBCuratela;
import it.unisa.upr.data.gestioneProdotti.ICuratelaManager;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import junit.framework.TestCase;


public class DBCuratelaTest extends TestCase{
private ICuratelaManager dbCur;
	
	@Before
	public void setUp() {
		try{
			dbCur = DBCuratela.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbCur = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testInserisciCuratela() {
		Curatela c = new Curatela(39, 1, "", "", "", "", 1, "", "curatela", "", "", "Curatela per tutti", "", "", "", "", "", "", "", "", "");
		boolean done = dbCur.inserisciCuratela(c);
		assertEquals(true, done);
	}
	
	@Test
	public void testModificaCuratela() {
		Curatela c = new Curatela(8, 1, "", "", "", "", 1, "", "curatela", "", "", "Curatela per tutti", "", "", "", "", "", "", "", "", "");
		boolean done = dbCur.modificaCuratela(c, "Nome_Rivista", "Le mie informazioni");
		assertEquals(true, done);
	}
	
	@Test
	public void testEliminaCuratela() {
		Curatela c = new Curatela(7, 1, "", "", "", "", 1, "", "curatela", "", "", "Ciao Curatela", "", "", "", "", "", "", "", "", "");
		boolean done = dbCur.eliminaCuratela(c);
		assertEquals(true, done);
	}

	@Test
	public void testVisualizzaDettagli() {
		Prodotto p = new Prodotto(21, 1, "", "", "", "", 1, "", "", "", "");
		Curatela c = dbCur.visualizzaDettagli(p);
		System.out.println("visualizzaDettagli(): "+ c.getNome_Rivista());
		assertNotNull(c);
	}
}