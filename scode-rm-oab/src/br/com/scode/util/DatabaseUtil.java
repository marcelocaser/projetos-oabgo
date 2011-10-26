package br.com.scode.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DatabaseUtil {
	

	private static Log log = LogFactory.getLog(DatabaseUtil.class);
	
	private Connection cnn;
	
	/**
	 * Não pode fazer new nessa classe, tem que usar o getDiEmpresa, ou o
	 * getIntegracao
	 */
	private DatabaseUtil() {
	}

	public static DatabaseUtil getDb() {
		DatabaseUtil db = new DatabaseUtil();
		return db;
	}

	public Connection conecta() throws Throwable {

		if (cnn != null && !cnn.isClosed()) {
			return cnn;
		}

		String jdbcUrl = ParametrosUtil.get("jdbc.url");
		if (jdbcUrl == null) {
			throw new Throwable("Não foi informado o valor de jdbc.url no arquivo de configuração");
		}
		log.trace("jdbcUrl = " + jdbcUrl);

		String jdbcDriver = ParametrosUtil.get("jdbc.driver");
		if (jdbcDriver == null || jdbcDriver.trim().equals("")) {
			throw new Throwable("Não foi informado o valor para jdbc.driver no arquivo de configuração");
		}
		log.trace("jdbcDriver = " + jdbcDriver);

		try {
			Class.forName(jdbcDriver);
		} catch (Exception e) {
			throw new Throwable("Problema ao carregar o driver odbc", e);
		}

		String user = ParametrosUtil.get("jdbc.user");
		String pwd = ParametrosUtil.get("jdbc.password");
		
		log.trace("Usuario = " + user);
		log.trace("Senha = *****");

		cnn = DriverManager.getConnection(jdbcUrl, user, pwd);
		
		autoCommit(false);

		return cnn;
	}

	public void desconecta() throws SQLException {
		if (cnn != null) {
			cnn.close();
			cnn = null;
		}
	}

	public PreparedStatement criarComando(String sql) throws SQLException, Throwable {
		return conecta().prepareStatement(sql);
	}

	public void executar(PreparedStatement stmt) throws SQLException {
		executar(stmt, true);
	}

	public void executar(PreparedStatement stmt, boolean fecha) throws SQLException {
		stmt.executeUpdate();
		stmt.close();
		if (fecha) {
			desconecta();
		}
	}

	public void executar(String sql) throws SQLException, Throwable {
		executar(sql, true);
	}

	public void executar(String sql, boolean fecha) throws SQLException, Throwable {
		Statement stmt = conecta().createStatement();
		stmt.executeUpdate(sql);
		if (fecha) {
			desconecta();
		}
	}

	public List<Map<String, Serializable>> lista(String sql, boolean fecha) throws SQLException, Throwable {
		List<Map<String, Serializable>> result = new ArrayList<Map<String, Serializable>>();

		Statement stmt = conecta().createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {

			Map<String, Serializable> record = new HashMap<String, Serializable>();

			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				String coluna = rsmd.getColumnName((i + 1));
				Serializable valor = (Serializable) rs.getObject(coluna);
				record.put(coluna, valor);
			}

			result.add(record);
		}

		libera(rs);
		stmt.close();

		if (fecha) {
			desconecta();
		}

		return result;
	}

	public List<Map<String, Serializable>> lista(String sql) throws SQLException, Throwable {
		return lista(sql, true);
	}

	public Map<String, Serializable> unico(String sql) throws SQLException, Throwable {
		return unico(sql, true);
	}

	public Map<String, Serializable> unico(String sql, boolean fecha) throws SQLException, Throwable {
		List<Map<String, Serializable>> lista = lista(sql, fecha);
		if (lista.size() == 0) {
			return null;
		} else if (lista.size() == 1) {
			return lista.get(0);
		} else {
			throw new Throwable("Consulta " + sql + " não é um resultado unico");
		}
	}

	public static void libera(ResultSet rs) throws SQLException {
		rs.close();
	}
	
	public void autoCommit(boolean autoCommit) throws SQLException {
		cnn.setAutoCommit(autoCommit);
	}
	
	public void rollback() throws SQLException {
		cnn.rollback();
	}
	
	public void commit() throws SQLException {
		cnn.commit();
	}
}
