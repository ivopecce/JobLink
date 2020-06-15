package it.univaq.disim.oop.joblink.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.UtenteNotFoundException;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Utente;

public class FileUtenteServiceImpl implements UtenteService {
	private static final String REPOSITORY_BASE = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "dati";
	private static final String UTENTI_FILE_NAME = REPOSITORY_BASE + File.separator + "utenti.txt";

	@Override
	public Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException {
		try {
			FileData filedata = Utility.readAllRows(UTENTI_FILE_NAME);
			for(String[] colonne : filedata.getRighe()) {
				if((colonne[1].equals(username)) && colonne[2].contentEquals(password)) {
					Utente utente = null;
					switch(colonne[3]) {
					case "azienda":
						utente = new Azienda();
						break;
					case "persona":
						utente = new Persona();
						break;
					default:
						break;
					}
					
					if(utente != null) {
						utente.setId(Integer.parseInt(colonne[0]));
						utente.setUsername(username);
						utente.setPassword(password);
						if(utente instanceof Azienda) {
							((Azienda) utente).setDenominazione(colonne[4]);
							utente.setEmail(colonne[5]);
							utente.setTelefono(colonne[6]);
							((Azienda) utente).setSitoWeb(colonne[7]);
							((Azienda) utente).setSede(colonne[8]);
							((Azienda) utente).setSettore(colonne[9]);
							((Azienda) utente).setNumeroDipendenti(Integer.parseInt(colonne[10]));
						}
						if(utente instanceof Persona) {
							((Persona) utente).setCognome(colonne[4]);
							((Persona) utente).setNome(colonne[5]);
							Date date;
							try {
								date = new SimpleDateFormat("yyyy-MM-dd").parse(colonne[6]);
								((Persona) utente).setDataDiNascita(date.getYear(), date.getMonth(), date.getDay());
							} catch (ParseException e) {
								e.printStackTrace();
							}
							((Persona) utente).setGenere(colonne[7]);
							utente.setEmail(colonne[8]);
							utente.setTelefono(colonne[9]);
							((Persona) utente).setResidenza(colonne[10]);
						}
					}
					return utente;
				}
			}
			throw new UtenteNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
			}
	}
}

