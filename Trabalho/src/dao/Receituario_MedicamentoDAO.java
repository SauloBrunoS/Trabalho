package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.Receituario_Medicamento;
import util.DatabaseConnection;


public class Receituario_MedicamentoDAO implements Receituario_MedicamentoDAOI{
	
	DatabaseConnection db;

	@Override
	public Receituario_Medicamento get(int numero_receituario, int id_medicamento) {
		
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.receituario_medicamento where numero_receituario=? and id_medicamento = ?";
			PreparedStatement st = db.getConnection().prepareStatement(sql);
			st.setInt(1, numero_receituario);
			st.setInt(2, id_medicamento);
			ResultSet res = st.executeQuery();
			if(res.next()) {
				Receituario_Medicamento rm = new Receituario_Medicamento(res.getInt("numero_receituario"), res.getInt("id_medicamento"), res.getString("metodo_de_aplicacao"));
				return rm;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Receituario_Medicamento> getAll() {
		try {
			db = new DatabaseConnection();
			String sql = "select * from trabalho.receituario_medicamento";
			Statement st = db.getConnection().createStatement();
			ResultSet res = st.executeQuery(sql);
			List<Receituario_Medicamento> receituariosmedicamentosList = new ArrayList<Receituario_Medicamento>();
			while(res.next()) {
				Receituario_Medicamento rm = new Receituario_Medicamento(res.getInt("numero_receituario"), res.getInt("id_medicamento"), res.getString("metodo_de_aplicacao"));
				receituariosmedicamentosList.add(rm);
			}
			return receituariosmedicamentosList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void save(Receituario_Medicamento newrm) {
		
		try {
			db = new DatabaseConnection();
			String sql = "insert into trabalho.receituario_medicamento(numero_receituario,id_medicamento,metodo_de_aplicacao) values(?,?,?)";
			PreparedStatement st;
			st = db.getConnection().prepareStatement(sql);
			st.setInt(1, newrm.getNumero_receituario());
			st.setInt(2, newrm.getId_medicamento());
			st.setString(3, newrm.getMetodo_de_aplicacao());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	@Override
	public void update(Receituario_Medicamento newrm, Receituario_Medicamento oldrm) {
		
	}

	@Override
	public void delete(int numero_receituario, int id_medicamento) {
		
	}
	
	public static void main(String[] args) {

		Receituario_MedicamentoDAO receituario_medicamentoDAO = new Receituario_MedicamentoDAO();

		// recebendo uma tupla da relação receituario_medicamento que está no banco de
		// dados
		Receituario_Medicamento rm1 = receituario_medicamentoDAO.get(3, 1);
		System.out.println("número do receituário: " + rm1.getNumero_receituario());
		System.out.println("id do medicamento: " + rm1.getId_medicamento());
		System.out.println("método de aplicação: " + rm1.getMetodo_de_aplicacao());

		// criando um novo objeto da classe Receituario_Medicamento e inserindo no banco
		// de dados
		Receituario_Medicamento rm2 = new Receituario_Medicamento(2, 7, "Aplicar quatro vezes ao dia, a cada 3 horas");
		receituario_medicamentoDAO.save(rm2);

		// exibindo todas as tuplas da relação receituario_medicamento que está no banco
		// de dados
		List<Receituario_Medicamento> allReceituario_Medicamentos = receituario_medicamentoDAO.getAll();
		for (int i = 0; i < allReceituario_Medicamentos.size(); i++) {
			Receituario_Medicamento rm = allReceituario_Medicamentos.get(i);
			System.out.println("número do receituário: " + rm.getNumero_receituario());
			System.out.println("id do medicamento: " + rm.getId_medicamento());
			System.out.println("método de aplicação: " + rm.getMetodo_de_aplicacao());
			System.out.println("------------------------");
		}


		
		try {
			receituario_medicamentoDAO.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}