package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Paciente;
import service.PacienteService;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ExameEditarWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JLabel lblTexto;
	
	private PacienteService pacienteService;
	private PacienteWindow telaInicial;

	
	private void abrirJanelaEditar() {
		try {
			Exame exame = this.exameService.buscarPacientePorID(Integer.parseInt(this.textID.getText()));
			PacientesCadastroWindow telaEditar = new PacientesCadastroWindow(this.telaInicial, this, paciente);
			telaEditar.setVisible(true);
			this.setVisible(false);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ExameEditarWindow(PacienteWindow pacienteWindow) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.pacienteService = new PacienteService();
		this.telaInicial = pacienteWindow;
		this.initComponents();
	}
	
	private void fecharJanela() {
		this.dispose();
		
		this.telaInicial.setVisible(true);
	}
	
	private void initComponents() {
		setTitle("Editar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 335, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTexto = new JLabel("Informe o ID do paciente que deseja alterar:");
		lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTexto.setBounds(10, 41, 281, 26);
		contentPane.add(lblTexto);
		
		textID = new JTextField();
		textID.setBounds(10, 83, 86, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaEditar();
			}
		});
		btnConfirmar.setBounds(202, 135, 89, 23);
		contentPane.add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharJanela();
			}
		});
		btnCancelar.setBounds(103, 135, 89, 23);
		contentPane.add(btnCancelar);
		
		setLocationRelativeTo(null);
	}
}
