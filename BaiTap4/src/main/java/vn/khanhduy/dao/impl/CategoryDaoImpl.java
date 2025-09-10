package vn.khanhduy.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.khanhduy.configs.JPAConfig;
import vn.khanhduy.dao.ICategoryDao;
import vn.khanhduy.entities.Category;

public class CategoryDaoImpl implements ICategoryDao {
	@Override
	public void insert(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
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
	public void edit(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
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
	public void delete(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			 Category category = enma.find(Category.class, id);
			 if (category != null) {
		            enma.remove(category); // entity đang managed -> xoa duoc (ko phai tao moi roi xoa)
		        }
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
	public Category findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			return enma.createQuery("select c from Category c where c.id = :id", Category.class)
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
	public Category findByName(String name) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			return enma.createQuery("select c from Category c where c.categoryName = :name", Category.class)
					.setParameter("name", name)
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
	public List<Category> getAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	@Override
	public List<Category> search(String keyword) {
		//key word la name
		return null;
	}

	@Override
	public List<Category> getAllForManager(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			/*
			 * return enma.createQuery("select c from Category c where c.userId = :id",
			 * Category.class) .setParameter("id", id) .getResultList();//sai
			 */			
			return enma.createQuery("select c from Category c where c.user.id = :id", Category.class)
					.setParameter("id", id)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public boolean hasPrivilege(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
