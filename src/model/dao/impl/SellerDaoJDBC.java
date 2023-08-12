package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
				Department depAux = new Department();
				depAux.setId(rs.getInt("DepartmentId")); // Pegando o id do departamento
				depAux.setName(rs.getString("DepName")); // Pegando o nome do departamento
				
				Seller obj = new Seller();
				obj.setId(rs.getInt("Id"));  // Os argumentos em "" são os nomes das colunas das tabelas no DB
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				obj.setDepartment(depAux);
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

	@Override
	public List<Seller> findall() {
		return null;
	}

}
