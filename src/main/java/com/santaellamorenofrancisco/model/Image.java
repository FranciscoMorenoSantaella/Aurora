package com.santaellamorenofrancisco.model;

import java.io.Serial;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "image")
public class Image {

	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "uniquename", unique = true)
	private String uniquename;
	@Column(name = "originalname")
	private String originalname;
	@Column(name = "url")
	private String url;
	@JsonIgnore
	// @JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	public Image(Long id, String uniquename, String originalname, String url, Product product) {
		super();
		this.id = id;
		this.uniquename = uniquename;
		this.originalname = originalname;
		this.url = url;
		this.product = product;
	}

	public Image() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniquename() {
		return uniquename;
	}

	public void setUniquename(String uniquename) {
		this.uniquename = uniquename;
	}

	public String getOriginalname() {
		return originalname;
	}

	public void setOriginalname(String originalname) {
		this.originalname = originalname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", uniquename=" + uniquename + ", originalname=" + originalname + ", url=" + url
				+ ", product=" + product + "]";
	}

}
