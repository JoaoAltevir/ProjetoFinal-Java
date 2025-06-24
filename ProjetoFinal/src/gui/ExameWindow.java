package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entities.Exame;
import service.ExameService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textOrientacoes;
	private JTable tblExames;
	private JButton btnApagar;
	private JButton btnAtualizar;
	private JButton btnCadastrar;
	private JTextField textValor;
	private JTextField textNome;
	private JScrollPane scrollPane;
	private JPanel painelExames;
	
	private InicialWindow telaInicial;
	private ExameService exameService;

	
	public ExameWindow(InicialWindow inicialWindow) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.exameService = new ExameService();
		this.telaInicial = inicialWindow;
		this.initComponents();
		buscarTodos();
	}
	
	public void buscarTodos() {		
		try {
			DefaultTableModel modelo = (DefaultTableModel) this.tblExames.getModel();
			modelo.setRowCount(0);
			for(Exame exame: this.exameService.buscarTodos()) {
				modelo.addRow(new Object[] {
						exame.getid_exame(),
						exame.getnome_exame(),
						exame.getValor(),
						exame.getOrientacoes()
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
	
	private void fecharJanela() {
		
		this.dispose();
		telaInicial.setVisible(true);
	}
	private void cadastrarExame() {
		try {
			Exame exame = new Exame();
			
			exame.setnome_exame(this.textNome.getText());
			exame.setOrientacoes(this.textOrientacoes.getText());
			exame.setValor(Double.parseDouble(this.textValor.getText()));
			this.exameService.cadastrar(exame);
			JOptionPane.showMessageDialog(this, "Exame cadastrado com sucesso!");
			this.buscarTodos();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Insira o valor em decimal com '.' e não com ',' ");
		}
		
		
	}
	
	private void apagarRegistro() {
		
		ExameExcluirWindow telaExcluir = new ExameExcluirWindow(this);
		telaExcluir.setVisible(true);
		
	}
	
	private void initComponents() {
		setTitle("Exames");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		painelExames = new JPanel();
		painelExames.setBorder(new TitledBorder(null, "Exames", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelExames.setBounds(292, 32, 374, 382);
		contentPane.add(painelExames);
		painelExames.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 354, 350);
		painelExames.add(scrollPane);
		
		tblExames = new JTable();
		tblExames.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Valor", "Orienta\u00E7\u00F5es"
			}
		));
		tblExames.getColumnModel().getColumn(0).setPreferredWidth(42);
		tblExames.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblExames.getColumnModel().getColumn(3).setPreferredWidth(200);
		scrollPane.setViewportView(tblExames);
		
		btnCadastrar = new JButton("Cadastrar Exame");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarExame();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrar.setBounds(509, 425, 157, 23);
		contentPane.add(btnCadastrar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizar.setBounds(404, 425, 100, 23);
		contentPane.add(btnAtualizar);
		
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apagarRegistro();
			}
		});
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnApagar.setBounds(292, 425, 109, 23);
		contentPane.add(btnApagar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(30, 32, 86, 14);
		contentPane.add(lblNome);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(30, 88, 86, 14);
		contentPane.add(lblValor);
		
		JLabel lblOrientacoes = new JLabel("Orientações:");
		lblOrientacoes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrientacoes.setBounds(30, 144, 86, 23);
		contentPane.add(lblOrientacoes);
		
		textOrientacoes = new JTextField();
		textOrientacoes.setBounds(30, 169, 252, 235);
		contentPane.add(textOrientacoes);
		textOrientacoes.setColumns(10);
		
		textValor = new JTextField();
		textValor.setBounds(30, 113, 86, 20);
		contentPane.add(textValor);
		textValor.setColumns(10);
		
		textNome = new JTextField();
		textNome.setBounds(30, 57, 86, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblTexto1 = new JLabel("Insira valor decimal com .");
		lblTexto1.setBounds(126, 116, 146, 14);
		contentPane.add(lblTexto1);
		
		JLabel lblTexto2 = new JLabel("Exemplo: ''50.00''");
		lblTexto2.setBounds(126, 129, 100, 14);
		contentPane.add(lblTexto2);
		
		setLocationRelativeTo(null);
		
	}
}
