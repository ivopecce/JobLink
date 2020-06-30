package it.univaq.disim.oop.joblink.domain;

import java.time.LocalDate;

public class Messaggio {
	private Integer id;
	private String oggetto;
	private String testo;
	private LocalDate data;
	
	private Azienda azienda;
	private Persona persona;
	private Utente mittente;
	private Utente destinatario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOggetto() {
		return oggetto;
	}
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Azienda getAzienda() {
		return azienda;
	}
	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Utente getMittente() {
		return mittente;
	}
	public void setMittente(Utente mittente) {
		this.mittente = mittente;
	}
	public Utente getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Utente destinatario) {
		this.destinatario = destinatario;
	}
	
	
}
