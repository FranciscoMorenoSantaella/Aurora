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

@Entity
@Table(name = "_order")
public class Order implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "num_order")
	private Long numorder;
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	@Column(name = "ispaid")
	private Boolean ispaid;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	public Order(Long id, Long numorder, LocalDateTime orderDate, Boolean ispaid, Client client, Product product) {
		super();
		this.id = id;
		this.numorder = numorder;
		this.orderDate = orderDate;
		this.ispaid = ispaid;
		this.client = client;
		this.product = product;
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

	public Long getNumorder() {
		return numorder;
	}

	public void setNumorder(Long numorder) {
		this.numorder = numorder;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Boolean getIspaid() {
		return ispaid;
	}

	public void setIspaid(Boolean ispaid) {
		this.ispaid = ispaid;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", numorder=" + numorder + ", orderDate=" + orderDate + ", ispaid=" + ispaid
				+ ", client=" + client + ", product=" + product + "]";
	}

}
