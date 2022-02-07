package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Consulta;
import model.Tratamento;
import util.DatabaseConnection;

public class TratamentoDAO implements TratamentoDAOI{
	
	public DatabaseConnection db;

	@Override
	public Tratamento get(int numero_tratamento) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.tratamento where numero_tratamento=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setInt(1, numero_tratamento);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Tratamento t = new Tratamento(res.getFloat("valor"), res.getString("descricao"), res.getInt("id_consulta"));
				t.setNumero_tratamento(res.getInt("numero_tratamento"));
				return t;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Tratamento> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.tratamento";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Tratamento> tratamentosList = new ArrayList<Tratamento>();
			while(res.next()) {
				Tratamento t = new Tratamento(res.getFloat("valor"), res.getString("descricao"), res.getInt("id_consulta"));
				t.setNumero_tratamento(res.getInt("numero_tratamento"));
				tratamentosList.add(t);
			}
			return tratamentosList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void save(Tratamento newt) {
		
		try {
			db = new DatabaseConnection();
			String sql = "insert into trabalho.tratamento(valor, descricao, id_consulta) values(?,?,?)";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setFloat(1, newt.getValor());
			st.setString(2, newt.getDescricao());
			st.setInt(3, newt.getId_consulta());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	@Override
	public void update(Tratamento newt, Tratamento oldt) {
		try {
			db = new DatabaseConnection();
			String sql = "update trabalho.tratamento set descricao=? where numero_tratamento=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, newt.getDescricao());
			st.setInt(2, newt.getNumero_tratamento());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getNumeroTratamento(Tratamento t) {
		TratamentoDAO tratamentoDAO = new TratamentoDAO();
		try {
			db = new DatabaseConnection();
			String sql = "select numero_tratamento from trabalho.tratamento where id_consulta=? ";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1,t.getId_consulta());
			ResultSet res = st.executeQuery();
			if(res.next()) {
				return res.getInt("numero_tratamento");
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
		
		// recebendo tratamento do banco de dados
		TratamentoDAO tratamentoDAO = new TratamentoDAO();
		Tratamento t1 = tratamentoDAO.get(2);
		System.out.println("número do tratamento:" + t1.getNumero_tratamento());
		System.out.println("valor do tratamento:" + t1.getValor());
		System.out.println("descrição do tratamento:" + t1.getDescricao());
		System.out.println("id da consulta:" + t1.getId_consulta());

		// criando novo tratamento e inserindo no banco de dados
		Tratamento t2 = new Tratamento(1700, "tratamento de cárie", c4.getId_consulta());
		tratamentoDAO.save(t2);
		// como o número do tratamento é serial, nós obtemos ele do banco de dados e
		// inserimos no objeto da
		// classe Tratamento
		t2.setNumero_tratamento(tratamentoDAO.getNumeroTratamento(t2));

		// atualizando tratamento no banco de dados
		Tratamento t3 = new Tratamento(1700, "tratamento de cárie em diversos dentes", c4.getId_consulta());
		t3.setNumero_tratamento(tratamentoDAO.getNumeroTratamento(t2));
		tratamentoDAO.update(t3, t2);

		// exibindo todos os tratamentos no banco de dados
		List<Tratamento> allTratamentos = tratamentoDAO.getAll();
		for (int i = 0; i < allTratamentos.size(); i++) {
			Tratamento t = allTratamentos.get(i);
			System.out.println("número do tratamento:" + t.getNumero_tratamento());
			System.out.println("valor do tratamento:" + t.getValor());
			System.out.println("descrição do tratamento:" + t.getDescricao());
			System.out.println("id da consulta:" + t.getId_consulta());
			System.out.println("--------------");
		}


	    		
		try {
			tratamentoDAO.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}