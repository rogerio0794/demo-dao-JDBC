package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	// Não expor a implementação
	public static SellerDao  createSellerDao() {
		return new SellerDaoJDBC();
	}

}
