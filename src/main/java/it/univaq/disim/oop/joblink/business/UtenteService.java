package it.univaq.disim.oop.joblink.business;

import it.univaq.disim.oop.joblink.domain.Utente;

public interface UtenteService {
	Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException;
}
