package dao;

import entities.Endereco;
import java.sql.*;
import java.util.*;

public class EnderecoDAO {

	private Connection conn;

    public EnderecoDAO(Connection conn) {
        this.conn = conn;
    }
    
    public int cadastrarEndereco(Endereco endereco) throws SQLException {
    	
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
                "INSERT INTO Endereco (numero, logradouro, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);

            st.setString(1, endereco.getNumero());
            st.setString(2, endereco.getLogradouro());
            st.setString(3, endereco.getBairro());
            st.setString(4, endereco.getCidade());
            st.setString(5, endereco.getEstado());

            st.executeUpdate();
            rs = st.getGeneratedKeys(); // retorna o id gerado (boa prática segundo a baleinha)
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
            
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public Endereco buscarPorIdEndereco(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Endereco WHERE id_endereco = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Endereco e = new Endereco();
                e.setId_endereco(rs.getInt("id_endereco"));
                e.setNumero(rs.getString("numero"));
                e.setLogradouro(rs.getString("logradouro"));
                e.setBairro(rs.getString("bairro"));
                e.setCidade(rs.getString("cidade"));
                e.setEstado(rs.getString("estado"));
                return e;
            }
            return null;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public List<Endereco> listarTodosEnderecos() throws SQLException {
    	
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Endereco");
            rs = st.executeQuery();

            List<Endereco> lista = new ArrayList<>();
            while (rs.next()) {
                Endereco e = new Endereco();
                e.setId_endereco(rs.getInt("id_endereco"));
                e.setNumero(rs.getString("numero"));
                e.setLogradouro(rs.getString("logradouro"));
                e.setBairro(rs.getString("bairro"));
                e.setCidade(rs.getString("cidade"));
                e.setEstado(rs.getString("estado"));
                lista.add(e);
            }
            return lista;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public int atualizarEndereco(Endereco endereco) throws SQLException {
    	
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE Endereco SET numero = ?, logradouro = ?, bairro = ?, cidade = ?, estado = ? WHERE id_endereco = ?");

            st.setString(1, endereco.getNumero());
            st.setString(2, endereco.getLogradouro());
            st.setString(3, endereco.getBairro());
            st.setString(4, endereco.getCidade());
            st.setString(5, endereco.getEstado());
            st.setInt(6, endereco.getId_endereco());

            return st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
        }
    }

    public int deletarEndereco(int id) throws SQLException {
    	
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM Endereco WHERE id_endereco = ?");
            st.setInt(1, id);
            return st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
        }
    }
}
