package it.univaq.disim.oop.joblink.business;

import java.util.List;

import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Offerta;

public interface OffertaService {
	List<Offerta> findAllOfferte(Azienda azienda) throws BusinessException;
	
	void createOfferta(Offerta offerta) throws BusinessException;

	void updateOfferta(Offerta offerta) throws BusinessException;
}
