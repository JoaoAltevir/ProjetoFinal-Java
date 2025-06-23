package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;
import service.ConsultaService;
import service.MedicoService;
import service.PacienteService;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgendarConsultaWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNomeMed;
	private JTable tableConsultas;
	private JButton btnApagar;
	private JButton btnAtualizar;
	private JButton btnCadastrar;
	private JFormattedTextField ftextHora;
	private JFormattedTextField ftextData;
	private JComboBox cbPacientes;
	private JComboBox cbMedico;
	private JPanel painelConsultas;
	private JScrollPane spConsultas;
	
	private PacienteService pacienteService;
	private MedicoService medicoService;
	private ConsultaService consultaService;
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraHora;
	private InicialWindow menuInicial;
	private JLabel lblNomePaciente;
	private JButton btnLimparCampos;
	
	

	public AgendarConsultaWindow(InicialWindow inicialWindow) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fecharJanela();
			}
		});
		
		this.pacienteService = new PacienteService();
		this.medicoService = new MedicoService();
		this.consultaService = new ConsultaService();
		this.criarDataeHora();
		this.menuInicial = inicialWindow;
		this.initComponents();
		inserirPacientes();
		inserirMedicos();
		medicoSelecionado();
		
	}
	
	private void fecharJanela() {
		this.dispose();
		this.menuInicial.setVisible(true);
	}
	
	private void criarDataeHora() {
		try {
			this.mascaraData = new MaskFormatter("##/##/####");
			this.mascaraHora = new MaskFormatter("##:##");
		} catch (ParseException e) {
			e.getMessage();
		}
	}
	
	private void inserirPacientes() {
		
		try {
			List<Paciente> pacientes = this.pacienteService.buscarTodos();
			
			for(Paciente paciente: pacientes) {
				this.cbPacientes.addItem(paciente);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void inserirMedicos() {
		
		try {
			List<Medico> listaMedicos = this.medicoService.buscarTodos();
			
			for(Medico medico: listaMedicos) {
				this.cbMedico.addItem(medico);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void medicoSelecionado() {
		
		if(cbMedico.getItemCount() > 0) {
			cbMedico.setSelectedIndex(0);
			atualizarTabelaConsultas((Medico) cbMedico.getSelectedItem());
		}
	}
	
	public void atualizarTabelaConsultas(Medico medico) {
		
		try {
			List<Consulta> listaConsultas;
			
			DefaultTableModel modelo  = (DefaultTableModel) tableConsultas.getModel();
			modelo.setRowCount(0);
			
			listaConsultas = this.consultaService.buscarConsultasPorMedico(medico);
			
			for(Consulta consulta: listaConsultas) {
				
				modelo.addRow(new Object[] {
					consulta.getid_consulta(),
					consulta.getPaciente().getNome(),
					consulta.getMedico().getnome_medico(),
					consulta.getdata_hora(),
					consulta.isRealizada()
				});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void abrirJanelaAtualizar() {
		
		AtualizarConsultaWindow janelaAtualizar = new AtualizarConsultaWindow(this);
		janelaAtualizar.setVisible(true);
		
	}
	
	private void abrirJanelaApagar() {
		
		ApagarConsultaWindow janelaApagar = new ApagarConsultaWindow(this);
		janelaApagar.setVisible(true);
		
	}
	
	private void limparCampos() {
		
		this.ftextData.setText("");
		this.ftextHora.setText("");
		this.cbMedico.setSelectedIndex(0);
		this.cbPacientes.setSelectedIndex(0);
		
	}
	private void agendarConsulta() {
		try {
			
			Consulta consulta = new Consulta();
			Paciente paciente = (Paciente) cbPacientes.getSelectedItem();
            Medico medico = (Medico) cbMedico.getSelectedItem();

            if (paciente == null || medico == null) {
                JOptionPane.showMessageDialog(this, "Tem que ser selecionado obrigatóriamente PACIENTE e MÉDICO", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String hora = ftextHora.getText();
            String data = ftextData.getText();
            SimpleDateFormat modelo = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date dataConsulta = modelo.parse(data + " " + hora);
            SimpleDateFormat dataConsultaBD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String dataFormatada = dataConsultaBD.format(dataConsulta);
            
            consulta.setdata_hora(dataFormatada);
            consulta.setMedico(medico);
            consulta.setPaciente(paciente);
            consulta.setrealizada(false);
            
            if(this.consultaService.verificarDiaConsulta(consulta) != null) {
            	JOptionPane.showMessageDialog(null, "Consulta nessa data já está cadastrada!");
            	return;
            }
            this.consultaService.cadastrar(consulta);
            this.atualizarTabelaConsultas(medico);
            limparCampos();
            JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso!");
		} catch (Exception e) {
	        e.printStackTrace(); 
	        JOptionPane.showMessageDialog(this, "Ocorreu um erro ao agendar a consulta:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void initComponents() {
		setTitle("Agendar Consulta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 786, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbMedico = new JComboBox<Medico>();
		cbMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Medico medicoSelecionado = (Medico) cbMedico.getSelectedItem();
				atualizarTabelaConsultas(medicoSelecionado);
			}
		});
		cbMedico.setBounds(26, 43, 139, 22);
		contentPane.add(cbMedico);
		
		lblNomeMed = new JLabel("Nome Médico:");
		lblNomeMed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeMed.setBounds(26, 25, 97, 14);
		contentPane.add(lblNomeMed);
		
		painelConsultas = new JPanel();
		painelConsultas.setBorder(new TitledBorder(null, "Consultas Agendadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelConsultas.setBounds(228, 11, 524, 282);
		contentPane.add(painelConsultas);
		painelConsultas.setLayout(null);
		
		spConsultas = new JScrollPane();
		spConsultas.setBounds(10, 21, 504, 250);
		painelConsultas.add(spConsultas);
		
		tableConsultas = new JTable();
		tableConsultas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Paciente", "M\u00E9dico", "Data e Hora", "Status"
			}
		));
		tableConsultas.getColumnModel().getColumn(0).setPreferredWidth(33);
		spConsultas.setViewportView(tableConsultas);
		
		btnCadastrar = new JButton("Cadastrar consulta");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendarConsulta();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCadastrar.setBounds(578, 304, 178, 25);
		contentPane.add(btnCadastrar);
		
		lblNomePaciente = new JLabel("Nome Paciente:");
		lblNomePaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomePaciente.setBounds(26, 76, 97, 14);
		contentPane.add(lblNomePaciente);
		
		cbPacientes = new JComboBox<Paciente>();
		cbPacientes.setBounds(26, 101, 139, 22);
		contentPane.add(cbPacientes);
		
		JLabel lblData = new JLabel("Data e Hora:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(26, 144, 123, 14);
		contentPane.add(lblData);
		
		ftextData = new JFormattedTextField(this.mascaraData);
		ftextData.setBounds(26, 174, 82, 20);
		contentPane.add(ftextData);
		
		ftextHora = new JFormattedTextField(this.mascaraHora);
		ftextHora.setBounds(26, 210, 51, 20);
		contentPane.add(ftextHora);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaAtualizar();
			}
		});
		btnAtualizar.setBounds(479, 304, 89, 23);
		contentPane.add(btnAtualizar);
		
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaApagar();
			}
		});
		btnApagar.setBounds(380, 305, 89, 23);
		contentPane.add(btnApagar);
		
		btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimparCampos.setBounds(26, 278, 124, 51);
		contentPane.add(btnLimparCampos);
		
		setLocationRelativeTo(null);
	}
}
