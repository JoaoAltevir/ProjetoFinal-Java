package dao;

import java.io.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class BancoDados {

	private static Connection conn = null;

	public static Connection conectar() throws SQLException, IOException {

		if (conn == null) {
			Properties props = carregarPropriedades();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
		}

		return conn;
	}
	
	public static Connection desconectar() throws SQLException {
		
		if (conn!=null) {
			conn.close();
			conn = null;
		}
		return conn;
	}
	
	private static Properties carregarPropriedades() throws IOException {
		
		FileInputStream propriedadesBanco = null;
		
		propriedadesBanco = new FileInputStream("database.properties");
		
		Properties props = new Properties();
		props.load(propriedadesBanco);
		
		return props;
	}
	
	public static void finalizarStatement(Statement st) throws SQLException {
		
		if (st != null) {
			st.close();
		}
	} 
	
	
	public static void finalizarResultSet (ResultSet rs) throws SQLException {
		
		if (rs != null) {
		
			rs.close();
		}
	}
}


