package gui;

import java.awt.EventQueue;
import java.awt.JobAttributes.DefaultSelectionType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Paciente;
import service.PacienteService;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PacienteWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblPacientes;
	private JScrollPane scrollPane;
	private JPanel painelPacientes;
	private JButton btnCadastrar;
	private JButton btnExcluir;
	
	private InicialWindow inicialWindow;
	private JMenuItem mntmVoltar;
	private JMenu mnArquivo;
	private JButton btnEditar;
	private PacienteService pacienteService;
	
	public PacienteWindow(InicialWindow inicialWindow) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.pacienteService = new PacienteService();
		this.inicialWindow = inicialWindow;
		this.initComponents();
		this.buscarTodos();
		
	}
	
	//CHAMADA DAS OUTRAS JANELAS
	
	private void editarRegistro() {
		
		PacienteEditarWindow telaEditar = new PacienteEditarWindow(this);
		telaEditar.setVisible(true);
		
		this.setVisible(false);
		
	}
	
	private void abrirJanelaCadastro() {
		
		PacientesCadastroWindow telaCadastro = new PacientesCadastroWindow(this);
		telaCadastro.setVisible(true);
		
		this.setVisible(false);
		
	}
	
	private void abrirJanelaApagar() {
		
		PacienteExcluirWindow telaExcluir = new PacienteExcluirWindow(this);
		telaExcluir.setVisible(true);
	}
	
	
	private void fecharJanela() {
		
		this.dispose();
		this.inicialWindow.setVisible(true);
	}
	
	//=============================================================================================
	
	//Funções desta tela
	
	private String formatarData(String data) {
		LocalDate dataFormatada = LocalDate.parse(data);
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataFormatada.format(formatacao);
	}
	
	public void buscarTodos() {
		try {
			DefaultTableModel modelo = (DefaultTableModel) tblPacientes.getModel();
			modelo.setRowCount(0);
			for(Paciente paciente: this.pacienteService.buscarTodos()) {
				modelo.addRow(new Object[] {
						paciente.getId_paciente(),
						paciente.getNome(),
						paciente.getSexo(),
						formatarData(paciente.getdata_nascimento()),
						paciente.getTelefone(),
						paciente.getforma_pagamento()
				});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//=============================================================================================
	public void initComponents() {
		
		setTitle("Pacientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 668, 561);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		mntmVoltar = new JMenuItem("Voltar");
		mntmVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharJanela();
			}
		});
		mntmVoltar.setIcon(null);
		mnArquivo.add(mntmVoltar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		painelPacientes = new JPanel();
		painelPacientes.setBorder(new TitledBorder(null, "Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelPacientes.setBounds(9, 11, 632, 374);
		contentPane.add(painelPacientes);
		painelPacientes.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 612, 342);
		painelPacientes.add(scrollPane);
		
		tblPacientes = new JTable();
		scrollPane.setViewportView(tblPacientes);
		tblPacientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Sexo", "Data Nascimento", "Telefone", "Forma Pagamento"
			}
		));
		tblPacientes.getColumnModel().getColumn(0).setPreferredWidth(39);
		tblPacientes.getColumnModel().getColumn(2).setPreferredWidth(60);
		tblPacientes.getColumnModel().getColumn(3).setPreferredWidth(112);
		tblPacientes.getColumnModel().getColumn(4).setPreferredWidth(92);
		tblPacientes.getColumnModel().getColumn(5).setPreferredWidth(108);
		
		btnCadastrar = new JButton("Cadastrar Novo");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaCadastro();
			}
		});
		btnCadastrar.setBounds(456, 396, 177, 43);
		contentPane.add(btnCadastrar);
		
		btnExcluir = new JButton("Apagar Registro");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaApagar();
			}
		});
		btnExcluir.setBounds(294, 396, 152, 43);
		contentPane.add(btnExcluir);
		
		btnEditar = new JButton("Editar Registro");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarRegistro();
			}
		});
		btnEditar.setBounds(123, 396, 152, 43);
		contentPane.add(btnEditar);
		
		setLocationRelativeTo(null);
	}
}
