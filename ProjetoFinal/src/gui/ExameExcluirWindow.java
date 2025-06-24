package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.ExameService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExameExcluirWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;
	private JLabel lblTexto;
	private JButton btnCancelar;
	
	private ExameWindow telaInicial;
	private ExameService exameService;

	
	public ExameExcluirWindow(ExameWindow exameWindow) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		
		this.exameService = new ExameService();
		this.telaInicial = exameWindow;
		this.initComponents();
		
	}
	
	private void excluirRegistro() {
		try {
			this.exameService.excluirRegistro(Integer.parseInt(this.textID.getText()));
			limparCampos();
			JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");
			this.telaInicial.buscarTodos();
		} catch (Exception e) {
			
		}
	}
	
	private void limparCampos() {
		this.textID.setText("");
	}
	private void fecharJanela() {
		this.dispose();
	}
	
	private void initComponents() {
		setTitle("Excluir exame");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 404, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTexto = new JLabel("Informe o ID do exame que deseja apagar o registro:");
		lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTexto.setBounds(10, 51, 368, 23);
		contentPane.add(lblTexto);
		
		textID = new JTextField();
		textID.setBounds(10, 85, 86, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir esse registro?", "Confirmação",JOptionPane.YES_NO_OPTION);
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
		setLocationRelativeTo(null);
	}

}
