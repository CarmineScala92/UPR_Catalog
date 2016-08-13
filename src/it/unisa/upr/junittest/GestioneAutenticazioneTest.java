package it.unisa.upr.junittest;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.Responsabile;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.gestioneAutenticazione.GestioneAutenticazione;
import it.unisa.upr.gestioneAutenticazione.IFacadeGestioneAutenticazione;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GestioneAutenticazioneTest extends TestCase{
	private IFacadeGestioneAutenticazione gestAut;
	
	@Before
	public void setUp() {
		try {
			gestAut = GestioneAutenticazione.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		gestAut = null;
		System.gc();	//per deallocare
	}
		
	@Test
	public void testLogin() {
		try {
			Ricercatore r = (Ricercatore) gestAut.login("luigi92", "luigi.dellaglio");
			System.out.println("Login(): " + r.getNome() + " " + r.getCognome());
			assertNotNull(r);
			
			Responsabile res = (Responsabile) gestAut.login("pamelo", "pamelo.forte");
			System.out.println("Login(): " + res.getNome() + " " + res.getCognome());
			assertNotNull(res);
			
			Amministratore a1 = (Amministratore) gestAut.login("sergio", "sergio.floccari");
			System.out.println("Login(): " + a1.getNome() + " " + a1.getCognome());
			assertNotNull(a1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
/*	@Test
	public void testLogout() {	//da' errore perche' viene passato un HttpServletRequest nullo
		HttpServletRequest request = null;
		gestAut.logout(request);
	}
*/	
}