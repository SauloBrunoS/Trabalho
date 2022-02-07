package model;

public class Receituario_Medicamento {
    private int numero_receituario;
    private int id_medicamento;
    private String metodo_de_aplicacao;
	
    public Receituario_Medicamento(int numero_receituario, int id_medicamento, String metodo_de_aplicacao) {
    	this.numero_receituario = numero_receituario;
    	this.id_medicamento = id_medicamento;
    	this.metodo_de_aplicacao = metodo_de_aplicacao;
    }
    
    public int getNumero_receituario() {
		return numero_receituario;
	}
	public void setNumero_receituario(int numero_receituario) {
		this.numero_receituario = numero_receituario;
	}
	public int getId_medicamento() {
		return id_medicamento;
	}
	public void setId_medicamento(int id_medicamento) {
		this.id_medicamento = id_medicamento;
	}
	public String getMetodo_de_aplicacao() {
		return metodo_de_aplicacao;
	}
	public void setMetodo_de_aplicacao(String metodo_de_aplicacao) {
		this.metodo_de_aplicacao = metodo_de_aplicacao;
	}
    
    
    
}
