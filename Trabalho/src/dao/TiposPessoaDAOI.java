package dao;
import java.util.List;

import model.TiposPessoa;

public interface TiposPessoaDAOI {
	TiposPessoa get(int id_tipo);
	List<TiposPessoa> getAll();
	void save(TiposPessoa newp);
	void update(TiposPessoa newp, TiposPessoa oldp);
	void delete(int id_tipo);
}