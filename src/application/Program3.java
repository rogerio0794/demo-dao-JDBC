package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


public class Program3 {

	public static void main(String[] args) {
		
		
		// Meu programa não conhece a implementação, apenas a interface (INJEção de dependencia)
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== Teste 1: department findById ====");
		Department department = departmentDao.findById(3);		
		System.out.println(department);
			
		
		
		System.out.println();
		System.out.println("=== Teste 4: department insert ====");
		Department newdepartment = new Department(null, "Futebol");
		departmentDao.insert(newdepartment);
		System.out.println("Inserido! Novo Id = " + newdepartment.getId());
		
		
		
		System.out.println();
		System.out.println("=== Teste 6: department delete ====");
		int idDelete = 11;
		departmentDao.deleById(idDelete);
		
		
		System.out.println();
		System.out.println("=== Teste 5: department update ====");
		department = departmentDao.findById(1); // pegando o departmento com id 1
		department.setName("NOTEBOOKS"); // Atualizando o nome
		departmentDao.update(department);
		System.out.println("Atualizado! Novo nome = " + department.getName());
		
		
		System.out.println();
		System.out.println("=== Teste 3: department findAll ====");
		List<Department> list1 = departmentDao.findAll();		
		
		for (Department obj: list1) {
			System.out.println(obj);
		}
		
		
		
		
		
		

	}

}
