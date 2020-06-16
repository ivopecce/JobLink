package it.univaq.disim.oop.joblink.domain;

public class Skill {
	private Integer id;
	private String skill;
	private LivelloSkill livello;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public LivelloSkill getLivello() {
		return livello;
	}
	
	
}
