package com.santaellamorenofrancisco.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santaellamorenofrancisco.model.Order;
import com.santaellamorenofrancisco.model.Product;
import com.santaellamorenofrancisco.model.ShoppingCart;
import com.santaellamorenofrancisco.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository repository;



	/**
	 * Metodo que trae una lista con todas las ordenes
	 * @return
	 * @throws Exception
	 */
	public List<Order> getAllOrders() throws Exception {
		try {
			List<Order> OrderList = repository.findAll();
			return OrderList;
		} catch (Exception e) {

			throw new Exception("No hay ordenes en la base de datos", e);
		}
	}

	/**
	 * Metodo que sirve para trar un orden segun su id
	 * 
	 * @param id es el id del orden que queremos buscar
	 * @return Un Order
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Order getOrderById(Long id) throws Exception, IllegalArgumentException, NullPointerException {
		if (id != null) {
			try {
				Optional<Order> getOrderById = repository.findById(id);
				if (getOrderById.isPresent()) {
					return getOrderById.get();
				} else {
					throw new Exception("El Order no existe");
				}
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else {
			throw new NullPointerException("El id es un objeto nulo");
		}
	}
	

	/**
	 * Metodo que sirve para crear un nuevo orden si el parametro que
	 * introducimos tiene un id que coincide con el de la base de datos dicho
	 * orden no se creara si no que se modificarán sus valores
	 * 
	 * @param order es el orden que vamos a crear o actualizar
	 * @return Un Order
	 * @throws Exception            Error si no se ha podido completar la operacion
	 * @throws NullPointerException Error que da si el orden que hemos introducido
	 *                              es nulo
	 */
	public Order createOrder(Order order) throws Exception, NullPointerException {
		if (order != null && order.getId() == null) {
			try {
				order.setOrderDate(LocalDateTime.now());
				return repository.save(order);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else {

			throw new NullPointerException("El order es nulo");
		}

	}

	/**
	 * Metodo que sirve para actualizar los datos del orden en la base de datos
	 * 
	 * @param order es el orden que vamos a actualizar en la base de datos
	 * @return Un Order
	 * @throws Exception Error si no se ha podido realizar la operacion
	 */
	public Order updateOrder(Order order) throws Exception {
		if (order != null) {
			try {
				return repository.save(order);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else {
	
			throw new NullPointerException("El orden es nulo");
		}

	}

	/**
	 * Metodo que sirve para borrar un orden introduciendo su id
	 * 
	 * @param id es el id que vamos a introducir para buscar dicho orden y
	 *           borrarlo
	 * @throws NullPointerException     Este error ocurre cuando el id es nulo
	 * @throws IllegalArgumentException Este error ocurre cuando pasamos un
	 *                                  parametro que no es el debido por ejemplo un
	 *                                  string cuando debe ser un entero
	 * @throws Exception
	 */
	public void deleteOrderById(Long id) throws NullPointerException, IllegalArgumentException, Exception {
		if (id != null) {
			Optional<Order> deleteOrderById;
			try {
				deleteOrderById = Optional.ofNullable(getOrderById(id));
				if (!deleteOrderById.isEmpty()) {
					repository.deleteById(id);
				} else {
					
					throw new Exception("El Order no existe");
				}
			} catch (IllegalArgumentException e1) {
				
				throw new IllegalArgumentException("El Order no existe");
			} catch (NullPointerException e1) {
	
				throw new NullPointerException("El Order no existe");
			} catch (Exception e) {

				throw new Exception("El Order no existe", e);
			}
		} else {

			
		}
	}
	
	/**
	 * Metodo que trae una orden segun el id de un carro de la compra
	 * @param shoppingcart_id es el id del carro de la compra
	 * @return devuelve una lista de ordenes
	 * @throws Exception
	 */
	public List<Order> getOrderByShoppingCartId(Long shoppingcart_id) throws Exception{
		if(shoppingcart_id != null) {
			try {
				return repository.getOrderByShoppingCartId(shoppingcart_id);
			} catch (Exception e) {
				throw new Exception("Error al traer la lista de productos");
			}
		}else {
			throw new NullPointerException("El id es nulo");
		}
		
	}
}
