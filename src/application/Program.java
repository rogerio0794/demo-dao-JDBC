package application;


import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		
		
		// Meu programa não conhece a implementação, apenas a interface (INJEção de dependencia)
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println("=== Teste 1: seller findById ====");
		Seller seller = sellerDao.findById(3);		
		System.out.println(seller);
		
		
		

	}

}
