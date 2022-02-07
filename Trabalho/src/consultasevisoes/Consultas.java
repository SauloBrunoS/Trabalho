package consultasevisoes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.Receituario_MedicamentoDAO;
import util.DatabaseConnection;

public class Consultas {
	
	DatabaseConnection db;
	
	public void consultaMedicos() {
		try {
			db = new DatabaseConnection();
			String sql = "select m.cpf, m.nome, t.numero, (m.hora_final_expediente - m.hora_inicio_expediente) as horas_trabalhadas, m.dias_que_trabalha as dias_que_trabalha from medico m left join telefone t on m.id_pessoa = t.id_pessoa\r\n"
					+ "order by horas_trabalhadas desc";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			while(res.next()) {
				System.out.println("cpf:" + res.getString("cpf"));
				System.out.println("nome do médico: " + res.getString("nome"));
				System.out.println("numero: " + res.getString("numero"));
				System.out.println("horas trabalhadas: " + res.getString("horas_trabalhadas"));
				System.out.println("dias que trabalha: " + res.getString("dias_que_trabalha"));
				System.out.println("-----------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void consultaPacientes() {
		try {
			db = new DatabaseConnection();
			String sql = "select p.cpf, p.nome as nome_paciente, count(c.estado_consulta) as consultas_agendadas \r\n"
					+ "from paciente p left join consulta c on c.cpf_paciente = p.cpf and c.estado_consulta = 'agendada'\r\n"
					+ "group by p.cpf, p.nome\r\n"
					+ "order by consultas_agendadas desc;\r\n";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			while(res.next()) {
				System.out.println("cpf: " + res.getString("cpf"));
				System.out.println("nome do paciente: " + res.getString("nome_paciente"));
				System.out.println("consultas agendadas: " + res.getString("consultas_agendadas"));
				System.out.println("-----------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public void consultaMedicoSegSex() {
        try {
            db = new DatabaseConnection();
            String sql = "select se.nome, se.dias_que_trabalha from secretario as se where se.dias_que_trabalha like 'Seg%' and se.dias_que_trabalha not like '%Sex'\r\n";
            Statement st = db.getConnection().createStatement();
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                System.out.println("nome: " + res.getString("nome"));
                System.out.println("dias_que_trabalha: " + res.getString("dias_que_trabalha"));
                System.out.println("-----------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

  public void VisaoInformacoesConsulta() {
    try {
        db = new DatabaseConnection();
        String sql = "select co.id_consulta as id, me.Nome as Nome_Medico, pa.Nome as Nome_Paciente,  tr.descricao as Procedimento_realizado,  numero_remedios_receituario(co.id_consulta) as Num_Remedios_Passados from Consulta as co, Medico as Me, Paciente as Pa, Tratamento as Tr where co.cpf_medico = me.cpf and co.cpf_Paciente = Pa.cpf and co.id_consulta = tr.id_consulta\r\n";
        Statement st = db.getConnection().createStatement();
        ResultSet res = st.executeQuery(sql);
        while(res.next()) {
          System.out.println("id : " + res.getString("co.id_consulta"));              
          System.out.println("nome_medico : " + res.getString("me.nome"));
          System.out.println("nome_paciente : " + res.getString("pa.nome"));
          System.out.println("Procedimento_realizado: " + res.getString("tr.descricao"));   
          System.out.println("Num_Remedios_Passados : " + res.getInt("numero_remedios_receituario(co.id_consulta)"));
          System.out.println("-----------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
	

	public static void main(String[] args) {
		Consultas consulta = new Consultas();
	
		consulta.consultaMedicos();
		consulta.consultaPacientes();
		
		try {
			consulta.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
