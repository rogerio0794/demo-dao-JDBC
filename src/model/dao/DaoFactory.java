package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	// N�o expor a implementa��o
	public static SellerDao  createSellerDao() {
		return new SellerDaoJDBC();
	}

}
