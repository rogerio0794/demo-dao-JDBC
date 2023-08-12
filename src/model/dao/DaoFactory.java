package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	// Não expor a implementação
	public static SellerDao  createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}

}
