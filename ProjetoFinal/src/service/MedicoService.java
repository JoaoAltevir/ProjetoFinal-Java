package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.MedicoDAO;
import entities.Medico;

public class MedicoService {
	
	private EnderecoService enderecoService;
	
	public MedicoService() {
		
		this.enderecoService = new EnderecoService();
	}
	public void cadastrar(Medico medico) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new MedicoDAO(conn).cadastrarMedico(medico);
		
	}
	
	public Medico buscarMedicoPorID(int id) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new MedicoDAO(conn).buscarPorIdMedico(id);
		
	}
	
	public List<Medico> buscarTodos() throws SQLException, IOException{
		
		Connection conn = BancoDados.conectar();
		return new MedicoDAO(conn).listarTodosMedicos();
		
	}
	
	public void excluirMedico(int id) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		Medico medico = this.buscarMedicoPorID(id);
		int idEndereco = medico.getEndereco().getId_endereco();
		new MedicoDAO(conn).deletarMedico(id);
		this.enderecoService.excluirEndereco(idEndereco);
		
	}
	
	public void atualizar(Medico medico) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new MedicoDAO(conn).atualizarMedico(medico);
	}
}
