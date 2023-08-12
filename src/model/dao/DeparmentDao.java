package model.dao;

import java.util.List;

import model.entities.Department;

public interface DeparmentDao {
	
	
	// Metodo para inserção do obj no banco de dados
	void insert(Department obj);
	
	// Metodo para atualizar o obj no banco de dados
	void update(Department obj);
	
	// Metodo para deletar um objeto do banco de dados via Id
	void deleById(Integer id);
	
	// Encontrar obj via id
	Department findById(Integer id);	
	
	// Retornar todos os departamentos
	List<Department> findall();
	
	
	
	

}
