package com.santaellamorenofrancisco.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	private Long id;
	private LocalDateTime orderDate;
	private Double totalprice;
	private List<Client> clientlist;
	private List<Product> productlist;
	
	public Order(Long id, LocalDateTime orderDate, Double totalprice, List<Client> clientlist,
			List<Product> productlist) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.totalprice = totalprice;
		this.clientlist = clientlist;
		this.productlist = productlist;
	}

	public Order(LocalDateTime orderDate, Double totalprice, List<Client> clientlist, List<Product> productlist) {
		super();
		this.orderDate = orderDate;
		this.totalprice = totalprice;
		this.clientlist = clientlist;
		this.productlist = productlist;
	}

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public List<Client> getClientlist() {
		return clientlist;
	}

	public void setClientlist(List<Client> clientlist) {
		this.clientlist = clientlist;
	}

	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", totalprice=" + totalprice + ", clientlist="
				+ clientlist + ", productlist=" + productlist + "]";
	}
	
	
}
