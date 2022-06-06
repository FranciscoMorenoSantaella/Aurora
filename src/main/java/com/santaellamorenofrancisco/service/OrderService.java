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

	// public static final Logger logger =
	// LoggerFactory.getLogger(OrderService.class);

	public List<Order> getAllOrders() throws Exception {
		try {
			List<Order> OrderList = repository.findAll();
			return OrderList;
		} catch (Exception e) {
			// logger.error("There is no orders in the database " + e);
			throw new Exception("No hay orderees en la base de datos", e);
		}
	}

	/**
	 * Método que sirve para trar un ordere según su id
	 * 
	 * @param id es el id del ordere que queremos buscar
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
					// logger.error("The Order doesn't exists in the database.");
					throw new Exception("El Order no existe");
				}
			} catch (IllegalArgumentException e) {
				// logger.error("IllegalArgumentException in the method getOrderById: " + e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				// logger.error("Exception in the method getOrderById: " + e);
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException in the method getOrderById id equals to
			// null.");
			throw new NullPointerException("El id es un objeto nulo");
		}
	}
	
	public List<Order> getOrderByNumOrder(Long numorder) throws Exception, IllegalArgumentException, NullPointerException {
		if (numorder != null) {
			try {
				List<Order> order = repository.getOrderByNumOrder(numorder);
				return order;
			} catch (IllegalArgumentException e) {
				//logger.error("IllegalArgumentException in the method getShoppingCartById: " + e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				//logger.error("Exception in the method getShoppingCartById: " + e);
				throw new Exception(e);
			}
		} else {
			//logger.error("NullPointerException in the method getShoppingCartById id equals to null.");
			throw new NullPointerException("El id es un objeto nulo");
		}
	}

	/**
	 * Método que sirve para crear un nuevo ordere, si el parametro que
	 * introducimos tiene un id que coincide con el de la base de datos dicho
	 * ordere no se creara si no que se modificarán sus valores
	 * 
	 * @param order es el ordere que vamos a crear o actualizar
	 * @return Un Order
	 * @throws Exception            Error si no se ha podido completar la operación
	 * @throws NullPointerException Error que da si el ordere que hemos introducido
	 *                              es nulo
	 */
	public Order createOrder(Order order) throws Exception, NullPointerException {
		System.out.println(order.toString());
		if (order != null && order.getId() == null) {
			try {
				//order.setNumorder(repository.getLastOrderNum().getNumorder()+1);
				order.setOrderDate(LocalDateTime.now());
				return repository.save(order);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException order equals to null.");
			throw new NullPointerException("El order es nulo");
		}

	}

	/**
	 * Método que sirve para actualizar los datos del ordere en la base de datos
	 * 
	 * @param order es el ordere que vamos a actualizar en la base de datos
	 * @return Un Order
	 * @throws Exception Error si no se ha podido realizar la operación
	 */
	public Order updateOrder(Order order) throws Exception {
		if (order != null) {
			try {
				return repository.save(order);
			} catch (Exception e) {
				// logger.error("Cannot update");
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException in the method updateOrder order is
			// null");
			throw new NullPointerException("El ordere es nulo");
		}

	}

	/**
	 * Método que sirve para borrar un ordere introduciendo su id
	 * 
	 * @param id es el id que vamos a introducir para buscar dicho ordere y
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
					// logger.error("Exception in the method deleteOrderById");
					throw new Exception("El Order no existe");
				}
			} catch (IllegalArgumentException e1) {
				// logger.error("Exception in the method deleteOrderById" + e1);
				throw new IllegalArgumentException("El Order no existe");
			} catch (NullPointerException e1) {
				// logger.error("Exception in the method deleteOrderById" + e1);
				throw new NullPointerException("El Order no existe");
			} catch (Exception e) {
				// logger.error("Exception in the method deleteOrderById" + e);
				throw new Exception("El Order no existe", e);
			}
		} else {
			// logger.error("Exception in the method deleteOrderById");
			
		}
	}
	
	public List<Product> getProductsInShoppingCart(Long product_id) throws Exception{
		if(product_id != null) {
			try {
				return repository.getProductsInShoppingCart(product_id);
			} catch (Exception e) {
				throw new Exception("Error al traer la lista de productos");
			}
		}else {
			throw new NullPointerException("El id es nulo");
		}
		
	}
}
