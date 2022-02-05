package model;

public class Atestado {
	private int id_consulta;
	private int numero_atestado;

	public Atestado(int id_consulta){ 	
		this.id_consulta=id_consulta;
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

	
}