package br.com.agenda.aplicacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ContatoDAO contatoDao = new ContatoDAO();
		
		//Cadastro no banco de dados
		Contato contato = new Contato();
		contato.setNome("Naruto ");
		contato.setIdade(26);
		contato.setDataCadastro(new Date());
		
//		contatoDao.save(contato);
		
		//Atualizar o Contato
		Contato c1 = new Contato();
		c1.setNome("Victor Eder Cozeto");
		c1.setId(4);
		c1.setDataCadastro(new Date());
		c1.setId(2);//Número que está no banco de dados PK
		
		
//		contatoDao.update(c1);
		
		//Deletar o contato pelo número do ID
		
		contatoDao.deleteByID(3);
		
		//Visualização dos registros do banco de dados TODOS
		for (Contato c : contatoDao.getContatos()) {
			System.out.println("Contato: " + c.getNome());
			
		}
		
	}

}
