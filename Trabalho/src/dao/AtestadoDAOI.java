package dao;

import java.util.List;

import model.Atestado;

public interface AtestadoDAOI {
	Atestado get(int numero_atestado);
	List<Atestado> getAll();
	void save(Atestado newa);
	void update(Atestado newa, Atestado olda);
	void delete(int numero_atestado);

}
