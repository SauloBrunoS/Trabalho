package dao;
import java.util.List;

import model.Paciente;

public interface PacienteDAOI {
	Paciente get(String email);
	List<Paciente> getAll();
	void save(Paciente newp);
	void update(Paciente newp, Paciente oldp);
	void delete(String email);
}