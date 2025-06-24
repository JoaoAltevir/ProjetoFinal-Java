package dao;

import entities.Paciente;
import entities.Endereco;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PacienteDAO {

    private Connection conn;

    public PacienteDAO(Connection conn) {
        this.conn = conn;
    }

    public int cadastrarPaciente(Paciente paciente) throws SQLException {
    	
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO Paciente (nome_paciente, foto_paciente, data_nascimento, sexo, telefone, forma_pagamento, fk_paciente_endereco) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, paciente.getNome());
            st.setString(2, paciente.getfoto_paciente());
            st.setDate(3, Date.valueOf(paciente.getdata_nascimento()));
            st.setString(4, String.valueOf(paciente.getSexo()));
            st.setString(5, paciente.getTelefone());
            st.setString(6, paciente.getforma_pagamento());
            st.setInt(7, paciente.getEndereco().getId_endereco());

            st.executeUpdate();
            rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public Paciente buscarPorIdPaciente(int id) throws SQLException {
    	
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Paciente WHERE id_paciente = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Paciente p = new Paciente();
                p.setId_paciente(rs.getInt("id_paciente"));
                p.setNome(rs.getString("nome_paciente"));
                p.setfoto_paciente(rs.getString("foto_paciente"));
                p.setdata_nascimento(rs.getDate("data_nascimento").toString());
                p.setSexo(rs.getString("sexo").charAt(0));
                p.setTelefone(rs.getString("telefone"));
                p.setforma_pagamento(rs.getString("forma_pagamento"));
                
                int id_endereco = rs.getInt("fk_paciente_endereco");
                
                EnderecoDAO enderecoDAO = new EnderecoDAO(conn);
                Endereco endereco = enderecoDAO.buscarPorIdEndereco(id_endereco);
                p.setEndereco(endereco);
                return p;
            }
            return null;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
            BancoDados.desconectar();
        }
    }

    public List<Paciente> listarTodosPacientes() throws SQLException {
    	
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Paciente");
            rs = st.executeQuery();

            List<Paciente> lista = new ArrayList<>();

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId_paciente(rs.getInt("id_paciente"));
                p.setNome(rs.getString("nome_paciente"));
                p.setfoto_paciente(rs.getString("foto_paciente"));
                p.setdata_nascimento(rs.getDate("data_nascimento").toString());
                p.setSexo(rs.getString("sexo").charAt(0));
                p.setTelefone(rs.getString("telefone"));
                p.setforma_pagamento(rs.getString("forma_pagamento"));

                int id_endereco = rs.getInt("fk_paciente_endereco");
                Endereco endereco = new EnderecoDAO(conn).buscarPorIdEndereco(id_endereco);
                p.setEndereco(endereco);
                lista.add(p);
            }
            return lista;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public int atualizarPaciente(Paciente paciente) throws SQLException {
    	
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE Paciente SET nome_paciente = ?, foto_paciente = ?, data_nascimento = ?, sexo = ?, telefone = ?, forma_pagamento = ?, fk_paciente_endereco = ? WHERE id_paciente = ?");

            st.setString(1, paciente.getNome());
            st.setString(2, paciente.getfoto_paciente());
            st.setDate(3, Date.valueOf(paciente.getdata_nascimento()));
            st.setString(4, String.valueOf(paciente.getSexo()));
            st.setString(5, paciente.getTelefone());
            st.setString(6, paciente.getforma_pagamento());
            st.setInt(7, paciente.getEndereco().getId_endereco());
            st.setInt(8, paciente.getId_paciente());

            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }

    public int deletarPaciente(int id) throws SQLException {
    	
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM Paciente WHERE id_paciente = ?");
            st.setInt(1, id);
            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }
}
