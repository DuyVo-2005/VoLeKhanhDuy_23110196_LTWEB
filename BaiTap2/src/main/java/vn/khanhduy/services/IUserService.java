package vn.khanhduy.services;

import vn.khanhduy.models.UserModel;

public interface IUserService {
	// service goi dao
	UserModel login(String username, String password);

	UserModel get(String username);

	boolean register(String email, String password, String username, String fullname, String phone);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);
	
	public void insert(UserModel user);
}
