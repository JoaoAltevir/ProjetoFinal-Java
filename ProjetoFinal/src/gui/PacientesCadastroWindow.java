package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import entities.Endereco;
import entities.Paciente;
import service.EnderecoService;
import service.PacienteService;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
	private PacienteEditarWindow pacienteEditarWindow;
	private PacienteService pacienteService;
	private EnderecoService enderecoService;
	private Paciente pacienteEdit;
	
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraNumeroContato;
	private final ButtonGroup bgSexo;
	private final ButtonGroup bgPagamento;
	private JTextField textCaminhoFoto;
	private JButton btnFileChooser;
	private JLabel lblFoto;
	private JRadioButton rdbtnDinheiro;
	private JRadioButton rdbtnPix;
	private JRadioButton rdbtnDebito;
	private JRadioButton rdbtnCredito;
	private JTextField textID;
	

	
	
	/**
	 * @wbp.parser.constructor
	 */
	public PacientesCadastroWindow(PacienteWindow pacienteWindow) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.enderecoService = new EnderecoService();
		this.pacienteService = new PacienteService();
		this.criarMascaraData();
		this.criarMascaraNumeroContato();
		this.bgSexo = new ButtonGroup();
		this.bgPagamento = new ButtonGroup();
		this.pacienteWindow = pacienteWindow;
		this.initComponents();
		this.textID.setText("");
	}
	
	public PacientesCadastroWindow(PacienteWindow pacienteWindow, PacienteEditarWindow pacienteEditarWindow, Paciente paciente) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.pacienteEdit = paciente;
		this.enderecoService = new EnderecoService();
		this.pacienteService = new PacienteService();
		this.criarMascaraData();
		this.criarMascaraNumeroContato();
		this.bgSexo = new ButtonGroup();
		this.bgPagamento = new ButtonGroup();
		this.pacienteWindow = pacienteWindow;
		this.pacienteEditarWindow = pacienteEditarWindow;
		this.initComponents();
		
		this.textID.setText(Integer.toString(this.pacienteEdit.getId_paciente()));
		this.textNome.setText(this.pacienteEdit.getNome());
		this.formattedText_Data.setText(formatarData(this.pacienteEdit.getdata_nascimento()));
		this.formattedText_Telefone.setText(this.pacienteEdit.getTelefone());
		this.textCaminhoFoto.setText(this.pacienteEdit.getfoto_paciente());
		selecionarSexo();
		selecionarFormaPagamento();
		buscarFoto();
		this.textBairro.setText(this.pacienteEdit.getEndereco().getBairro());
		this.textCidade.setText(this.pacienteEdit.getEndereco().getCidade());
		this.textEstado.setText(this.pacienteEdit.getEndereco().getEstado());
		this.textLogradouro.setText(this.pacienteEdit.getEndereco().getLogradouro());
		this.textNumeroCasa.setText(this.pacienteEdit.getEndereco().getNumero());

	}
	
	//FUNÇÕES DE EDIÇÃO DE REGISTRO
	private String formatarData(String data) {
		LocalDate dataFormatada = LocalDate.parse(data);
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataFormatada.format(formatacao);
	}
	
	private void buscarFoto() {
		
		String caminhoFoto = this.pacienteEdit.getfoto_paciente();
		ImageIcon previewFoto = new ImageIcon(caminhoFoto);
    	Image foto = previewFoto.getImage().getScaledInstance(138, 110, Image.SCALE_SMOOTH);
    	this.lblFoto.setIcon(new ImageIcon(foto));
    	
	}
	
	private void selecionarSexo() {
		if(this.pacienteEdit.getSexo() == 'M') {
			rbMasculino.setSelected(true);
		}else if(this.pacienteEdit.getSexo() == 'F') {
			rbFeminino.setSelected(true);
		}
	}
	
	private void selecionarFormaPagamento() {
		if(this.pacienteEdit.getforma_pagamento().equals(rdbtnPix.getText())) {
			rdbtnPix.setSelected(true);
		}else if(this.pacienteEdit.getforma_pagamento().equals(rdbtnDebito.getText())) {
			rdbtnDebito.setSelected(true);
		}else if(this.pacienteEdit.getforma_pagamento().equals(rdbtnDinheiro.getText())) {
			rdbtnDinheiro.setSelected(true);
		}else if(this.pacienteEdit.getforma_pagamento().equals(rdbtnCredito.getText())) {
			rdbtnCredito.setSelected(true);
		}
	}
	
	private void editar() {
		try {
			
			this.pacienteEdit.setNome(this.textNome.getText());
			this.pacienteEdit.setTelefone(this.formattedText_Telefone.getText());
			this.pacienteEdit.setfoto_paciente(this.textCaminhoFoto.getText());
			this.pacienteEdit.setdata_nascimento(converterData().toString());
			this.pacienteEdit.setSexo(verificarSexo());
			this.pacienteEdit.setforma_pagamento(verificarPagamento());
			this.pacienteEdit.getEndereco().setCidade(this.textCidade.getText());
			this.pacienteEdit.getEndereco().setBairro(this.textBairro.getText());
			this.pacienteEdit.getEndereco().setEstado(this.textEstado.getText());
			this.pacienteEdit.getEndereco().setLogradouro(this.textLogradouro.getText());
			this.pacienteEdit.getEndereco().setNumero(this.textNumeroCasa.getText());
			
			this.enderecoService.atualizarEndereco(this.pacienteEdit.getEndereco());
			
			this.pacienteService.atualizarPaciente(this.pacienteEdit);
			
			JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
			limparComponentes();
			this.pacienteWindow.buscarTodos();
			this.fecharJanela();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	//================================================
	
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
		this.textCaminhoFoto.setText("");
		this.lblFoto.setIcon(null);

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
	
	private void selecionarFoto() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Selecione uma imagem de perfil:");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		
		FileNameExtensionFilter filtroImagem = new FileNameExtensionFilter("Imagens (JPG, PNG, GIF)", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filtroImagem);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        int resultado = fileChooser.showOpenDialog(null);
        
        if(resultado == JFileChooser.APPROVE_OPTION) {
        	File fotoSelecionada = fileChooser.getSelectedFile();
        	
        	String caminhoFoto = fotoSelecionada.getAbsolutePath();
        	
        	this.textCaminhoFoto.setText(caminhoFoto);
        	
        	ImageIcon previewFoto = new ImageIcon(caminhoFoto);
        	
        	Image foto = previewFoto.getImage().getScaledInstance(138, 110, Image.SCALE_SMOOTH);
        	this.lblFoto.setIcon(new ImageIcon(foto));
      
        }
	}
	
	private LocalDate converterData() {
		
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(this.formattedText_Data.getText(), dataFormatada);
		
	}
	
	private void cadastrar() {
		try {
			Paciente paciente = new Paciente();
			Endereco endereco = new Endereco();
			
			paciente.setNome(this.textNome.getText());
			paciente.setTelefone(this.formattedText_Telefone.getText());
			paciente.setfoto_paciente(this.textCaminhoFoto.getText());
			paciente.setdata_nascimento(converterData().toString());
			paciente.setSexo(verificarSexo());
			paciente.setforma_pagamento(verificarPagamento());
			endereco.setCidade(this.textCidade.getText());
			endereco.setBairro(this.textBairro.getText());
			endereco.setEstado(this.textEstado.getText());
			endereco.setLogradouro(this.textLogradouro.getText());
			endereco.setNumero(this.textNumeroCasa.getText());
			int idEndereco = this.enderecoService.cadastrar(endereco);
			endereco.setId_endereco(idEndereco);		
			paciente.setEndereco(endereco);
			
			this.pacienteService.cadastrar(paciente);
			
			JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
			limparComponentes();
			this.pacienteWindow.buscarTodos();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	
	private char verificarSexo() {
		if(rbMasculino.isSelected()) {
			return 'M';
		}else if(rbFeminino.isSelected()){
			return 'F';
		}
		return 'N';
	}
	
	private String verificarPagamento() {
		if(rdbtnCredito.isSelected()) {
			return this.rdbtnCredito.getText();
		}else if(rdbtnDebito.isSelected()) {
			return this.rdbtnDebito.getText();
		}else if(rdbtnDinheiro.isSelected()) {
			return this.rdbtnDinheiro.getText();
		}else if(rdbtnPix.isSelected()) {
			return this.rdbtnPix.getText();
		}
		
		return "null";
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
		painelFoto.setBounds(455, 17, 158, 132);
		painelDados.add(painelFoto);
		painelFoto.setLayout(null);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(10, 11, 138, 110);
		painelFoto.add(lblFoto);
		
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
		painelPagamento.setBounds(156, 106, 201, 87);
		painelDados.add(painelPagamento);
		painelPagamento.setLayout(null);
		
		rdbtnPix = new JRadioButton("PIX");
		bgPagamento.add(rdbtnPix);
		rdbtnPix.setBounds(6, 19, 57, 23);
		painelPagamento.add(rdbtnPix);
		
		rdbtnDebito = new JRadioButton("Débito");
		bgPagamento.add(rdbtnDebito);
		rdbtnDebito.setBounds(6, 45, 57, 23);
		painelPagamento.add(rdbtnDebito);
		
		rdbtnCredito = new JRadioButton("Crédito");
		bgPagamento.add(rdbtnCredito);
		rdbtnCredito.setBounds(65, 19, 109, 23);
		painelPagamento.add(rdbtnCredito);
		
		rdbtnDinheiro = new JRadioButton("Dinheiro");
		bgPagamento.add(rdbtnDinheiro);
		rdbtnDinheiro.setBounds(65, 45, 109, 23);
		painelPagamento.add(rdbtnDinheiro);
		
		textCaminhoFoto = new JTextField();
		textCaminhoFoto.setBounds(455, 160, 158, 20);
		painelDados.add(textCaminhoFoto);
		textCaminhoFoto.setColumns(10);
		
		btnFileChooser = new JButton("Selecionar imagem...");
		btnFileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarFoto();     
			}
		});
		btnFileChooser.setBounds(455, 181, 158, 23);
		painelDados.add(btnFileChooser);
		
		btnCadastrar = new JButton("Atualizar/Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textID.getText().equals("")) {
					cadastrar();
				}else{
					editar();
				}
			}
		});
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
		
		textID = new JTextField();
		textID.setBounds(23, 452, 86, 20);
		textID.setVisible(false);
		contentPane.add(textID);
		textID.setColumns(10);
	
		
		setLocationRelativeTo(null);
	}
}
