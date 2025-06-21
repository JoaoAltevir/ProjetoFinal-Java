package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PacientesCadastroWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEstado;
	private JTextField textCidade;
	private JTextField textNumeroCasa;
	private JTextField textLogradouro;
	private JTextField textBairro;
	private JButton btnLimparComponentes;
	private JButton btnCadastrar;
	private JPanel painelEndereço;
	private JPanel painelDados;
	private JRadioButton rbFeminino;
	private JRadioButton rbMasculino;
	private JPanel painelSexo;
	private JPanel painelPagamento;
	private JComboBox cbPagamento;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblDataNascimento;
	private JLabel lblEstado;
	private JLabel lblLogradouro;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblNumeroCasa;
	private JFormattedTextField formattedText_Telefone;
	private JFormattedTextField formattedText_Data;
	private JMenu mnArquivo;
	private JMenuItem mntmSair;
	private JMenuBar menuBar;
	
	private PacienteWindow pacienteWindow;
	
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraNumeroContato;
	private final ButtonGroup bgSexo;

	
	//TODO inserir foto no cadastro
	
	public PacientesCadastroWindow(PacienteWindow pacienteWindow) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.criarMascaraData();
		this.criarMascaraNumeroContato();
		this.bgSexo = new ButtonGroup();
		this.initComponents();
		this.pacienteWindow = pacienteWindow;
	}
	
	private void limparComponentes() {
		
		this.textNome.setText("");
		this.formattedText_Telefone.setText("");
		this.formattedText_Data.setText("");
		this.textBairro.setText("");
		this.textLogradouro.setText("");
		this.textEstado.setText("");
		this.textNumeroCasa.setText("");
		this.textCidade.setText("");
		this.rbFeminino.setSelected(false);
		this.rbMasculino.setSelected(false);		
		
	}
	
	private void criarMascaraData() {

		try {

			this.mascaraData = new MaskFormatter("##/##/####");

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	private void criarMascaraNumeroContato() {
		try {
			
			this.mascaraNumeroContato = new MaskFormatter("(##)#####-####");
		} catch (ParseException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	private void fecharJanela() {
		
		this.dispose();
		this.pacienteWindow.setVisible(true);
	}
	
	
	public void initComponents() {
		setTitle("Cadastro de Paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 673, 570);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		painelDados = new JPanel();
		painelDados.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		painelDados.setBounds(10, 11, 637, 417);
		contentPane.add(painelDados);
		painelDados.setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(23, 17, 46, 23);
		painelDados.add(lblNome);
		
		painelSexo = new JPanel();
		painelSexo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		painelSexo.setBounds(23, 106, 129, 87);
		painelDados.add(painelSexo);
		painelSexo.setLayout(null);
		
		rbMasculino = new JRadioButton("Masculino");
		bgSexo.add(rbMasculino);
		rbMasculino.setBounds(6, 23, 109, 23);
		painelSexo.add(rbMasculino);
		
		rbFeminino = new JRadioButton("Feminino");
		bgSexo.add(rbFeminino);
		rbFeminino.setBounds(6, 49, 109, 23);
		painelSexo.add(rbFeminino);
		
		painelEndereço = new JPanel();
		painelEndereço.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelEndereço.setBounds(10, 204, 617, 185);
		painelDados.add(painelEndereço);
		painelEndereço.setLayout(null);
		
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
		
		lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCidade.setBounds(329, 43, 46, 14);
		painelEndereço.add(lblCidade);
		
		textEstado = new JTextField();
		textEstado.setBounds(72, 40, 247, 20);
		painelEndereço.add(textEstado);
		textEstado.setColumns(10);
		
		textCidade = new JTextField();
		textCidade.setBounds(383, 40, 224, 20);
		painelEndereço.add(textCidade);
		textCidade.setColumns(10);
		
		textNumeroCasa = new JTextField();
		textNumeroCasa.setBounds(393, 81, 214, 20);
		painelEndereço.add(textNumeroCasa);
		textNumeroCasa.setColumns(10);
		
		textLogradouro = new JTextField();
		textLogradouro.setBounds(103, 81, 216, 20);
		painelEndereço.add(textLogradouro);
		textLogradouro.setColumns(10);
		
		textBairro = new JTextField();
		textBairro.setBounds(72, 121, 247, 20);
		painelEndereço.add(textBairro);
		textBairro.setColumns(10);
		
		textNome = new JTextField();
		textNome.setBounds(67, 20, 377, 20);
		painelDados.add(textNome);
		textNome.setColumns(10);
		
		JPanel painelFoto = new JPanel();
		painelFoto.setBounds(489, 23, 123, 155);
		painelDados.add(painelFoto);
		
		JLabel lblNewLabel = new JLabel("foto");
		painelFoto.add(lblNewLabel);
		
		lblDataNascimento = new JLabel("Data de Nascimento:");
		lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataNascimento.setBounds(23, 76, 132, 14);
		painelDados.add(lblDataNascimento);
		
		formattedText_Data = new JFormattedTextField(mascaraData);
		formattedText_Data.setBounds(156, 75, 93, 20);
		painelDados.add(formattedText_Data);
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(23, 51, 68, 14);
		painelDados.add(lblTelefone);
		
		formattedText_Telefone = new JFormattedTextField(mascaraNumeroContato);
		formattedText_Telefone.setBounds(88, 50, 123, 20);
		painelDados.add(formattedText_Telefone);
		
		painelPagamento = new JPanel();
		painelPagamento.setBorder(new TitledBorder(null, "Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelPagamento.setBounds(177, 106, 201, 87);
		painelDados.add(painelPagamento);
		painelPagamento.setLayout(null);
		
		cbPagamento = new JComboBox();
		cbPagamento.setBounds(10, 30, 181, 22);
		painelPagamento.add(cbPagamento);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrar.setBounds(493, 441, 154, 39);
		contentPane.add(btnCadastrar);
		
		btnLimparComponentes = new JButton("Limpar Componentes");
		btnLimparComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparComponentes();
			}
		});
		btnLimparComponentes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLimparComponentes.setBounds(294, 441, 189, 39);
		contentPane.add(btnLimparComponentes);
		
		setLocationRelativeTo(null);
	}
}
