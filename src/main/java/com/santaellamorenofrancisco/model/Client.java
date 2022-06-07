package com.santaellamorenofrancisco.model;

import java.io.Serial;
import java.io.Serializable;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "client")
public class Client implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "uid")
	private String uid;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "email")
	private String email;
	@Column(name = "phonenumber")
	private String phonenumber;
	@Column(name = "balance")
	private Double balance;
	@Column(name = "shoppingcartlist")
	@OneToMany(mappedBy = "client", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<ShoppingCart> shoppingcartlist;

	public Client(Long id, String uid, String name, String surname, String email, String phonenumber, Double balance,
			Set<ShoppingCart> shoppingcartlist) {
		super();
		this.id = id;
		this.uid = uid;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phonenumber = phonenumber;
		this.balance = balance;
		this.shoppingcartlist = shoppingcartlist;
	}

	public Client() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Set<ShoppingCart> getShoppingcartlist() {
		return shoppingcartlist;
	}

	public void setShoppingcartlist(Set<ShoppingCart> shoppingcartlist) {
		this.shoppingcartlist = shoppingcartlist;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", uid=" + uid + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", phonenumber=" + phonenumber + ", balance=" + balance + ", shoppingcartlist=" + shoppingcartlist
				+ "]";
	}

}
