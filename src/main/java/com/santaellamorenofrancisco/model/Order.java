package com.santaellamorenofrancisco.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "_Order")
public class Order implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	@Column(name = "total_price")
	private Double totalprice;
	// @JsonIgnoreProperties("userOrders")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, targetEntity = Client.class)
	@JoinColumn(name = "order_client", nullable = false)
	private Client client;

	// @JsonIgnoreProperties("orders")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinTable(name = "Order_Product", joinColumns = @JoinColumn(name = "id_order", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_product", nullable = false), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "id_order", "id_product" }) })
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Product.class)
	private List<Product> productlist;

	public Order(Long id, LocalDateTime orderDate, Double totalprice, Client client, List<Product> productlist) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.totalprice = totalprice;
		this.client = client;
		this.productlist = productlist;
	}

	public Order(LocalDateTime orderDate, Double totalprice, Client client, List<Product> productlist) {
		super();
		this.orderDate = orderDate;
		this.totalprice = totalprice;
		this.client = client;
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
		return "Order [id=" + id + ", orderDate=" + orderDate + ", totalprice=" + totalprice + ", productlist="
				+ productlist + "]";
	}

}
