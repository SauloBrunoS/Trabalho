package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Telefone;
import util.DatabaseConnection;

public class TelefoneDAO implements TelefoneDAOI{
	
	DatabaseConnection db;

	@Override
	public Telefone get(String numero) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from Telefone where numero=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setString(1, numero);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Telefone p = new Telefone(res.getInt("id_pessoa"), res.getString("numero"));
				p.setNumero(res.getString("numero"));
				return p;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Telefone> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.telefone";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Telefone> telefonesList = new ArrayList<Telefone>();
			while(res.next()) {
				Telefone p = new Telefone(res.getInt("id_pessoa"), res.getString("numero"));
				p.setNumero(res.getString("numero"));
				telefonesList.add(p);
			}
			return telefonesList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    return null;
  }

	@Override
	public void save(Telefone newp) {
		try {
			db = new DatabaseConnection();
			String sql = "insert into trabalho.telefone((id_pessoa, numero) values(?, ?)";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, newp.getId_pessoa());
			st.setString(2, newp.getNumero());
			st.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(Telefone newp, Telefone oldp) {
		
	}

	@Override
	public void delete(String numero) {
		try {
			db = new DatabaseConnection();
			String sql = "delete from telefone where numero=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setString(1, numero);
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


  public static void main(String[] args) {
		TelefoneDAO telefoneDao = new TelefoneDAO();

		Telefone s2 = new Telefone(31,"0530875033");
		
		telefoneDao.save(s2);
		
		List<Telefone> allTelefones = telefoneDao.getAll();
		for(int i = 0; i < allTelefones.size(); i++) {
			Telefone p = allTelefones.get(i);
			System.out.println(p.getNumero());
		}
		
		try {
		telefoneDao.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}