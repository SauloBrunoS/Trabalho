package dao;

import java.util.List;

import model.Receituario;

public interface ReceituarioDAOI {
	Receituario get(int numero_receituario);
	List<Receituario> getAll();
	void save(Receituario newr);
	void update(Receituario newr, Receituario oldr);
	void delete(int numero_receituario);

}
