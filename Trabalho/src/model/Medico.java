package model;

public class Medico {
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
  private String hora_inicio_expediente;
  private String hora_final_expediente;
	private double salario_parcial_fixo;
	private double percentual_comissao;
	private double salario_total;
	private String dias_que_trabalha;
	
	public Medico(){ 		
	}
	
	public Medico(String CPF, String RG, int id_pessoa, String nome, String data_nascimento, String sexo, String endereco_rua, String endereco_numero, String endereco_CEP, String hora_inicio_expediente, String hora_final_expediente, double salario_parcial_fixo, double percentual_comissao, double salario_total, String dias_que_trabalha){ 	
		this.nome=nome;
		this.CPF=CPF;
    this.RG=RG;
    this.id_pessoa = id_pessoa;
    this.data_nascimento=data_nascimento;
    this.sexo=sexo;
    this.endereco_rua=endereco_rua;
    this.endereco_numero=endereco_numero;
    this.endereco_CEP=endereco_CEP;
    this.hora_inicio_expediente=hora_inicio_expediente;
    this.hora_final_expediente=hora_final_expediente;
    this.salario_parcial_fixo=salario_parcial_fixo;
    this.percentual_comissao=percentual_comissao;
    this.salario_total=salario_total;
    this.dias_que_trabalha = dias_que_trabalha;

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

  public void setsexo(String sexo) {
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

  public String getHIE() {
		return hora_inicio_expediente;
	}

  public void setHIE(String hora_inicio_expediente) {
		this.hora_inicio_expediente = hora_inicio_expediente;
	}
  public String getHFE() {
		return hora_final_expediente;
	}

  public void setHFE(String hora_final_expediente) {
		this.hora_final_expediente = hora_final_expediente;
	}

  public double getSPF() {
		return salario_parcial_fixo;
	}

  public void setSPF(float salario_parcial_fixo) {
		this.salario_parcial_fixo = salario_parcial_fixo;
	}

  public double getPC() {
		return percentual_comissao;
	}

  public void setPC(float percentual_comissao) {
		this.percentual_comissao = percentual_comissao;
	}
  public double getST() {
		return salario_total;
	}

  public void setST(float salario_total) {
		this.salario_total = salario_total;
	}

  public String getDQT() {
		return dias_que_trabalha;
	}

  public void setDQT(String dias_que_trabalha) {
		this.dias_que_trabalha = dias_que_trabalha;
	}
}