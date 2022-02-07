package model;


public class Paciente {
	//private long id;
	private String CPF;
	private String RG;
  private int id_pessoa;
  private String nome;
  private String data_nascimento;
  private String sexo;
  private String endereco_rua;
  private String endereco_numero;
  private String endereco_CEP;
	
	public Paciente(){ 		
	}
	
	public Paciente(String CPF, String RG, int id_pessoa, String nome, String data_nascimento, String sexo, String endereco_rua, String endereco_numero, String endereco_CEP){ 	
		this.nome=nome;
		this.CPF=CPF;
    this.RG=RG;
    this.id_pessoa=id_pessoa;
    this.data_nascimento=data_nascimento;
    this.sexo=sexo;
    this.endereco_rua=endereco_rua;
    this.endereco_numero=endereco_numero;
    this.endereco_CEP=endereco_CEP;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

  public String getRG() {
		return RG;
	}

  public void setRG(String RG) {
		this.RG = RG;
	}

  public int getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

  public String getDN() {
		return data_nascimento;
	}

  public void setDN(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

  public String getSexo() {
		return sexo;
	}

  public void setSexo(String sexo) {
		this.sexo = sexo;
	}

  public String getER() {
		return endereco_rua;
	}

  public void setER(String endereco_rua) {
		this.endereco_rua = endereco_rua;
	}

  public String getEN() {
		return endereco_numero;
	}

  public void setEN(String endereco_numero) {
		this.endereco_numero = endereco_numero;
	}

  public String getEC() {
		return endereco_CEP;
	}

  public void setEC(String endereco_CEP) {
		this.endereco_CEP = endereco_CEP;
	}

  
}