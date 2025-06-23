package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.EspecialidadeDAO;
import entities.Especialidade;

public class EspecialidadeService {
	
	
	public void cadastrar(Especialidade especialidade) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new EspecialidadeDAO(conn).cadastrarEspecialidade(especialidade);
	}
	
	public void atualizar(Especialidade especialidade) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		new EspecialidadeDAO(conn).atualizarEspecialidade(especialidade);
	}
	
	public List<Especialidade> buscarTodos() throws SQLException, IOException{
		Connection conn = BancoDados.conectar();
		return new EspecialidadeDAO(conn).listarTodasEspecialidades();
	}
	
	public void apagarEspecialidade(int id) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		new EspecialidadeDAO(conn).deletarEspecialidade(id);
		
	}
	
	public Especialidade buscarEspecialidadePorID(int id) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new EspecialidadeDAO(conn).buscarPorIdEspecialidade(id);
	}
}
