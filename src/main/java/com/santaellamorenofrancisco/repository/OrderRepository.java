package com.santaellamorenofrancisco.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santaellamorenofrancisco.model.Order;
import com.santaellamorenofrancisco.model.Product;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	/*@Query(nativeQuery = true, value = "SELECT p.* FROM product p ORDER BY creation_date desc")
	Long getLastOrderNum();*/
	
	@Query(nativeQuery = true, value = "SELECT o.* FROM _order o ORDER BY o.num_order desc Limit 1")
	public Order getLastOrderNum();
	
	@Query(nativeQuery = true, value = "SELECT p.* FROM _order o, product p WHERE o.client_id = ?1 AND o.ispaid = false AND p.id = o.product_id")
	public List<Product> getProductsInShoppingCart(@Param("client_id") Long client_id);

	@Query(nativeQuery = true, value = "INSERT INTO _order(id, ispaid, num_order, order_date, client_id, product_id) VALUES (?1,?2,?3,?4,?5,?6")
	public Boolean createOrder(@Param("id") Long id, @Param("ispaid")Boolean ispaid,@Param("num_order")Long num_order,@Param("order_date")LocalDateTime order_date,@Param("client_id") Long client_id,@Param("product_id") Long product_id);
	
	@Query(nativeQuery = true, value = "SELECT o.* FROM shoppingcart sc, _order o WHERE o.shoppingcart_id = sc.id AND num_order = ?1")
	public List<Order> getOrderByNumOrder(@Param("num_order") Long numorder);
	
	@Query(nativeQuery = true, value = "SELECT o.* FROM _order o WHERE o.shoppingcart_id = ?1")
	public List<Order> getOrderByShoppingCartId(@Param("shoppingcart_id") Long shoppingcart_id);
}
