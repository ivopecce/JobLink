package it.univaq.disim.oop.joblink.domain;

import java.time.LocalDate;

public class Offerta {
	private Integer id;
	private LocalDate dataCreazione;
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

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate date) {
		this.dataCreazione = date;
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
