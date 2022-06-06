package com.santaellamorenofrancisco.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santaellamorenofrancisco.Customdata.getProductAmount;
import com.santaellamorenofrancisco.model.Order;
import com.santaellamorenofrancisco.model.Product;
import com.santaellamorenofrancisco.model.ShoppingCart;
import com.santaellamorenofrancisco.request.ProductAmountRequest;
@Repository
@Transactional
public interface ShoppingCartRepository  extends JpaRepository<ShoppingCart, Long> {
	@Query(nativeQuery = true, value = "SELECT p.* AS Product ,o.amount AS Amount FROM _order o, shoppingcart sc, product p WHERE o.shoppingcart_id = sc.id AND num_order =?1 AND p.id = o.product_id")
	public List<ProductAmountRequest> getProductAmountByShoppingCartId(@Param("id") Long id);
	
	@Query(nativeQuery = true, value = "SELECT SUM(p.price * o.amount) AS PRICE FROM _order o, shoppingcart sc, product p WHERE o.shoppingcart_id = sc.id AND p.id = o.product_id AND num_order = ?1")
	public Double getTotalPrice(@Param("num_order") Integer numorder);


}
