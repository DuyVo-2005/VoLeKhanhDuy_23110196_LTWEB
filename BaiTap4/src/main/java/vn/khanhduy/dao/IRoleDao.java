package vn.khanhduy.dao;

import vn.khanhduy.entities.Roles;

public interface IRoleDao {
	Roles findById(int roleId);
}
