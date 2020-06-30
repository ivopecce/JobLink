package it.univaq.disim.oop.joblink.business;

import java.util.List;

import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Possiede;
import it.univaq.disim.oop.joblink.domain.Richiesta;

public interface SkillService {
	String skillRichieste(Offerta offerta) throws BusinessException;
	List<Richiesta> getSkillRichieste(Offerta offerta) throws BusinessException;
	void createRichiesta(Richiesta richiesta) throws BusinessException;
	void updateRichiesta(Richiesta richiesta) throws BusinessException;
	void deleteRichiesta(Richiesta richiesta) throws BusinessException;
	void createPossiede(Possiede possiede) throws BusinessException;
	void updatePossiede(Possiede possiede) throws BusinessException;
	void deletePossiede(Possiede possiede) throws BusinessException;
}
