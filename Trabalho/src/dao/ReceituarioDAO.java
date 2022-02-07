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



public class ReceituarioDAO implements ReceituarioDAOI{
	
	public DatabaseConnection db;

	@Override
	public Receituario get(int numero_receituario) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.receituario where numero_receituario=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setInt(1, numero_receituario);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Receituario r = new Receituario(res.getInt("id_consulta"));
				r.setNumero_receituario(res.getInt("numero_receituario"));
				return r;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Receituario> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.receituario";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Receituario> receituariosList = new ArrayList<Receituario>();
			while(res.next()) {
				Receituario r = new Receituario(res.getInt("id_consulta"));
				r.setNumero_receituario(res.getInt("numero_receituario"));
				receituariosList.add(r);
			}
			return receituariosList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void save(Receituario newr) {
		
		try {
			db = new DatabaseConnection();
			String sql = "insert into trabalho.receituario(id_consulta) values(?)";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, newr.getId_consulta());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	@Override
	public void update(Receituario newr, Receituario oldr) {
		
	}
	
	public void delete(int numero_receituario) {
		
	}
	
	public int getNumeroReceituario(Receituario r) {
		ReceituarioDAO receituarioDAO = new ReceituarioDAO();
		try {
			db = new DatabaseConnection();
			String sql = "select numero_receituario from trabalho.receituario where id_consulta=? ";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1,r.getId_consulta());
			ResultSet res = st.executeQuery();
			if(res.next()) {
				return res.getInt("numero_receituario");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;

	}

	public void numero_remedios_receituario(int numero_receituario) {
		try {
			db = new DatabaseConnection();
			String sql = "select numero_remedios_receituario2(?)";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setInt(1, numero_receituario);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				System.out.println("numero de remédios no receituário: " + res.getInt("numero_remedios_receituario2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	public static void main(String[] args) {

		ReceituarioDAO receituarioDAO = new ReceituarioDAO();
		ConsultaDAO consultaDAO = new ConsultaDAO();

		// recebendo receituário do banco de dados
		Receituario r1 = receituarioDAO.get(2);
		System.out.println("id da consulta:" + r1.getId_consulta());
		System.out.println("número do receituário:" + r1.getNumero_receituario());

		Consulta c4 = new Consulta(17, 1, 2022, "07:35:00", "08:00:00", "finalizada", "23043783310", "85729969317");
		consultaDAO.save(c4);
		c4.setId_consulta(consultaDAO.getID(c4));
		
		// criando novo receituário e inserindo no banco de dados
		Receituario r2 = new Receituario(c4.getId_consulta());
		receituarioDAO.save(r2);
		// como o número do receituário é serial, nós obtemos ele do banco de dados e
		// inserimos no objeto da
		// classe Receituario
		r2.setNumero_receituario(receituarioDAO.getNumeroReceituario(r2));

		// exibindo todos os receituários que estão no banco de dados
		List<Receituario> allReceituarios = receituarioDAO.getAll();
		for (int i = 0; i < allReceituarios.size(); i++) {
			Receituario r = allReceituarios.get(i);
			System.out.println("id da consulta:" + r.getId_consulta());
			System.out.println("número do receituário:" + r.getNumero_receituario());
			System.out.println("--------------");
		}

		/* quantidade de remédios do receituário de número 2 */
		receituarioDAO.numero_remedios_receituario(2);

	    
		try {
			receituarioDAO.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}