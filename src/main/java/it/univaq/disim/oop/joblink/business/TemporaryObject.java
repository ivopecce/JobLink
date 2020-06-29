package it.univaq.disim.oop.joblink.business;

import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Persona;

public class TemporaryObject {
	private Persona persona;
	private Offerta offerta;
	
	public TemporaryObject(Offerta offerta, Persona persona) {
		this.persona = persona;
		this.offerta = offerta;
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
	
	
}
