package it.univaq.disim.oop.joblink.business.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.OffertaService;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Offerta;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.StatoOfferta;

public class DBOffertaServiceImpl implements OffertaService {
	
	private Connection dbConnection;

	public DBOffertaServiceImpl(Connection connection) {
		this.dbConnection = connection;
	}

	@Override
	public List<Offerta> findAllOfferte(Azienda azienda) throws BusinessException {
		List<Offerta> result = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Offerta WHERE idAzienda = ?";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, azienda.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Offerta offerta = new Offerta();
				offerta.setId(rs.getInt(1));
				offerta.setDataCreazione(LocalDate.parse(rs.getDate(2).toString()));
				offerta.setTitoloOfferta(rs.getString(3));
				offerta.setTestoOfferta(rs.getString(4));
				offerta.setLocalita(rs.getString(5));
				offerta.setStato(StatoOfferta.valueOf(rs.getString(6)));
				offerta.setAzienda(azienda);
				result.add(offerta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
		return result;
	} 

	@Override
	public Offerta findOffertaById(int id) throws BusinessException {
		Offerta result = new Offerta();
		try {
			String sql = "SELECT * FROM Offerta WHERE idOfferta = ?";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			result.setId(rs.getInt(1));
			result.setDataCreazione(LocalDate.parse(rs.getDate(2).toString()));
			result.setTitoloOfferta(rs.getString(3));
			result.setTestoOfferta(rs.getString(4));
			result.setLocalita(rs.getString(5));
			if(rs.getString(6) == "ATTIVA") result.setStato(StatoOfferta.ATTIVA);
			if(rs.getString(6) == "NON_ATTIVA") result.setStato(StatoOfferta.NON_ATTIVA);
			result.setAzienda(new Azienda());
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
				
	}
	
	@Override
	public List<Offerta> findOfferteAttinenti(Persona persona) throws BusinessException {
		List<Offerta> result = new ArrayList<>();
		try {
			String sql = "CALL offerte_attinenti(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, persona.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Offerta offerta = new Offerta();
				offerta.setId(rs.getInt(1));
				offerta.setDataCreazione(LocalDate.parse(rs.getString(2)));
				offerta.setTitoloOfferta(rs.getString(3));
				offerta.setTestoOfferta(rs.getString(4));
				offerta.setLocalita(rs.getString(5));
				offerta.setStato(StatoOfferta.ATTIVA);
				Azienda azienda = new Azienda();
				azienda.setId(rs.getInt(6));
				azienda.setDenominazione(rs.getString(7));
				offerta.setAzienda(azienda);
				result.add(offerta);
			}
			
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

	@Override
	public void createOfferta(Offerta offerta) throws BusinessException {
		try {
			String sql = "INSERT INTO Offerta(dataCreazione, titoloOfferta, testoOfferta, localita, stato, idAzienda) "+
					"VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(1, format.format(offerta.getDataCreazione()));
			ps.setString(2, offerta.getTitoloOfferta());
			ps.setString(3, offerta.getTestoOfferta());
			ps.setString(4, offerta.getLocalita());
			if(offerta.getStato().equals(StatoOfferta.ATTIVA)) ps.setString(5, "ATTIVA");
			if(offerta.getStato().equals(StatoOfferta.NON_ATTIVA)) ps.setString(5, "NON_ATTIVA");
			ps.setInt(6, offerta.getAzienda().getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	@Override
	public void updateOfferta(Offerta offerta) throws BusinessException {
		try {
			String sql = "UPDATE Offerta SET titoloOfferta=?, testoOfferta=?, localita=?, stato=? WHERE idOfferta=?;";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setString(1, offerta.getTitoloOfferta());
			ps.setString(2, offerta.getTestoOfferta());
			ps.setString(3, offerta.getLocalita());
			if(offerta.getStato().equals(StatoOfferta.ATTIVA)) ps.setString(4, "ATTIVA");
			if(offerta.getStato().equals(StatoOfferta.NON_ATTIVA)) ps.setString(4, "NON_ATTIVA");
			ps.setInt(5, offerta.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	@Override
	public void deleteOfferta(Offerta offerta) throws BusinessException {
		try {
			String sql = "DELETE FROM Offerta WHERE idOfferta=?;";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, offerta.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
	}
	
	@Override
	public void SetCandidatura(Offerta offerta, Persona persona, Boolean candidatura) throws BusinessException {
		try {
			String sql = "CALL set_candidatura(?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, offerta.getId());
			ps.setInt(2, persona.getId());
			ps.setBoolean(3, candidatura);
			ps.execute();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	
	@Override
	public Boolean getCandidatura(Offerta offerta, Persona persona) throws BusinessException {
		Boolean candidato = false;
		try {
			String sql = "CALL get_candidatura(?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(2, offerta.getId());
			ps.setInt(1, persona.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(!rs.getString(1).isEmpty()) candidato = true;
				else candidato = false;
			}
			return candidato;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

}
