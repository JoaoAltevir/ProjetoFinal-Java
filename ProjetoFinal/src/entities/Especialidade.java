package entities;

public class Especialidade {
	
	private int id_especialidade;
    private String nome_especialidade;
    
	public Especialidade() { }

	public Especialidade(int id, String nome_especialidade) {

		this.nome_especialidade = nome_especialidade;
		
	}

	public int getid_especialidade() {
		return id_especialidade;
	}

	public void setid_especialidade(int id_especialidade) {
		this.id_especialidade = id_especialidade;
	}

	public String getnome_especialidade() {
		return nome_especialidade;
	}

	public void setnome_especialidade(String nome_especialidade) {
		this.nome_especialidade = nome_especialidade;
	}

	@Override
	public String toString() {
	    return this.nome_especialidade;
	}
	
	

}
