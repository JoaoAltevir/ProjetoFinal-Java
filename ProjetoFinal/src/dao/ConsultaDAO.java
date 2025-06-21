package dao;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class ConsultaDAO {

    private Connection conn;

    public ConsultaDAO(Connection conn) {
        this.conn = conn;
    }

    public int cadastrarConsulta(Consulta consulta) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO Consulta (data_hora, realizada, id_paciente, id_medico) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setTimestamp(1, Timestamp.valueOf(consulta.getdata_hora()));
            st.setBoolean(2, consulta.getrealizada());
            st.setInt(3, consulta.getPaciente().getId_paciente());
            st.setInt(4, consulta.getMedico().getid_medico());

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

    public Consulta buscarPorIdConsulta(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Consulta WHERE id_consulta = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Consulta c = new Consulta();
                c.setid_consulta(rs.getInt("id_consulta"));
                c.setdata_hora(rs.getTimestamp("data_hora").toLocalDateTime());
                c.setrealizada(rs.getBoolean("realizada"));

                int id_paciente = rs.getInt("id_paciente");
                int id_medico = rs.getInt("id_medico");

                Paciente paciente = new PacienteDAO(conn).buscarPorIdPaciente(id_paciente);
                Medico medico = new MedicoDAO(conn).buscarPorIdMedico(id_medico);

                c.setPaciente(paciente);
                c.setMedico(medico);

                return c;
            }

            return null;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public List<Consulta> listarTodasConsultas() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Consulta");
            rs = st.executeQuery();

            List<Consulta> lista = new ArrayList<>();

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setid_consulta(rs.getInt("id_consulta"));
                c.setdata_hora(rs.getTimestamp("data_hora").toLocalDateTime());
                c.setrealizada(rs.getBoolean("realizada"));

                int id_paciente = rs.getInt("id_paciente");
                int id_medico = rs.getInt("id_medico");

                Paciente paciente = new PacienteDAO(conn).buscarPorIdPaciente(id_paciente);
                Medico medico = new MedicoDAO(conn).buscarPorIdMedico(id_medico);

                c.setPaciente(paciente);
                c.setMedico(medico);

                lista.add(c);
            }

            return lista;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public int atualizarConsulta(Consulta consulta) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "UPDATE Consulta SET data_hora = ?, realizada = ?, id_paciente = ?, id_medico = ? WHERE id_consulta = ?"
            );

            st.setTimestamp(1, Timestamp.valueOf(consulta.getdata_hora()));
            st.setBoolean(2, consulta.getrealizada());
            st.setInt(3, consulta.getPaciente().getId_paciente());
            st.setInt(4, consulta.getMedico().getid_medico());
            st.setInt(5, consulta.getid_consulta());

            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }

    public int deletarConsulta(int id) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM Consulta WHERE id_consulta = ?");
            st.setInt(1, id);
            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }
}
