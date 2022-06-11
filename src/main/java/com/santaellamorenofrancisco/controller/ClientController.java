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

import com.santaellamorenofrancisco.model.Client;
import com.santaellamorenofrancisco.service.ClientService;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	ClientService service;

	/**
	 * Método que devuelve una lista de clientes
	 * 
	 * @return Lista de clientes con un codigo 200 o una respuesta 400 si no
	 *         se ha realizado correctamente si devuelve dicha respuesta normalmente
	 *         será porque no hay clientes en la base de datos
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping

	public ResponseEntity<List<Client>> getAllClients() {
		try {
			List<Client> clientlist = service.getAllClients();
			return new ResponseEntity<List<Client>>(clientlist, new HttpHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Client>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para buscar un cliente según su id
	 * 
	 * @param id es el id del cliente que queremos buscar
	 * @return Un codigo 200 con un cliente si se ha realizado la operacion correctamente o un codigo 400 si no se ha realizado
	 * correctamente
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable Long id) {
		try {
			Client client = service.getClientById(id);
			return new ResponseEntity<Client>(client, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<Client>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Metodo que devuelve un cliente introduciendo su uid
	 * @param uid es uid que traemos de firebase
	 * @return devuelve una respuesta 200 con el cliente si la operacion se ha realizado con exito o una respuesta 400 en caso
	 * de que no se haya realizado con exito
	 */
	@GetMapping("/getclientbyuid/{uid}")
	public ResponseEntity<Client> getUserByUid(@PathVariable String uid) {
		try {
			Client client = service.getClientByUid(uid);
			return new ResponseEntity<Client>(client, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<Client>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para borrar un cliente por su id
	 * 
	 * @param id El id del cliente que queremos borrar
	 * @return Un código 200 de que la operación se ha realizado o un código 400 si
	 *         ha fallado
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@DeleteMapping("/{id}")
	public ResponseEntity<Client> deleteClientById(@PathVariable Long id) {
		try {
			service.deleteClientById(id);
			return new ResponseEntity<Client>(new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Client>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para crear un nuevo cliente
	 * 
	 * @param Client es el objeto que vamos a pasar para crearlo en la base de datos
	 * @return devuelve una respuesta 200 con el cliente si se ha realizado
	 *         correctamente si no se realiza correctamente devuelve un respuesta
	 *         400
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Client> createClient(@RequestBody Client Client) {
		if (Client != null) {
			try {
				Client createClient = service.createClient(Client);
				return new ResponseEntity<Client>(createClient, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Client>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<Client>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método que sirve para cambiar los valores del cliente en la base de
	 * datos
	 * 
	 * @param Client es el objeto que vamos a pasar para actualizarlo en la base de
	 *              datos
	 * @return Una respuesta 200 si se ha realizado correctamente la operación o un
	 *         error 400 si no se ha realizado correctamente
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@PutMapping
	public ResponseEntity<Client> updateClient(@RequestBody Client Client) {
		if (Client != null && Client.getId() != -1) {
			try {
				service.updateClient(Client);
				return new ResponseEntity<Client>(new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Client>(new Client(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<Client>(new Client(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

}
