package vn.khanhduy.services;

import java.util.List;

import vn.khanhduy.entities.Category;


public interface ICategoryService {
	void insert(Category category);
	void edit(Category category);
	void delete(int id);
	Category findById(int id);
	Category findByName(String name);
	List<Category> getAll();
	List<Category> getAllForManager(int id);
	List<Category> search(String keyword);
}
