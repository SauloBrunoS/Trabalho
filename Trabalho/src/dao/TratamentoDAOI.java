package dao;

import java.util.List;

import model.Tratamento;

public interface TratamentoDAOI {
	Tratamento get(int numero_tratamento);
	List<Tratamento> getAll();
	void save(Tratamento newt);
	void update(Tratamento newt, Tratamento oldt);
}
