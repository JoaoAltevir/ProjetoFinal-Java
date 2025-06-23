package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class AgendarExameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textCodExame;
	private JTextField textNomePaciente;
	private JTextField textCRM;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JLabel lblCodExame;
	private JLabel lblNomePaciente;
	private JLabel lblCRM;
	private JLabel lblData;
	private JLabel lblValor;
	private JButton btnCadastrar;
	private JPanel panel;

	
	public AgendarExameWindow() {
		setTitle("Agendar Exame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Exames agendados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(297, 23, 426, 401);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 406, 368);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblCodExame = new JLabel("Código exame:");
		lblCodExame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodExame.setBounds(10, 32, 112, 22);
		contentPane.add(lblCodExame);
		
		textCodExame = new JTextField();
		textCodExame.setBounds(10, 65, 92, 20);
		contentPane.add(textCodExame);
		textCodExame.setColumns(10);
		
		lblNomePaciente = new JLabel("Nome Paciente:");
		lblNomePaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomePaciente.setBounds(10, 108, 112, 14);
		contentPane.add(lblNomePaciente);
		
		textNomePaciente = new JTextField();
		textNomePaciente.setBounds(10, 133, 244, 20);
		contentPane.add(textNomePaciente);
		textNomePaciente.setColumns(10);
		
		lblCRM = new JLabel("CRM Médico:");
		lblCRM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCRM.setBounds(10, 164, 112, 14);
		contentPane.add(lblCRM);
		
		textCRM = new JTextField();
		textCRM.setBounds(10, 189, 143, 20);
		contentPane.add(textCRM);
		textCRM.setColumns(10);
		
		lblData = new JLabel("Data Exame:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(10, 228, 92, 14);
		contentPane.add(lblData);
		
		JFormattedTextField ftextData = new JFormattedTextField();
		ftextData.setBounds(10, 253, 112, 20);
		contentPane.add(ftextData);
		
		lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(10, 294, 46, 14);
		contentPane.add(lblValor);
		
		textField = new JTextField();
		textField.setBounds(10, 320, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrar.setBounds(580, 435, 143, 23);
		contentPane.add(btnCadastrar);
	}
}
