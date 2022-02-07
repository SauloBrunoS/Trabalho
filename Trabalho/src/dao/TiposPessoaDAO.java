package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.TiposPessoa;
import util.DatabaseConnection;

public class TiposPessoaDAO implements TiposPessoaDAOI{
	
	DatabaseConnection db;

	@Override
	public TiposPessoa get(int id_tipo) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.tipospessoa where id_tipo=?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setInt(1, id_tipo);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				TiposPessoa p = new TiposPessoa(res.getInt("id_tipo"),res.getString("nome_tipo"));
				return p;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<TiposPessoa> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.tipospessoa";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<TiposPessoa> tipospessoasList = new ArrayList<TiposPessoa>();
			while(res.next()) {
				TiposPessoa p = new TiposPessoa(res.getInt("id_tipo"),res.getString("nome_tipo"));
				tipospessoasList.add(p);
			}
			return tipospessoasList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
  return null;
  }


	@Override
	public void save(TiposPessoa newp) {
		try {
			db = new DatabaseConnection();
			String sql = "insert into tipospessoa((id_tipo,nome_tipo) values(?,?)";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, newp.getId_tipo());
      st.setString(2,newp.getNome_tipo());
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(TiposPessoa newp, TiposPessoa oldp) {
		
	}

	@Override
	public void delete(int id_tipo) {
		try {
			db = new DatabaseConnection();
			String sql = "delete from trabalho.pessoa where id_pessoa=?";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, id_tipo);
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		TiposPessoaDAO tiposPessoaDAO = new TiposPessoaDAO();


    tiposPessoaDAO.save(new TiposPessoa(4,"Assistente"));
		
		
		List<TiposPessoa> allTiposPessoa = tiposPessoaDAO.getAll();
		for(int i = 0; i < allTiposPessoa.size(); i++) {
			TiposPessoa p = allTiposPessoa.get(i);
			System.out.println(p.getNome_tipo());
		}
		
		try {
		tiposPessoaDAO.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}