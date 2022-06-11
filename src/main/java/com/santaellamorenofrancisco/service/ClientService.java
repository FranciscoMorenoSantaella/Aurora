package com.santaellamorenofrancisco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santaellamorenofrancisco.model.Client;
import com.santaellamorenofrancisco.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	ClientRepository repository;
	
	
	

	/**
	 * Metodo trae una lista con todos los clientes
	 * @return devuelve una lista con todos los clientes
	 * @throws Exception
	 */
	public List<Client> getAllClients() throws Exception {
		try {
			List<Client> ClientList = repository.findAll();
			return ClientList;
		} catch (Exception e) {
			//logger.error("There is no clients in the database " + e);
			throw new Exception("No hay clientees en la base de datos", e);
		}
	}
	
	/**
	 * Metodo que sirve para trar un cliente segun su id
	 * @param id es el id del cliente que queremos buscar
	 * @return Un Client
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Client getClientById(Long id) throws Exception, IllegalArgumentException, NullPointerException {
		if (id != null) {
			try {
				Optional<Client> getClientById = repository.findById(id);
				if (getClientById.isPresent()) {
					return getClientById.get();
				} else {
					//logger.error("The Client doesn't exists in the database.");
					throw new Exception("El Client no existe");
				}
			} catch (IllegalArgumentException e) {
				//logger.error("IllegalArgumentException in the method getClientById: " + e);
				throw new IllegalArgumentException(e);
			} catch (Exception e) {
				//logger.error("Exception in the method getClientById: " + e);
				throw new Exception(e);
			}
		} else {
			//logger.error("NullPointerException in the method getClientById id equals to null.");
			throw new NullPointerException("El id es un objeto nulo");
		}
	}
	
	public Client getClientByUid(String uid) throws Exception, IllegalArgumentException, NullPointerException {
		if (uid != null) {
			try {
				Client client = repository.getUserByUid(uid);
				if (client !=null) {
					return client;
				} else {

					throw new Exception("El User no existe");
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
	 * Metodo que sirve para crear un nuevo cliente, si el parametro que introducimos tiene un id
	 * que coincide con el de la base de datos dicho cliente 
	 * no se creara si no que se modificaran sus valores
	 * @param client es el cliente que vamos a crear o actualizar
	 * @return Un Client
	 * @throws Exception Error si no se ha podido completar la operación
	 * @throws NullPointerException Error que da si el cliente que hemos introducido es nulo
	 */
	public Client createClient(Client client) throws Exception, NullPointerException {
		if (client != null && client.getId()==null) {
			try {
				return repository.save(client);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else if (client != null) {

			try {
				return updateClient(client);
			} catch (Exception e) {
				throw new Exception(e);
			}
		}else {
			throw new NullPointerException("El client es nulo");
		}
		
	}
	

	/**
	 * Metodo que sirve para actualizar los datos del cliente en la base de datos
	 * @param client es el cliente que vamos a actualizar en la base de datos 
	 * @return Un Client
	 * @throws Exception Error si no se ha podido realizar la operación
	 */
	public Client updateClient(Client client) throws Exception {
		if (client != null) {
			try {
				return repository.save(client);
			} catch (Exception e) {
				//logger.error("Cannot update");
				throw new Exception(e);
			}
		} else {
			//logger.error("NullPointerException in the method updateClient client is null");
			throw new NullPointerException("El cliente es nulo");
		}

	}
	
	
	/**
	 * Metodo que sirve para borrar un cliente introduciendo su id
	 * @param id es el id que vamos a introducir para buscar dicho cliente y borrarlo
	 * @throws NullPointerException Este error ocurre cuando el id es nulo
	 * @throws IllegalArgumentException Este error ocurre cuando pasamos un parametro
	 *  que no es el debido por ejemplo un string cuando debe ser un entero
	 * @throws Exception
	 */
	public void deleteClientById(Long id) throws NullPointerException, IllegalArgumentException, Exception {
		if (id != null) {
			Optional<Client> deleteClientById;
			try {
				deleteClientById = Optional.ofNullable(getClientById(id));
				if (!deleteClientById.isEmpty()) {
					repository.deleteById(id);
				} else {
					throw new Exception("El Client no existe");
				}
			} catch (IllegalArgumentException e1) {
				throw new IllegalArgumentException("El Client no existe");
			} catch (NullPointerException e1) {
				throw new NullPointerException("El Client no existe");
			} catch (Exception e) {
				throw new Exception("El Client no existe", e);
			}
		} else {
			throw new NullPointerException("El id es nulo");
		}
	}
}
