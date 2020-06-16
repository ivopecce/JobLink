package it.univaq.disim.oop.joblink.business.impl.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;


public class DBConnector {
	
	
	
	private Connection con;
	
	public Connection getConnection() throws SQLException {
		if(con == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setDatabaseName("joblink");
			dataSource.setPortNumber(3306);
			dataSource.setServerName("127.0.0.1");
			dataSource.setUser("root");
			dataSource.setPassword("password");
			
			con = dataSource.getConnection();
		}
		
		return con;
	}

}
