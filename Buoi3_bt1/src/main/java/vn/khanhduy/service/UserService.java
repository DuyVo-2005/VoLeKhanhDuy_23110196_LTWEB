package vn.khanhduy.service;

import vn.khanhduy.models.User;

public interface UserService {
	User login(String username, String password);
	User get(String username);
}
