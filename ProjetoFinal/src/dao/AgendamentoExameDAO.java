package dao;

import entities.AgendamentoExame;

import java.sql.*;
import java.util.*;

public class AgendamentoExameDAO {

    private Connection conn;

    public AgendamentoExameDAO(Connection conn) {
        this.conn = conn;
    }

    public int cadastrarAgendamento(AgendamentoExame agendamento) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO AgendamentoExame (id_exame, id_paciente, id_medico, data_hora, valor_pago, realizado) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, agendamento.getExame().getid_exame());
            st.setInt(2, agendamento.getPaciente().getId_paciente());
            st.setInt(3, agendamento.getMedico().getid_medico());
            st.setTimestamp(4, Timestamp.valueOf(agendamento.getdata_hora()));
            st.setDouble(5, agendamento.getvalor_pago());
            st.setBoolean(6, agendamento.getrealizado());

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

    public AgendamentoExame buscarPorIdAgendamento(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM AgendamentoExame WHERE id_agendamento_exame = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                AgendamentoExame ae = new AgendamentoExame();
                ae.setid_agendamento_exame(rs.getInt("id_agendamento_exame"));
                ae.setdata_hora(rs.getTimestamp("data_hora").toLocalDateTime());
                ae.setvalor_pago(rs.getDouble("valor_pago"));
                ae.setrealizado(rs.getBoolean("realizado"));

                ae.setExame(new ExameDAO(conn).buscarPorIdExame(rs.getInt("id_exame")));
                ae.setPaciente(new PacienteDAO(conn).buscarPorIdPaciente(rs.getInt("id_paciente")));
                ae.setMedico(new MedicoDAO(conn).buscarPorIdMedico(rs.getInt("id_medico")));

                return ae;
            }

            return null;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public List<AgendamentoExame> listarTodosAgendamentos() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM AgendamentoExame");
            rs = st.executeQuery();

            List<AgendamentoExame> lista = new ArrayList<>();

            while (rs.next()) {
                AgendamentoExame ae = new AgendamentoExame();
                ae.setid_agendamento_exame(rs.getInt("id_agendamento_exame"));
                ae.setdata_hora(rs.getTimestamp("data_hora").toLocalDateTime());
                ae.setvalor_pago(rs.getDouble("valor_pago"));
                ae.setrealizado(rs.getBoolean("realizado"));

                ae.setExame(new ExameDAO(conn).buscarPorIdExame(rs.getInt("id_exame")));
                ae.setPaciente(new PacienteDAO(conn).buscarPorIdPaciente(rs.getInt("id_paciente")));
                ae.setMedico(new MedicoDAO(conn).buscarPorIdMedico(rs.getInt("id_medico")));

                lista.add(ae);
            }

            return lista;

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public int atualizarAgendamento(AgendamentoExame agendamento) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "UPDATE AgendamentoExame SET id_exame = ?, id_paciente = ?, id_medico = ?, data_hora = ?, valor_pago = ?, realizado = ? WHERE id_agendamento_exame = ?"
            );

            st.setInt(1, agendamento.getExame().getid_exame());
            st.setInt(2, agendamento.getPaciente().getId_paciente());
            st.setInt(3, agendamento.getMedico().getid_medico());
            st.setTimestamp(4, Timestamp.valueOf(agendamento.getdata_hora()));
            st.setDouble(5, agendamento.getvalor_pago());
            st.setBoolean(6, agendamento.getrealizado());
            st.setInt(7, agendamento.getid_agendamento_exame());

            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }

    public int deletarAgendamento(int id) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM AgendamentoExame WHERE id_agendamento_exame = ?");
            st.setInt(1, id);
            return st.executeUpdate();

        } finally {
            BancoDados.finalizarStatement(st);
        }
    }
}
