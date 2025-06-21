package entities;

import java.time.LocalDateTime;

public class AgendamentoExame {

	private int id_agendamento_exame;
    private Exame exame;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime data_hora;
    private double valor_pago;
    private boolean realizado;
    
	public AgendamentoExame() {	}

	public AgendamentoExame(Exame exame, Paciente paciente, Medico medico, LocalDateTime data_hora, double valor_pago,
			boolean realizado) {

		this.exame = exame;
		this.paciente = paciente;
		this.medico = medico;
		this.data_hora = data_hora;
		this.valor_pago = valor_pago;
		this.realizado = realizado;
	}
	
	public int getid_agendamento_exame() {
		return id_agendamento_exame;
	}

	public void setid_agendamento_exame(int id_agendamento_exame) {
		this.id_agendamento_exame = id_agendamento_exame;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public LocalDateTime getdata_hora() {
		return data_hora;
	}

	public void setdata_hora(LocalDateTime data_hora) {
		this.data_hora = data_hora;
	}

	public double getvalor_pago() {
		return valor_pago;
	}

	public void setvalor_pago(double valor_pago) {
		this.valor_pago = valor_pago;
	}

	public boolean getrealizado() {
		return realizado;
	}

	public void setrealizado(boolean realizado) {
		this.realizado = realizado;
	}

	@Override
	public String toString() {
	    return "AgendamentoExame [id=" + id_agendamento_exame + 
	           ", exame=" + exame + 
	           ", paciente=" + paciente + 
	           ", medico=" + medico + 
	           ", data_hora=" + data_hora + 
	           ", valor_pago=" + valor_pago + 
	           ", realizado=" + realizado + "]";
	}
	
}
