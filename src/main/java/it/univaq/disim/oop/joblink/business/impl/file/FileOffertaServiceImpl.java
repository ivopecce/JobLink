package it.univaq.disim.oop.joblink.business.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.StatoOfferta;


public class FileOffertaServiceImpl implements OffertaService {
//	

	private String offerteFileName;
	
	public FileOffertaServiceImpl(String offerteFileName) {
		this.offerteFileName = offerteFileName;
	}

	@Override
	public List<Offerta> findAllOfferte(Azienda azienda) throws BusinessException {
		List<Offerta> result = new ArrayList<>();
		try {
			FileData fileData = Utility.readAllRows(offerteFileName);
			for(String[] colonne : fileData.getRighe()) {
				Offerta offerta = new Offerta();
				offerta.setId(Integer.parseInt(colonne[0]));
				Date date;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(colonne[1]);
					offerta.setDataCreazione(date.getYear(), date.getMonth(), date.getDay());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				offerta.setTitoloOfferta(colonne[2]);
				offerta.setTestoOfferta(colonne[3]);
				offerta.setLocalita(colonne[4]);
				if(colonne[5]=="ATTIVA") offerta.setStato(StatoOfferta.ATTIVA);
				if(colonne[5]=="NON_ATTIVA") offerta.setStato(StatoOfferta.NON_ATTIVA);
				Azienda az = new Azienda();
				az.setDenominazione(colonne[6]);
				offerta.setAzienda(az);
				result.add(offerta);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
		return result;
	}

	@Override
	public void createOfferta(Offerta offerta) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(offerteFileName);
			try(PrintWriter writerPrintWriter = new PrintWriter(new File(offerteFileName))){
				long contatore = fileData.getContatore();
				writerPrintWriter.println(contatore+1);
				for(String[] righe : fileData.getRighe()) {
					writerPrintWriter.println(String.join(Utility.SEPARATORE_COLONNA, righe));
				}
				StringBuilder row = new StringBuilder();
				row.append(contatore);
				row.append(Utility.SEPARATORE_COLONNA);
				StringBuilder s = new StringBuilder();
				s.append(offerta.getDataCreazione().YEAR);
				s.append("-");
				s.append(offerta.getDataCreazione().MONTH);
				s.append("-");
				s.append(offerta.getDataCreazione().DAY_OF_MONTH);
				row.append(s);
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(offerta.getTitoloOfferta());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(offerta.getTestoOfferta());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(offerta.getLocalita());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(offerta.getStato().toString());
				row.append(Utility.SEPARATORE_COLONNA);
				row.append(offerta.getAzienda().getDenominazione());
				writerPrintWriter.println(row.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	@Override
	public void updateOfferta(Offerta offerta) throws BusinessException {
		try {
			FileData fileData = Utility.readAllRows(offerteFileName);
			try (PrintWriter writerPrintWriter = new PrintWriter(new File(offerteFileName))) {
				writerPrintWriter.println(fileData.getContatore());
				for (String[] righe : fileData.getRighe()) {
					if (Long.parseLong(righe[0]) == offerta.getId()) {
						StringBuilder row = new StringBuilder();
						row.append(offerta.getId());
						row.append(Utility.SEPARATORE_COLONNA);
						StringBuilder s = new StringBuilder();
						s.append(offerta.getDataCreazione().YEAR);
						s.append("-");
						s.append(offerta.getDataCreazione().MONTH);
						s.append("-");
						s.append(offerta.getDataCreazione().DAY_OF_MONTH);
						row.append(s);
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(offerta.getTitoloOfferta());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(offerta.getTestoOfferta());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(offerta.getLocalita());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(offerta.getStato().toString());
						row.append(Utility.SEPARATORE_COLONNA);
						row.append(offerta.getAzienda().getDenominazione());
						writerPrintWriter.println(row.toString());
					} else {
						writerPrintWriter.println(String.join(Utility.SEPARATORE_COLONNA, righe));
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	@Override
	public Offerta findOffertaById(int id) throws BusinessException {
		Offerta result = new Offerta();
		try {
			FileData fileData = Utility.readAllRows(offerteFileName);
			for (String[] colonne : fileData.getRighe()) {
				if (Integer.parseInt(colonne[0]) == id) {
					result.setId(id);
					Date date;
					try {
						date = new SimpleDateFormat("yyyy-MM-dd").parse(colonne[1]);
						result.setDataCreazione(date.getYear(), date.getMonth(), date.getDay());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					result.setTitoloOfferta(colonne[2]);
					result.setTestoOfferta(colonne[3]);
					result.setLocalita(colonne[4]);
					if(colonne[5]=="ATTIVA") result.setStato(StatoOfferta.ATTIVA);
					if(colonne[5]=="NON_ATTIVA") result.setStato(StatoOfferta.NON_ATTIVA);
					Azienda az = new Azienda();
					az.setDenominazione(colonne[6]);
					result.setAzienda(az);
					return result;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

		return result;
	}


}
