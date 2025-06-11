package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EspecialidadeCadastroWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCadastrar;
	private JTextField textNome;
	private JLabel lblNome;
	
	private EspecialidadeWindow telaEspecialidade;

	public EspecialidadeCadastroWindow(EspecialidadeWindow telaPrincipal) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.telaEspecialidade = telaPrincipal;
		this.initComponents();
	}
	
	private void fecharJanela() {
		this.dispose();
	}
	
private void limparComponentes() {
		
		this.textNome.setText("");
		
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
				//cadastrar fazer a função
				
				limparComponentes();
			}
		});
		btnCadastrar.setBounds(65, 147, 132, 23);
		contentPane.add(btnCadastrar);
		
		setLocationRelativeTo(null);
	}
}
