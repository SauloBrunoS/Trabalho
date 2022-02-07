package dao;
import java.util.List;

import model.Telefone;

public interface TelefoneDAOI {
	Telefone get(String numero);
	List<Telefone> getAll();
	void save(Telefone newp);
	void update(Telefone newp, Telefone oldp);
	void delete(String numero);
}