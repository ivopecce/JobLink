package it.univaq.disim.oop.joblink.business;

import it.univaq.disim.oop.joblink.domain.Messaggio;

public interface MessaggiService {
	void sendMessaggio(Messaggio messaggio) throws BusinessException;
}
