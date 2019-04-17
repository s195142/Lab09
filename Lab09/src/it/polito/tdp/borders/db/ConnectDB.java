package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectDB {

//	static private final String jdbcUrl = "jdbc:mysql://localhost/countries?user=root&password=root";
//	private static HikariDataSource ds;
//	
//	public static Connection getConnection() {
//		
//		if (ds == null) {
//			HikariConfig config = new HikariConfig();
//			config.setJdbcUrl(jdbcUrl);
//			config.setUsername("root");
//			config.setPassword("");
//			
//			// configurazione MySQL
//			config.addDataSourceProperty("cachePrepStmts", "true");
//			config.addDataSourceProperty("prepStmtCacheSize", "250");
//			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//			
//			ds = new HikariDataSource(config);
//		}
//		
//		try {
//			
//			return ds.getConnection();
//
//		} catch (SQLException e) {
//			System.err.println("Errore connessione al DB");
//			throw new RuntimeException(e);
//		}
//	}


	private static final String jdbcURL = "jdbc:mysql://localhost/countries";
	private static HikariDataSource ds;

	public static Connection getConnection() {

		if (ds == null) {
			
			ds = new HikariDataSource();

			ds.setJdbcUrl(jdbcURL);
			ds.setUsername("root");
			ds.setPassword("root");

			// configurazione MySQL
			ds.addDataSourceProperty("cachePrepStmts", "true");
			ds.addDataSourceProperty("prepStmtCacheSize", "250");
			ds.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		}

		try {

			return ds.getConnection();

		} catch (SQLException e) {
			System.err.println("Errore connessione al DB");
			throw new RuntimeException(e);
		}
	}
}
