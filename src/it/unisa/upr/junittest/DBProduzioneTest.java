package it.unisa.upr.junittest;

import java.util.ArrayList;

import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneProdotti.DBProduzione;
import it.unisa.upr.data.gestioneProdotti.IProduzioneManager;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import it.unisa.upr.data.gestioneProdotti.Produzione;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBProduzioneTest extends TestCase{
	private IProduzioneManager dbProduz;
	
	@Before
	public void setUp() {
		try{
			dbProduz = DBProduzione.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbProduz = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testEliminaProdotto() {
		Prodotto p = new Prodotto(30, 2, "", "", "", "", 1, "", "monografia", "", "");
		boolean done = dbProduz.elimina(p);
		assertEquals(true, done);
	}
	
	public void testEliminaAccount() {
		Account a = new Account(32, "", "" ,"ricercatore");
		boolean done  = dbProduz.elimina(a);
		assertEquals(true, done);
	}
	
	@Test
	public void testInserisciProduzione() {
		Produzione p = new Produzione(30, 12);
		boolean done = dbProduz.inserisciProduzione(p);
		assertEquals(true, done);
	}
	
	@Test
	public void testGetProduzioni() {
		Prodotto p = new Prodotto(5, 3, "", "", "", "", 1, "", "brevetto", "", "");
		ArrayList<Produzione> list = dbProduz.getProduzioni(p);
		System.out.println("getProduzioni():");
		for(Produzione pr: list)
			System.out.println(pr.getID_Prodotto() + " " + pr.getID_Ricercatore());
		System.out.println("Fine getProduzioni()\n");
		assertNotNull(list);
	}
	
	@Test
	public void testGetRicercatoriProduzione() {
		Prodotto p = new Prodotto(5, 3, "", "", "", "", 1, "", "brevetto", "", "");
		ArrayList<Ricercatore> list = dbProduz.getRicercatoriProduzione(p);
		System.out.println("getRicercatoriProduzione():");
		for(Ricercatore r : list)
			System.out.println(r.getNome() + " " + r.getCognome());
		System.out.println("Fine getRicercatoriProduzione()");
		assertNotNull(list);
	}
}