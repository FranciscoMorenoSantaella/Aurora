package com.santaellamorenofrancisco.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santaellamorenofrancisco.model.Admin;
import com.santaellamorenofrancisco.model.Product;
import com.santaellamorenofrancisco.repository.AdminRepository;
import com.santaellamorenofrancisco.repository.ImageRepository;
import com.santaellamorenofrancisco.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	AdminRepository adminrepository;

	// public static final Logger logger =
	// LoggerFactory.getLogger(ProductService.class);

	public List<Product> getAllProducts() throws Exception {
		try {
			List<Product> ProductList = repository.findAll();
			return ProductList;
		} catch (Exception e) {
			// logger.error("There is no products in the database " + e);
			throw new Exception("No hay productees en la base de datos", e);
		}
	}

	/**
	 * Método que sirve para trar un producte según su id
	 * 
	 * @param id es el id del producte que queremos buscar
	 * @return Un Product
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Product getProductById(Long id) throws Exception, IllegalArgumentException, NullPointerException {
		if (id != null) {
			try {
				Optional<Product> getProductById = repository.findById(id);
				if (getProductById.isPresent()) {
					return getProductById.get();
				} else {
					// logger.error("The Product doesn't exists in the database.");
					throw new Exception("El Product no existe");
				}
			} catch (IllegalArgumentException e) {
				// logger.error("IllegalArgumentException in the method getProductById: " + e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				// logger.error("Exception in the method getProductById: " + e);
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException in the method getProductById id equals to
			// null.");
			throw new NullPointerException("El id es un objeto nulo");
		}
	}

	/**
	 * Método que sirve para crear un nuevo producte, si el parametro que
	 * introducimos tiene un id que coincide con el de la base de datos dicho
	 * producte no se creara si no que se modificarán sus valores
	 * 
	 * @param product es el producte que vamos a crear o actualizar
	 * @return Un Product
	 * @throws Exception            Error si no se ha podido completar la operación
	 * @throws NullPointerException Error que da si el producte que hemos
	 *                              introducido es nulo
	 */
	@Transactional
	public Product createProduct(Product product) throws Exception, NullPointerException {
		if (product != null && product.getId() == null) {
			try {
				product.setCreation_date(LocalDateTime.now());
				return repository.save(product);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else if (product != null) {

			try {
				return updateProduct(product);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException product equals to null.");
			throw new NullPointerException("El product es nulo");
		}

	}

	/**
	 * Método que sirve para actualizar los datos del producte en la base de datos
	 * 
	 * @param product es el producte que vamos a actualizar en la base de datos
	 * @return Un Product
	 * @throws Exception Error si no se ha podido realizar la operación
	 */
	public Product updateProduct(Product product) throws Exception {
		if (product != null) {
			try {
				return repository.save(product);
			} catch (Exception e) {
				// logger.error("Cannot update");
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException in the method updateProduct product is
			// null");
			throw new NullPointerException("El producte es nulo");
		}

	}

	/**
	 * Método que sirve para borrar un producte introduciendo su id
	 * 
	 * @param id es el id que vamos a introducir para buscar dicho producte y
	 *           borrarlo
	 * @throws NullPointerException     Este error ocurre cuando el id es nulo
	 * @throws IllegalArgumentException Este error ocurre cuando pasamos un
	 *                                  parametro que no es el debido por ejemplo un
	 *                                  string cuando debe ser un entero
	 * @throws Exception
	 */
	public void deleteProductById(Long id) throws NullPointerException, IllegalArgumentException, Exception {
		if (id != null) {
			Optional<Product> deleteProductById;
			try {
				deleteProductById = Optional.ofNullable(getProductById(id));
				if (!deleteProductById.isEmpty()) {
					repository.deleteById(id);
				} else {
					// logger.error("Exception in the method deleteProductById");
					throw new Exception("El Product no existe");
				}
			} catch (IllegalArgumentException e1) {
				// logger.error("Exception in the method deleteProductById" + e1);
				throw new IllegalArgumentException("El Product no existe");
			} catch (NullPointerException e1) {
				// logger.error("Exception in the method deleteProductById" + e1);
				throw new NullPointerException("El Product no existe");
			} catch (Exception e) {
				// logger.error("Exception in the method deleteProductById" + e);
				throw new Exception("El Product no existe", e);
			}
		} else {
			// logger.error("Exception in the method deleteProductById");
			throw new NullPointerException("El id es nulo");
		}
	}

	/**
	 * Metodo que trae los productos de forma paginada
	 * @param pagenumber es la pagina de los productos que queremos traer 
	 * @param pagesize es el tamano de la pagina que queremos
	 * por ejemplo la pagina 0 con limite 10 traera los productos del 1 al 9
	 * @return una pagina de productos
	 * @throws Exception
	 */
	public Page<Product> getProductByPage(int pagenumber, int pagesize) throws Exception {

		if (pagenumber >= 0 && pagesize >= 0) {
			try {
				Sort sort = Sort.by(Sort.Direction.ASC, "id");
				Pageable pageable = PageRequest.of(pagenumber, pagesize, sort);
				return repository.getProductsByPage(pageable);
			} catch (Exception e) {
				throw new Exception("Error en la consulta", e);
			}

		} else {
			throw new Exception("El numero de pagina y/o el limite no puede ser menor que 0");
		}

	}

	/**
	 * Metodo que trae los productos de tipo anillo de forma paginada
	 * @param pagenumber es la pagina de los productos que queremos traer 
	 * @param pagesize es el tamano de la pagina que queremos
	 * por ejemplo la pagina 0 con limite 10 traera los productos del 1 al 9
	 * @return una pagina de productos
	 * @throws Exception
	 */
	public Page<Product> getRingProductsByPage(int pagenumber, int pagesize) throws Exception {

		if (pagenumber >= 0 && pagesize >= 0) {
			try {
				Sort sort = Sort.by(Sort.Direction.ASC, "id");
				Pageable pageable = PageRequest.of(pagenumber, pagesize, sort);
				return repository.getRingProductsByPage(pageable);
			} catch (Exception e) {
				throw new Exception("Error en la consulta", e);
			}

		} else {
			throw new Exception("El numero de pagina y/o el limite no puede ser menor que 0");
		}

	}

	/**
	 * Metodo que trae los productos de tipo collar de forma paginada
	 * @param pagenumber es la pagina de los productos que queremos traer 
	 * @param pagesize es el tamano de la pagina que queremos
	 * por ejemplo la pagina 0 con limite 10 traera los productos del 1 al 9
	 * @return una pagina de productos
	 * @throws Exception
	 */
	public Page<Product> getnecklaceProductsByPage(int pagenumber, int pagesize) throws Exception {

		if (pagenumber >= 0 && pagesize >= 0) {
			try {
				Sort sort = Sort.by(Sort.Direction.ASC, "id");
				Pageable pageable = PageRequest.of(pagenumber, pagesize, sort);
				return repository.getnecklaceProductsByPage(pageable);
			} catch (Exception e) {
				throw new Exception("Error en la consulta", e);
			}

		} else {
			throw new Exception("El numero de pagina y/o el limite no puede ser menor que 0");
		}

	}

	/**
	 * Metodo que resta una cantidad de un producto en stock
	 * @param amount es la cantidad que queremos restar
	 * @param product_id es el id del producto que vamos a restar
	 * @return un booleano (true si se resta o false si no hay suficiente stock y no se resta)
	 * @throws Exception
	 */
	public Boolean subtractStock(Long amount, Long product_id) throws Exception {
		Boolean result = false;
		if (product_id != null) {
			try {
				Integer cantidad = repository.subtractStock(amount, product_id);
				if (cantidad >= 0) {
					try {
						Product p = getProductById(product_id);
						p.setStock(cantidad);
						repository.save(p);
						result = true;
						return result;
					} catch (Exception e) {
						throw new Exception("Error en la consulta", e);
					}

				} else {
					result = false;
					return result;
				}
			} catch (Exception e) {
				// logger.error("Cannot update");
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException in the method updateShoppingCart
			// shoppingcart is null");
			throw new NullPointerException("El shoppingcart es nulo");
		}

	}

}
