package model;

public class TiposPessoa {
	//private long id;
	private int id_tipo;
  private String nome_tipo;
	
	public TiposPessoa(){ 		
	}
	
	public TiposPessoa(int id_tipo, String nome_tipo){ 	
		this.id_tipo=id_tipo;
    this.nome_tipo=nome_tipo;

	}

	public int getId_tipo() {
		return id_tipo;
	}

  public void setId_tipo(int id_tipo){
    this.id_tipo=id_tipo;
  }
  public String getNome_tipo() {
		return nome_tipo;
	}

  public void setNome_tipo(String id_tipo){
    this.nome_tipo=nome_tipo;
  }


  
}