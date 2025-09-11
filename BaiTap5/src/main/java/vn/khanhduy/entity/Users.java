package vn.khanhduy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
//@Table(name = "users")
@NamedQuery(name = "Users.findAll", query = "select u from Users u")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "varchar(252)")
	private String fullname;


	@Column(columnDefinition = "varchar(252)")
	private String phone;

	@Column(columnDefinition = "nvarchar(max)")
	private String image;

	public Users() {
	}

	public Users(int id, String fullname, String phone, String image) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
