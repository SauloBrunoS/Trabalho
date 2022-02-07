package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Consulta;
import model.Medicamento;
import model.Receituario;
import model.Medicamento;
import util.DatabaseConnection;



public class MedicamentoDAO implements MedicamentoDAOI{
	
	public DatabaseConnection db;

	@Override
	public Medicamento get(int id_medicamento) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.medicamento where id_medicamento=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setInt(1, id_medicamento);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Medicamento m = new Medicamento(res.getString("nome"));
				m.setId_medicamento(res.getInt("id_medicamento"));
				return m;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Medicamento> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.medicamento";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Medicamento> medicamentosList = new ArrayList<Medicamento>();
			while(res.next()) {
				Medicamento m = new Medicamento(res.getString("nome"));
				m.setId_medicamento(res.getInt("id_medicamento"));
				medicamentosList.add(m);
			}
			return medicamentosList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void save(Medicamento newm) {
		
		try {
			db = new DatabaseConnection();
			String sql = "insert into trabalho.medicamento(nome) values(?)";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, newm.getNome());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	@Override
	public void update(Medicamento newm, Medicamento oldm) {
		
	}
	
	public void delete(int id_medicamento) {
	}
	
	public int getIdMedicamento(Medicamento m) {
		MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
		try {
			db = new DatabaseConnection();
			String sql = "select id_medicamento from trabalho.medicamento where nome=? ";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1,m.getNome());
			ResultSet res = st.executeQuery();
			if(res.next()) {
				return res.getInt("id_medicamento");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;

	}


	
	public static void main(String[] args) {
	
		// recebendo medicamento do banco de dados
		MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
		Medicamento m1 = medicamentoDAO.get(7);
		System.out.println("id do medicamento: " + m1.getId_medicamento());
		System.out.println("nome do medicamento: " + m1.getNome());
		
		// criando novo medicamento e inserindo no banco de dados
		Medicamento m2 = new Medicamento("peroxodum");
		medicamentoDAO.save(m2);
		// como o id do medicamento é serial, nós obtemos ele do banco de dados e
		// inserimos no objeto da
		// classe Medicamento
		m2.setId_medicamento(medicamentoDAO.getIdMedicamento(m2));
		
		// exibindo todos os medicamentos do banco de dados
		List<Medicamento> allMedicamentos = medicamentoDAO.getAll();
		for (int i = 0; i < allMedicamentos.size(); i++) {
			Medicamento m = allMedicamentos.get(i);
			System.out.println("id do medicamento: " + m.getId_medicamento());
			System.out.println("nome do medicamento: " + m.getNome());
			System.out.println("--------------");
		}

	  
		try {
			medicamentoDAO.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}