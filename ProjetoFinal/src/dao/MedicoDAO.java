package dao;

import entities.Medico;
import entities.Endereco;
import entities.Especialidade;

import java.sql.*;
import java.util.*;

public class MedicoDAO {

    private Connection conn;

    public MedicoDAO(Connection conn) {
        this.conn = conn;
    }

    public int cadastrarMedico(Medico medico) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO Medico (crm, nome_medico, telefone, id_endereco, id_especialidade) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, medico.getCrm());
            st.setString(2, medico.getnome_medico());
            st.setString(3, medico.getTelefone());
            st.setInt(4, medico.getEndereco().getId_endereco());
            st.setInt(5, medico.getEspecialidade().getid_especialidade());

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

    public Medico buscarPorIdMedico(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Medico WHERE id_medico = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Medico m = new Medico();
                m.setid_medico(rs.getInt("id_medico"));
                m.setCrm(rs.getString("crm"));
                m.setnome_medico(rs.getString("nome_medico"));
                m.setTelefone(rs.getString("telefone"));

                int id_endereco = rs.getInt("id_endereco");
                Endereco endereco = new EnderecoDAO(conn).buscarPorIdEndereco(id_endereco);
                m.setEndereco(endereco);

                int id_especialidade = rs.getInt("id_especialidade");
                Especialidade especialidade = new EspecialidadeDAO(conn).buscarPorIdEspecialidade(id_especialidade);
                m.setEspecialidade(especialidade);

                return m;
            }
            return null;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public List<Medico> listarTodosMedicos() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Medico");
            rs = st.executeQuery();

            List<Medico> lista = new ArrayList<>();

            while (rs.next()) {
                Medico m = new Medico();
                m.setid_medico(rs.getInt("id_medico"));
                m.setCrm(rs.getString("crm"));
                m.setnome_medico(rs.getString("nome_medico"));
                m.setTelefone(rs.getString("telefone"));

                int id_endereco = rs.getInt("id_endereco");
                Endereco endereco = new EnderecoDAO(conn).buscarPorIdEndereco(id_endereco);
                m.setEndereco(endereco);

                int id_especialidade = rs.getInt("id_especialidade");
                Especialidade especialidade = new EspecialidadeDAO(conn).buscarPorIdEspecialidade(id_especialidade);
                m.setEspecialidade(especialidade);

                lista.add(m);
            }

            return lista;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public int atualizarMedico(Medico medico) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "UPDATE Medico SET crm = ?, nome_medico = ?, telefone = ?, id_endereco = ?, id_especialidade = ? WHERE id_medico = ?"
            );

            st.setString(1, medico.getCrm());
            st.setString(2, medico.getnome_medico());
            st.setString(3, medico.getTelefone());
            st.setInt(4, medico.getEndereco().getId_endereco());
            st.setInt(5, medico.getEspecialidade().getid_especialidade());
            st.setInt(6, medico.getid_medico());

            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }

    public int deletarMedico(int id) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM Medico WHERE id_medico = ?");
            st.setInt(1, id);
            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }
}
