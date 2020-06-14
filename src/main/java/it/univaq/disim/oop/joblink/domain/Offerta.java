package it.univaq.disim.oop.joblink.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Offerta {
	private Integer id;
	private Calendar dataCreazione;
	private String titoloOfferta;
	private String testoOfferta;
	private String localita;
	private StatoOfferta stato;
	
	private Azienda azienda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(int anno, int mese, int giorno) {
		this.dataCreazione = new GregorianCalendar();
		this.dataCreazione.set(Calendar.YEAR, anno);
		this.dataCreazione.set(Calendar.MONTH, mese);
		this.dataCreazione.set(Calendar.DAY_OF_MONTH, giorno);
	}

	public String getTitoloOfferta() {
		return titoloOfferta;
	}

	public void setTitoloOfferta(String titoloOfferta) {
		this.titoloOfferta = titoloOfferta;
	}

	public String getTestoOfferta() {
		return testoOfferta;
	}

	public void setTestoOfferta(String testoOfferta) {
		this.testoOfferta = testoOfferta;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}
	
	public StatoOfferta getStato() {
		return stato;
	}

	public void setStato(StatoOfferta stato) {
		this.stato = stato;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
	
	
	
	
}
