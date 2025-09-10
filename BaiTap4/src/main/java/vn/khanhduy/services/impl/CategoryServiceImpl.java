package vn.khanhduy.services.impl;

import java.io.File;
import java.util.List;

import vn.khanhduy.dao.ICategoryDao;
import vn.khanhduy.dao.impl.CategoryDaoImpl;
import vn.khanhduy.entities.Category;
import vn.khanhduy.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	ICategoryDao categoryDao = new CategoryDaoImpl();
	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(Category category) {
		Category oldCate = categoryDao.findById(category.getId());
		oldCate.setCategoryName(category.getCategoryName());
		if(category.getImage() != null) {
			//xoa anh cu
			String fileName = oldCate.getImage();
			final String dir = "D:\\upload";
			File file = new File(dir + "/category" + fileName);
			if(file.exists()) {
				file.delete();
			}
			oldCate.setImage(category.getImage());
		}
		categoryDao.edit(oldCate);
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category findById(int id) {
		return categoryDao.findById(id);
	}

	@Override
	public Category findByName(String name) {
		return categoryDao.findByName(name);
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}
	
	@Override
	public List<Category> getAllForManager(int id) {
		return categoryDao.getAllForManager(id);
	}

	@Override
	public List<Category> search(String keyword) {
		return categoryDao.search(keyword);//seacch catename
	}
}
