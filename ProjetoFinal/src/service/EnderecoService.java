package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.EnderecoDAO;
import entities.Endereco;

public class EnderecoService {
	
	public int cadastrar(Endereco endereco) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new EnderecoDAO(conn).cadastrarEndereco(endereco);	
		
	}
	
	public Endereco buscarPorId(int id) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new EnderecoDAO(conn).buscarPorIdEndereco(id);
		
	}
	
	public void atualizarEndereco(Endereco endereco) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new EnderecoDAO(conn).atualizarEndereco(endereco);
		
	}
	
	public void excluirEndereco(int id) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new EnderecoDAO(conn).deletarEndereco(id);
		
	}
}
