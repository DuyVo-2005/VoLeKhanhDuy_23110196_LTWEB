package vn.khanhduy.services;

import vn.khanhduy.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);
	UserModel get(String username);
	//service goi dao
}
