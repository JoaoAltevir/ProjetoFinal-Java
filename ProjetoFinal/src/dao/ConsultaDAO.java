package dao;

import entities.Consulta;
import entities.Especialidade;
import entities.Medico;
import entities.Paciente;
import service.EspecialidadeService;
import service.MedicoService;
import service.PacienteService;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class ConsultaDAO {

    private Connection conn;
    private PacienteService pacienteService;

    public ConsultaDAO(Connection conn) {
        this.conn = conn;
        this.pacienteService = new PacienteService();
    }
    
    public Consulta buscarConsultaPorDataEHora(Consulta consulta) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM consulta WHERE data_hora = ? AND fk_consulta_medico = ?");
            st.setString(1, consulta.getdata_hora());
            st.setInt(2, consulta.getMedico().getid_medico());
            rs = st.executeQuery();
            
            Consulta c = new Consulta();
            if (rs.next()) {
                c.setid_consulta(rs.getInt("id_consulta"));
                c.setdata_hora(rs.getString("data_hora"));
                c.setrealizada(rs.getBoolean("realizada"));
                Paciente paciente = new Paciente();
                Medico medico = new Medico();
                c.setPaciente(paciente);
                c.setMedico(medico);
                c.getPaciente().setId_paciente(rs.getInt("fk_consulta_paciente"));
                c.getMedico().setid_medico(rs.getInt("fk_consulta_medico"));
            }
            
            return c;

        } finally {
            BancoDados.finalizarResultSet(rs);
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }

       
    }


    public int cadastrarConsulta(Consulta consulta) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
       

        try {
            st = conn.prepareStatement(
                "INSERT INTO Consulta (data_hora, realizada, fk_consulta_paciente, fk_consulta_medico) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            
            System.out.println(consulta + "" + consulta.getPaciente().getId_paciente() + " " + consulta.getMedico().getid_medico());
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
            BancoDados.desconectar();
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
                c.setdata_hora(rs.getTimestamp("data_hora").toString());
                c.setrealizada(rs.getBoolean("realizada"));

                int id_paciente = rs.getInt("fk_consulta_paciente");
                int id_medico = rs.getInt("fk_consulta_medico");

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
                c.setdata_hora(rs.getTimestamp("data_hora").toString());
                c.setrealizada(rs.getBoolean("realizada"));

                int id_paciente = rs.getInt("fk_consulta_paciente");
                int id_medico = rs.getInt("fk_consulta_medico");

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
                "UPDATE Consulta SET realizada = ? WHERE id_consulta = ?"
            );

            st.setBoolean(1, consulta.getrealizada());
            st.setInt(2, consulta.getid_consulta());

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
    
    public List<Consulta> buscarConsultasPorMedico(Medico medico) throws SQLException, IOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Consulta> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM consulta WHERE fk_consulta_medico = ?");
            st.setInt(1, medico.getid_medico());
            rs = st.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setid_consulta(rs.getInt("id_consulta"));
                c.setdata_hora(rs.getString("data_hora"));
                c.setrealizada(rs.getBoolean("realizada"));

                MedicoService medicoService = new MedicoService();
				Medico m = medicoService.buscarMedicoPorID(medico.getid_medico());
                c.setMedico(m);
                
                PacienteService pacienteService = new PacienteService();
                int pacienteID = rs.getInt("fk_consulta_paciente");
                Paciente paciente = pacienteService.buscarPacientePorID(pacienteID);
                c.setPaciente(paciente);

                lista.add(c);
            }

        } finally {
            BancoDados.finalizarResultSet(rs);
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }

        return lista;
    }
}
