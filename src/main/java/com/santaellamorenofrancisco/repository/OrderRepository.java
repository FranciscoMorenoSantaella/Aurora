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
	

	/*@Query(nativeQuery = true, value = "SELECT o.* FROM shoppingcart sc, _order o WHERE o.shoppingcart_id = sc.id AND num_order = ?1")
	public List<Order> getOrderByNumOrder(@Param("num_order") Long numorder);*/
	
	/**
	 * Metodo que trae las ordenes segun el id del carrito de a compra
	 * @param shoppingcart_id es el id del carro de la compra del que queremos traer sus ordenes
	 * @return una lista de ordenes
	 */
	@Query(nativeQuery = true, value = "SELECT o.* FROM _order o WHERE o.shoppingcart_id = ?1")
	public List<Order> getOrderByShoppingCartId(@Param("shoppingcart_id") Long shoppingcart_id);
}
