package gui;

import java.awt.EventQueue;
import java.awt.JobAttributes.DefaultSelectionType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class MedicoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblPacientes;
	private JScrollPane scrollPane;
	private JPanel painelMedicos;
	private JButton btnCadastrar;
	private JButton btnExcluir;
	
	private InicialWindow inicialWindow;
	private JMenuItem mntmVoltar;
	private JMenu mnArquivo;
	private JButton btnEditar;
	
	public MedicoWindow(InicialWindow inicialWindow) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.initComponents();
		this.inicialWindow = inicialWindow;
		
	}
	
//	private void abrirJanelaEditar() {
//		
//		PacienteEditarWindow telaEditar = new (this);
//		//TODO mandar dados do paciente selecionado.
//		telaEditar.setVisible(true);
//		
//		this.setVisible(false);
//	}
	
	private void abrirJanelaCadastro() {
		
		MedicoCadastroWindow telaCadastro = new MedicoCadastroWindow(this);
		telaCadastro.setVisible(true);
		
		this.setVisible(false);
		
	}
	
	private void fecharJanela() {
		
		this.dispose();
		this.inicialWindow.setVisible(true);
	}
	
	public void initComponents() {
		
		setTitle("Médicos");
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
		
		painelMedicos = new JPanel();
		painelMedicos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Medicos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		painelMedicos.setBounds(9, 11, 632, 374);
		contentPane.add(painelMedicos);
		painelMedicos.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 612, 342);
		painelMedicos.add(scrollPane);
		
		tblPacientes = new JTable();
		scrollPane.setViewportView(tblPacientes);
		tblPacientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		tblPacientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
			}
		});
		
		btnCadastrar = new JButton("Cadastrar Novo");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaCadastro();
			}
		});
		btnCadastrar.setBounds(456, 396, 177, 43);
		contentPane.add(btnCadastrar);
		
		btnExcluir = new JButton("Apagar Registro");
		btnExcluir.setBounds(294, 396, 152, 43);
		contentPane.add(btnExcluir);
		
		btnEditar = new JButton("Editar Registro");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblPacientes.getSelectedRow();
				
				if(selectedRow != -1) {
					//pega os valores de das linhas
				}else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione um registro para editar");
				}
				
			}
		});
		btnEditar.setBounds(123, 396, 152, 43);
		contentPane.add(btnEditar);
		
		setLocationRelativeTo(null);
	}
}
