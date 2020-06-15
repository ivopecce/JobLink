package it.univaq.disim.oop.joblink.business.impl.file;

import java.io.File;

import it.univaq.disim.oop.joblink.business.JobLinkBusinessFactory;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.business.UtenteService;

public class FileJobLinkBusinessFactoryImpl extends JobLinkBusinessFactory {

	private UtenteService utenteService;
	private OffertaService offertaService;
	
	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "dati";
	private static final String OFFERTE_FILE_NAME = REPOSITORY_BASE + File.separator + "offerte.txt";
	private static final String UTENTI_FILE_NAME = REPOSITORY_BASE + File.separator + "utenti.txt";
	
	public FileJobLinkBusinessFactoryImpl() {

		offertaService = new FileOffertaServiceImpl(OFFERTE_FILE_NAME);
		utenteService = new FileUtenteServiceImpl(UTENTI_FILE_NAME);
	}
	
	@Override
	public UtenteService getUtenteService() {
		return utenteService;
	}

	@Override
	public OffertaService getOffertaService() {
		return offertaService;
	}

}
