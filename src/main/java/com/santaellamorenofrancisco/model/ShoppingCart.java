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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * En este objeto vemos de que cliente es el carro de la compra y cuanto vale la suma de todos sus productos y si lo ha pagado ya
 * o no, si el carro que tiene el cliente esta pagado ya y el cliente quiere añadir otro producto a su carro se generara automaticamente un nuevo carro con
 * un nuevo carro (creando un carro con un nuevo id) de forma automatica.
 * @author Francisco Antonio Moreno Santaella
 *
 */
@Entity
@Table(name = "shoppingcart")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) //quitar si actua raro
public class ShoppingCart implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "date")
	private LocalDateTime date;
	@Column(name = "total_price")
	private Double totalprice;
	@Column(name = "ispayed")
	private Boolean ispayed;
	

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "client_id")
	@JsonIgnore
	private Client client;

	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
	@JsonIgnore
	private Set<Order> orderlist;

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

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
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
