package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Consulta;
import model.Receituario;
import util.DatabaseConnection;
import model.Medico_Administrador;


public class Medico_AdministradorDAO implements Medico_AdministradorDAOI{
	
	DatabaseConnection db;

	@Override
	public Medico_Administrador get(String cpf) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.medico_administrador where cpf=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setString(1, cpf);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Medico_Administrador ma = new Medico_Administrador(res.getString("cpf"));
				return ma;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Medico_Administrador> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.medico_administrador";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Medico_Administrador> medico_administradorList = new ArrayList<Medico_Administrador>();
			while(res.next()) {
				Medico_Administrador ma = new Medico_Administrador(res.getString("cpf"));
				medico_administradorList.add(ma);
			}
			return medico_administradorList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void save(Medico_Administrador newma) {
		
		try {
			db = new DatabaseConnection();
			String sql = "insert into trabalho.medico_administrador(cpf) values(?)";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, newma.getCpf());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	@Override
	public void update(Medico_Administrador newma, Medico_Administrador oldma) {
		
	}
	
	public void delete(String cpf) {
		try {
			db = new DatabaseConnection();
			String sql = "delete from trabalho.medico_administrador where cpf=?";
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
	
		// recebendo um medico_administrador do banco de dados
		Medico_AdministradorDAO medico_administradorDAO = new Medico_AdministradorDAO();
		Medico_Administrador ma1 = medico_administradorDAO.get("57792296350");
		System.out.println("cpf do médico administrador: " + ma1.getCpf());

		// criando novo medico_administrador e inserindo no banco de dados
		Medico_Administrador ma2 = new Medico_Administrador("05976920330");
		medico_administradorDAO.save(ma2);

		// removendo medico_administrador do banco de dados
		medico_administradorDAO.delete("05976920330");

		// exibindo todos os médicos administradores do banco de dados
		List<Medico_Administrador> allMedico_Administradores = medico_administradorDAO.getAll();
		for (int i = 0; i < allMedico_Administradores.size(); i++) {
			Medico_Administrador ma = allMedico_Administradores.get(i);
			System.out.println("cpf do médico administrador: " + ma.getCpf());
			System.out.println("--------------");
		}
    		
		try {
			medico_administradorDAO.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}