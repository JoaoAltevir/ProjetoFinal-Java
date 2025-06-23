package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Especialidade;
import service.EspecialidadeService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

public class EspecialidadeCadastroWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCadastrar;
	private JTextField textNome;
	private JLabel lblNome;
	
	private EspecialidadeWindow telaEspecialidade;
	private EspecialidadeService especialidadeService;

	public EspecialidadeCadastroWindow(EspecialidadeWindow telaPrincipal) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.especialidadeService = new EspecialidadeService();
		this.telaEspecialidade = telaPrincipal;
		this.initComponents();
		
	}
	
	private void fecharJanela() {
		this.dispose();
	}
	
	private void limparComponentes() {
		
		this.textNome.setText("");
		
	}
	
	private void cadastrarEspecialidade() {
		try {
			Especialidade especialidade = new Especialidade();
			especialidade.setnome_especialidade(this.textNome.getText());
			
			especialidadeService.cadastrar(especialidade);
			limparComponentes();
			this.telaEspecialidade.buscarTodos();
			JOptionPane.showMessageDialog(this, "Especialidade cadastrada com sucesso!");
		} catch (SQLException | IOException e) {
			e.getMessage();
		}
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 223, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNome = new JLabel("Nome da Especialidade:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(10, 41, 147, 21);
		contentPane.add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(10, 62, 187, 21);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarEspecialidade();
				limparComponentes();
			}
		});
		btnCadastrar.setBounds(97, 146, 100, 23);
		contentPane.add(btnCadastrar);
		
		setLocationRelativeTo(null);
	}
}
