package dao;

import java.util.List;

import model.Consulta;

public interface ConsultaDAOI {
	Consulta get(int id_consulta);
	List<Consulta> getAll();
	void save(Consulta newc);
	void update(Consulta newc, Consulta oldc);
	void delete(int id_consulta);
}
