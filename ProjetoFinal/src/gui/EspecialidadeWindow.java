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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EspecialidadeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblEspecialidade;
	private JPanel painelEspecialidade;
	private JScrollPane scrollPane;
	private JMenu mnArquivo;
	private JMenuBar menuBar;
	private JButton btnApagarEspecialidade;
	private JButton btnAtualizarEspecialidade;

	private InicialWindow telaInicial;
	private JButton btnCadEspecialidade;

	
	
	public EspecialidadeWindow(InicialWindow telaInicial) {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.telaInicial = telaInicial;
		this.initComponets();
	}
	
	private void fecharJanela() {
		
		this.dispose();
		this.telaInicial.setVisible(true);
		
	}
	
	private void abrirCadastro() {
		
		EspecialidadeCadastroWindow telaCadastro = new EspecialidadeCadastroWindow(this);
		telaCadastro.setVisible(true);
		
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
		painelEspecialidade.setBounds(27, 23, 162, 364);
		contentPane.add(painelEspecialidade);
		painelEspecialidade.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 142, 331);
		painelEspecialidade.add(scrollPane);
		
		tblEspecialidade = new JTable();
		scrollPane.setViewportView(tblEspecialidade);
		tblEspecialidade.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome"
			}
		));
		
		btnCadEspecialidade = new JButton("Cadastrar Especialidade");
		btnCadEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCadastro();
			}
		});
		btnCadEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCadEspecialidade.setBounds(275, 80, 162, 73);
		contentPane.add(btnCadEspecialidade);
		
		btnApagarEspecialidade = new JButton("Apagar Especialidade");
		btnApagarEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnApagarEspecialidade.setBounds(275, 164, 162, 73);
		contentPane.add(btnApagarEspecialidade);
		
		btnAtualizarEspecialidade = new JButton("Atualizar Especialidade");
		btnAtualizarEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtualizarEspecialidade.setBounds(275, 248, 162, 73);
		contentPane.add(btnAtualizarEspecialidade);
		
		setLocationRelativeTo(null);
		
	}
}
