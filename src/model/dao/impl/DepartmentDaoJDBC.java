package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	
	// Injeção de dependencia
	private Connection conn;
		
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		
		
		PreparedStatement st = null;
		
		String query = "INSERT INTO department "
				+ "(Name) "
				+ "VALUES "
				+ "(?)";
		
		try {
			st  = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			
			
			// Numero de linhas criadas pela iserção
			int rowAffected = st.executeUpdate();
			
			if (rowAffected > 0) {
				// Inseriu algo
				ResultSet rs = st.getGeneratedKeys(); // pegando as chaves
				if (rs.next()) {
					int id = rs.getInt(1); // linha 1 é onde esta o id, por isso o rs.next para sair da linha 0 para 1
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado, nenhuma linha foi afetada");
			}
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);			
		}
		
		
		
	}

	@Override
	public void update(Department obj) {
		
		PreparedStatement st = null;
		
		String query = "UPDATE department SET Name = ? WHERE Id = ?";
		
		try {
			st  = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());			
			st.setInt(2, obj.getId()); 
			
			
			st.executeUpdate();
			
						
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);			
		}
		
	}

	@Override
	public void deleById(Integer id) {
		
		PreparedStatement st = null;
		
		String query = "DELETE FROM department "
				+ "WHERE Id = ?";
		
		try {
			st = conn.prepareStatement(query);		
			
			st.setInt(1, id);
			
			
			// Se deletar alguma linha vai obter o numero das linhas
			int rows = st.executeUpdate();
			
			if (rows > 0) {
				System.out.println("Deletado com sucesso!");
			} else {
				throw new DbException("Id não encontrado!");
			}
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);			
		}
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM department WHERE Id = ?";
		
		try {
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery(); // Aqui eu tenho a tabela, eu preciso dos objetos instanciados em memória!
			
			// Na posição 1 temos um objeto
			if (rs.next()) {				
				// Se entrou, significa que o rs retornou uma linha da consulta
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
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
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String query = "SELECT * from department";
		
		try {
			st = conn.prepareStatement(query);
			rs = st.executeQuery(); 
			
			List<Department> list = new ArrayList<>();
			
			// Agora precisamos de um while pois pode retornar mais de uma linha
			while (rs.next()) {	
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
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
