package it.univaq.disim.oop.joblink.business;

import java.sql.SQLException;
import java.time.LocalDate;

import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Utente;

public interface UtenteService {
	Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException;
	void registerAzienda(String username, String password, String email, String telefono, String denominazione, String sede, String settore, String sitoweb, Integer dipendenti) throws SQLException, BusinessException;
	void registerPersona(String username, String password, String email, String telefono, String cognome, String nome, LocalDate dataDiNascita, String genere, String residenza) throws SQLException, BusinessException;
	void deletePersona(Persona persona) throws BusinessException;
	void deleteAzienda(Azienda azienda) throws BusinessException;
}
