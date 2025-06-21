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
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ExameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableExames;
	private JTextField textOrientacoes;
	private JTextField textValor;
	private JTextField textNome;

	
	public ExameWindow() {
		setTitle("Exames");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel painelExames = new JPanel();
		painelExames.setBorder(new TitledBorder(null, "Exames", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelExames.setBounds(292, 32, 374, 382);
		contentPane.add(painelExames);
		painelExames.setLayout(null);
		
		JScrollPane spExames = new JScrollPane();
		spExames.setBounds(10, 22, 354, 349);
		painelExames.add(spExames);
		
		tableExames = new JTable();
		spExames.setViewportView(tableExames);
		
		JButton btnCadastrar = new JButton("Cadastrar Exame");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrar.setBounds(509, 425, 157, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtualizar.setBounds(404, 425, 100, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnApagar.setBounds(292, 425, 109, 23);
		contentPane.add(btnApagar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(30, 69, 86, 14);
		contentPane.add(lblNome);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(30, 122, 86, 14);
		contentPane.add(lblValor);
		
		JLabel lblOrientacoes = new JLabel("Orientações:");
		lblOrientacoes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrientacoes.setBounds(30, 174, 86, 14);
		contentPane.add(lblOrientacoes);
		
		textOrientacoes = new JTextField();
		textOrientacoes.setBounds(30, 199, 252, 215);
		contentPane.add(textOrientacoes);
		textOrientacoes.setColumns(10);
		
		textValor = new JTextField();
		textValor.setBounds(30, 147, 86, 20);
		contentPane.add(textValor);
		textValor.setColumns(10);
		
		textNome = new JTextField();
		textNome.setBounds(30, 91, 86, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
	}
}
