package application;


import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		
		
		// Meu programa n�o conhece a implementa��o, apenas a interface (INJE��o de dependencia)
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println("=== Teste 1: seller findById ====");
		Seller seller = sellerDao.findById(3);		
		System.out.println(seller);
		
		System.out.println();
		System.out.println("=== Teste 2: seller findByDepartment ====");
		Department department = new Department(2,null);
		List<Seller> list = sellerDao.findByDepartment(department);		
		
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
		System.out.println("Inserido! Noco Id = " + newSeller.getId());
		
		
		

	}

}
