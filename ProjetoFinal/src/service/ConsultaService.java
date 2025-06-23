package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import dao.BancoDados;
import dao.ConsultaDAO;
import entities.Consulta;
import entities.Medico;

public class ConsultaService {
	
	public Consulta verificarDiaConsulta(Consulta consulta) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new ConsultaDAO(conn).buscarConsultaPorDataEHora(consulta);
		
	}
	
	public void cadastrar(Consulta consulta) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new ConsultaDAO(conn).cadastrarConsulta(consulta);
		
	}

	public List<Consulta> buscarConsultasPorMedico(Medico medico) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new ConsultaDAO(conn).buscarConsultasPorMedico(medico);
		
	}
	
	public void atualizar(int id) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		Consulta consulta = buscarConsultaPorId(id);
		consulta.setrealizada(true);
		new ConsultaDAO(conn).atualizarConsulta(consulta);
		
	}
	
	private Consulta buscarConsultaPorId(int id) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new ConsultaDAO(conn).buscarPorIdConsulta(id);
		
	}
	
	public void excluirConsulta(int id) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new ConsultaDAO(conn).deletarConsulta(id);
		
	}
}
