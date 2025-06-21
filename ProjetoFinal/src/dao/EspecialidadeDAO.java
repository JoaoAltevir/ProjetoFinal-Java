package dao;

import entities.Especialidade;

import java.sql.*;
import java.util.*;

public class EspecialidadeDAO {

    private Connection conn;

    public EspecialidadeDAO(Connection conn) {
        this.conn = conn;
    }

    public int cadastrarEspecialidade(Especialidade especialidade) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO Especialidade (nome_especialidade) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, especialidade.getnome_especialidade());

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

    public Especialidade buscarPorIdEspecialidade(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Especialidade WHERE id_especialidade = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Especialidade e = new Especialidade();
                e.setid_especialidade(rs.getInt("id_especialidade"));
                e.setnome_especialidade(rs.getString("nome_especialidade"));
                return e;
            }

            return null;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public List<Especialidade> listarTodasEspecialidades() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Especialidade");
            rs = st.executeQuery();

            List<Especialidade> lista = new ArrayList<>();

            while (rs.next()) {
                Especialidade e = new Especialidade();
                e.setid_especialidade(rs.getInt("id_especialidade"));
                e.setnome_especialidade(rs.getString("nome_especialidade"));
                lista.add(e);
            }

            return lista;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public int atualizarEspecialidade(Especialidade esp) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "UPDATE Especialidade SET nome_especialidade = ? WHERE id_especialidade = ?"
            );

            st.setString(1, esp.getnome_especialidade());
            st.setInt(2, esp.getid_especialidade());

            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }

    public int deletarEspecialidade(int id) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM Especialidade WHERE id_especialidade = ?");
            st.setInt(1, id);
            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }
}
