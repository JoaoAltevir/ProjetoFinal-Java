package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.PacienteService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PacienteExcluirWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;
	private JButton btnConfirmar;
	
	private PacienteWindow telaInicial;
	private PacienteService pacienteService;

	
	public PacienteExcluirWindow(PacienteWindow pacienteWindow) {
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
	}
	
	private void limparCampos() {
		
		this.textID.setText("");
	}
	
	private void initComponents() {
		setTitle("Excluir paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 367, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTexto = new JLabel("Informe o ID do paciente que deseja apagar o registro:");
		lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTexto.setBounds(10, 51, 345, 23);
		contentPane.add(lblTexto);
		
		textID = new JTextField();
		textID.setBounds(10, 85, 86, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse registro?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(confirmacao == JOptionPane.YES_OPTION) {
					ExcluirWorker thread = new ExcluirWorker(Integer.parseInt(textID.getText())); 
					thread.execute();
				}
			}
		});
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirmar.setBounds(234, 175, 107, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharJanela();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(135, 175, 89, 23);
		contentPane.add(btnCancelar);
		
		setLocationRelativeTo(null);	
	}
	
	private class ExcluirWorker extends SwingWorker<Void, String> {

		private int idPaciente;

		public ExcluirWorker(int id) {
			this.idPaciente = id;
		}

		@Override
		protected Void doInBackground() throws Exception {
			pacienteService.excluirPaciente(idPaciente);
			return null;
		}

		@Override
		protected void done() {
			try {
				get(); 
			
				JOptionPane.showMessageDialog(PacienteExcluirWindow.this, "Registro excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				telaInicial.buscarTodos();
				limparCampos();
				fecharJanela();

			} catch (InterruptedException | ExecutionException e) {
				Throwable causa = e.getCause(); 
				JOptionPane.showMessageDialog(
					PacienteExcluirWindow.this, 
					"Ocorreu um erro ao excluir o registro.\nCausa: " + (causa != null ? causa.getMessage() : e.getMessage()), 
					"Erro de Exclusão", 
					JOptionPane.ERROR_MESSAGE
				);
				causa.printStackTrace();
			} 
		}
	}
}


