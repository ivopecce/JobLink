package it.univaq.disim.oop.joblink.domain;

public class Richiesta {
	private Integer id;
	private Offerta offerta;
	private Skill skill;
	private LivelloSkill livelloRichiesto;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Offerta getOfferta() {
		return offerta;
	}
	public void setOfferta(Offerta offerta) {
		this.offerta = offerta;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public LivelloSkill getLivelloRichiesto() {
		return livelloRichiesto;
	}
	public void setLivelloRichiesto(LivelloSkill livelloRichiesto) {
		this.livelloRichiesto = livelloRichiesto;
	}
	
	
	
}
