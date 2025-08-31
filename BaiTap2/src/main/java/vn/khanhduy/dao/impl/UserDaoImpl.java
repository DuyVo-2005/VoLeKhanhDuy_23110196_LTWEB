package vn.khanhduy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.khanhduy.configs.DbConnection;
import vn.khanhduy.dao.IUserDao;
import vn.khanhduy.models.UserModel;

public class UserDaoImpl extends DbConnection implements IUserDao{
	// 1. UserDaoImpl -> add unimplement method

		// 3. tao 3 bien chung cho cac phuong thuc
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		@Override
		public List<UserModel> findAll() {
			String sql = "select * from [User]";

			List<UserModel> list = new ArrayList<>();// 2. tao 1 list de truyen du lieu

			try {
				// 4. super dung cho truong hop co extends -> ket noi db bang ke thua
				conn = super.getConnection();// hoac conn = new DbConnect().getConnection(); neu ko ke thua
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()/* next tung dong den cuoi bang */) {
					list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
							rs.getString("fullname"), rs.getString("email"), rs.getString("avatar"), rs.getInt("roleid"),
							rs.getString("phone"), rs.getDate("createdDate")));
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();

			}
			return null;
		}

		@Override
		public UserModel findById(int id) {
			UserModel user = null;
			try {
				String sql = "select * from [User] where id=?";
				conn = super.getConnection();

				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();

				if (rs.next()) {
					user = new UserModel();
					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setUserName(rs.getString("username"));
					user.setFullName(rs.getString("fullname"));
					user.setPassWord(rs.getString("password"));
					user.setAvatar(rs.getString("avatar"));
					user.setRoleid(rs.getInt("roleid"));
					user.setCreatedDate(rs.getDate("createdDate"));
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (ps != null)
						rs.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return user;
		}

		@Override
		public void insert(UserModel user) {
			String sql = "insert into [User] values (?,?,?,?,?,?,?,?,?)";
			try {
				conn = super.getConnection();// ket noi db
				ps = conn.prepareStatement(sql);// nem sql vao ps

				ps.setInt(1, user.getId());
				ps.setString(2, user.getUserName());
				ps.setString(3, user.getPassWord());
				ps.setString(4, user.getFullName());
				ps.setString(5, user.getEmail());
				ps.setString(6, user.getAvatar());
				ps.setInt(7, user.getRoleid());
				ps.setString(8, user.getPhone());
				ps.setDate(9, user.getCreatedDate());

				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static void main(String[] args) {
			UserDaoImpl userDao = new UserDaoImpl();

			/*
			 * // Date now = new Date(0);//sql date Date sqlDate =
			 * Date.valueOf("2025-08-31"); userDao.insert(new UserModel(3, "duy3",
			 * "12345678", "vlkduy", "d2@gmail.com", "yes", 1, "565", sqlDate));
			 */

			/*
			 * List<UserModel> list = userDao.findAll(); for (UserModel user : list) {
			 * System.out.println(user); }
			 */
			System.out.println(userDao.findByUsername("Duy3"));
		}

		@Override
		public UserModel findByUsername(String username) {
			String sql = "SELECT * FROM [User] WHERE username = ? ";
			try {
				conn = new DbConnection().getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				while (rs.next()) {
					UserModel user = new UserModel();
					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setUserName(rs.getString("username"));
					user.setFullName(rs.getString("fullname"));
					user.setPassWord(rs.getString("password"));
					user.setAvatar(rs.getString("avatar"));
					user.setRoleid(Integer.parseInt(rs.getString("roleid")));
					user.setPhone(rs.getString("phone"));
					user.setCreatedDate(rs.getDate("createdDate"));
					return user;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}
