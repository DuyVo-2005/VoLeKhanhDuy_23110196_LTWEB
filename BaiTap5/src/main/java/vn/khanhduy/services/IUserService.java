package vn.khanhduy.services;

import java.util.List;

import vn.khanhduy.entity.Users;

public interface IUserService {
	void edit(Users user);
	public List<Users> getAll();
	Users findById(int id);
}
