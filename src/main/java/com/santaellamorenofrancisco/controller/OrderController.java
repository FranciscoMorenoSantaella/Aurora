package com.santaellamorenofrancisco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santaellamorenofrancisco.model.Order;
import com.santaellamorenofrancisco.model.Product;
import com.santaellamorenofrancisco.service.OrderService;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService service;

	/**
	 * Método que devuelve una lista de orderistradores
	 * 
	 * @return Lista de orderistradores con un codigo 200 o una respuesta 400 si no
	 *         se ha realizado correctamente si devuelve dicha respuesta normalmente
	 *         será porque no hay orderistradores en la base de datos
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping

	public ResponseEntity<List<Order>> getAllOrders() {
		try {
			List<Order> orderlist = service.getAllOrders();
			return new ResponseEntity<List<Order>>(orderlist, new HttpHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Order>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para buscar un order según su id
	 * 
	 * @param id es el id del orderistrador que queremos buscar
	 * @return Un orderistrador
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(Long id) {
		try {
			Order order = service.getOrderById(id);
			return new ResponseEntity<Order>(order, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<Order>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("numorder/{numorder}")
	public ResponseEntity<List<Order>> getOrderByNumOrder(@PathVariable Long numorder) {
		try {
			List<Order> orderlist = service.getOrderByNumOrder(numorder);
			return new ResponseEntity<List<Order>>(orderlist, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<List<Order>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}


	/**
	 * Método que sirve para borrar un orderistrador por su id
	 * 
	 * @param id El id del orderistrador que queremos borrar
	 * @return Un código 200 de que la operación se ha realizado o un código 400 si
	 *         ha fallado
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@DeleteMapping("/{id}")
	public ResponseEntity<Order> deleteOrderById(Long id) {
		try {
			service.deleteOrderById(id);
			return new ResponseEntity<Order>(new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Order>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para crear un nuevo orderistrador
	 * 
	 * @param Order es el objeto que vamos a pasar para crearlo en la base de datos
	 * @return devuelve una respuesta 200 con el orderistrador si se ha realizado
	 *         correctamente si no se realiza correctamente devuelve un respuesta
	 *         400
	 * @throws Exception 
	 * @throws NullPointerException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) throws NullPointerException, Exception {

		
				Order createOrder = service.createOrder(order);
				return new ResponseEntity<Order>(createOrder, new HttpHeaders(), HttpStatus.OK);
			
	}


	/**
	 * Método que sirve para cambiar los valores del order en la base de
	 * datos
	 * 
	 * @param Order es el objeto que vamos a pasar para actualizarlo en la base de
	 *              datos
	 * @return Una respuesta 200 si se ha realizado correctamente la operación o un
	 *         error 400 si no se ha realizado correctamente
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@PutMapping
	public ResponseEntity<Order> updateOrder(@RequestBody Order Order) {
		if (Order != null && Order.getId() != -1) {
			try {
				service.updateOrder(Order);
				return new ResponseEntity<Order>(new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Order>(new Order(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<Order>(new Order(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("shoppingcart/{product_id}")

	public ResponseEntity<List<Product>> getProductsInShoppingCart(@PathVariable Long product_id) {
		try {
			List<Product> productlist = service.getProductsInShoppingCart(product_id);
			return new ResponseEntity<List<Product>>(productlist, new HttpHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Product>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
