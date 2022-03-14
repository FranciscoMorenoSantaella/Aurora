package com.santaellamorenofrancisco.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
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
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, mappedBy = "productlist", targetEntity = Order.class)
	private List<Order> orderlist;

	public Product(Long id, String name, Double price, String type, int stock, List<Order> orderlist) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.stock = stock;
		this.orderlist = orderlist;
	}

	public Product(String name, Double price, String type, int stock, List<Order> orderlist) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
		this.stock = stock;
		this.orderlist = orderlist;
	}

	public Product(String name, Double price, String type, int stock) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
		this.stock = stock;
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

	public List<Order> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<Order> orderlist) {
		this.orderlist = orderlist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", stock=" + stock
				+ ", orderlist=" + orderlist + "]";
	}

}
