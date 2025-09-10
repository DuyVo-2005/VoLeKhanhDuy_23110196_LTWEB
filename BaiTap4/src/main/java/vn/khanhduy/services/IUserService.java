package vn.khanhduy.services;

import vn.khanhduy.entities.Users;

public interface IUserService {
	// service goi dao
		Users login(String username, String password);

		Users findByUsername(String username);

		boolean register(String usernamwe, String password, String email, String phone);

		boolean checkExistEmail(String email);

		boolean checkExistUsername(String username);

		boolean checkExistPhone(String phone);
		
		public void insert(Users user);
}
