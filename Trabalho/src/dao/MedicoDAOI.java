package dao;
import java.util.List;

import model.Medico;

public interface MedicoDAOI {
	Medico get(String email);
	List<Medico> getAll();
	void save(Medico newp);
	void update(Medico newp, Medico oldp);
	void delete(String email);
}