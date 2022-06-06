package com.santaellamorenofrancisco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santaellamorenofrancisco.model.Order;
import com.santaellamorenofrancisco.model.ShoppingCart;
import com.santaellamorenofrancisco.repository.ShoppingCartRepository;
import com.santaellamorenofrancisco.request.ProductAmountRequest;


@Service
public class ShoppingCartService {
	@Autowired
	ShoppingCartRepository repository;

	
	//public static final Logger logger = LoggerFactory.getLogger(ShoppingCartService.class);
	
	

	public List<ShoppingCart> getAllShoppingCarts() throws Exception {
		try {
			List<ShoppingCart> ShoppingCartList = repository.findAll();
			return ShoppingCartList;
		} catch (Exception e) {
			//logger.error("There is no shoppingcarts in the database " + e);
			throw new Exception("No hay shoppingcartes en la base de datos", e);
		}
	}
	
	/**
	 * M�todo que sirve para trar un shoppingcartistraodr seg�n su id
	 * @param id es el id del shoppingcart que queremos buscar
	 * @return Un ShoppingCart
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public ShoppingCart getShoppingCartById(Long id) throws Exception, IllegalArgumentException, NullPointerException {
		if (id != null) {
			try {
				ShoppingCart shoppingcart = repository.getById(id);
				return shoppingcart;
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
	 * M�todo que sirve para crear un nuevo shoppingcart, si el parametro que introducimos tiene un id
	 * que coincide con el de la base de datos dicho shoppingcart 
	 * no se creara si no que se modificar�n sus valores
	 * @param shoppingcart es el shoppingcart que vamos a crear o actualizar
	 * @return Un ShoppingCart
	 * @throws Exception Error si no se ha podido completar la operaci�n
	 * @throws NullPointerException Error que da si el shoppingcart que hemos introducido es nulo
	 */
	public ShoppingCart createShoppingCart(ShoppingCart shoppingcart) throws Exception, NullPointerException {
		if (shoppingcart != null && shoppingcart.getId()==null) {
			try {
				return repository.save(shoppingcart);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else if (shoppingcart != null) {

			try {
				return updateShoppingCart(shoppingcart);
			} catch (Exception e) {
				throw new Exception(e);
			}
		}else {
			//logger.error("NullPointerException shoppingcart equals to null.");
			throw new NullPointerException("El shoppingcart es nulo");
		}
		
	}
	

	/**
	 * M�todo que sirve para actualizar los datos del shoppingcart en la base de datos
	 * @param shoppingcart es el shoppingcart que vamos a actualizar en la base de datos 
	 * @return Un ShoppingCart
	 * @throws Exception Error si no se ha podido realizar la operaci�n
	 */
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingcart) throws Exception {
		if (shoppingcart != null) {
			try {
				return repository.save(shoppingcart);
			} catch (Exception e) {
				//logger.error("Cannot update");
				throw new Exception(e);
			}
		} else {
			//logger.error("NullPointerException in the method updateShoppingCart shoppingcart is null");
			throw new NullPointerException("El shoppingcart es nulo");
		}

	}
	
	
	/**
	 * M�todo que sirve para borrar un shoppingcart introduciendo su id
	 * @param id es el id que vamos a introducir para buscar dicho shoppingcart y borrarlo
	 * @throws NullPointerException Este error ocurre cuando el id es nulo
	 * @throws IllegalArgumentException Este error ocurre cuando pasamos un parametro
	 *  que no es el debido por ejemplo un string cuando debe ser un entero
	 * @throws Exception
	 */
	public void deleteShoppingCartById(Long id) throws NullPointerException, IllegalArgumentException, Exception {
		if (id != null) {
			Optional<ShoppingCart> deleteShoppingCartById;
			try {
				deleteShoppingCartById = Optional.ofNullable(getShoppingCartById(id));
				if (!deleteShoppingCartById.isEmpty()) {
					repository.deleteById(id);
				} else {
					//logger.error("Exception in the method deleteShoppingCartById");
					throw new Exception("El ShoppingCart no existe");
				}
			} catch (IllegalArgumentException e1) {
				//logger.error("Exception in the method deleteShoppingCartById" + e1);
				throw new IllegalArgumentException("El ShoppingCart no existe");
			} catch (NullPointerException e1) {
				//logger.error("Exception in the method deleteShoppingCartById" + e1);
				throw new NullPointerException("El ShoppingCart no existe");
			} catch (Exception e) {
				//logger.error("Exception in the method deleteShoppingCartById" + e);
				throw new Exception("El ShoppingCart no existe", e);
			}
		} else {
			//logger.error("Exception in the method deleteShoppingCartById");
			throw new NullPointerException("El id es nulo");
		}
	}
	
	/*public List<ProductAmountRequest> getProductAmountByShoppingCartId(Long id) throws Exception, IllegalArgumentException, NullPointerException {
		if (id != null) {
			try {
				System.out.println("entro");
				List<ProductAmountRequest> productamount = r.getProductAmountByShoppingCartId(id);
				System.out.println(productamount);
		
					return productamount;
				
			} catch (IllegalArgumentException e) {
				//logger.error("IllegalArgumentException in the method getShoppingCartById: " + e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				//logger.error("Exception in the method getProductAmountByShoppingCartId: " + e);
				throw new Exception(e);
			}
		} else {
			//logger.error("NullPointerException in the method getShoppingCartById id equals to null.");
			throw new NullPointerException("El id es nulo");
		}
	}*/

	public Double getTotalPrice(Integer id) throws Exception, IllegalArgumentException, NullPointerException {
		if (id != null) {
			try {
				System.out.println("entro");
				Double totalprice = repository.getTotalPrice(id);
				System.out.println(totalprice);
		
					return totalprice;
				
			} catch (IllegalArgumentException e) {
				//logger.error("IllegalArgumentException in the method getShoppingCartById: " + e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				//logger.error("Exception in the method getProductAmountByShoppingCartId: " + e);
				throw new Exception(e);
			}
		} else {
			//logger.error("NullPointerException in the method getShoppingCartById id equals to null.");
			throw new NullPointerException("El id es nulo");
		}
	}
	
	

	
}