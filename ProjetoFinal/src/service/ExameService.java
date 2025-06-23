package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.ExameDAO;
import entities.Exame;

public class ExameService {
	
	
	public void cadastrar(Exame exame) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new ExameDAO(conn).cadastrarExame(exame);
		
	}
}
