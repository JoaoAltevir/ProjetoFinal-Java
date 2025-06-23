package entities;

import java.time.LocalDateTime;

public class Consulta {
	
	private int id_consulta;
    private Paciente paciente;
    private Medico medico;
    private String data_hora;
    private boolean realizada;
    
	public Consulta() {	}

	public Consulta(Paciente paciente, Medico medico, String data_hora, boolean realizada) {
	
		this.paciente = paciente;
		this.medico = medico;
		this.data_hora = data_hora;
		this.realizada = realizada;
		
	}
	
	public String isRealizada() {
		if(this.realizada == true) {
			return "Realizada";
		}else {
			return "Não realizada";
		}
	}

	public int getid_consulta() {
		return id_consulta;
	}

	public void setid_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
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

	public String getdata_hora() {
		return data_hora;
	}

	public void setdata_hora(String data_hora) {
		this.data_hora = data_hora;
	}

	public boolean getrealizada() {
		return realizada;
	}

	public void setrealizada(boolean realizada) {
		this.realizada = realizada;
	}

	@Override
	public String toString() {
	    return "Consulta {id=" + id_consulta + 
	           ", paciente=" + paciente.getNome() + 
	           ", medico=" + medico.getnome_medico() + 
	           ", data_hora=" + data_hora + 
	           ", realizada=" + realizada + "}";
	}
    
    

}
