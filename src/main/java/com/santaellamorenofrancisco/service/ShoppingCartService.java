package com.santaellamorenofrancisco.service;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santaellamorenofrancisco.model.Client;
import com.santaellamorenofrancisco.model.Order;
import com.santaellamorenofrancisco.model.ShoppingCart;
import com.santaellamorenofrancisco.repository.ClientRepository;
import com.santaellamorenofrancisco.repository.ShoppingCartRepository;
import com.santaellamorenofrancisco.request.ProductAmountRequest;

@Service
public class ShoppingCartService {
	@Autowired
	ShoppingCartRepository repository;

	@Autowired
	ClientRepository clientrepository;

	ProductService productservice;
	// public static final Logger logger =
	// LoggerFactory.getLogger(ShoppingCartService.class);

	/**
	 * Metodo que trae todos los carros de la compra
	 * 
	 * @return devuelve una lista de carros de la compra
	 * @throws Exception
	 */
	public List<ShoppingCart> getAllShoppingCarts() throws Exception {
		try {
			List<ShoppingCart> ShoppingCartList = repository.findAll();
			return ShoppingCartList;
		} catch (Exception e) {
			// logger.error("There is no shoppingcarts in the database " + e);
			throw new Exception("No hay shoppingcartes en la base de datos", e);
		}
	}

	/**
	 * Metodo que sirve para trar un shoppingcartistraodr segun su id
	 * 
	 * @param id es el id del shoppingcart que queremos buscar
	 * @return Un ShoppingCart
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public ShoppingCart getShoppingCartById(Long id) throws Exception, IllegalArgumentException, NullPointerException {
		if (id != null) {
			try {
				ShoppingCart shoppingcart = repository.findById(id).get();
				;
				return shoppingcart;
			} catch (IllegalArgumentException e) {
				// logger.error("IllegalArgumentException in the method getShoppingCartById: " +
				// e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				// logger.error("Exception in the method getShoppingCartById: " + e);
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException in the method getShoppingCartById id
			// equals to null.");
			throw new NullPointerException("El id es un objeto nulo");
		}
	}

	/**
	 * Metodo que sirve para crear un nuevo shoppingcart, si el parametro que
	 * introducimos tiene un id que coincide con el de la base de datos dicho
	 * shoppingcart no se creara si no que se modificaran sus valores
	 * 
	 * @param shoppingcart es el shoppingcart que vamos a crear o actualizar
	 * @return Un ShoppingCart
	 * @throws Exception            Error si no se ha podido completar la operacion
	 * @throws NullPointerException Error que da si el shoppingcart que hemos
	 *                              introducido es nulo
	 */
	public ShoppingCart createShoppingCart(ShoppingCart shoppingcart) throws Exception, NullPointerException {
		if (shoppingcart != null && shoppingcart.getId() == null) {
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
		} else {
			// logger.error("NullPointerException shoppingcart equals to null.");
			throw new NullPointerException("El shoppingcart es nulo");
		}

	}

