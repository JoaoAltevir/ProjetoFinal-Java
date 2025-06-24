package dao;

import entities.Exame;

import java.sql.*;
import java.util.*;

public class ExameDAO {

    private Connection conn;

    public ExameDAO(Connection conn) {
        this.conn = conn;
    }

    public int cadastrarExame(Exame exame) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO Exame (nome_exame, valor, orientacoes) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, exame.getnome_exame());
            st.setDouble(2, exame.getValor());
            st.setString(3, exame.getOrientacoes());

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

    public Exame buscarPorIdExame(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Exame WHERE id_exame = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Exame e = new Exame();
                e.setid_exame(rs.getInt("id_exame"));
                e.setnome_exame(rs.getString("nome_exame"));
                e.setValor(rs.getDouble("valor"));
                e.setOrientacoes(rs.getString("orientacoes"));
                return e;
            }

            return null;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
            BancoDados.desconectar();
        }
    }

    public List<Exame> listarTodosExames() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Exame");
            rs = st.executeQuery();

            List<Exame> lista = new ArrayList<>();

            while (rs.next()) {
                Exame e = new Exame();
                e.setid_exame(rs.getInt("id_exame"));
                e.setnome_exame(rs.getString("nome_exame"));
                e.setValor(rs.getDouble("valor"));
                e.setOrientacoes(rs.getString("orientacoes"));
                lista.add(e);
            }

            return lista;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public int atualizarExame(Exame exame) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE Exame SET nome_exame = ?, valor = ?, orientacoes = ? WHERE id_exame = ?");

            st.setString(1, exame.getnome_exame());
            st.setDouble(2, exame.getValor());
            st.setString(3, exame.getOrientacoes());
            st.setInt(4, exame.getid_exame());

            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }

    public int deletarExame(int id) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM Exame WHERE id_exame = ?");
            st.setInt(1, id);
            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }
}
