package vn.khanhduy.dao.impl;

import jakarta.persistence.EntityManager;
import vn.khanhduy.configs.JPAConfig;
import vn.khanhduy.dao.IRoleDao;
import vn.khanhduy.entities.Roles;

public class RoleDaoImpl implements IRoleDao {

	@Override
	public Roles findById(int roleId) {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			return enma.find(Roles.class, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			enma.close();
		}
	}

}
