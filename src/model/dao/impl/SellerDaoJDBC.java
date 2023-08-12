package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	
	// Injeção de dependencia
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		
	}

	@Override
	public void update(Seller obj) {
		
	}

	@Override
	public void deleById(Integer id) {
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String query = "SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "WHERE seller.Id = ?";
		
		try {
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery(); // Aqui eu tenho a tabela, eu preciso dos objetos instanciados em memória!
			
			// Na posição 1 temos um objeto
			if (rs.next()) {				
				// Se entrou, significa que o rs retornou uma linha da consulta
				Department depAux = instantiateDepartment(rs);		// Utilizando metodos pois vão ser reutilizados		
				Seller obj = instantiateSeller(rs, depAux); 
				return obj;				
			}
			return null;
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	}

	private Seller instantiateSeller(ResultSet rs, Department depAux) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));  // Os argumentos em "" são os nomes das colunas das tabelas no DB
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(depAux);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department depAux = new Department();
		depAux.setId(rs.getInt("DepartmentId")); // Pegando o id do departamento
		depAux.setName(rs.getString("DepName")); // Pegando o nome do departamento
		// As exceções vão ser tratatas  em findById.
		return depAux;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String query = "SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "				
				+ "ORDER BY Name";
		
		try {
			st = conn.prepareStatement(query);
			rs = st.executeQuery(); 
			
			List<Seller> list = new ArrayList<>();	
			Map<Integer, Department> map = new HashMap<>();
			
			// Agora precisamos de um while pois pode retornar mais de uma linha
			while (rs.next()) {			
				
				// Não posso repetir o departamento
				// Vou usar um dicionario (Chave, valor)
				
				Department dep = map.get(rs.getInt("DepartmentId")); // Estou pegando o deparmento pela chave (Id)
				// Se dep foir igual a nulo significa que não existia ainda no meu dicionario
				if (dep == null) {
					dep = instantiateDepartment(rs);
					// Vamos guardar esse departamento no meu map, para que na próxima vez ele saiba que já existe esse departamento e não criar outro objeto
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				
				Seller obj = instantiateSeller(rs, dep); 
				list.add(obj);				
			}
			return list;
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {		
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String query = "SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "WHERE DepartmentId = ? "
				+ "ORDER BY Name";
		
		try {
			st = conn.prepareStatement(query);
			st.setInt(1, department.getId());
			rs = st.executeQuery(); 
			
			List<Seller> list = new ArrayList<>();	
			Map<Integer, Department> map = new HashMap<>();
			
			// Agora precisamos de um while pois pode retornar mais de uma linha
			while (rs.next()) {			
				
				// Não posso repetir o departamento
				// Vou usar um dicionario (Chave, valor)
				
				Department dep = map.get(rs.getInt("DepartmentId")); // Estou pegando o deparmento pela chave (Id)
				// Se dep foir igual a nulo significa que não existia ainda no meu dicionario
				if (dep == null) {
					dep = instantiateDepartment(rs);
					// Vamos guardar esse departamento no meu map, para que na próxima vez ele saiba que já existe esse departamento e não criar outro objeto
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				
				Seller obj = instantiateSeller(rs, dep); 
				list.add(obj);				
			}
			return list;
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	}
	
	 
	
	
	
	
	
	
	

}
