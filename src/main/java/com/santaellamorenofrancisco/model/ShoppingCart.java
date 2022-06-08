package com.santaellamorenofrancisco.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "shoppingcart")
public class ShoppingCart implements Serializable {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "date")
	private LocalDateTime date;
	@Column(name = "total_price")
	private Float totalprice;
	@Column(name = "ispayed")
	private Boolean ispayed;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
	private Set<Order> orderlist;

	public ShoppingCart(Long id, LocalDateTime date, Float totalprice, Boolean ispayed, Client client,
			Set<Order> orderlist) {
		super();
		this.id = id;
		this.date = date;
		this.totalprice = totalprice;
		this.ispayed = ispayed;
		this.client = client;
		this.orderlist = orderlist;
	}
	
	
	
	

	public ShoppingCart(Client client) {
		super();
		this.client = client;
	}





	public ShoppingCart() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Float totalprice) {
		this.totalprice = totalprice;
	}

	public Boolean getIspayed() {
		return ispayed;
	}

	public void setIspayed(Boolean ispayed) {
		this.ispayed = ispayed;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<Order> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(Set<Order> orderlist) {
		this.orderlist = orderlist;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", date=" + date + ", totalprice=" + totalprice + ", ispayed=" + ispayed
				+ ", client=" + client + ", orderlist=" + orderlist + "]";
	}

	
	

	

}
