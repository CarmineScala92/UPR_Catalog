package it.unisa.upr.junittest;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.IAccountManager;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBAccountTest extends TestCase{
	private IAccountManager dbAcc;
	
	@Before 
	public void setUp() {
		try{
			dbAcc = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown(){
		dbAcc = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testModificaAccount() {
		Account acc;
		try {
			acc = dbAcc.getAccount("ciccio", "molliccio");
			acc.setPassword("ciccio.molliccio");
			boolean done = dbAcc.modificaAccount(acc);
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIsPresentAccount() {
		try {
			boolean done = dbAcc.isPresentAccount("nello54", "nello.polidoro");
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAccount(){
		Account acc;
		try {
			acc = dbAcc.getAccount("luigi92", "luigi.dellaglio");
			assertNotNull(acc);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminaAccount() {
		try{
			Account a = dbAcc.getAccount("ciccio", "ciccio.molliccio");
			boolean done = dbAcc.eliminaAccount(a);
			assertEquals(true, done);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInserisciAccount() {
		Account a = new Account(40, "ciccio", "molliccio", "Ricercatore");
		boolean done = dbAcc.inserisciAccount(a);
		assertEquals(true, done);
	}
	
	@Test
	public void testGetUltimoIDAccount() {
		try {
			int maxID = dbAcc.getUltimoIDAccount();
			assertNotNull(maxID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVisualizzaAccount() {
		try {
			Account a = dbAcc.visualizzaAccount(1);
			assertNotNull(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRicercaAccount() {
		ArrayList<Account> list = dbAcc.ricercaAccount("Nello", "Polidoro");
	/*	System.out.println("output di ricercaAccount():");	//per controllare che l'output è giusto
		for(Account a : list)
			System.out.println(a.getUsername() + " "+ a.getTipologia());
		System.out.println("fine output di ricercaAccount() \n");
	*/	assertNotNull(list);
	}
	
	@Test
	public void testGetListaAccount() {
		ArrayList<Account> list = dbAcc.getListaAccount();
	/*	System.out.println("output di getListaAccount():");	//per controllare che l'output è giusto
		for(Account a : list)
			System.out.println(a.getUsername() + " "+ a.getTipologia());
		System.out.println("fine output di getListaAccount() \n");
	*/	assertNotNull(list);
	}
}