package vn.khanhduy.services.impl;

import vn.khanhduy.dao.IUserDao;
import vn.khanhduy.dao.impl.UserDaoImpl;
import vn.khanhduy.models.UserModel;
import vn.khanhduy.services.IUserService;

public class UserServiceImpl implements IUserService {
	// lay cac ham dinh nghia trong user dao tai day
	IUserDao userDao = new UserDaoImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.get(username);// get cua tang service
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel get(String username) {
		return userDao.findByUsername(username);
	}
}
