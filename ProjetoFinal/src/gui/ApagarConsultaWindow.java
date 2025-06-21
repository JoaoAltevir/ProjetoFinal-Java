package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ApagarConsultaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;

	
	public ApagarConsultaWindow() {
		setTitle("Apagar Consulta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 179);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTexto = new JLabel("Qual consulta deseja apagar?");
		lblTexto.setBounds(10, 30, 156, 14);
		contentPane.add(lblTexto);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(10, 55, 19, 14);
		contentPane.add(lblID);
		
		JButton btnOK = new JButton("CONFIRMAR");
		btnOK.setBounds(167, 106, 93, 23);
		contentPane.add(btnOK);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(69, 106, 93, 23);
		contentPane.add(btnCancelar);
		
		textID = new JTextField();
		textID.setBounds(32, 52, 44, 20);
		contentPane.add(textID);
		textID.setColumns(10);
	}
}
