package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Pessoa;
import util.DatabaseConnection;

public class PessoaDAO implements PessoaDAOI{
	
	DatabaseConnection db;

	@Override
	public Pessoa get(int id_pessoa) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from pessoa where id_pessoa=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setInt(1, id_pessoa);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Pessoa p = new Pessoa(res.getInt("id_pessoa"),res.getInt("id_tipo"));
				p.setId_pessoa(res.getInt("id_pessoa"));
				return p;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Pessoa> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from pessoa";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Pessoa> pessoasList = new ArrayList<Pessoa>();
			while(res.next()) {
				Pessoa p = new Pessoa(res.getInt("id_pessoa"),res.getInt("id_tipo"));
				p.setId_pessoa(res.getInt("id_pessoa"));
				pessoasList.add(p);
			}
			return pessoasList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    return null;
  }

	@Override
	public void save(Pessoa newp) {
		try {
			db = new DatabaseConnection();
			String sql = "insert into pessoa((id_pessoa, id_tipo) values(?, ?)";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, newp.getId_pessoa());
			st.setInt(2, newp.getId_tipo());
			st.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(Pessoa newp, Pessoa oldp) {
		
	}

	@Override
	public void delete(int id_pessoa) {
		try {
			db = new DatabaseConnection();
			String sql = "delete from trabalho.pessoa where id_pessoa=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, id_pessoa);
			st.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		PessoaDAO pessoaDao = new PessoaDAO();
		Pessoa p2 = new Pessoa(31,1);
    Pessoa p3 = new Pessoa(32,2);
    Pessoa p4 = new Pessoa(33,3);
		
		pessoaDao.save(p2);
    pessoaDao.save(p3);
    pessoaDao.save(p4);
		
		List<Pessoa> allPessoas = pessoaDao.getAll();
		for(int i = 0; i < allPessoas.size(); i++) {
			Pessoa p = allPessoas.get(i);
			System.out.println(p.getId_pessoa());
		}
		
		try {
		pessoaDao.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}