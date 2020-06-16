package it.univaq.disim.oop.joblink.domain;

public class Possiede {
	private Persona persona;
	private Skill skill;
	private LivelloSkill livelloPosseduto;
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public LivelloSkill getLivelloPosseduto() {
		return livelloPosseduto;
	}
	public void setLivelloPosseduto(LivelloSkill livelloPosseduto) {
		this.livelloPosseduto = livelloPosseduto;
	}
	
	
}
