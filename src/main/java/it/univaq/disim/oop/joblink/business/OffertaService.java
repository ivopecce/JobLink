package it.univaq.disim.oop.joblink.business;

import java.util.List;

import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Persona;

public interface OffertaService {
	List<Offerta> findAllOfferte(Azienda azienda) throws BusinessException;
	
	Offerta findOffertaById(int id) throws BusinessException;
	
	List<Offerta> findOfferteAttinenti(Persona persona) throws BusinessException;
	
	void createOfferta(Offerta offerta) throws BusinessException;

	void updateOfferta(Offerta offerta) throws BusinessException;
	
	void deleteOfferta(Offerta offerta) throws BusinessException;
	
	Boolean getCandidatura(Offerta offerta, Persona persona) throws BusinessException;
	void SetCandidatura(Offerta offerta, Persona persona, Boolean candidatura) throws BusinessException;

}
