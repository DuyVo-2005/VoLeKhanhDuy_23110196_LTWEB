package vn.khanhduy.dao;

import java.util.List;

import vn.khanhduy.models.UserModel;

public interface IUserDao {
List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);
	
	//register
	
	//login
	UserModel findByUsername(String username);
}
