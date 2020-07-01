/**
 * Abstract factory dei services
 */
package it.univaq.disim.oop.joblink.business;

import it.univaq.disim.oop.joblink.business.impl.db.DBJobLinkBusinessFactoryImpl;

public abstract class JobLinkBusinessFactory {
	private static JobLinkBusinessFactory factory = new DBJobLinkBusinessFactoryImpl();
	
	public static JobLinkBusinessFactory getInstance() {
		return factory;
	}
	
	public abstract UtenteService getUtenteService();
	
	public abstract OffertaService getOffertaService();
	
	public abstract ProfiloPersonaService getProfiloPersonaService();
	
	public abstract SkillService getSkillService();
	
	public abstract MessaggiService getMessaggiService();
}
