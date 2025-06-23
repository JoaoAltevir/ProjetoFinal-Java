package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.ConsultaService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AtualizarConsultaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;
	private JButton btnCancelar;
	private JButton btnOK;
	
	private ConsultaService consultaService;
	private AgendarConsultaWindow telaConsulta;

	
	
	public AtualizarConsultaWindow(AgendarConsultaWindow agendarConsultaWindow) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.telaConsulta = agendarConsultaWindow;
		this.consultaService = new ConsultaService();
		this.initComponents();
	}
	
	private void editarConsulta() {
		try {
			this.consultaService.atualizar(Integer.parseInt(this.textID.getText()));
			this.telaConsulta.medicoSelecionado();
		} catch (NumberFormatException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void fecharJanela() {
		
		this.dispose();
		this.telaConsulta.setVisible(true);
	}
	
	private void initComponents() {
		setTitle("Atualizar Consulta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 301, 179);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTexto = new JLabel("Qual consulta deseja marcar como realizada?");
		lblTexto.setBounds(10, 30, 238, 14);
		contentPane.add(lblTexto);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(10, 55, 19, 14);
		contentPane.add(lblID);
		
		btnOK = new JButton("CONFIRMAR");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Desejar marcar como realizada?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(confirmacao == JOptionPane.YES_OPTION) {
					editarConsulta();
				}
			}
		});
		btnOK.setBounds(167, 106, 93, 23);
		contentPane.add(btnOK);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharJanela();
			}
		});
		btnCancelar.setBounds(69, 106, 93, 23);
		contentPane.add(btnCancelar);
		
		textID = new JTextField();
		textID.setBounds(32, 52, 44, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		setLocationRelativeTo(null);
	}
}
