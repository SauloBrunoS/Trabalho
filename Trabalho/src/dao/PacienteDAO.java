package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Paciente;
import util.DatabaseConnection;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
public class PacienteDAO implements PacienteDAOI{
	
	DatabaseConnection db;

	@Override
	public Paciente get(String cpf) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from paciente where cpf=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setString(1, cpf);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Paciente p = new Paciente(res.getString("CPF"),res.getString("RG"), res.getInt("id_pessoa"), res.getString("nome"), res.getString("data_nascimento"), res.getString("sexo"), res.getString("endereco_rua"), res.getString("endereco_numero"), res.getString("endereco_CEP"));
				p.setCPF(res.getString("CPF"));
				return p;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Paciente> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from paciente";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Paciente> pacientesList = new ArrayList<Paciente>();
			while(res.next()) {
				Paciente p = new Paciente(res.getString("CPF"),res.getString("RG"), res.getInt("id_pessoa"), res.getString("nome"), res.getString("data_nascimento"), res.getString("sexo"), res.getString("endereco_rua"), res.getString("endereco_numero"), res.getString("endereco_CEP"));
				p.setCPF(res.getString("CPF"));
				pacientesList.add(p);
			}
			return pacientesList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    return null;
  }
	@Override
	public void save(Paciente newp) {
		try {
			db = new DatabaseConnection();
			String sql = "insert into paciente(CPF,RG,id_pessoa, nome, data_nascimento, sexo, endereco_rua, endereco_numero, endereco_cep) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			st.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(Paciente newp, Paciente oldp) {
		try {
			db = new DatabaseConnection();
			String sql = "update paciente set nome=?, endereco_rua=?, endereco_numero=?, endereco_CEP=?  where cpf=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, newp.getNome());
      st.setString(2, newp.getER());
      st.setString(3, newp.getEN());
      st.setString(4, newp.getER());
      st.setString(5, newp.getCPF());


			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(String cpf) {
		try {
			db = new DatabaseConnection();
			String sql = "delete from paciente where cpf=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, cpf);
			st.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		PacienteDAO pacienteDao = new PacienteDAO();
	

		Paciente p2 = new Paciente("85729969313","153094297",32,"Fabianna Stefany","22/01/1981","F","Vila São José","856","60440804");
		
		pacienteDao.save(p2);


		List<Paciente> allPacientes = pacienteDao.getAll();
		for(int i = 0; i < allPacientes.size(); i++) {
			Paciente p = allPacientes.get(i);
			System.out.println(p.getNome());
		}
		
		try {
		pacienteDao.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}