package com.santaellamorenofrancisco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santaellamorenofrancisco.model.Admin;
import com.santaellamorenofrancisco.service.AdminService;


@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService service;

	/**
	 * Metodo que devuelve una lista de administradores
	 * 
	 * @return Lista de administradores con un codigo 200 o una respuesta 400 si no
	 *         se ha realizado correctamente si devuelve dicha respuesta normalmente
	 *         sera porque no hay administradores en la base de datos
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping

	public ResponseEntity<List<Admin>> getAllAdmins() {
		try {
			List<Admin> adminlist = service.getAllAdmins();
			return new ResponseEntity<List<Admin>>(adminlist, new HttpHeaders(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Admin>>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Metodo que sirve para buscar un administrador segun su id
	 * 
	 * @param id es el id del administrador que queremos buscar
	 * @return Un administrador
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/{id}")
	public ResponseEntity<Admin> getAdminById(Long id) {
		try {
			Admin admin = service.getAdminById(id);
			return new ResponseEntity<Admin>(admin, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Metodo que sirve para borrar un administrador por su id
	 * 
	 * @param id El id del administrador que queremos borrar
	 * @return Un codigo 200 de que la operacion se ha realizado o un codigo 400 si
	 *         ha fallado
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@DeleteMapping("/{id}")
	public ResponseEntity<Admin> deleteAdminById(Long id) {
		try {
			service.deleteAdminById(id);
			return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Metodo que sirve para crear un nuevo administrador
	 * 
	 * @param Admin es el objeto que vamos a pasar para crearlo en la base de datos
	 * @return devuelve una respuesta 200 con el administrador si se ha realizado
	 *         correctamente si no se realiza correctamente devuelve un respuesta
	 *         400
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Admin> createAdmin(@RequestBody Admin Admin) {
		if (Admin != null) {
			try {
				Admin createAdmin = service.createAdmin(Admin);
				return new ResponseEntity<Admin>(createAdmin, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Metodo que sirve para cambiar los valores del administrador en la base de
	 * datos
	 * 
	 * @param Admin es el objeto que vamos a pasar para actualizarlo en la base de
	 *              datos
	 * @return Una respuesta 200 si se ha realizado correctamente la operacion o un
	 *         error 400 si no se ha realizado correctamente
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@PutMapping
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin Admin) {
		if (Admin != null && Admin.getId() != -1) {
			try {
				service.updateAdmin(Admin);
				return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Admin>(new Admin(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<Admin>(new Admin(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
}
