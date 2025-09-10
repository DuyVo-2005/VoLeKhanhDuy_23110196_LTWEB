package vn.khanhduy.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.khanhduy.configs.JPAConfig;
import vn.khanhduy.dao.IUserDao;
import vn.khanhduy.entities.Users;

public class UserDaoImpl implements IUserDao {

	@Override
	public List<Users> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Users> query = enma.createNamedQuery("Users.findAll", Users.class);
		return query.getResultList();
	}

	@Override
	public Users findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
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
	public void insert(Users user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(user);// insert
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
	public Users findByUsername(String username) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			return enma.createQuery("select u from Users u where u.userName = :username", Users.class)
					.setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return null;
		} finally {
			enma.close();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Long count = enma.createQuery("select count(u) from Users u where u.email = :email", Long.class)
					.setParameter("email", email)
					.getSingleResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;
		} finally {
			enma.close();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Long count = enma.createQuery("select count(u) from Users u where u.userName = :username", Long.class)
					.setParameter("username", username)
					.getSingleResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;	
		} finally {
			enma.close();
		}
	}

	@Override
	public boolean checkExistPhone(String phone) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Long count = enma.createQuery("select count(u) from Users u where u.phone = :phone", Long.class)
					.setParameter("phone", phone)
					.getSingleResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;	
		} finally {
			enma.close();
		}
	}

}
