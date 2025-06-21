package entities;

public class Exame {
	
	private int id_exame;
    private String nome_exame;
    private double valor;
    private String orientacoes;
    
    
	public Exame() { }

	public Exame(String nome_exame, double valor, String orientacoes) {
	
		this.nome_exame = nome_exame;
		this.valor = valor;
		this.orientacoes = orientacoes;
	}
	
	public int getid_exame() {
		return id_exame;
	}

	public void setid_exame(int id_exame) {
		this.id_exame = id_exame;
	}

	public String getnome_exame() {
		return nome_exame;
	}

	public void setnome_exame(String nome_exame) {
		this.nome_exame = nome_exame;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}
	
	@Override
	public String toString() {
	    return "Exame {\n" +
	           "  id=" + id_exame + ",\n" +
	           "  nome_exame='" + nome_exame + "',\n" +
	           "  valor=" + String.format("R$%.2f", valor) + ",\n" +
	           "  orientacoes='" + orientacoes + "'\n" +
	           "}";
	}
    
    

}
