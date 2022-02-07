package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Atestado;
import model.Consulta;
import model.Medicamento;
import model.Receituario;
import model.Tratamento;
import util.DatabaseConnection;

public class AtestadoDAO implements AtestadoDAOI{
	
	DatabaseConnection db;

	@Override
	public Atestado get(int numero_atestado) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.atestado where numero_atestado=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setInt(1, numero_atestado);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Atestado a = new Atestado(res.getInt("id_consulta"), res.getString("numero_autenticacao"));
				a.setNumero_atestado(res.getInt("numero_atestado"));
				return a;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Atestado> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.atestado";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Atestado> atestadosList = new ArrayList<Atestado>();
			while(res.next()) {
				Atestado a = new Atestado(res.getInt("id_consulta"), res.getString("numero_autenticacao"));
				a.setNumero_atestado(res.getInt("numero_atestado"));
				atestadosList.add(a);
			}
			return atestadosList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void save(Atestado newa) {
		
		try {
			db = new DatabaseConnection();
			String sql = "insert into trabalho.atestado(id_consulta, numero_autenticacao) values(?,?)";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, newa.getId_consulta());
			st.setString(2, newa.getNumero_autenticacao());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	@Override
	public void update(Atestado newm, Atestado oldm) {
		
	}
	
	public void delete(int id_medicamento) {
		
	}
	
	public int getNumeroAtestado(Atestado a) {
		AtestadoDAO atestadoDAO = new AtestadoDAO();
		try {
			db = new DatabaseConnection();
			String sql = "select numero_atestado from trabalho.atestado where numero_autenticacao=? ";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1,a.getNumero_autenticacao());
			ResultSet res = st.executeQuery();
			if(res.next()) {
				return res.getInt("numero_atestado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;

	}
	
	public static void main(String[] args) {
		ConsultaDAO consultaDAO = new ConsultaDAO();
		Consulta c4 = new Consulta(17, 1, 2022, "07:35:00", "08:00:00", "finalizada", "23043783310", "85729969317");
		consultaDAO.save(c4);
		c4.setId_consulta(consultaDAO.getID(c4));
		
		// recebendo atestado do banco de dados
		AtestadoDAO atestadoDAO = new AtestadoDAO();
		Atestado a1 = atestadoDAO.get(2);
		System.out.println("id da consulta: " + a1.getId_consulta());
		System.out.println("número do atestado: " + a1.getNumero_atestado());
		System.out.println("número de autenticação: " + a1.getNumero_autenticacao());

		// criando novo atestado e inserindo no banco de dados
		Atestado a2 = new Atestado(c4.getId_consulta(), "79789789");
		atestadoDAO.save(a2);
		// como o número do atestado é serial, nós obtemos ele do banco de dados e
		// inserimos no objeto da
		// classe Atestado
		a2.setNumero_atestado(atestadoDAO.getNumeroAtestado(a2));

		// exibindo todos os atestados do banco de dados
		List<Atestado> allAtestados = atestadoDAO.getAll();
		for (int i = 0; i < allAtestados.size(); i++) {
			Atestado a = allAtestados.get(i);
			System.out.println("id da consulta: " + a.getId_consulta());
			System.out.println("número do atestado: " + a.getNumero_atestado());
			System.out.println("número de autenticação: " + a.getNumero_autenticacao());
			System.out.println("--------------");
		}

		try {
			atestadoDAO.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}