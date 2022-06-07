package com.santaellamorenofrancisco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.santaellamorenofrancisco.model.Product;
import com.santaellamorenofrancisco.service.ProductService;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService service;

	/**
	 * Método que devuelve una lista de productistradores
	 * 
	 * @return Lista de productistradores con un codigo 200 o una respuesta 400 si
	 *         no se ha realizado correctamente si devuelve dicha respuesta
	 *         normalmente será porque no hay productistradores en la base de datos
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping

	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			List<Product> productlist = service.getAllProducts();
			return new ResponseEntity<List<Product>>(productlist, new HttpHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Product>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para buscar un product según su id
	 * 
	 * @param id es el id del productistrador que queremos buscar
	 * @return Un productistrador
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		try {
			Product product = service.getProductById(id);
			return new ResponseEntity<Product>(product, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}


	/**
	 * Método que sirve para borrar un productistrador por su id
	 * 
	 * @param id El id del productistrador que queremos borrar
	 * @return Un código 200 de que la operación se ha realizado o un código 400 si
	 *         ha fallado
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProductById(Long id) {
		try {
			service.deleteProductById(id);
			return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para crear un nuevo productistrador
	 * 
	 * @param Product es el objeto que vamos a pasar para crearlo en la base de
	 *                datos
	 * @return devuelve una respuesta 200 con el productistrador si se ha realizado
	 *         correctamente si no se realiza correctamente devuelve un respuesta
	 *         400
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product Product) {
		if (Product != null) {
			try {
				Product createProduct = service.createProduct(Product);
				return new ResponseEntity<Product>(createProduct, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para cambiar los valores del product en la base de datos
	 * 
	 * @param Product es el objeto que vamos a pasar para actualizarlo en la base de
	 *                datos
	 * @return Una respuesta 200 si se ha realizado correctamente la operación o un
	 *         error 400 si no se ha realizado correctamente
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product Product) {
		if (Product != null && Product.getId() != -1) {
			try {
				service.updateProduct(Product);
				return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Product>(new Product(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<Product>(new Product(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getproduct/{pagenumber}/{pagesize}", method = RequestMethod.GET)
	public ResponseEntity<Page<Product>> getProductByPage(@PathVariable int pagenumber, @PathVariable int pagesize) {
		if (pagenumber >= 0 && pagesize >= 0) {
			try {
				Page<Product> pageproducts = service.getProductByPage(pagenumber, pagesize);
				return new ResponseEntity<Page<Product>>(pageproducts, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Page<Product>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}

		} else {
			return new ResponseEntity<Page<Product>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/getrings/{pagenumber}/{pagesize}", method = RequestMethod.GET)
	public ResponseEntity<Page<Product>> getRingProductsByPage(@PathVariable int pagenumber, @PathVariable int pagesize) {
		if (pagenumber >= 0 && pagesize >= 0) {
			try {
				Page<Product> pageproducts = service.getRingProductsByPage(pagenumber, pagesize);
				return new ResponseEntity<Page<Product>>(pageproducts, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Page<Product>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}

		} else {
			return new ResponseEntity<Page<Product>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/getnecklace/{pagenumber}/{pagesize}", method = RequestMethod.GET)
	public ResponseEntity<Page<Product>> getnecklaceProductsByPage(@PathVariable int pagenumber, @PathVariable int pagesize) {
		if (pagenumber >= 0 && pagesize >= 0) {
			try {
				Page<Product> pageproducts = service.getnecklaceProductsByPage(pagenumber, pagesize);
				return new ResponseEntity<Page<Product>>(pageproducts, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Page<Product>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}

		} else {
			return new ResponseEntity<Page<Product>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}
}
