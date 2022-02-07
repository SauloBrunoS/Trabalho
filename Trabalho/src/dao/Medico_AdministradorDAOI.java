package dao;

import java.util.List;

import model.Medico_Administrador;

public interface Medico_AdministradorDAOI {
	Medico_Administrador get(String cpf);
	List<Medico_Administrador> getAll();
	void save(Medico_Administrador newma);
	void update(Medico_Administrador newma, Medico_Administrador oldma);
	void delete(String cpf);
}
