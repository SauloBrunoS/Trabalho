package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.Consulta;
import util.DatabaseConnection;
public class ConsultaDAO implements ConsultaDAOI{
	
	DatabaseConnection db;

	@Override
	public Consulta get(int id_consulta) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.consulta where id_consulta=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setInt(1, id_consulta);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Consulta c = new Consulta(res.getInt("data_dia"), res.getInt("data_mes"), res.getInt("data_ano"), res.getString("horario_marcado"), res.getString("horario_fim"), res.getString("estado_consulta"), res.getFloat("valor_total"), res.getString("cpf_medico"), res.getString("cpf_paciente"), res.getInt("numero_tratamento"));
				c.setId_consulta(res.getInt("id_consulta"));
				return c;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Consulta> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.consulta";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Consulta> consultasList = new ArrayList<Consulta>();
			while(res.next()) {
				Consulta c = new Consulta(res.getInt("data_dia"), res.getInt("data_mes"), res.getInt("data_ano"), res.getString("horario_marcado"), res.getString("horario_fim"), res.getString("estado_consulta"), res.getFloat("valor_total"), res.getString("cpf_medico"), res.getString("cpf_paciente"), res.getInt("numero_tratamento"));
				c.setId_consulta(res.getInt("id_consulta"));
				consultasList.add(c);
			}
			return consultasList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void save(Consulta newc) {
		
		try {
			db = new DatabaseConnection();
			String sql = "insert into trabalho.consulta(data_dia, data_mes,data_ano, horario_marcado, horario_fim, estado_consulta,\r\n"
					+ "cpf_medico,cpf_paciente) values(?,?,?,?,?,?,?,?)";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, newc.getData_dia());
			st.setInt(2, newc.getData_mes());
			st.setInt(3, newc.getData_ano());
			String Hora[] = newc.getHorario_marcado().split(":");
			st.setObject(4, new java.sql.Time(Integer.parseInt(Hora[0]), Integer.parseInt(Hora[1]), Integer.parseInt(Hora[2])));
			String Hora2[] = newc.getHorario_fim().split(":");
			st.setObject(5, new java.sql.Time(Integer.parseInt(Hora2[0]), Integer.parseInt(Hora2[1]), Integer.parseInt(Hora2[2])));
			st.setString(6, newc.getEstado_consulta());
			st.setString(7, newc.getCpf_medico());
			st.setString(8, newc.getCpf_paciente());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public int getID(Consulta c) {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		try {
			db = new DatabaseConnection();
			String sql = "select id_consulta from trabalho.consulta where data_dia = ? and data_mes = ? and data_ano = ? and horario_marcado = ? ";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, c.getData_dia());
			st.setInt(2, c.getData_mes());
			st.setInt(3, c.getData_ano());
			String Hora[] = c.getHorario_marcado().split(":");
			st.setObject(4, new java.sql.Time(Integer.parseInt(Hora[0]), Integer.parseInt(Hora[1]), Integer.parseInt(Hora[2])));
			ResultSet res = st.executeQuery();
			if(res.next()) {
				return res.getInt("id_consulta");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;

	}

	@Override
	public void update(Consulta newc, Consulta oldc) {
		try {
			db = new DatabaseConnection();
			String sql = "update trabalho.consulta set data_dia=?, data_mes=?, data_ano = ?, horario_marcado = ?, horario_fim = ?, estado_consulta = ? where id_consulta=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, newc.getData_dia());
			st.setInt(2, newc.getData_mes());
			st.setInt(3, newc.getData_ano());
			String Hora[] = newc.getHorario_marcado().split(":");
			st.setObject(4, new java.sql.Time(Integer.parseInt(Hora[0]), Integer.parseInt(Hora[1]), Integer.parseInt(Hora[2])));
			String Hora2[] = newc.getHorario_fim().split(":");
			st.setObject(5, new java.sql.Time(Integer.parseInt(Hora2[0]), Integer.parseInt(Hora2[1]), Integer.parseInt(Hora2[2])));
			st.setString(6, newc.getEstado_consulta());
			st.setInt(7, newc.getId_consulta());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id_consulta) {
		try {
			db = new DatabaseConnection();
			String sql = "delete from trabalho.consulta where id_consulta=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, id_consulta);
			st.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws ParseException {

		// recebendo consulta do banco de dados
		ConsultaDAO consultaDAO = new ConsultaDAO();

		Consulta c1 = consultaDAO.get(8);
		System.out.println("id da consulta:" + c1.getId_consulta());
		System.out.println("dia:" + c1.getData_dia());
		System.out.println("mês:" +c1.getData_mes());
		System.out.println("ano:" +c1.getData_ano());
		System.out.println("horário marcado:" + c1.getHorario_marcado());
		System.out.println("horário fim" + c1.getHorario_fim());
		System.out.println("estado da consulta:" + c1.getEstado_consulta());
		System.out.println("valor da consulta:" + c1.getValor_total());
		System.out.println("cpf do médico:" + c1.getCpf_medico());
		System.out.println("cpf do paciente:" + c1.getCpf_paciente());
		System.out.println("número do tratamento:" + c1.getNumero_tratamento());

		// criando novo consulta e inserindo no banco de dados
		Consulta c2 = new Consulta(18, 1, 2022, "07:35:00", "08:00:00", "finalizada", "23043783310", "85729969317");
		consultaDAO.save(c2);
		// como o id da consulta é serial, nós obtemos ele do banco de dados e inserimos
		// no objeto da
		// classe Consulta
		c2.setId_consulta(consultaDAO.getID(c2));
		// atualizando consulta do banco de dados
		Consulta c3 = new Consulta(18, 1, 2022, "07:35:00", "08:35:00", "finalizada", "23043783310", "85729969317");
		c3.setId_consulta(consultaDAO.getID(c2));
		consultaDAO.update(c3, c2);

		// removendo consulta do banco de dados
		consultaDAO.delete(c3.getId_consulta());

		// exibindo todas as consultas do banco de dados
		List<Consulta> allConsultas = consultaDAO.getAll();
		for (int i = 0; i < allConsultas.size(); i++) {
			Consulta c = allConsultas.get(i);
			System.out.println("id da consulta:" + c.getId_consulta());
			System.out.println("dia:" + c.getData_dia());
			System.out.println("mês:" +c.getData_mes());
			System.out.println("ano:" +c.getData_ano());
			System.out.println("horário marcado:" + c.getHorario_marcado());
			System.out.println("horário fim" + c.getHorario_fim());
			System.out.println("estado da consulta:" + c.getEstado_consulta());
			System.out.println("valor da consulta:" + c.getValor_total());
			System.out.println("cpf do médico:" + c.getCpf_medico());
			System.out.println("cpf do paciente:" + c.getCpf_paciente());
			System.out.println("número do tratamento:" + c.getNumero_tratamento());
            System.out.println("------------------------");
		}

		try {
			consultaDAO.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}