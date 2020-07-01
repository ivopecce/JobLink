package it.univaq.disim.oop.joblink.business;

import java.util.List;

import it.univaq.disim.oop.joblink.domain.Messaggio;
import it.univaq.disim.oop.joblink.domain.Utente;

public interface MessaggiService {
	void sendMessaggio(Messaggio messaggio) throws BusinessException;
	List<Messaggio> getMessaggiRicevuti(Utente utente) throws BusinessException;
	List<Messaggio> getMessaggiInviati(Utente utente) throws BusinessException;

}
