package it.unisa.upr.junittest;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.DBRicercatore;
import it.unisa.upr.data.gestioneAutenticazione.IRicercatoreManager;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBRicercatoreTest extends TestCase{
	private IRicercatoreManager dbRic;
	
	@Before
	public void setUp() {
		try{
			dbRic = DBRicercatore.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbRic = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetRicercatore() {
		Account a = new Account(3,"luigi","luigi.dellaglio","ricercatore");
		try {
			Ricercatore r = dbRic.getRicercatore(a);
			System.out.println("getRicercatore(Account) : "+r.getNome());
			assertNotNull(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testModificaRicercatore() {
		Ricercatore r = new Ricercatore(13, "ernesto", "ernesto.sparalesto", "ricercatore", 13, "","","","1910-10-10","","","", "", "1970-3-10", 1, 2, "", "");
		boolean done = dbRic.modificaRicercatore(r);
		assertEquals(true, done);
	}
	
	@Test
	public void testGetListaRicercatori() {
		try {
			ArrayList<Ricercatore> ric = dbRic.getListaRicercatori();
			System.out.println("getListaRicercatori() :");
			for(Ricercatore r : ric)
				System.out.println(r.getNome() + " " + r.getEmail());
			System.out.println("Fine getListaRicercatori()\n");
			assertNotNull(ric);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminaRicercatore() {
		Ricercatore r = new Ricercatore(26, "", "", "ricercatore", 26, "", "", "", "1980-10-20", "", "", "", "", "2010-20-10", 1, 1, "", "");
		boolean done = dbRic.eliminaRicercatore(r);
		assertEquals(true, done);
	}
	
	@Test
	public void testInserisciRicercatore() {
		Ricercatore r = new Ricercatore(32, "ciao182", "ciao.ciao", "ricercatore", 32, "Giorgio", "Rosati", "GRGRST12I13H123A", "1960-2-10","Nola", "Na", "2298776235", "ciao@gmail.com", "2000-10-10", 1, 1, "ricercatore", "M");
		boolean done = dbRic.inserisciRicercatore(r);
		assertEquals(true, done);
	}
	
	@Test
	public void testRicercaRicercatore() {
		try {
			ArrayList<Ricercatore> ric = dbRic.getRicercatore("gi", "");
			System.out.println("getRicercatore(Nome,Cognome) :");
			for(Ricercatore r : ric)
				System.out.println(r.getNome() + " " + r.getCognome());
			System.out.println("Fine getRicercatore(Nome,Cognome)\n");
			assertNotNull(ric);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
