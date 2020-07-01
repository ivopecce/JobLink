package it.univaq.disim.oop.joblink.business.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.MessaggiService;
import it.univaq.disim.oop.joblink.domain.Messaggio;
import it.univaq.disim.oop.joblink.domain.Utente;

public class DBMessaggiServiceImpl implements MessaggiService {
	
	private Connection dbConnection;
	
	public DBMessaggiServiceImpl(Connection connection) {
		this.dbConnection = connection;
	}

	@Override
	public void sendMessaggio(Messaggio messaggio) throws BusinessException {
		try {
			String sql = "CALL send_messaggio(?, ?, ?, ?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setString(1, messaggio.getOggetto());
			ps.setString(2, messaggio.getTesto());
			ps.setInt(3, messaggio.getMittente().getId());
			ps.setInt(4, messaggio.getDestinatario().getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}

	}

	@Override
	public List<Messaggio> getMessaggiRicevuti(Utente utente) throws BusinessException {
		List<Messaggio> result = new ArrayList<>();
		try {
			String sql = "CALL get_messaggi_ricevuti(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, utente.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Messaggio messaggio = createMessaggio(rs);
				messaggio.setMittente(getUtente(rs.getInt(5)));
				messaggio.setDestinatario(utente);
				result.add(messaggio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
		return result;
	}

	@Override
	public List<Messaggio> getMessaggiInviati(Utente utente) throws BusinessException {
		List<Messaggio> result = new ArrayList<>();
		try {
			String sql = "CALL get_messaggi_inviati(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, utente.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Messaggio messaggio = createMessaggio(rs);
				messaggio.setMittente(utente);
				messaggio.setDestinatario(getUtente(rs.getInt(6)));
				result.add(messaggio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		
		return result;
	}
	
//	private List<Messaggio> createList(ResultSet rs) throws SQLException{
//		List<Messaggio> result = new ArrayList<>();
//		while(rs.next()) {
//			Messaggio messaggio = new Messaggio();
//			messaggio.setId(rs.getInt(1));
//			messaggio.setOggetto(rs.getString(2));
//			messaggio.setTesto(rs.getString(3));
//			messaggio.setData(LocalDate.parse(rs.getString(4)));
//			
//		}
//		return result;
//		
//	}
	
	private Messaggio createMessaggio(ResultSet rs) throws SQLException{
		Messaggio messaggio = new Messaggio();
		messaggio.setId(rs.getInt(1));
		messaggio.setOggetto(rs.getString(2));
		messaggio.setTesto(rs.getString(3));
		messaggio.setData(LocalDate.parse(rs.getString(4)));
		
		return messaggio;
	}
	
	private Utente getUtente(int idUtente) throws SQLException{
		Utente utente = new Utente();
		String sql = "CALL get_utente(?);";
		PreparedStatement ps = dbConnection.prepareStatement(sql);
		ps.setInt(1, idUtente);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			utente.setId(rs.getInt(1));
			utente.setUsername(rs.getString(2));
			utente.setPassword(rs.getString(3));
			utente.setEmail(rs.getString(4));
			utente.setTelefono(rs.getString(5));
			return utente;
		}
		else throw new SQLException();
	}

}
