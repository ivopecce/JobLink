package it.univaq.disim.oop.joblink.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;



public class Persona extends Utente {
	private String cognome;
	private String nome;
	private Calendar dataDiNascita;
	private String genere;
	private String residenza;
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(int anno, int mese, int giorno) {
		dataDiNascita = new GregorianCalendar();
		this.dataDiNascita.set(Calendar.YEAR, anno);
		this.dataDiNascita.set(Calendar.MONTH, mese);
		this.dataDiNascita.set(Calendar.DAY_OF_MONTH, giorno);
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getResidenza() {
		return residenza;
	}
	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}
	
	
}
