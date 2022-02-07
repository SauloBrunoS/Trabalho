package model;

public class Pessoa {
	//private long id;
	private int id_pessoa;
	private int id_tipo;
	
	public Pessoa(){ 		
	}
	
	public Pessoa(int id_pessoa, int id_tipo){ 	
		this.id_tipo=id_tipo;
    this.id_pessoa=id_pessoa;

	}

	public int getId_tipo() {
		return id_tipo;
	}

  public void setId_tipo(int id_tipo){
    this.id_tipo=id_tipo;
  }


  public int getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}


  
}