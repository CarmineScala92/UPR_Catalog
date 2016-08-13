package it.unisa.upr.junittest;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.IAmministratoreManager;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBAmministratoreTest extends TestCase{
	private IAmministratoreManager dbAmm;
	
	@Before
	public void setUp() {
		try{
			dbAmm = (IAmministratoreManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_AMMINISTRATORE);
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbAmm = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testModificaAmministratore() {
		Amministratore a = new Amministratore(33, "ciccio", "molliccio", "Amministratore", 33, "Ciccio", "Molliccio");
		boolean done = dbAmm.modificaAmministratore(a);
		assertEquals(true, done);
	}
	
	@Test
	public void testGetAmministratore() {
		Account acc = new Account(33, "", "", "");
		try {
			Amministratore amm = dbAmm.getAmministratore(acc);
			System.out.println("getAmministratore(Account): " + amm.getNome() + " " + amm.getCognome());
			assertNotNull(amm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminaAmministratore() {
		Amministratore amm = new Amministratore(34, "", "", "", 34, "", "");
		boolean done = dbAmm.eliminaAmministratore(amm);
		assertEquals(true, done);
	}
	
	@Test
	public void testInserisciAmministratore() {
		Amministratore amm = new Amministratore(36, "terry59", "terry.malco", "Amministratore", 36, "Terry", "Malco");
		boolean done = dbAmm.inserisciAmministratore(amm);
		assertEquals(true, done);
	}
	
	@Test
	public void testRicercaAmministratore() {
		try {
			ArrayList<Amministratore> list = dbAmm.getAmministratore("Terry", "Malco");
		/*	System.out.println("getAmministratore(Username, Tipologia):");	//per controllare che l'output è giusto
			for(Amministratore a : list)
				System.out.println(a.getUsername() + " " + a.getTipologia());
			System.out.println("Fine getAmministratore(Username, Tipologia) \n");
		*/	assertNotNull(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}