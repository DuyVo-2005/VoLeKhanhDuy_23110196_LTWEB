package vn.khanhduy.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;

@Entity
//@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "select c from Category c")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "nvarchar(252)")
	private String categoryName;
	
	@Column(columnDefinition = "nvarchar(max)")
	private String image;
	
	@Column(columnDefinition = "int")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false, referencedColumnName = "id")
	@JsonBackReference
	private Users user;//userId la khoa phu lien ket khoa chinh id

	public Category() {
	}

	public Category(int id, String categoryName, String image, int quantity, Users user) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.image = image;
		this.quantity = quantity;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	
	
}
