package it.univaq.disim.oop.joblink.business.impl.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.UtenteNotFoundException;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Persona;
import it.univaq.disim.oop.joblink.domain.Utente;


public class DBUtenteServiceImpl implements UtenteService {
	
	private Connection dbConnection;

	public DBUtenteServiceImpl(Connection connection) {
		this.dbConnection = connection;
	}

	@Override
	public Utente authenticate(String username, String password) throws UtenteNotFoundException, BusinessException {
		try {
			String sql = "CALL login(?, ?)";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			
			if(rs.next()) {
				Utente utente = null;
				switch(rs.getString(1)) {
				case "azienda":
					utente = new Azienda();
					break;
				case "persona":
					utente = new Persona();
					break;
				default:
					break;
				}
				
				if(utente != null) {
					utente.setUsername(rs.getString(3));
					utente.setPassword(rs.getString(4));
					utente.setEmail(rs.getString(5));
					utente.setTelefono(rs.getString(6));
					
					if(utente instanceof Azienda) {
						utente.setId(rs.getInt(2));
						((Azienda) utente).setDenominazione(rs.getString(7));
						((Azienda) utente).setSitoWeb(rs.getString(8));
						((Azienda) utente).setSede(rs.getString(9));
						((Azienda) utente).setSettore(rs.getString(10));
						((Azienda) utente).setNumeroDipendenti(rs.getInt(11));
					}
					if(utente instanceof Persona) {
						utente.setId(rs.getInt(2));
						((Persona) utente).setCognome(rs.getString(7));
						((Persona) utente).setNome(rs.getString(8));
						Date date;
						try {
							date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(9));
							((Persona) utente).setDataDiNascita(date.getYear(), date.getMonth(), date.getDay());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						((Persona) utente).setGenere(rs.getString(10));
						((Persona) utente).setResidenza(rs.getString(11));
					}
				}
				return utente;
			}
			throw new UtenteNotFoundException();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}

}
