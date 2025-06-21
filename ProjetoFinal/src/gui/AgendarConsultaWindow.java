package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;

public class AgendarConsultaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNomeMed;
	private JTable tableConsultas;

	public AgendarConsultaWindow() {
		setTitle("Agendar Consulta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 634, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox cbMedico = new JComboBox();
		cbMedico.setBounds(26, 43, 139, 22);
		contentPane.add(cbMedico);
		
		lblNomeMed = new JLabel("Nome Médico:");
		lblNomeMed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeMed.setBounds(26, 25, 97, 14);
		contentPane.add(lblNomeMed);
		
		JPanel painelConsultas = new JPanel();
		painelConsultas.setBorder(new TitledBorder(null, "Consultas Agendadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelConsultas.setBounds(228, 11, 380, 282);
		contentPane.add(painelConsultas);
		painelConsultas.setLayout(null);
		
		JScrollPane spConsultas = new JScrollPane();
		spConsultas.setBounds(10, 21, 360, 250);
		painelConsultas.add(spConsultas);
		
		tableConsultas = new JTable();
		tableConsultas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Paciente", "M\u00E9dico", "Data e Hora", "Status"
			}
		));
		tableConsultas.getColumnModel().getColumn(0).setPreferredWidth(33);
		spConsultas.setViewportView(tableConsultas);
		
		JButton btnCadastrar = new JButton("Cadastrar consulta");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCadastrar.setBounds(485, 306, 123, 23);
		contentPane.add(btnCadastrar);
		
		JLabel lblNomePaciente = new JLabel("Nome Paciente:");
		lblNomePaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomePaciente.setBounds(26, 76, 97, 14);
		contentPane.add(lblNomePaciente);
		
		JComboBox cbPacientes = new JComboBox();
		cbPacientes.setBounds(26, 101, 139, 22);
		contentPane.add(cbPacientes);
		
		JLabel lblData = new JLabel("Data e Hora:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(26, 144, 123, 14);
		contentPane.add(lblData);
		
		JFormattedTextField ftextData = new JFormattedTextField();
		ftextData.setBounds(26, 174, 82, 20);
		contentPane.add(ftextData);
		
		JFormattedTextField ftextHora = new JFormattedTextField();
		ftextHora.setBounds(26, 210, 51, 20);
		contentPane.add(ftextHora);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(394, 306, 89, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(302, 306, 89, 23);
		contentPane.add(btnApagar);
		
		
		
		
	}
}
