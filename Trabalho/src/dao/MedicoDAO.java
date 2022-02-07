package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import model.Medico;
import util.DatabaseConnection;

public class MedicoDAO implements MedicoDAOI{
	
	DatabaseConnection db;

	@Override
	public Medico get(String cpf) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from medico where cpf=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setString(1, cpf);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Medico p = new Medico(res.getString("CPF"),res.getString("RG"), res.getInt("id_pessoa"), res.getString("nome"), res.getString("data_nascimento"), res.getString("sexo"), res.getString("endereco_rua"), res.getString("endereco_numero"), res.getString("endereco_CEP"), res.getString("hora_inicio_expediente"), res.getString("hora_final_expediente"), res.getFloat("salario_parcial_fixo"), res.getFloat("percentual_comissao"),res.getFloat("salario_total"), res.getString("dias_que_trabalha"));
				p.setCPF(res.getString("CPF"));
				return p;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Medico> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from medico";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Medico> medicosList = new ArrayList<Medico>();
			while(res.next()) {
				Medico p = new Medico(res.getString("CPF"),res.getString("RG"), res.getInt("id_pessoa"), res.getString("nome"), res.getString("data_nascimento"), res.getString("sexo"), res.getString("endereco_rua"), res.getString("endereco_numero"), res.getString("endereco_CEP"), res.getString("hora_inicio_expediente"), res.getString("hora_final_expediente"), res.getFloat("salario_parcial_fixo"), res.getFloat("percentual_comissao"),res.getFloat("salario_total"), res.getString("dias_que_trabalha"));
				p.setCPF(res.getString("CPF"));
				medicosList.add(p);
			}
			return medicosList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void save(Medico newp) {
		try {
			db = new DatabaseConnection();
			String sql = "insert into medico(CPF,RG,id_pessoa, nome, data_nascimento, sexo, endereco_rua, endereco_numero, endereco_cep, hora_inicio_expediente, hora_final_expediente, salario_parcial_fixo,percentual_comissao, salario_total, dias_que_trabalha) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st;
      Date date = new Date();

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");

      String formattedDate = simpleDateFormat.format(date);
      String Datnas[] =newp.getDN().split("/");
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, newp.getCPF());
			st.setString(2, newp.getRG());
      st.setInt(3, newp.getId_pessoa());
      st.setString(4, newp.getNome());
      st.setObject(5, new java.sql.Date(Integer.parseInt(Datnas[1]),Integer.parseInt(Datnas[2]),Integer.parseInt(Datnas[3])));
      st.setString(6, newp.getSexo());
      st.setString(7, newp.getER());
      st.setString(8, newp.getEN());
      st.setString(9, newp.getEC());
      st.setString(10, newp.getHIE());
      st.setString(11, newp.getHFE());
      st.setDouble(12, newp.getSPF());
      st.setDouble(13, newp.getPC());
      st.setDouble(14, newp.getST());
      st.setString(15, newp.getDQT());
			st.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(Medico newp, Medico oldp) {
		try {
			db = new DatabaseConnection();
			String sql = "update medico set nome=?, endereco_rua=?, endereco_numero=?, endereco_CEP=?,hora_inicio_expediente=?, hora_final_expediente=?, salario_parcial_fixo=? ,percentual_comissao=?, dias_que_trabalha=?  where cpf=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, newp.getNome());
      st.setString(2, newp.getER());
      st.setString(3, newp.getEN());
      st.setString(4, newp.getER());
      st.setString(5, newp.getHIE());
      st.setString(6, newp.getHFE());
      st.setDouble(7, newp.getSPF());
      st.setDouble(8, newp.getPC());
      st.setString(9, newp.getDQT());
      st.setString(10, newp.getCPF());


			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(String cpf) {
		try {
			db = new DatabaseConnection();
			String sql = "delete from medico where cpf=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, cpf);
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		MedicoDAO medicoDao = new MedicoDAO();
		Medico m2 = new Medico("57792296354", "335062836",31, "Bernardo Jan Carlos","26/05/1981","M","Fagundes Marela", "699","60714010","09:00:00","18:00:00",7000, 0.2, 10000, "Ter/Qua/Qui");
		
		medicoDao.save(m2);
		
		List<Medico> allMedicos = medicoDao.getAll();
		for(int i = 0; i < allMedicos.size(); i++) {
			Medico p = allMedicos.get(i);
			System.out.println(p.getNome());
		}
		
		try {
			medicoDao.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}