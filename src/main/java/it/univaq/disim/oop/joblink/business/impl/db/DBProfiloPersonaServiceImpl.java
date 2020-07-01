/**
 * Impementazione per database del service del profilo persona
 */
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

	/*Restituisce tutte le esperienze formative della persona passata in ingresso*/
	@Override
	public List<Formazione> findAllFormazione(Persona persona) throws BusinessException {
		List<Formazione> result = new ArrayList<>();
		try {
			String sql = "CALL find_formazione(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(persona.getIdPersona(), 1);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				Formazione formazione = new Formazione();
				formazione.setId(rs.getInt(1));
				formazione.setTitolo(rs.getString(2));
				formazione.setDescrizione(rs.getString(3));
				formazione.setIstituto(rs.getString(4));
				formazione.setDataInizio(LocalDate.parse(rs.getString(5)));
				if(rs.getDate(6) != null) formazione.setDataFine(LocalDate.parse(rs.getString(6)));
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

	/*Restituisce tutte le esperienze lavorative della persona in ingresso*/
	@Override
	public List<Esperienza> findAllEsperienza(Persona persona) throws BusinessException {
		List<Esperienza> result = new ArrayList<>();
		try {
			String sql = "CALL find_esperienza(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(persona.getIdPersona(), 1);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				Esperienza esperienza = new Esperienza();
				esperienza.setId(rs.getInt(1));
				esperienza.setTitolo(rs.getString(2));
				esperienza.setAzienda(rs.getString(3));
				esperienza.setDataInizio(LocalDate.parse(rs.getString(4)));
				if(rs.getDate(5) != null) esperienza.setDataFine(LocalDate.parse(rs.getString(5)));
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

	/*Restituisce tutte le skill di una persona*/
	@Override
	public List<Possiede> findAllSkill(Persona persona) throws BusinessException {
		List<Possiede> result = new ArrayList<>();
		try {
			String sql = "CALL find_skill(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(persona.getIdPersona(), 1);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				Skill skill = new Skill();
				Possiede possiede = new Possiede();
				possiede.setId(rs.getInt(4));
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	/*Aggiunge una formazione ad una persona*/
	@Override
	public void createFormazione(Formazione formazione) throws BusinessException {
		try {
			String sql = "CALL create_formazione(?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setString(1, formazione.getTitolo());
			ps.setString(2, formazione.getDescrizione());
			ps.setString(3, formazione.getIstituto());
			ps.setString(4, formazione.getDataInizio().toString());
			if(formazione.getDataFine().equals(null)) ps.setString(5, null);
			else ps.setString(5, formazione.getDataFine().toString());
			ps.setInt(6, formazione.getVoto());
			ps.setInt(7, formazione.getPersona().getIdPersona());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	/*Aggiorna un'esperienza formativa*/
	@Override
	public void updateFormazione(Formazione formazione) throws BusinessException {
		try {
			String sql = "CALL update_formazione(?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, formazione.getId());
			ps.setString(2, formazione.getTitolo());
			ps.setString(3, formazione.getDescrizione());
			ps.setString(4, formazione.getIstituto());
			ps.setString(5, formazione.getDataInizio().toString());
			if(formazione.getDataFine().equals(null)) ps.setString(6, null);
			else ps.setString(6, formazione.getDataFine().toString());
			ps.setInt(7, formazione.getVoto());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	/*Elimina un'esperienza formativa*/
	@Override
	public void deleteFormazione(Formazione formazione) throws BusinessException {
		try {
			String sql = "CALL delete_formazione(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, formazione.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	/*Aggiunge un'esperienza lavorativa di una persona*/
	@Override
	public void createEsperienza(Esperienza esperienza) throws BusinessException {
		try {
			String sql = "CALL create_esperienza(?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setString(1, esperienza.getTitolo());
			ps.setString(2, esperienza.getDescrizione());
			ps.setString(3, esperienza.getAzienda());
			ps.setString(4, esperienza.getDataInizio().toString());
			if(esperienza.getDataFine() == null) ps.setString(5, null);
			else ps.setString(5, esperienza.getDataFine().toString());
			ps.setString(6, esperienza.getLocalita());
			ps.setInt(7, esperienza.getPersona().getIdPersona());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	/*Aggiorna un'esperienza lavorativa*/
	@Override
	public void updateEsperienza(Esperienza esperienza) throws BusinessException {
		try {
			String sql = "CALL update_esperienza(?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, esperienza.getId());
			ps.setString(2, esperienza.getTitolo());
			ps.setString(3, esperienza.getDescrizione());
			ps.setString(4, esperienza.getAzienda());
			ps.setString(5, esperienza.getDataInizio().toString());
			if(esperienza.getDataFine().equals(null)) ps.setString(6, null);
			else ps.setString(6, esperienza.getDataFine().toString());
			ps.setString(7, esperienza.getLocalita());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	/*Elimina un'esperienza lavorativa*/
	@Override
	public void deleteEsperienza(Esperienza esperienza) throws BusinessException {
		try {
			String sql = "CALL delete_esperienza(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, esperienza.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

}
