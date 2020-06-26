package it.univaq.disim.oop.joblink.business.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.ProfiloPersonaService;
import it.univaq.disim.oop.joblink.domain.Esperienza;
import it.univaq.disim.oop.joblink.domain.Formazione;
import it.univaq.disim.oop.joblink.domain.LivelloSkill;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Possiede;
import it.univaq.disim.oop.joblink.domain.Skill;

public class DBProfiloPersonaServiceImpl implements ProfiloPersonaService {
	
	private Connection dbConnection;

	public DBProfiloPersonaServiceImpl(Connection connection) {
		this.dbConnection = connection;
	}

	@Override
	public List<Formazione> findAllFormazione(Persona persona) throws BusinessException {
		List<Formazione> result = new ArrayList<>();
		try {
			String sql = "CALL find_formazione(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(persona.getId(), 1);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				Formazione formazione = new Formazione();
				formazione.setId(rs.getInt(1));
				formazione.setTitolo(rs.getString(2));
				formazione.setDescrizione(rs.getString(3));
				formazione.setIstituto(rs.getString(4));
				formazione.setDataInizio(LocalDate.parse(rs.getString(5)));
				formazione.setDataFine(LocalDate.parse(rs.getString(6)));
				formazione.setVoto(rs.getInt(7));
				formazione.setPersona(persona);
				result.add(formazione);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Esperienza> findAllEsperienza(Persona persona) throws BusinessException {
		List<Esperienza> result = new ArrayList<>();
		try {
			String sql = "CALL find_esperienza(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(persona.getId(), 1);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				Esperienza esperienza = new Esperienza();
				esperienza.setId(rs.getInt(1));
				esperienza.setTitolo(rs.getString(2));
				esperienza.setAzienda(rs.getString(3));
				esperienza.setDataInizio(LocalDate.parse(rs.getString(4)));
				esperienza.setDataFine(LocalDate.parse(rs.getString(5)));
				esperienza.setDescrizione(rs.getString(6));
				esperienza.setLocalita(rs.getString(7));
				esperienza.setPersona(persona);
				result.add(esperienza);
			}
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Possiede> findAllSkill(Persona persona) throws BusinessException {
		List<Possiede> result = new ArrayList<>();
		try {
			String sql = "CALL find_skill(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(persona.getId(), 1);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				Skill skill = new Skill();
				Possiede possiede = new Possiede();
				skill.setId(rs.getInt(1));
				skill.setSkill(rs.getString(2));
				possiede.setSkill(skill);
				possiede.setPersona(persona);
				switch(rs.getString(3)){
					case "BASE":
						possiede.setLivelloPosseduto(LivelloSkill.BASE);
						break;
					case "MEDIO":
						possiede.setLivelloPosseduto(LivelloSkill.MEDIO);
						break;
					case "AVANZATO":
						possiede.setLivelloPosseduto(LivelloSkill.AVANZATO);
						break;
				}
				result.add(possiede);
			}
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

}
