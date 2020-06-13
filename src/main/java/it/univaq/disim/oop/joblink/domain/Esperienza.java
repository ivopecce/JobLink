package it.univaq.disim.oop.joblink.domain;

import java.util.Calendar;

public class Esperienza {
	private Integer id;
	private String titolo;
	private String azienda;
	private Calendar dataInizio;
	private Calendar dataFine;
	private String descrizione;
	private String localita;
	
	private Persona persona;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public Calendar getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Calendar dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Calendar getDataFine() {
		return dataFine;
	}

	public void setDataFine(Calendar dataFine) {
		this.dataFine = dataFine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
