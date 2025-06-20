package entities;

public class Endereco {
	
	private int id_endereco;
    private String numero;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;  
    
	public Endereco() {	}
	
	public Endereco(String numero, String logradouro, String bairro, String cidade, String estado) {
		super();
		this.numero = numero;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public int getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(int id_endereco) {
		this.id_endereco = id_endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
	    return "Endereco [" +
	           "id=" + id_endereco +
	           ", numero=" + numero +
	           ", logradouro=" + logradouro +
	           ", bairro=" + bairro +
	           ", cidade=" + cidade +
	           ", estado=" + estado +
	           "]";
	}

}
