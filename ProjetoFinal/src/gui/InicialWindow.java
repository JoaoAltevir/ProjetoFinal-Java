package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class InicialWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicialWindow frame = new InicialWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicialWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 575);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadPaciente = new JButton("Cadastrar Pacientes");
		btnCadPaciente.setBounds(225, 59, 154, 60);
		contentPane.add(btnCadPaciente);
		
		JButton btnCadEspecialidade = new JButton("Cadastrar Especialidade");
		btnCadEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadEspecialidade.setBounds(225, 134, 154, 60);
		contentPane.add(btnCadEspecialidade);
		
		JButton btnCadMedico = new JButton("Cadastrar Médico");
		btnCadMedico.setBounds(225, 206, 154, 60);
		contentPane.add(btnCadMedico);
		
		JButton btnAgendarConsulta = new JButton("Agendar Consulta");
		btnAgendarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgendarConsulta.setBounds(225, 280, 154, 60);
		contentPane.add(btnAgendarConsulta);
		
		JButton btnAgendarExame = new JButton("Agendar Exame");
		btnAgendarExame.setBounds(225, 358, 154, 60);
		contentPane.add(btnAgendarExame);
		
		JButton btnExames = new JButton("Exames");
		btnExames.setBounds(225, 429, 154, 60);
		contentPane.add(btnExames);
		
		JLabel lblNewLabel = new JLabel("Olá! Seja Bem-Vindo ao sistema de saúde! ");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		lblNewLabel.setBounds(170, 0, 310, 60);
		contentPane.add(lblNewLabel);
		
	}
	
	public void initComponents() {
		
	}
}
