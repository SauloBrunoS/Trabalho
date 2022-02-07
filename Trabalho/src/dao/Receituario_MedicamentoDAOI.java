package dao;

import java.util.List;

import model.Receituario_Medicamento;

public interface Receituario_MedicamentoDAOI {
	Receituario_Medicamento get(int numero_receituario, int id_medicamento);
	List<Receituario_Medicamento> getAll();
	void save(Receituario_Medicamento newrm);
	void update(Receituario_Medicamento newrm, Receituario_Medicamento oldrm);
	void delete(int numero_receituario, int id_medicamento);

}
