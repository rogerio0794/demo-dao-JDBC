package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

	public static void main(String[] args) {
		
		
		Department obj = new Department(1, "Books");	
		
		System.out.println(obj);
		
		
		Seller seller = new Seller(10,"Aluno","Aluno@gmail.com", new Date(), 3000.0, obj);
		
		System.out.println(seller);
		
		
		// Meu programa n�o conhece a implementa��o, apenas a interface (INJE��o de dependencia)
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller2 = sellerDao.findById(3);
		
		System.out.println(seller2);
		
		
		

	}

}
