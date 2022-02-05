package model;

import java.util.Date;

public class Tratamento {
	private int numero_tratamento;
	private float valor;
	private String descricao;
	private int id_consulta;
	
	public Tratamento(float valor, String descricao, int id_consulta){ 	
		this.valor=valor;
		this.descricao=descricao;
		this.id_consulta=id_consulta;
	}
	
	public int getNumero_tratamento() {
		return numero_tratamento;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getId_consulta() {
		return id_consulta;
	}
	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}

 
}


