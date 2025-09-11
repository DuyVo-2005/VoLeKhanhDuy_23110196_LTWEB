package vn.khanhduy.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.khanhduy.configs.JPAConfigs;
import vn.khanhduy.dao.IUserDao;
import vn.khanhduy.entity.Users;

public class UserDaoImpl implements IUserDao{

	@Override
	public void edit(Users user) {
		EntityManager enma = JPAConfigs.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	@Override
	public Users findById(int id) {
		EntityManager enma = JPAConfigs.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			return enma.createQuery("select u from Users u where u.id = :id", Users.class)
					.setParameter("id", id)
					.getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	@Override
	public List<Users> getAll() {
		EntityManager enma = JPAConfigs.getEntityManager();
		TypedQuery<Users> query = enma.createNamedQuery("Users.findAll", Users.class);
		return query.getResultList();
	}
}
