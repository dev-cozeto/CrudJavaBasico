package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	/*
	 * Paramatrização para que o java se molde ao banco de Dados CRUD - CREATE,
	 * READ, UPDATE, DELETE
	 * DATA ACCESS OBJECT
	 */

	// Comando CREATE do CRUD
	public void save(Contato contato) {

		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?,?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criamos uma Prepared Statement para executar uma query
			pstm = (PreparedStatement) conn.prepareCall(sql);

			// Adicionar valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// Executar a query
			pstm.execute();

			System.out.println("Contato Salvo com Sucesso!!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechar as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// Comando READ do UPDATE
	public void update(Contato contato) throws Exception {
		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ?" + "WHERE id = ? ";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para visualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getId());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// Qual o ID do registro que deseja atualizar ?
			pstm.setInt(4, contato.getId());

			// Executar a query
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Comando READ do DELETE
	public void deleteByID(int id) throws Exception { 
		String sql = "DELETE FROM contatos WHERE id = ? ";
		
		//Variáveis de conexão do banco de dados 
		Connection conn = null;
		PreparedStatement pstm = null; 
		
		try {
			//Conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Prepara a classe para excução da query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1,id);
			
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	// Comando READ do CRUD
	public List<Contato> getContatos() {

		String sql = "SELECT * FROM contatos ";

		List<Contato> contatos = new ArrayList<Contato>();
		// Conexão com o banco
		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco ***SELECT****
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Contato contato = new Contato();

				// Recuperar Id
				contato.setId(rset.getInt("id"));
				// REcuperar nome
				contato.setNome(rset.getString("nome"));
				// Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				// Recuperar data de cadastro
				contato.setDataCadastro(rset.getDate("datacadastro"));

				contatos.add(contato);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contatos;

	}
}
