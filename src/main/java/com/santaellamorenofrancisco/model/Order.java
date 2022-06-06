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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "_order")
/**
 * Objecto en el que tenemos las ordenes de nuestros clientes estas estan relacionadas al carro de la compra
 * cuando paguemos con el carro de la compra se acabara el pedido actual en el que se seteara el precio total y
 * la fecha del pedido
 * @author Francisco Antonio Moreno Santaella
 *
 */
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
	@Column(name = "amount")
	private Integer amount;
	//@JsonIgnoreProperties(value = { "shoppingcartlist", "product" },allowSetters = true)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnore
	@JoinColumn(name = "shoppingcart_id", nullable = false)
	private ShoppingCart shoppingcart;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties(value = { "orderlist", "shoppingcart" },allowSetters = true)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	public Order(Long id, Long numorder, LocalDateTime orderDate, Integer amount, ShoppingCart shoppingcart,
			Product product) {
		super();
		this.id = id;
		this.numorder = numorder;
		this.orderDate = orderDate;
		this.amount = amount;
		this.shoppingcart = shoppingcart;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public ShoppingCart getShoppingcart() {
		return shoppingcart;
	}

	public void setShoppingcart(ShoppingCart shoppingcart) {
		this.shoppingcart = shoppingcart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", numorder=" + numorder + ", orderDate=" + orderDate + ", amount=" + amount
				+ ", shoppingcart=" + shoppingcart + ", product=" + product + "]";
	}

}
