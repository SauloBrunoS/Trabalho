package model;
import java.util.Date;

public class Consulta {
	private int id_consulta;
	private int data_dia;
	private int data_mes;
	private int data_ano;
	private Date horario_marcado;
	private Date horario_fim;
	private String estado_consulta;
	private float valor_total;
    private String cpf_medico;
    private String cpf_paciente;
    private int numero_tratamento;
    
    public Consulta(int data_dia, int data_mes, int data_ano, Date horario_marcado, String estado_consulta, String cpf_medico, String cpf_paciente){ 	
		this.data_dia=data_dia;
		this.data_mes=data_mes;
		this.data_ano=data_ano;
		this.horario_marcado = horario_marcado;
		this.estado_consulta=estado_consulta;
		this.cpf_medico=cpf_medico;
		this.cpf_paciente=cpf_paciente;
	}
    
    public Consulta(int data_dia, int data_mes, int data_ano, Date horario_marcado, Date horario_fim, String estado_consulta, String cpf_medico, String cpf_paciente){ 	
		this.data_dia=data_dia;
		this.data_mes=data_mes;
		this.data_ano=data_ano;
		this.horario_marcado = horario_marcado;
		this.estado_consulta=estado_consulta;
		this.cpf_medico=cpf_medico;
		this.cpf_paciente=cpf_paciente;
	}
    
	
	public Consulta(int data_dia, int data_mes, int data_ano, Date horario_marcado,  Date horario_fim, String estado_consulta, float valor_total, String cpf_medico, String cpf_paciente,  int numero_tratamento){ 	
		this.data_dia=data_dia;
		this.data_mes=data_mes;
		this.data_ano=data_ano;
		this.horario_marcado = horario_marcado;
		this.horario_fim=horario_fim;
		this.estado_consulta=estado_consulta;
		this.valor_total=valor_total;
		this.cpf_medico=cpf_medico;
		this.cpf_paciente=cpf_paciente;
		this.numero_tratamento=numero_tratamento;
	}

	public int getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}

	public int getData_dia() {
		return data_dia;
	}

	public void setData_dia(int data_dia) {
		this.data_dia = data_dia;
	}

	public int getData_mes() {
		return data_mes;
	}

	public void setData_mes(int data_mes) {
		this.data_mes = data_mes;
	}

	public int getData_ano() {
		return data_ano;
	}

	public void setData_ano(int data_ano) {
		this.data_ano = data_ano;
	}

	public Date getHorario_marcado() {
		return horario_marcado;
	}

	public void setHorario_marcado(Date horario_marcado) {
		this.horario_marcado = horario_marcado;
	}

	public Date getHorario_fim() {
		return horario_fim;
	}

	public void setHorario_fim(Date horario_fim) {
		this.horario_fim = horario_fim;
	}

	public String getEstado_consulta() {
		return estado_consulta;
	}

	public void setEstado_consulta(String estado_consulta) {
		this.estado_consulta = estado_consulta;
	}

	public float getValor_total() {
		return valor_total;
	}

	public void setValor_total(float valor_total) {
		this.valor_total = valor_total;
	}

	public String getCpf_medico() {
		return cpf_medico;
	}

	public void setCpf_medico(String cpf_medico) {
		this.cpf_medico = cpf_medico;
	}

	public String getCpf_paciente() {
		return cpf_paciente;
	}

	public void setCpf_paciente(String cpf_paciente) {
		this.cpf_paciente = cpf_paciente;
	}

	public int getNumero_tratamento() {
		return numero_tratamento;
	}

	public void setNumero_tratamento(int numero_tratamento) {
		this.numero_tratamento = numero_tratamento;
	}
}

