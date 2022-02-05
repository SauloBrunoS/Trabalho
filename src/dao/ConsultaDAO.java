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
				Consulta c = new Consulta(res.getInt("data_dia"), res.getInt("data_mes"), res.getInt("data_ano"), res.getDate("horario_marcado"), res.getDate("horario_fim"), res.getString("estado_consulta"), res.getFloat("valor_total"), res.getString("cpf_medico"), res.getString("cpf_paciente"), res.getInt("numero_tratamento"));
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
				Consulta c = new Consulta(res.getInt("data_dia"), res.getInt("data_mes"), res.getInt("data_ano"), res.getDate("horario_marcado"), res.getDate("horario_fim"), res.getString("estado_consulta"), res.getFloat("valor_total"), res.getString("cpf_medico"), res.getString("cpf_paciente"), res.getInt("numero_tratamento"));
				c.setId_consulta(res.getInt("id_consulta"));
				consultasList.add(c);
			}
			return consultasList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

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
			st.setDate(4, new java.sql.Date(newc.getHorario_marcado().getTime()));
			st.setDate(5,  new java.sql.Date(newc.getHorario_fim().getTime()));
			st.setString(6, newc.getEstado_consulta());
			st.setString(7, newc.getCpf_medico());
			st.setString(8, newc.getCpf_paciente());
			st.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
			st.setDate(4, new java.sql.Date(newc.getHorario_marcado().getTime()));
			st.setDate(5,  new java.sql.Date(newc.getHorario_fim().getTime()));
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
		ConsultaDAO consultaDAO = new ConsultaDAO();
	
		/*
		Consulta c1 = consultaDAO.get(2);
		System.out.println(c1.getId_consulta());
		System.out.println(c1.getData_dia());
		System.out.println(c1.getData_mes());
		System.out.println(c1.getData_ano());
		System.out.println(c1.getHorario_marcado());
		System.out.println(c1.getHorario_fim());
		System.out.println(c1.getEstado_consulta());
		System.out.println(c1.getValor_total());
		System.out.println(c1.getCpf_medico());
		System.out.println(c1.getCpf_paciente());
		System.out.println(c1.getNumero_tratamento());
		*/
		
		String sdate1 = "13:00:00";
		Date date1 = new SimpleDateFormat("hh:mm:ss").parse(sdate1);
		String sdate2 = "13:30:00";
		Date date2 = new SimpleDateFormat("hh:mm:ss").parse(sdate2);
		
		Consulta c2 = new Consulta(1, 2, 2022, date1, date2, "finalizada", "48091016301", "85729969317");
		
		consultaDAO.save(c2);
		
		List<Consulta> allConsultas = consultaDAO.getAll();
		for(int i = 0; i < allConsultas.size(); i++) {
			Consulta c = allConsultas.get(i);
			System.out.println(c.getId_consulta());
			System.out.println(c.getData_dia());
			System.out.println(c.getData_mes());
			System.out.println(c.getData_ano());
			System.out.println(c.getHorario_marcado());
			System.out.println(c.getHorario_fim());
			System.out.println(c.getEstado_consulta());
			System.out.println(c.getValor_total());
			System.out.println(c.getCpf_medico());
			System.out.println(c.getCpf_paciente());
			System.out.println(c.getNumero_tratamento());
			System.out.println("__________________________");
		}
	    
		
		try {
			consultaDAO.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}