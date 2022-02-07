package model;

import java.util.Date;

public class Receituario {
	private int id_consulta;
	private int numero_receituario;
	
	public Receituario(int id_consulta){ 	
		this.id_consulta = id_consulta;
	}

	public int getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}

	public int getNumero_receituario() {
		return numero_receituario;
	}

	public void setNumero_receituario(int numero_receituario) {
		this.numero_receituario = numero_receituario;
	}
	
	
}

	

	