package vn.khanduy.dao;

import vn.khanhduy.models.User;

public interface UserDao {
	User get(String username);
}
