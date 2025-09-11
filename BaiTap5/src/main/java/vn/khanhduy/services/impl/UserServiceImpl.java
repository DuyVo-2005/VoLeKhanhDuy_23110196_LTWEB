package vn.khanhduy.services.impl;

import java.io.File;
import java.util.List;

import vn.khanhduy.dao.IUserDao;
import vn.khanhduy.dao.impl.UserDaoImpl;
import vn.khanhduy.entity.Users;
import vn.khanhduy.services.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao userDao = new UserDaoImpl();

	@Override
	public void edit(Users user) {
		Users oldUser = userDao.findById(user.getId());
		oldUser.setFullname(user.getFullname());
		if (user.getImage() != null) {
			// xoa anh cu
			String fileName = oldUser.getImage();
			final String dir = "D:\\upload";
			File file = new File(dir + "/user" + fileName);
			if (file.exists()) {
				file.delete();
			}
			oldUser.setImage(user.getImage());
		}
		userDao.edit(oldUser);
	}
	
	@Override
	public List<Users> getAll() {
		return userDao.getAll();
	}

	@Override
	public Users findById(int id) {
		return userDao.findById(id);
	}
}
