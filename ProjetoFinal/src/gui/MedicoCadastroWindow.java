package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class MedicoCadastroWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private MedicoWindow medicoWindow;
	private JTextField textNome;
	private JTextField textCidade;
	private JTextField textNumeroCasa;
	private JButton btnLimparComponentes;
	private JButton btnCadastrar;
	private JPanel painelDados;
	private JPanel painelEndereço;
	private JTextField textBairro;
	private JLabel lblBairro;
	private JLabel lblLogradouro;
	private JTextField textLogradouro;
	private JLabel lblEstado;
	private JTextField textEstado;
	private JLabel lblNumeroCasa;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblCRM;
	private JFormattedTextField ftextCRM;
	private JFormattedTextField ftextTelefone;
	private JLabel lblEspecialidade;
	private JComboBox cbEspecialidade;


	public MedicoCadastroWindow(MedicoWindow medicoWindow) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.initComponents();
		this.medicoWindow = medicoWindow;
	}
	
	private void fecharJanela() {
		
		this.dispose();
		this.medicoWindow.setVisible(true);
	}
	private void initComponents() {
		setTitle("Cadastro de Médicos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		painelDados = new JPanel();
		painelDados.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelDados.setBounds(10, 11, 614, 363);
		contentPane.add(painelDados);
		painelDados.setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(23, 44, 46, 14);
		painelDados.add(lblNome);
		
		lblCRM = new JLabel("CRM:");
		lblCRM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCRM.setBounds(23, 104, 40, 14);
		painelDados.add(lblCRM);
		
		lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEspecialidade.setBounds(264, 72, 103, 19);
		painelDados.add(lblEspecialidade);
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(23, 74, 56, 14);
		painelDados.add(lblTelefone);
		
		textNome = new JTextField();
		textNome.setBounds(70, 43, 506, 20);
		painelDados.add(textNome);
		textNome.setColumns(10);
		
		cbEspecialidade = new JComboBox();
		cbEspecialidade.setBounds(353, 72, 175, 22);
		painelDados.add(cbEspecialidade);
		
		ftextTelefone = new JFormattedTextField();
		ftextTelefone.setBounds(89, 73, 165, 20);
		painelDados.add(ftextTelefone);
		
		ftextCRM = new JFormattedTextField();
		ftextCRM.setBounds(59, 103, 56, 20);
		painelDados.add(ftextCRM);
		
		painelEndereço = new JPanel();
		painelEndereço.setLayout(null);
		painelEndereço.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelEndereço.setBounds(10, 148, 594, 204);
		painelDados.add(painelEndereço);
		
		lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogradouro.setBounds(21, 76, 89, 26);
		painelEndereço.add(lblLogradouro);
		
		lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBairro.setBounds(21, 116, 46, 26);
		painelEndereço.add(lblBairro);
		
		lblNumeroCasa = new JLabel("Número:");
		lblNumeroCasa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroCasa.setBounds(329, 82, 61, 14);
		painelEndereço.add(lblNumeroCasa);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstado.setBounds(21, 41, 61, 14);
		painelEndereço.add(lblEstado);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCidade.setBounds(329, 43, 46, 14);
		painelEndereço.add(lblCidade);
		
		textEstado = new JTextField();
		textEstado.setColumns(10);
		textEstado.setBounds(72, 40, 247, 20);
		painelEndereço.add(textEstado);
		
		textCidade = new JTextField();
		textCidade.setColumns(10);
		textCidade.setBounds(383, 40, 201, 20);
		painelEndereço.add(textCidade);
		
		textNumeroCasa = new JTextField();
		textNumeroCasa.setColumns(10);
		textNumeroCasa.setBounds(393, 81, 191, 20);
		painelEndereço.add(textNumeroCasa);
		
		textLogradouro = new JTextField();
		textLogradouro.setColumns(10);
		textLogradouro.setBounds(103, 81, 216, 20);
		painelEndereço.add(textLogradouro);
		
		textBairro = new JTextField();
		textBairro.setColumns(10);
		textBairro.setBounds(72, 121, 247, 20);
		painelEndereço.add(textBairro);
		
		btnLimparComponentes = new JButton("Limpar Componentes");
		btnLimparComponentes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLimparComponentes.setBounds(271, 389, 189, 39);
		contentPane.add(btnLimparComponentes);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrar.setBounds(470, 389, 154, 39);
		contentPane.add(btnCadastrar);
		
		setLocationRelativeTo(null);
	}
}
