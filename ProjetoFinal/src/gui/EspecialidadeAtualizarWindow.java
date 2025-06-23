package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Especialidade;
import service.EspecialidadeService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EspecialidadeAtualizarWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;
	private JTextField textEspecialidade;
	private JLabel lblTexto;
	private JLabel lblNewLabel;
	private JButton btnCancelar;
	private JButton btnConfirmar;
	
	private EspecialidadeWindow telaInicial;
	private EspecialidadeService especialidadeService;
	

	
	public EspecialidadeAtualizarWindow(EspecialidadeWindow especialidadeWindow) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		this.especialidadeService = new EspecialidadeService();
		this.telaInicial = especialidadeWindow;
		this.initComponents();
	}
	
	private void atualizarRegistro() {
		try {
			
			Especialidade especialidade = new Especialidade();
			especialidade.setid_especialidade(Integer.parseInt(this.textID.getText()));
			especialidade.setnome_especialidade(this.textEspecialidade.getText());
			this.especialidadeService.atualizar(especialidade);
			JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
			limparCampos();
			this.telaInicial.buscarTodos();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ID informado não existe!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	private void limparCampos() {
		this.textEspecialidade.setText("");
		this.textID.setText("");
	}
	private void fecharJanela() {
		this.dispose();
	}
	
	private void initComponents() {
		setTitle("Editar especialidade");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 396, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTexto = new JLabel("Informe o ID da especialidade que deseja alterar:");
		lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTexto.setBounds(10, 23, 304, 21);
		contentPane.add(lblTexto);
		
		lblNewLabel = new JLabel("Insira o novo nome da especialidade:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 86, 243, 14);
		contentPane.add(lblNewLabel);
		
		textID = new JTextField();
		textID.setBounds(10, 55, 86, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		textEspecialidade = new JTextField();
		textEspecialidade.setBounds(10, 111, 86, 20);
		contentPane.add(textEspecialidade);
		textEspecialidade.setColumns(10);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Você deseja mesmo alterar esse registro?", "Atualizar", JOptionPane.YES_NO_OPTION);
				if(confirmacao == JOptionPane.YES_OPTION) {
					atualizarRegistro();
				}else {
					fecharJanela();
				}
			}
		});
		btnConfirmar.setBounds(264, 175, 89, 23);
		contentPane.add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharJanela();
			}
		});
		btnCancelar.setBounds(164, 175, 89, 23);
		contentPane.add(btnCancelar);
		
		setLocationRelativeTo(null);	}

}
