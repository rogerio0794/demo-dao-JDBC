package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {
	
	// Metodo para inserção do obj no banco de dados
	void insert(Seller obj);
	
	// Metodo para atualizar o obj no banco de dados
	void update(Seller obj);
	
	// Metodo para deletar um objeto do banco de dados via Id
	void deleById(Integer id);
	
	// Encontrar obj via id
	Seller findById(Integer id);	
	
	// Retornar todos os vendedores
	List<Seller> findAll();
	
	// Retornar os vendedores por departamento
	List<Seller> findByDepartment(Department department);
	

}
