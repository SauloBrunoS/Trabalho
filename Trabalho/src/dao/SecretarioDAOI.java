package dao;
import java.util.List;

import model.Secretario;

public interface SecretarioDAOI {
	Secretario get(String email);
	List<Secretario> getAll();
	void save(Secretario newp);
	void update(Secretario newp, Secretario oldp);
	void delete(String email);
}