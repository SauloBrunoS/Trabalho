package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Secretario;
import util.DatabaseConnection;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SecretarioDAO implements SecretarioDAOI{
	
	DatabaseConnection db;

	@Override
	public Secretario get(String cpf) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from secretario where cpf=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setString(1, cpf);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Secretario p = new Secretario(res.getString("CPF"), res.getString("RG"), res.getInt("id_pessoa"), res.getString("nome"), res.getString("data_nascimento"), res.getString("sexo"), res.getString("endereco_rua"), res.getString("endereco_numero"), res.getString("endereco_CEP"), res.getString("hora_inicio_expediente"), res.getString("hora_final_expediente"), res.getFloat("salario_total"), res.getString("dias_que_trabalha"));
				p.setCPF(res.getString("CPF"));
				return p;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Secretario> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from secretario";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Secretario> secretariosList = new ArrayList<Secretario>();
			while(res.next()) {
				Secretario p = new Secretario(res.getString("CPF"),res.getString("RG"), res.getInt("id_pessoa"), res.getString("nome"), res.getString("data_nascimento"), res.getString("sexo"), res.getString("endereco_rua"), res.getString("endereco_numero"), res.getString("endereco_CEP"), res.getString("hora_inicio_expediente"), res.getString("hora_final_expediente"), res.getFloat("salario_total"), res.getString("dias_que_trabalha"));
				p.setCPF(res.getString("CPF"));
				secretariosList.add(p);
			}
			return secretariosList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void save(Secretario newp) {
		try {
			db = new DatabaseConnection();
			String sql = "insert into secretario(CPF,RG,id_pessoa, nome, data_nascimento, sexo, endereco_rua, endereco_numero, endereco_cep, hora_inicio_expediente, hora_final_expediente, salario_total, dias_que_trabalha) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
      st.setFloat(12, newp.getST());
      st.setString(13, newp.getDQT());
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void update(Secretario newp, Secretario oldp) {
		try {
			db = new DatabaseConnection();
			String sql = "update secretario set nome=?, endereco_rua=?, endereco_numero=?, endereco_CEP=?,hora_inicio_expediente=?, hora_final_expediente=?, dias_que_trabalha=?  where cpf=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, newp.getNome());
      st.setString(2, newp.getER());
      st.setString(3, newp.getEN());
      st.setString(4, newp.getER());
      st.setString(5, newp.getHIE());
      st.setString(6, newp.getHFE());
      st.setString(7, newp.getDQT());
      st.setString(8, newp.getCPF());


			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(String cpf) {
		try {
			db = new DatabaseConnection();
			String sql = "delete from trabalho.secretario where cpf=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, cpf);
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		SecretarioDAO secretarioDao = new SecretarioDAO();

		Secretario s2 = new Secretario("44071806378", "342196556",21,"Iago Marcelo", "26/03/2001", "M", "Conde de Irajá", "182","60182500","07:00:00","19:00:00",5000,"Seg / Ter / Qua / Qui");
		
		secretarioDao.save(s2);
		
		List<Secretario> allSecretarios = secretarioDao.getAll();
		for(int i = 0; i < allSecretarios.size(); i++) {
			Secretario p = allSecretarios.get(i);
			System.out.println(p.getNome());
		}
		
		try {
		secretarioDao.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}