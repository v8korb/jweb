package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.ConnectionFactory;

public class ConectionFactory {
	private static final String user = "root";
	private static final String password = "";
	private static final String url = "jdbc:mysql://localhost:3306/farmaciadb";

	public static Connection connect() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(url, user, password);
		return conexao;
	}

	public static void main(String[] args) {
		try {
			Connection conexao = ConectionFactory.connect();
			System.out.println("Conexão OK");
		} catch (SQLException ex) {
			System.out.println("Sem Conexão");
		}
	}
}
