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
	private JLabel lblNewLabel;
	private JButton btnCadPaciente;
	private JButton btnCadEspecialidade;
	private JButton btnCadMedico;
	private JButton btnAgendarConsulta;
	private JButton btnAgendarExame;
	private JButton btnExames;


	private void abrirJanelaPacientes() {
		
		PacienteWindow janelaPaciente = new PacienteWindow(this);
		janelaPaciente.setVisible(true);
		
		this.setVisible(false);
		
	}	
	
	private void abrirJanelaEspecialidade() {
		
		EspecialidadeWindow janelaEspecialidade = new EspecialidadeWindow(this);
		janelaEspecialidade.setVisible(true);
		
		this.setVisible(false);
		
	}
	
	public InicialWindow() {
		
		this.initComponents();
		
	}
	
	public void initComponents() {
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
		
		btnCadPaciente = new JButton("Cadastrar Pacientes");
		btnCadPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				abrirJanelaPacientes();
			}
		});
		btnCadPaciente.setBounds(225, 59, 187, 60);
		contentPane.add(btnCadPaciente);
		
		btnCadEspecialidade = new JButton("Cadastrar Especialidade");
		btnCadEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaEspecialidade();
				//abrirJanelaEspecialidade(); TODO implementar tela e seu metodo construtor
				
			}
		});
		btnCadEspecialidade.setBounds(225, 134, 187, 60);
		contentPane.add(btnCadEspecialidade);
		
		btnCadMedico = new JButton("Cadastrar Médico");
		btnCadMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO tela de médicos
			}
		});
		btnCadMedico.setBounds(225, 206, 187, 60);
		contentPane.add(btnCadMedico);
		
		btnAgendarConsulta = new JButton("Agendar Consulta");
		btnAgendarConsulta.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                            //TODO tela de agendamento TELA DE CONSULTAS (listando todas as consultas), (cadastro de consulta), (apagar cadastro)
			}
		});
		btnAgendarConsulta.setBounds(225, 280, 187, 60);
		contentPane.add(btnAgendarConsulta);
		
		btnAgendarExame = new JButton("Agendar Exame");
		btnAgendarExame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO telas (menu exames agendados), (cadastrar agenda de exame), (apagar agenda de exame)
			}
		});
		btnAgendarExame.setBounds(225, 358, 187, 60);
		contentPane.add(btnAgendarExame);
		
		btnExames = new JButton("Exames");
		btnExames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO telas: (exames disponiveis no sistema), (cadastrar novo exame), (apagar exame)
			}
		});
		btnExames.setBounds(225, 429, 187, 60);
		contentPane.add(btnExames);
		
		lblNewLabel = new JLabel("Olá! Seja Bem-Vindo ao sistema de saúde! ");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		lblNewLabel.setBounds(170, 0, 310, 60);
		contentPane.add(lblNewLabel);
		
		setLocationRelativeTo(null);
	}
	

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

}
