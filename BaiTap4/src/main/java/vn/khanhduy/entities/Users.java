package vn.khanhduy.entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
//@Table(name = "users")
@NamedQuery(name = "Users.findAll", query = "select u from Users u")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "varchar(252)")
	private String userName;
	
	@Column(columnDefinition = "varchar(252)")
	private String passWord;
	
	@Column(columnDefinition = "varchar(252)")
	private String email;
	
	@Column(columnDefinition = "varchar(252)")
	private String sdt;
	
	@Column(columnDefinition = "nvarchar(max)")
	private String image;
	
	@OneToOne(cascade =  CascadeType.ALL)// xoa role id -> xoa luon user
	@JoinColumn(name = "roleId", referencedColumnName = "roleId")
	@JsonManagedReference
	//thanh phan chinh cua quan he
	private Roles role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Category> categories;//set de lay ra tap hop mat hang ko trung
	

	public Users() {
	}


	public Users(int id, String userName, String passWord, String email, String sdt, String image, Roles role,
			Set<Category> categories) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.sdt = sdt;
		this.image = image;
		this.role = role;
		this.categories = categories;
	}
	
	
	
	public Users(String userName, String passWord, String email, String sdt, Roles role) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.sdt = sdt;
		this.role = role;
	}

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSdt() {
		return sdt;
	}


	public void setSdt(String sdt) {
		this.sdt = sdt;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Roles getRole() {
		return role;
	}


	public void setRole(Roles role) {
		this.role = role;
	}


	public Set<Category> getCategories() {
		return categories;
	}


	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}
