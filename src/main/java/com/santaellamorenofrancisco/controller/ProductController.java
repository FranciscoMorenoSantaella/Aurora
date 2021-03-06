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
	 * Metodo que devuelve una lista de productos
	 * 
	 * @return Lista de productos con un codigo 200 o una respuesta 400 si no se ha
	 *         realizado correctamente si devuelve dicha respuesta normalmente sera
	 *         porque no hay productos en la base de datos
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
	 * Metodo que sirve para buscar un producto segun su id
	 * 
	 * @param id es el id del producto que queremos buscar
	 * @return Un producto
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
	 * Metodo que sirve para borrar un producto por su id
	 * 
	 * @param id El id del producto que queremos borrar
	 * @return Un codigo 200 de que la operacion se ha realizado o un codigo 400 si
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
	 * Metodo que sirve para crear un nuevo producto
	 * 
	 * @param Product es el objeto que vamos a pasar para crearlo en la base de
	 *                datos
	 * @return devuelve una respuesta 200 con el producto si se ha realizado
	 *         correctamente si no se realiza correctamente devuelve un respuesta
	 *         400
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		if (product != null) {
			try {
				Product createProduct = service.createProduct(product);
				return new ResponseEntity<Product>(createProduct, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Metodo que sirve para cambiar los valores del producto en la base de datos
	 * 
	 * @param Product es el objeto que vamos a pasar para actualizarlo en la base de
	 *                datos
	 * @return Una respuesta 200 si se ha realizado correctamente la operacion o un
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

	/**
	 * Metodo que devuelve una lista de productos de forma paginada
	 * 
	 * @param pagenumber es el numero de la pagina de los productos que queremos
	 *                   traer
	 * @param pagesize   es el tamano que tiene dicha pagina por ejemplo si el
	 *                   tamano es 10 trae 10 productos
	 * @return devuelve una pagina de productos
	 */
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

	/**
	 * Metodo que devuelve una pagina de productos que son anillos (de tipo anillos)
	 * 
	 * @param pagenumber es el numero de la pagina de los productos que queremos
	 *                   traer
	 * @param pagesize   es el tamano que tiene dicha pagina por ejemplo si el
	 *                   tamano es 10 trae 10 productos
	 * 
	 * @return una page de productos 
	 */
	@RequestMapping(value = "/getrings/{pagenumber}/{pagesize}", method = RequestMethod.GET)
	public ResponseEntity<Page<Product>> getRingProductsByPage(@PathVariable int pagenumber,
			@PathVariable int pagesize) {
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

	/**
	 * Metodo que devuelve una pagina de productos que son collares (de tipo collar)
	 * 
	 * @param pagenumber es el numero de la pagina de los productos que queremos
	 *                   traer
	 * @param pagesize   es el tamano que tiene dicha pagina por ejemplo si el
	 *                   tamano es 10 trae 10 productos
	 * 
	 * @return una page de productos 
	 */
	@RequestMapping(value = "/getnecklace/{pagenumber}/{pagesize}", method = RequestMethod.GET)
	public ResponseEntity<Page<Product>> getnecklaceProductsByPage(@PathVariable int pagenumber,
			@PathVariable int pagesize) {
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
	
	/**
	 * Metodo que resta la cantidad de stock de producto
	 * @param amount es la cantidad a restar
	 * @param product_id es el id del producto que queremos restar
	 * @return si se resta delvuelve true si no devuelve false
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/subtrackstock/{amount}/{product_id}")
	public ResponseEntity<Boolean> subtractStock(@PathVariable Long amount, @PathVariable  Long product_id) {
		try {
			Boolean result = service.subtractStock(amount,product_id );
			return new ResponseEntity<Boolean>(result, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<Boolean>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
}
