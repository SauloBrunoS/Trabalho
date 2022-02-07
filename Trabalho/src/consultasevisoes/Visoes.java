package consultasevisoes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DatabaseConnection;

public class Visoes {
	
	DatabaseConnection db;
	
	public void visaoLucroMedicos() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from medicos_melhorlucro";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			while(res.next()) {
				System.out.println("nome do médico: " + res.getString("nome_medico"));
				System.out.println("cpf do médico: " + res.getString("cpf_medico"));
				System.out.println("lucro do mês: " + res.getFloat("lucro_mes"));
				System.out.println("salário total: " + res.getFloat("salario_total"));
				System.out.println("-----------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	  public void ConsultaVisaoConsulta() {
	      try {
	          db = new DatabaseConnection();
	          String sql = "select IC.Nome_Medico, IC.Nome_Paciente, IC.Procedimento_realizado, IC.Num_Remedios_Passados from Consulta as co, Informacoes_Consultas_Tratamentos as IC where co.data_mes = 1 and co.data_ano = 2022 and co.id_consulta = Ic.id\r\n";
	          Statement st = db.getConnection().createStatement();
	          ResultSet res = st.executeQuery(sql);
	          while(res.next()) {
	              System.out.println("nome_medico : " + res.getString("IC.Nome_Medico"));
	              System.out.println("nome_paciente : " + res.getString("IC.Nome_Paciente"));
	              System.out.println("Procedimento_realizado : " + res.getString("IC.Procedimento_realizado"));
	              System.out.println("Num_Remedios_Passados: " + res.getInt("IC.Num_Remedios_Passados"));
	              System.out.println("-----------------");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } 

		
	public static void main(String[] args) {
		Visoes visao = new Visoes();
	
		visao.visaoLucroMedicos();
		
		try {
			visao.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
