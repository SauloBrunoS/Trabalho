package dao;
import java.util.List;

import model.Pessoa;

public interface PessoaDAOI {
	Pessoa get(int id_pessoa);
	List<Pessoa> getAll();
	void save(Pessoa newp);
	void update(Pessoa newp, Pessoa oldp);
	void delete(int id_pessoa);
}