package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.MedicoService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MedicoApagarWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JLabel lblTexto;
	
	private MedicoService medicoService;
	private MedicoWindow medicoWindow;

	
	public MedicoApagarWindow(MedicoWindow medicoWindow) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.medicoService = new MedicoService();
		this.medicoWindow = medicoWindow;
		this.initComponents();
	}
	
	private void limparCampo() {
		
		this.textID.setText("");
	}

	private void fecharJanela() {
		
		this.dispose();
		this.medicoWindow.setVisible(true);
	}
	
	private void excluirRegistro() {
		try {
			this.medicoService.excluirMedico(Integer.parseInt(this.textID.getText()));
			JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");
			limparCampo();
			this.medicoWindow.buscarTodos();
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
	
	private void initComponents() {
		setTitle("Excluir paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 404, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTexto = new JLabel("Informe o ID do médico que deseja apagar o registro:");
		lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTexto.setBounds(10, 51, 339, 23);
		contentPane.add(lblTexto);
		
		textID = new JTextField();
		textID.setBounds(10, 85, 86, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(confirmacao == JOptionPane.YES_OPTION) {
					excluirRegistro();
				}else {
					fecharJanela();
				}
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirmar.setBounds(271, 175, 107, 23);
		contentPane.add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharJanela();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(172, 175, 89, 23);
		contentPane.add(btnCancelar);
	}

}

