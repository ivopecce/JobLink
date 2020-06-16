package it.univaq.disim.oop.joblink.business;

import java.sql.Date;

import it.univaq.disim.oop.joblink.domain.Utente;

public interface UtenteService {
	Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException;
	void registerAzienda(String username, String password, String email, String telefono, String denominazione, String sede, String settore, String sitoweb, Integer dipendenti) throws BusinessException;
	void registerPersona(String username, String password, String email, String telefono, String cognome, String nome, Date dataDiNascita, String genere, String residenza) throws BusinessException;

}
