package model;

public class Atestado {
	private int id_consulta;
	private int numero_atestado;
    private String numero_autenticacao;
	
	public Atestado(int id_consulta, String numero_autenticacao){ 	
		this.id_consulta=id_consulta;
		this.numero_autenticacao = numero_autenticacao;
	}

	public int getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}

	public int getNumero_atestado() {
		return numero_atestado;
	}

	public String getNumero_autenticacao() {
		return numero_autenticacao;
	}

	public void setNumero_autenticacao(String numero_autenticacao) {
		this.numero_autenticacao = numero_autenticacao;
	}

	public void setNumero_atestado(int numero_atestado) {
		this.numero_atestado = numero_atestado;
	}
	
	

	
}