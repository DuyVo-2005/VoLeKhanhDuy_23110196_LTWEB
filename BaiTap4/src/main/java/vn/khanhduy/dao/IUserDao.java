package vn.khanhduy.dao;

import java.util.List;

import vn.khanhduy.entities.Users;


public interface IUserDao {
	List<Users> findAll();

	Users findById(int id);

	void insert(Users user);

	// login
	Users findByUsername(String username);

	// register
	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);
}
