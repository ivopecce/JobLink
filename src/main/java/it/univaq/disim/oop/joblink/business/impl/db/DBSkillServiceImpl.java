/**
 * Impementazione per DB del service delle skill
 */
package it.univaq.disim.oop.joblink.business.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.SkillService;
import it.univaq.disim.oop.joblink.domain.LivelloSkill;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Possiede;
import it.univaq.disim.oop.joblink.domain.Richiesta;
import it.univaq.disim.oop.joblink.domain.Skill;

public class DBSkillServiceImpl implements SkillService {
	
	private Connection dbConnection;

	public DBSkillServiceImpl(Connection connection) {
		this.dbConnection = connection;
	}
	
	/*Restituisce una stringa avente la concatenazione delle skill richieste per un'offerta lavorativa*/
	@Override
	public String skillRichieste(Offerta offerta) throws BusinessException {
		String skillRichieste = new String();
		try {
			String sql = "CALL skill_richieste(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, offerta.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) skillRichieste = rs.getString("skillRichieste");
			return skillRichieste;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
	/*Restituisce un ArrayList contenente tutte le skill richieste per un'offerta*/
	@Override
	public List<Richiesta> getSkillRichieste(Offerta offerta) throws BusinessException {
		List<Richiesta> result = new ArrayList<>();
		try {
			String sql = "CALL get_skill_richieste(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, offerta.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Richiesta richiesta = new Richiesta();
				richiesta.setId(rs.getInt(1));
				richiesta.setOfferta(offerta);
				richiesta.setSkill(new Skill());
				richiesta.getSkill().setId(rs.getInt(2));
				richiesta.getSkill().setSkill(rs.getString(3));
				switch(rs.getString(4)) {
				case "BASE":
					richiesta.setLivelloRichiesto(LivelloSkill.BASE);
					break;
				case "MEDIO":
					richiesta.setLivelloRichiesto(LivelloSkill.MEDIO);
					break;
				case "AVANZATO":
					richiesta.setLivelloRichiesto(LivelloSkill.AVANZATO);
					break;
				}
				result.add(richiesta);
			}
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	/*Inserisce una nuova skill richiesta per un'offerta*/
	@Override
	public void createRichiesta(Richiesta richiesta) throws BusinessException {
		try {
			String sql = "CALL create_richiesta(?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, richiesta.getOfferta().getId());
			ps.setString(2, richiesta.getSkill().getSkill());
			ps.setString(3, richiesta.getLivelloRichiesto().toString());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	/*Aggiorna una richiesta di skill per un'offerta*/
	@Override
	public void updateRichiesta(Richiesta richiesta) throws BusinessException {
		try {
			String sql = "CALL update_richiesta(?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, richiesta.getId());
			ps.setString(2, richiesta.getSkill().getSkill());
			ps.setString(3, richiesta.getLivelloRichiesto().toString());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	/*Elimina una skill richiesta da un'offerta*/
	@Override
	public void deleteRichiesta(Richiesta richiesta) throws BusinessException {
		try {
			String sql = "DELETE FROM Richiesta WHERE idRichiesta=?;";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, richiesta.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	/*Crea una skill posseduta da una persona*/
	@Override
	public void createPossiede(Possiede possiede) throws BusinessException {
		try {
			String sql = "CALL create_possiede(?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, possiede.getPersona().getIdPersona());
			ps.setString(2, possiede.getSkill().getSkill());
			ps.setString(3, possiede.getLivelloPosseduto().toString());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	/*Aggiorna una skill posseduta da una persona*/
	@Override
	public void updatePossiede(Possiede possiede) throws BusinessException {
		try {
			String sql = "CALL update_possiede(?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, possiede.getId());
			ps.setString(2, possiede.getSkill().getSkill());
			ps.setString(3, possiede.getLivelloPosseduto().toString());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}

	/*Elimina una skill posseduta da una persona*/
	@Override
	public void deletePossiede(Possiede possiede) throws BusinessException {
		try {
			String sql = "CALL delete_possiede(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, possiede.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}
	
	

}
