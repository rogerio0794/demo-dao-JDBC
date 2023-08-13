package application;


import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		
		
		// Meu programa não conhece a implementação, apenas a interface (INJEção de dependencia)
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println("=== Teste 1: seller findById ====");
		Seller seller = sellerDao.findById(3);		
		System.out.println(seller);
		
		System.out.println();
		System.out.println("=== Teste 2: seller findByDepartment ====");
		Department department = new Department(2,null); // Departamento Electronics
		List<Seller> list = sellerDao.findByDepartment(department);	// Criando a lista de todos os vendedores deste departamento	
		
		for (Seller obj: list) {
			System.out.println(obj);
		}
		
		
		System.out.println();
		System.out.println("=== Teste 3: seller findAll ====");
		List<Seller> list1 = sellerDao.findAll();		
		
		for (Seller obj: list1) {
			System.out.println(obj);
		}
		
		System.out.println();
		System.out.println("=== Teste 4: seller insert ====");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department   );
		sellerDao.insert(newSeller);
		System.out.println("Inserido! Novo Id = " + newSeller.getId());
		
		
		
		System.out.println();
		System.out.println("=== Teste 5: seller update ====");
		seller = sellerDao.findById(1); // pegando o vendedor com id 1
		seller.setName("Ronaldo"); // Atualizando o nome
		sellerDao.update(seller);
		System.out.println("Atualizado! Novo nome = " + seller.getName());
		
		
		
		System.out.println();
		System.out.println("=== Teste 6: seller delete ====");
		int idDelete = 17;
		sellerDao.deleById(idDelete);
		
		
		
		
		
		
		

	}

}
