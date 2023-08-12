package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	// N�o expor a implementa��o
	public static SellerDao  createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}

}
