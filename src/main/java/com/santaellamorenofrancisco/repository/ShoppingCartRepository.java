package com.santaellamorenofrancisco.repository;

import java.time.LocalDateTime;
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
	
	
	@Query(nativeQuery = true, value = "SELECT sc.id FROM shoppingcart sc, client c WHERE c.id = sc.client_id AND c.id = ?1 ORDER BY sc.id DESC LIMIT 1")
	public Integer getLastShoppingCartIdByClientId(@Param("client_id")Long client_id);
	
	@Query(nativeQuery = true, value = "SELECT sc.id FROM shoppingcart sc, client c WHERE c.id = sc.client_id AND sc.ispayed = false ORDER BY sc.id DESC LIMIT 1")
	public Integer getLastShoppingCartIdNotPayedByClientId(@Param("client_id")Long client_id);
	
	@Query(nativeQuery = true, value = "INSERT INTO public.shoppingcart(id, date, ispayed, client_id) VALUES (?1, ?2, ?3, ?4)" )
	public int insertShoppingcart(@Param("id") Long id, @Param("date") LocalDateTime date,@Param("ispayed") Boolean ispayed, @Param("client_id") Long client_id);
}
