package entities;

public class Medico {
	
	private int id_medico;
	private String crm;
    private String nome_medico;
    private String telefone;
    private Endereco endereco;
    private Especialidade especialidade;
    
	public Medico() { }

	public Medico(String crm, String nome_medico, String telefone, Endereco endereco, Especialidade especialidade) {
		
		this.crm = crm;
		this.nome_medico = nome_medico;
		this.endereco = endereco;
		this.telefone = telefone;
		this.especialidade = especialidade;
		
	}

	public int getid_medico() {
		return id_medico;
	}

	public void setid_medico(int id_medico) {
		this.id_medico = id_medico;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getnome_medico() {
		return nome_medico;
	}

	public void setnome_medico(String nome_medico) {
		this.nome_medico = nome_medico;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	
	@Override
	public String toString() {
	    return nome_medico;
	}
    

}
