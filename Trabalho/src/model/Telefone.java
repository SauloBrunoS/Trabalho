package model;

public class Telefone {
	//private long id;
	private int id_pessoa;
  private String numero;
	
	
	public Telefone(){ 		
	}
	
	public Telefone(int id_pessoa, String numero){ 	
		this.id_pessoa=id_pessoa;
    this.numero=numero;

	}

	public String getNumero() {
		return numero;
	}

  public void setNumero(String numero){
    this.numero=numero;
  }


  public int getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}


  
}