package it.univaq.disim.oop.joblink.domain;

public class Azienda extends Utente {
	private Integer idAzienda;
	private String denominazione;
	private String sede;
	private Integer numeroDipendenti;
	private String settore;
	private String sitoWeb;
	
	
	public Integer getIdAzienda() {
		return idAzienda;
	}
	public void setIdAzienda(Integer idAzienda) {
		this.idAzienda = idAzienda;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public Integer getNumeroDipendenti() {
		return numeroDipendenti;
	}
	public void setNumeroDipendenti(Integer numeroDipendenti) {
		this.numeroDipendenti = numeroDipendenti;
	}
	public String getSettore() {
		return settore;
	}
	public void setSettore(String settore) {
		this.settore = settore;
	}
	public String getSitoWeb() {
		return sitoWeb;
	}
	public void setSitoWeb(String sitoWeb) {
		this.sitoWeb = sitoWeb;
	}
		
	
}
