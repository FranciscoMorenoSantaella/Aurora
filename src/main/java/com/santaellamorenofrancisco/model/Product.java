package com.santaellamorenofrancisco.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private Double price;
	@Column(name = "type")
	private String type;
	@Column(name = "stock")
	private int stock;
	@Column(name = "creation_date")
	private LocalDateTime creation_date;
	@OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<Order> orderlist;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "admin_id")
	private Admin admin;
	
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<Image> imagelist;
	
	public Product(Long id, String name, Double price, String type, int stock, LocalDateTime creation_date,
			Set<Order> orderlist, Admin admin, Set<Image> imagelist) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.stock = stock;
		this.creation_date = creation_date;
		this.orderlist = orderlist;
		this.admin = admin;
		this.imagelist = imagelist;
	}

	public Product() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public LocalDateTime getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(LocalDateTime creation_date) {
		this.creation_date = creation_date;
	}

	public Set<Order> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(Set<Order> orderlist) {
		this.orderlist = orderlist;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Set<Image> getImagelist() {
		return imagelist;
	}

	public void setImagelist(Set<Image> imagelist) {
		this.imagelist = imagelist;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", stock=" + stock
				+ ", creation_date=" + creation_date + ", orderlist=" + orderlist + ", admin=" + admin + ", imagelist="
				+ imagelist + "]";
	}
	
}
