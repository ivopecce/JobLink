package it.univaq.disim.oop.joblink.business;

import it.univaq.disim.oop.joblink.business.impl.file.FileJobLinkBusinessFactoryImpl;

public abstract class JobLinkBusinessFactory {
	private static JobLinkBusinessFactory factory = new FileJobLinkBusinessFactoryImpl();
	
	public static JobLinkBusinessFactory getInstance() {
		return factory;
	}
	
	public abstract UtenteService getUtenteService();
	
	public abstract OffertaService getOffertaService();
}
