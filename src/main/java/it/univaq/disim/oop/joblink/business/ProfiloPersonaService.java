package it.univaq.disim.oop.joblink.business;

import java.util.List;

import it.univaq.disim.oop.joblink.domain.Esperienza;
import it.univaq.disim.oop.joblink.domain.Formazione;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Possiede;


public interface ProfiloPersonaService {
	List<Formazione> findAllFormazione(Persona persona) throws BusinessException;
	List<Esperienza> findAllEsperienza(Persona persona) throws BusinessException;
	List<Possiede> findAllSkill(Persona persona) throws BusinessException;
}
