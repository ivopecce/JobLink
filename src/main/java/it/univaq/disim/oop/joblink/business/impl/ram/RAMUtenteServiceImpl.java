package it.univaq.disim.oop.joblink.business.impl.ram;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.UtenteNotFoundException;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Utente;

public class RAMUtenteServiceImpl implements UtenteService {

	@Override
	public Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException {
		if("azienda".equalsIgnoreCase(username)) {
			Azienda azienda = new Azienda();
			azienda.setUsername(username);
			azienda.setPassword(password);
			azienda.setDenominazione("Telespazio");
			azienda.setEmail("info@telespazio.com");
			azienda.setTelefono("+390863100000");
			azienda.setSitoWeb("www.telespazio.com");
			azienda.setSede("Fucino");
			azienda.setSettore("Telecomunicazioni");
			azienda.setNumeroDipendenti(5000);
			
			return azienda;
		}
		
		if("persona".equalsIgnoreCase(username)) {
			Persona persona = new Persona();
			persona.setUsername(username);
			persona.setPassword(password);
			persona.setCognome("Pecce");
			persona.setNome("Ivo");
			persona.setDataDiNascita(1990, 10, 1);
			persona.setGenere("Maschile");
			persona.setEmail("email@esempio.it");
			persona.setTelefono("+390863000000");
			persona.setResidenza("Avezzano");
			
			return persona;
			
		}
		
		throw new UtenteNotFoundException();
	}
	
}
