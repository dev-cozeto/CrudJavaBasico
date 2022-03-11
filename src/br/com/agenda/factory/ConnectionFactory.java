package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//Nome do Usuário do mysql
	private static final String USERNAME = "root";
	
	//Senha do banco
	private static final String PASSWORD = "admin"; 
	
	//Caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3307/agenda?autoReconnect=true&useSSL=false";
	
	
	
	/*
	 * Conexão com o banco de dados 
	 */
	
	public static Connection createConnectionToMySQL() throws Exception {
		//Faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		//Chamando o driver para conexão 
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		//Recuperar uma conexão com o banco de dados 
		Connection con = createConnectionToMySQL();
		
		//Testar se a conexão é nula
		if (con != null) {
			System.out.println("Conexão obtida com sucesso! ");
			con.close();
		}else { 
			System.out.println("Não Conectado. Tente novamente!");
		}
		
	
	}
	
	
}
