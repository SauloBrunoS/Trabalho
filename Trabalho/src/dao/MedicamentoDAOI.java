package dao;

import java.util.List;

import model.Medicamento;

public interface MedicamentoDAOI {
	Medicamento get(int id_medicamento);
	List<Medicamento> getAll();
	void save(Medicamento newm);
	void update(Medicamento newm, Medicamento oldm);
	void delete(int id_medicamento);

}
