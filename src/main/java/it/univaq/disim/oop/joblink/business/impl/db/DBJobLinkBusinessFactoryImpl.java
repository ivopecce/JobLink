package it.univaq.disim.oop.joblink.business.impl.db;

import java.sql.SQLException;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.business.ProfiloPersonaService;
import it.univaq.disim.oop.joblink.business.UtenteService;

public class DBJobLinkBusinessFactoryImpl extends JobLinkBusinessFactory{
	private UtenteService utenteService;
	private OffertaService offertaService;
	private ProfiloPersonaService profiloPersonaService;
	private DBConnector dbConnector = new DBConnector();
	
	public DBJobLinkBusinessFactoryImpl() {
		try {
		utenteService = new DBUtenteServiceImpl(dbConnector.getConnection());
		offertaService = new DBOffertaServiceImpl(dbConnector.getConnection());
		profiloPersonaService = new DBProfiloPersonaServiceImpl(dbConnector.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public UtenteService getUtenteService() {
		return utenteService;
	}
	
	@Override
	public OffertaService getOffertaService() {
		return offertaService;
	}
	
	@Override
	public ProfiloPersonaService getProfiloPersonaService() {
		return profiloPersonaService;
	}
	
}
