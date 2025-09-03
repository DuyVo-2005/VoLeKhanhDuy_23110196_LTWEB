package vn.khanhduy.services.impl;

import java.io.File;
import java.util.List;

import vn.khanhduy.dao.CategoryDAO;
import vn.khanhduy.dao.impl.CategoryDAOImpl;
import vn.khanhduy.models.CategoryModel;
import vn.khanhduy.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	CategoryDAO categoryDao = new CategoryDAOImpl();
	@Override
	public void insert(CategoryModel category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(CategoryModel category) {
		CategoryModel oldCate = categoryDao.get(category.getCateid());
		oldCate.setCatename(category.getCatename());
		if(category.getIcon() != null) {
			//xoa anh cu
			String fileName = oldCate.getIcon();
			final String dir = "D:\\upload";
			File file = new File(dir + "/category" + fileName);
			if(file.exists()) {
				file.delete();
			}
			oldCate.setIcon(category.getIcon());
		}
		categoryDao.edit(oldCate);
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public CategoryModel get(int id) {
		return categoryDao.get(id);
	}

	@Override
	public CategoryModel get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<CategoryModel> getAll() {
		return categoryDao.getAll();
	}

	@Override
	public List<CategoryModel> search(String keyword) {
		return categoryDao.search(keyword);//seacch catename
	}

}
