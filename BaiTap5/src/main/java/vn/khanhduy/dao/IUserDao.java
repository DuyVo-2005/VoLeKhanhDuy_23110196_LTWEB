package vn.khanhduy.dao;

import java.util.List;

import vn.khanhduy.entity.Users;

public interface IUserDao {
	void edit(Users user);
	public Users findById(int id);
	public List<Users> getAll();
}
