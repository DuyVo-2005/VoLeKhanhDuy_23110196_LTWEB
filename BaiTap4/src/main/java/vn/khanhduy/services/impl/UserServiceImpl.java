package vn.khanhduy.services.impl;

import vn.khanhduy.dao.IRoleDao;
import vn.khanhduy.dao.IUserDao;
import vn.khanhduy.dao.impl.RoleDaoImpl;
import vn.khanhduy.dao.impl.UserDaoImpl;
import vn.khanhduy.entities.Roles;
import vn.khanhduy.entities.Users;
import vn.khanhduy.services.IUserService;

public class UserServiceImpl implements IUserService {
	
	IUserDao userDao = new UserDaoImpl();

	@Override
	public Users login(String username, String password) {
		Users user = this.findByUsername(username);// this la get cua tang service
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}

	@Override
	public Users findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public boolean register(String username, String password, String email, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		
		IRoleDao roleDao = new RoleDaoImpl();
		Roles role = roleDao.findById(1);
		userDao.insert(new Users(username, password, email, phone, role));
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public void insert(Users user) {
		userDao.insert(user);
	}

}
