package com.santaellamorenofrancisco.request;

import com.santaellamorenofrancisco.model.Product;

/**
 * Objeto que va a ser usado para hacer una consulta que nos devuelva este objeto en especifico que es un producto mas el campo
 * de otra tabla que es la cantidad de productos de la order, esto sirve para poder traer al carrito de la compra el producto,
 * la cantidad del producto y asi calcular la suma de todos los productos.
 * @author Francisco Moreno Santaella
 *
 */
public class ProductAmountRequest {
	private Product product;
	private Integer amount;
	
	public ProductAmountRequest(Product product, Integer amount) {
		super();
		this.product = product;
		this.amount = amount;
	}

	public ProductAmountRequest() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ProductAmountRequest [product=" + product + ", amount=" + amount + "]";
	}
		
}
