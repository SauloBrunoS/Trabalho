package model;

public class Medicamento {
	private int id_medicamento;
	private String nome;
	
	public Medicamento(String nome){ 	
		this.nome=nome;
	}

	public int getId_medicamento() {
		return id_medicamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
		
}
