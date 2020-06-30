package it.univaq.disim.oop.joblink.domain;

public class Possiede {
	private Integer id;
	private Persona persona;
	private Skill skill;
	private LivelloSkill livelloPosseduto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
