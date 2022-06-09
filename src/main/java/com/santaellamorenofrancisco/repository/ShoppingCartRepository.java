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
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	/*
	 * @Query(nativeQuery = true, value =
	 * "SELECT p.* AS Product ,o.amount AS Amount FROM _order o, shoppingcart sc, product p WHERE o.shoppingcart_id = sc.id AND num_order =?1 AND p.id = o.product_id"
	 * ) public List<ProductAmountRequest>
	 * getProductAmountByShoppingCartId(@Param("id") Long id);
	 */

	/**
	 * Consulta que calcula el precio total (la suma) de todos los productos que hay
	 * en un carro de la compra
	 * 
	 * @param shoppingcart_id es el id del carro de la compra del que vamos a
	 *                        calcular su precio total
	 * @return devuelve una Double
	 */
	@Query(nativeQuery = true, value = "SELECT SUM(p.price * o.amount) AS PRICE FROM _order o, shoppingcart sc, product p WHERE o.shoppingcart_id = sc.id AND p.id = o.product_id AND sc.id = ?1")
	public Double getTotalPrice(@Param("shoppingcart_id") Long shoppingcart_id);

	/**
	 * Consulta que devuelve el ultimo id de los carros de la compra segun un id de
	 * cliente
	 * 
	 * @param client_id es el id del cliente del que queremos saber su ultimo carro
	 *                  de la compra
	 * @return un entero que es el id del ultimo carro de la compra de un cliente
	 */
	@Query(nativeQuery = true, value = "SELECT sc.id FROM shoppingcart sc, client c WHERE c.id = sc.client_id AND c.id = ?1 ORDER BY sc.id DESC LIMIT 1")
	public Integer getLastShoppingCartIdByClientId(@Param("client_id") Long client_id);

	/**
	 * Consulta que devuelve el ultimo carro de la compra no pagado de un cliente en
	 * especifico, el ultimo carro no pagado es el que se le mostrara al usuario
	 * para que pueda pulsar el boton de pagar y que se envien los productos
	 * 
	 * @param client_id es el id del cliente del que queremos traer el id de su
	 *                  ultimo carro de la compra que aun no ha pagado
	 * @return devuelve un entero que es el id del carro de la compra
	 */
	@Query(nativeQuery = true, value = "SELECT sc.id FROM shoppingcart sc, client c WHERE c.id = sc.client_id AND sc.ispayed = false AND c.id = ?1 ORDER BY sc.id DESC LIMIT 1")
	public Long getLastShoppingCartIdNotPayedByClientId(@Param("client_id") Long client_id);

	/**
	 * Consulta que inserta en la base de datos un shoppingcart
	 * 
	 * @param id        es el id del shoppingcart
	 * @param date      es la fecha en la que se ha creado
	 * @param ispayed   es si esta pagado o no
	 * @param client_id es el id del cliente del que se va a crear su nuevo carro de
	 *                  la compra
	 * @return 1 si se ha insertado o 0 si no (devuelve uno porque es una inserccion
	 *         de una linea en la base de datos o 0 si no se ha insertado)
	 */
	@Query(nativeQuery = true, value = "INSERT INTO public.shoppingcart(id, date, ispayed, client_id) VALUES (?1, ?2, ?3, ?4)")
	public int insertShoppingcart(@Param("id") Long id, @Param("date") LocalDateTime date,
			@Param("ispayed") Boolean ispayed, @Param("client_id") Long client_id);

	/**
	 * Metodo que resta el precio total de producto del carro de la compra con el
	 * saldo del cliente (esta consulta sirve para pagar el carro de la compra)
	 * 
	 * @param client_id       es el id del cliete que va a pagar el carro de la
	 *                        compra
	 * @param shoppingcart_id es el id del carro de la compra que que el cliente va
	 *                        a pagar
	 * @return el resultado de la resta del precio total y el saldo, este resultado
	 *         sera el saldo actual del cliente
	 */
	@Query(nativeQuery = true, value = "SELECT SUM(p.price * o.amount) - c.balance FROM product p, _order o,shoppingcart sc, client c WHERE p.id = o.product_id AND sc.id = o.shoppingcart_id AND c.id = sc.client_id AND c.id = ?1 AND sc.id = ?2 GROUP BY c.id")
	public Double payShoppingCart(@Param("client_id") Long client_id, @Param("shoppingcart_id") Long shoppingcart_id);
	
	
}
