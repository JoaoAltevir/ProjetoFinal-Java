package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.table.DefaultTableModel;

import entities.Especialidade;
import service.EspecialidadeService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EspecialidadeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel painelEspecialidade;
	private JMenu mnArquivo;
	private JMenuBar menuBar;
	private JButton btnApagarEspecialidade;
	private JButton btnAtualizarEspecialidade;

	private InicialWindow telaInicial;
	private JButton btnCadEspecialidade;
	private JTable tableEspecialidade;
	private JScrollPane spEspecialidade;
	private EspecialidadeService especialidadeService;

	
	
	public EspecialidadeWindow(InicialWindow telaInicial) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.especialidadeService = new EspecialidadeService();
		this.telaInicial = telaInicial;
		this.initComponets();
		this.buscarTodos();
	}
	
	private void fecharJanela() {
		
		this.dispose();
		this.telaInicial.setVisible(true);
		
	}
	
	private void abrirCadastro() {
		
		EspecialidadeCadastroWindow telaCadastro = new EspecialidadeCadastroWindow(this);
		telaCadastro.setVisible(true);
		
	}
	
	private void abrirAtualizar() {
		
		EspecialidadeAtualizarWindow telaAtualizar = new EspecialidadeAtualizarWindow(this);
		telaAtualizar.setVisible(true);
	}
	
	private void abrirExcluir() {
		
		EspecialidadeExcluirWindow telaExcluir = new EspecialidadeExcluirWindow(this);
		telaExcluir.setVisible(true);
		
	}
	
	public void buscarTodos() {
		try {
			DefaultTableModel modelo = (DefaultTableModel) this.tableEspecialidade.getModel();
			modelo.setRowCount(0);
			for (Especialidade especialidade: especialidadeService.buscarTodos()) {
				modelo.addRow(new Object[] {
					especialidade.getid_especialidade(),
					especialidade.getnome_especialidade()
				});
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void initComponets() {
		setTitle("Especialidades");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 552, 537);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		painelEspecialidade = new JPanel();
		painelEspecialidade.setBorder(new TitledBorder(null, "Especialidades", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelEspecialidade.setBounds(27, 23, 226, 425);
		contentPane.add(painelEspecialidade);
		painelEspecialidade.setLayout(null);
		
		spEspecialidade = new JScrollPane();
		spEspecialidade.setBounds(10, 21, 206, 393);
		painelEspecialidade.add(spEspecialidade);
		
		tableEspecialidade = new JTable();
		tableEspecialidade.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome"
			}
		));
		tableEspecialidade.getColumnModel().getColumn(0).setPreferredWidth(37);
		tableEspecialidade.getColumnModel().getColumn(0).setMinWidth(14);
		spEspecialidade.setViewportView(tableEspecialidade);
		
		btnCadEspecialidade = new JButton("Cadastrar Especialidade");
		btnCadEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCadastro();
			}
		});
		btnCadEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCadEspecialidade.setBounds(300, 86, 162, 73);
		contentPane.add(btnCadEspecialidade);
		
		btnApagarEspecialidade = new JButton("Apagar Especialidade");
		btnApagarEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirExcluir();
			}
		});
		btnApagarEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnApagarEspecialidade.setBounds(300, 170, 162, 73);
		contentPane.add(btnApagarEspecialidade);
		
		btnAtualizarEspecialidade = new JButton("Atualizar Especialidade");
		btnAtualizarEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirAtualizar();
			}
		});
		btnAtualizarEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtualizarEspecialidade.setBounds(300, 254, 162, 73);
		contentPane.add(btnAtualizarEspecialidade);
		
		setLocationRelativeTo(null);
		
	}
}
