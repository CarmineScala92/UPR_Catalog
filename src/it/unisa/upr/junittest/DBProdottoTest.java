package it.unisa.upr.junittest;

import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.TestCase;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneProdotti.DBProdotto;
import it.unisa.upr.data.gestioneProdotti.IProdottoManager;
import it.unisa.upr.data.gestioneProdotti.Prodotto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBProdottoTest extends TestCase{
	private IProdottoManager dbProd;
	
	@Before
	public void setUp() {
		try{
			dbProd = DBProdotto.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		dbProd = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testEliminaProdotti() {
		Account a = new Account(2, "", "", "Ricercatore");
		boolean done = dbProd.eliminaProdotti(a);
		assertEquals(true, done);
	}
	
	@Test
	public void testCaricaProdotti() {
		Account a = new Account(5, "", "", "ricercatore");
		ArrayList<Prodotto> prod = dbProd.caricaProdotti(a);
		System.out.println("CaricaProdotti(Account):");
		for(Prodotto p : prod)
			System.out.println(p.getID_Proprietario() + " " + p.getTitolo());
		System.out.println("Fine CaricaProdotti(Account)\n");
		assertNotNull(prod);
	}
	
	@Test
	public void testCaricaProdottiCompleti() {
		try {
			ArrayList<Prodotto> prod = dbProd.caricaProdottiCompleti();
			System.out.println("CaricaProdottiCompleti():");
			for(Prodotto p : prod)
				System.out.println(p.getStato() + " " + p.getTitolo());
			System.out.println("Fine CaricaProdottiCompleti()\n");
			assertNotNull(prod);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCaricaProdottiAccettati() {
		try {
			ArrayList<Prodotto> prod = dbProd.caricaProdottiAccettati();
			System.out.println("CaricaProdottiAccettati():");
			for(Prodotto p : prod)
				System.out.println(p.getStato() + " " + p.getTitolo());
			System.out.println("Fine CaricaProdottiAccettati()\n");
			assertEquals("accettato", prod.get(0).getStato());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRicercaProdotto() {
		ArrayList<Prodotto> prod = dbProd.ricercaProdotto("", "2013", "brevetto", "", "", "", "");
		System.out.println("ricercaProdotto():");
		for(Prodotto p : prod)
			System.out.println(p.getTitolo() + " " + p.getTipologia() + " " + p.getAnno_Pubblicazione());
		System.out.println("Fine ricercaProdotto()\n");
		assertEquals("brevetto", prod.get(0).getTipologia());
	}
	
	@Test
	public void testRicercaProdottoUe() {
		ArrayList<Prodotto> prod = dbProd.ricercaProdottoUe("", "2013", "brevetto", "", "", "", "");
		System.out.println("ricercaProdottoUe():");
		for(Prodotto p : prod)
			System.out.println(p.getTitolo() + " " + p.getTipologia() + " " + p.getAnno_Pubblicazione());
		System.out.println("Fine ricercaProdottoUe()\n");
		assertEquals("brevetto", prod.get(0).getTipologia());
	}
	
	@Test
	public void testInserisciProdotto() {
		Prodotto p = new Prodotto(35, 23, "cuori solitari", "Ciccio Molliccio", "2013", "parla di cuori solitari", 0, "validato", "brevetto", "cuoriColitari.pdf", "");
		boolean done = dbProd.inserisciProdotto(p);
		assertEquals(true, done);
	}
	
	@Test
	public void testModificaProdotto() {
		Prodotto p = new Prodotto(30, 30, "", "", "", "", 0, "", "monografia", "", "");
		boolean done = dbProd.modificaProdotto(p, "Stato", "completo");
		assertEquals(true, done);
	}
	
	@Test
	public void testEliminaProdotto() {
		Prodotto p = new Prodotto(3, 30, "", "", "", "", 0, "", "", "", "");
		boolean done = dbProd.eliminaProdotto(p);
		assertEquals(true, done);
	}
	
	@Test
	public void testVisualizzaProdotto() {
		try {
			Prodotto p = dbProd.visualizzaProdotto(30);
			assertEquals("Canoni d’Arcadia. Muratori Maffei Lemene Ceva Quadrio ", p.getTitolo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUltimoIDProdotto() {	//funziona, si deve cambiare il valore dell'ultimo id
		try {
			int ultimo = dbProd.getUltimoIDProdotto();
			assertEquals(30, ultimo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testExists() {
		try {
			boolean done = dbProd.exists("Canoni d’Arcadia. Muratori Maffei Lemene Ceva Quadrio", "monografia", "2010");
			assertEquals(false, done);
			
			done  = dbProd.exists("Canoni d’Arcadia. Muratori Maffei Lemene Ceva Quadrio", "curatela", "2013");
			assertEquals(false, done);
			
			done  = dbProd.exists("Canoni Arcadia. Muratori Maffei Lemene Ceva Quadrio", "monografia", "2010");
			assertEquals(false, done);
			
			done  = dbProd.exists("Canoni Arcadia. Muratori Maffei Lemene Ceva Quadrio", "curatela", "2013");
			assertEquals(false, done);
			
			done  = dbProd.exists("Canoni d’Arcadia. Muratori Maffei Lemene Ceva Quadrio", "monografia", "2013");
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRicercaProdottiAmministratore() {
		ArrayList<Prodotto> list = dbProd.ricercaProdottiAmministratore();
		System.out.println("ricercaProdottiAmministratore():");
		for(Prodotto p : list)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdottiAmministratore()");
		assertNotNull(list);
	}
}