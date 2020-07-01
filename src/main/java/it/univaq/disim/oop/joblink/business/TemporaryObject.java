/**
 * Classe per la generazione di un oggetto temporaneo contenente altri oggetti e che viene usata per il passaggio di piu' oggetti da inizializzare in alcune viste
 */
package it.univaq.disim.oop.joblink.business;

import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Persona;

public class TemporaryObject {
	private Persona persona;
	private Offerta offerta;
	private String cercaTitolo;
	private String cercaLocalita;
	
	public TemporaryObject(Offerta offerta, Persona persona) {
		this.persona = persona;
		this.offerta = offerta;
	}
	
	public TemporaryObject() {
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Offerta getOfferta() {
		return offerta;
	}

	public void setOfferta(Offerta offerta) {
		this.offerta = offerta;
	}

	public String getCercaTitolo() {
		return cercaTitolo;
	}

	public void setCercaTitolo(String cercaTitolo) {
		this.cercaTitolo = cercaTitolo;
	}

	public String getCercaLocalita() {
		return cercaLocalita;
	}

	public void setCercaLocalita(String cercaLocalita) {
		this.cercaLocalita = cercaLocalita;
	}
	
	
}
