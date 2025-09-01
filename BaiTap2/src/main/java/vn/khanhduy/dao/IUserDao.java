package vn.khanhduy.dao;

import java.util.List;

import vn.khanhduy.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();

	UserModel findById(int id);

	void insert(UserModel user);

	// login
	UserModel findByUsername(String username);

	// register
	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);
}
