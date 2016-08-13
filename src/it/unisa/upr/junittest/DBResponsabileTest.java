package it.unisa.upr.junittest;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.IResponsabileManager;
import it.unisa.upr.data.gestioneAutenticazione.Responsabile;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBResponsabileTest extends TestCase{
	private IResponsabileManager dbResp;
	
	@Before
	public void setUp() {
		try{
			dbResp = (IResponsabileManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RESPONSABILE);
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbResp = null;
		System.gc();	//per deallocare
	}

	@Test
	public void testGetResponsabile() {
		Account a = new Account(35, "", "", "responsabile");
		try {
			Responsabile r = dbResp.getResponsabile(a);
			System.out.println(r.getNome());
			assertNotNull(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificaResponsabile() {
		Responsabile r = new Responsabile(35, "pamelo", "pamelo.forte", "responsabile", 35, "Pamelo", "Fortino");
		boolean done = dbResp.modificaResponsabile(r);
		assertEquals(true, done);
	}
	
	@Test
	public void testEliminaResponsabile() {
		Responsabile r = new Responsabile(35, "", "", "responsabile", 35, "", "");
		boolean done = dbResp.eliminaResponsabile(r);
		assertEquals(true, done);
	}
	
	@Test
	public void testInserisciResponsabile() {
		Responsabile r = new Responsabile(35, "ciccio", "ciccio.forte", "responsabile", 35, "Ciccio", "Forte");
		dbResp.inserisciResponsabile(r);
	}
	
	@Test
	public void testRicercaResponsabile() {
		try {
			ArrayList<Responsabile> resp = dbResp.getResponsabile("Pamelo", "Forte");
			assertNotNull(resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}