	/**
	 * Metodo que sirve para actualizar los datos del shoppingcart en la base de
	 * datos
	 * 
	 * @param shoppingcart es el shoppingcart que vamos a actualizar en la base de
	 *                     datos
	 * @return Un ShoppingCart
	 * @throws Exception Error si no se ha podido realizar la operacion
	 */
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingcart) throws Exception {
		if (shoppingcart != null) {
			try {
				return repository.save(shoppingcart);
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

	/**
	 * Metodo que sirve para borrar un shoppingcart introduciendo su id
	 * 
	 * @param id es el id que vamos a introducir para buscar dicho shoppingcart y
	 *           borrarlo
	 * @throws NullPointerException     Este error ocurre cuando el id es nulo
	 * @throws IllegalArgumentException Este error ocurre cuando pasamos un
	 *                                  parametro que no es el debido por ejemplo un
	 *                                  string cuando debe ser un entero
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
					// logger.error("Exception in the method deleteShoppingCartById");
					throw new Exception("El ShoppingCart no existe");
				}
			} catch (IllegalArgumentException e1) {
				// logger.error("Exception in the method deleteShoppingCartById" + e1);
				throw new IllegalArgumentException("El ShoppingCart no existe");
			} catch (NullPointerException e1) {
				// logger.error("Exception in the method deleteShoppingCartById" + e1);
				throw new NullPointerException("El ShoppingCart no existe");
			} catch (Exception e) {
				// logger.error("Exception in the method deleteShoppingCartById" + e);
				throw new Exception("El ShoppingCart no existe", e);
			}
		} else {
			// logger.error("Exception in the method deleteShoppingCartById");
			throw new NullPointerException("El id es nulo");
		}
	}

	/**
	 * Metodo que calcula el precio total de los productos que hay en un carro de la
	 * compra
	 * 
	 * @param shoppingcart_id es el id del carro de la compra del que queremos saber
	 *                        el precio total de sus productos
	 * @return devuelve un Double que es el precio total de los productos
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Double getTotalPrice(Long shoppingcart_id) throws Exception, IllegalArgumentException, NullPointerException {
		if (shoppingcart_id != null) {
			try {
				Double totalprice = repository.getTotalPrice(shoppingcart_id);
				return totalprice;

			} catch (IllegalArgumentException e) {
				// logger.error("IllegalArgumentException in the method getShoppingCartById: " +
				// e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				// logger.error("Exception in the method getProductAmountByShoppingCartId: " +
				// e);
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException in the method getShoppingCartById id
			// equals to null.");
			throw new NullPointerException("El id es nulo");
		}
	}

	/**
	 * Metodo que sirve para comprar un carro de la compra se resta el saldo del
	 * cliente segun el precio total del carro y se pone el carro en ispayed = true
	 * 
	 * @param client_id       es el id del cliente que va a pagar el carro de la
	 *                        compra
	 * @param shoppingcart_id es el id del carro que vamos a compra
	 * @return un booleano (true si se ha podido comprar o false si no)
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Boolean payShoppingCart(Long client_id, Long shoppingcart_id)
			throws Exception, IllegalArgumentException, NullPointerException {
		Boolean result = false;
		if (client_id != null && shoppingcart_id != null) {
			try {
				Double clientbalance = repository.payShoppingCart(shoppingcart_id);

				ShoppingCart sc = getShoppingCartById(shoppingcart_id);
				Client c = clientrepository.findById(client_id).get();
				if (clientbalance >= 0) {

					c.setBalance(clientbalance);
					clientrepository.save(c);
					updateShoppingCart(sc);
					sc.setTotalprice(getTotalPrice(shoppingcart_id));
					sc.setIspayed(true);
					repository.save(sc);
					result = true;
					return result;
				} else {
					return result;
				}
			} catch (IllegalArgumentException e) {

				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				// logger.error("Exception in the method getProductAmountByShoppingCartId: " +
				// e);
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException in the method getShoppingCartById id
			// equals to null.");
			throw new NullPointerException("El id es nulo");
		}

	}

	/**
	 * Metodo que trae el id del ultimo carro de la compra de un cliente
	 * 
	 * @param client_id es el id del cliente del que queremos saber su ultimo carro
	 *                  de la compra
	 * @return devuelve el id del carro de la compra
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Integer getLastShoppingCartIdByClientId(Long client_id)
			throws Exception, IllegalArgumentException, NullPointerException {
		if (client_id != null) {
			try {
				Integer shoppingcartid = repository.getLastShoppingCartIdByClientId(client_id);
				return shoppingcartid;
			} catch (IllegalArgumentException e) {
				// logger.error("IllegalArgumentException in the method getShoppingCartById: " +
				// e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				// logger.error("Exception in the method getShoppingCartById: " + e);
				throw new Exception(e);
			}
		} else {
			// logger.error("NullPointerException in the method getShoppingCartById id
			// equals to null.");
			throw new NullPointerException("El id es un objeto nulo");
		}
	}

	/**
	 * Metodo que devuelve el id del ultimo carro de la compra del cliente no
	 * pagado, esto sirve para mostrar su carro no pagado que es el que se muestra
	 * para comprarlo en la app
	 * 
	 * @param client_id es el id del cliente del que queremos traer su ultimo carro
	 *                  del cliente no pagado
	 * @return Un Long que es el id del caro de la compra
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Long getLastShoppingCartIdNotPayedByClientId(Long client_id)
			throws Exception, IllegalArgumentException, NullPointerException {
		if (client_id != null) {
			try {
				Long shoppingcartid = repository.getLastShoppingCartIdNotPayedByClientId(client_id);
				if (shoppingcartid == null) {
					Client c = clientrepository.findById(client_id).get();
					ShoppingCart sc = new ShoppingCart();
					sc.setClient(c);
					sc.setIspayed(false);
					sc = createShoppingCart(sc);
					repository.save(sc);
					return sc.getId();
				} else {
					return shoppingcartid;
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
	 * Metodo que crea un nuevo carro de la compra
	 * 
	 * @param shoppingcart es el id del carro de la compra
	 * @param client_id    es el id del cliente al que se le va a asignar ese carro
	 *                     de la compra
	 * @return un entero que es 1 si se ha insertado o 0 si no
	 * @throws Exception
	 */
	public int insertShoppingcart(ShoppingCart shoppingcart, Long client_id) throws Exception {
		if (shoppingcart != null) {
			try {
				LocalDateTime now = LocalDateTime.now();
				return repository.insertShoppingcart(-1L, now, shoppingcart.getIspayed(), client_id);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else {
			throw new NullPointerException("El shoppingcart es nulo");
		}

	}

}
