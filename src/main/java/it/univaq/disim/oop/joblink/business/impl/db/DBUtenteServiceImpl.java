package it.univaq.disim.oop.joblink.business.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import it.univaq.disim.oop.joblink.business.BusinessException;
import it.univaq.disim.oop.joblink.business.UtenteNotFoundException;
import it.univaq.disim.oop.joblink.business.UtenteService;
import it.univaq.disim.oop.joblink.domain.Azienda;
import it.univaq.disim.oop.joblink.domain.Genere;
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
						LocalDate date;
						date = LocalDate.parse(rs.getString(9));
						((Persona) utente).setDataDiNascita(date);
						if (rs.getString(10).equals(Genere.MASCHIO.toString()))((Persona) utente).setGenere(Genere.MASCHIO);
						if (rs.getString(10).equals(Genere.FEMMINA.toString()))((Persona) utente).setGenere(Genere.FEMMINA);
						if (rs.getString(10).equals(Genere.ALTRO.toString()))((Persona) utente).setGenere(Genere.ALTRO);
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

	@Override
	public void registerAzienda(String username, String password, String email, String telefono, String denominazione,
			String sede, String settore, String sitoweb, Integer dipendenti) throws BusinessException {
		String sql = "CALL registerAzienda(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, telefono);
			ps.setString(5, denominazione);
			ps.setString(6, sede);
			ps.setString(7, settore);
			ps.setString(8, sitoweb);
			ps.setInt(9, dipendenti);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		
		
	}

	@Override
	public void registerPersona(String username, String password, String email, String telefono, String cognome,
		String nome, LocalDate dataDiNascita, String genere, String residenza) throws BusinessException {
		String sql = "CALL registerPersona(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = dbConnection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, telefono);
			ps.setString(5, cognome);
			ps.setString(6, nome);
			ps.setString(7, dataDiNascita.toString());
			ps.setString(8, genere);
			ps.setString(9, residenza);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		
	}

	@Override
	public void deletePersona(Persona persona) throws BusinessException{
		try {
			String sql = "CALL delete_persona(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, persona.getId());
			ps.execute();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		
	}

	@Override
	public void deleteAzienda(Azienda azienda) throws BusinessException{
		try {
			String sql = "CALL delete_azienda(?);";
			PreparedStatement ps = dbConnection.prepareStatement(sql);
			ps.setInt(1, azienda.getId());
			ps.execute();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		
	}

}
