package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.PacienteDAO;
import entities.Paciente;
import service.EnderecoService;

public class PacienteService {
	
	private EnderecoService enderecoService;
	
	public PacienteService() {
		this.enderecoService = new EnderecoService();
	}
	public void cadastrar(Paciente paciente) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new PacienteDAO(conn).cadastrarPaciente(paciente);
		
	}
	
	public List<Paciente> buscarTodos() throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new PacienteDAO(conn).listarTodosPacientes();
		
	}
	
	public void excluirPaciente(int id) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		Paciente paciente = this.buscarPacientePorID(id);
		int idEndereco = paciente.getEndereco().getId_endereco();
		new PacienteDAO(conn).deletarPaciente(id);
		enderecoService.excluirEndereco(idEndereco);
		
	}
	
	public Paciente buscarPacientePorID(int id) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new PacienteDAO(conn).buscarPorIdPaciente(id);
		
	}
	
	public void atualizarPaciente(Paciente paciente) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new PacienteDAO(conn).atualizarPaciente(paciente);
		
	}
}
