package entities;


public class Paciente {
	
	private int id_paciente;
	private String nome;
	private String foto_paciente;
	private String data_nascimento;
	private char sexo;
	private String telefone;
	private String forma_pagamento;
	private Endereco endereco;
	
	public Paciente() {	}

	public Paciente(String nome, String foto_paciente, String data_nascimento, char sexo,
			String telefone, String forma_pagamento, Endereco endereco) {

		this.nome = nome;
		this.foto_paciente = foto_paciente;
		this.data_nascimento = data_nascimento;
		this.sexo = sexo;
		this.telefone = telefone;
		this.forma_pagamento = forma_pagamento;
		this.endereco = endereco;
		
	}

	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getfoto_paciente() {
		return foto_paciente;
	}

	public void setfoto_paciente(String foto_paciente) {
		this.foto_paciente = foto_paciente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getdata_nascimento() {
		return data_nascimento;
	}

	public void setdata_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getforma_pagamento() {
		return forma_pagamento;
	}

	public void setforma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}

	@Override
	public String toString() {
	    return nome;
	}
	
}
