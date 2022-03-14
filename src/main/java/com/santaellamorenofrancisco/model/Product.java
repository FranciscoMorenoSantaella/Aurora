package com.santaellamorenofrancisco.model;

import java.util.List;

public class Product {
	private Long id;
	private String name;
	private Double price;
	private String type;
	private int stock;
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
