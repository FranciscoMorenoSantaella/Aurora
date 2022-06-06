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

import com.santaellamorenofrancisco.Customdata.getProductAmount;
import com.santaellamorenofrancisco.model.Order;
import com.santaellamorenofrancisco.model.ShoppingCart;
import com.santaellamorenofrancisco.request.ProductAmountRequest;
import com.santaellamorenofrancisco.service.ShoppingCartService;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping("shoppingcart")
public class ShoppingCartController {

	@Autowired
	ShoppingCartService service;

	/**
	 * Método que devuelve una lista de shoppingcarts
	 * 
	 * @return Lista de shoppingcarts con un codigo 200 o una respuesta 400 si no
	 *         se ha realizado correctamente si devuelve dicha respuesta normalmente
	 *         será porque no hay shoppingcart en la base de datos
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping

	public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {
		try {
			List<ShoppingCart> shoppingcartlist = service.getAllShoppingCarts();
			return new ResponseEntity<List<ShoppingCart>>(shoppingcartlist, new HttpHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<ShoppingCart>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para buscar un shoppingcart según su id
	 * 
	 * @param id es el id del shoppingcart que queremos buscar
	 * @return Un shoppingcart
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/{id}")
	public ResponseEntity<ShoppingCart> getShoppingCartById(Long id) {
		try {
			ShoppingCart shoppingcart = service.getShoppingCartById(id);
			return new ResponseEntity<ShoppingCart>(shoppingcart, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<ShoppingCart>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	

	/**
	 * Método que sirve para borrar un Shoppingcart por su id
	 * 
	 * @param id El id del shoppingcart que queremos borrar
	 * @return Un código 200 de que la operación se ha realizado o un código 400 si
	 *         ha fallado
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@DeleteMapping("/{id}")
	public ResponseEntity<ShoppingCart> deleteShoppingCartById(Long id) {
		try {
			service.deleteShoppingCartById(id);
			return new ResponseEntity<ShoppingCart>(new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ShoppingCart>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para crear un nuevo shoppingcart
	 * 
	 * @param ShoppingCart es el objeto que vamos a pasar para crearlo en la base de datos
	 * @return devuelve una respuesta 200 con el shoppingcart si se ha realizado
	 *         correctamente si no se realiza correctamente devuelve un respuesta
	 *         400
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingcart) {
		if (shoppingcart != null) {
			try {
				ShoppingCart createShoppingCart = service.createShoppingCart(shoppingcart);
				return new ResponseEntity<ShoppingCart>(createShoppingCart, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<ShoppingCart>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<ShoppingCart>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para cambiar los valores del shoppingcart en la base de
	 * datos
	 * 
	 * @param ShoppingCart es el objeto que vamos a pasar para actualizarlo en la base de
	 *              datos
	 * @return Una respuesta 200 si se ha realizado correctamente la operación o un
	 *         error 400 si no se ha realizado correctamente
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@PutMapping
	public ResponseEntity<ShoppingCart> updateShoppingCart(@RequestBody ShoppingCart shoppingcart) {
		if (shoppingcart != null && shoppingcart.getId() != -1) {
			try {
				service.updateShoppingCart(shoppingcart);
				return new ResponseEntity<ShoppingCart>(new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<ShoppingCart>(new ShoppingCart(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<ShoppingCart>(new ShoppingCart(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/*@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("productamount/{id}")
	public ResponseEntity<List<ProductAmountRequest>> getProductAmountByShoppingCartId(@PathVariable Long id) {
		try {
			
			List<ProductAmountRequest> productamount = service.getProductAmountByShoppingCartId(id);
			return new ResponseEntity<List<ProductAmountRequest>>(productamount, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<List<ProductAmountRequest>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}*/
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("totalprice/{id}")
	public ResponseEntity<Double> getTotalPrice(@PathVariable Integer id) {
		try {
			Double totalprice = service.getTotalPrice(id);
			return new ResponseEntity<Double>(totalprice, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<Double>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
}
