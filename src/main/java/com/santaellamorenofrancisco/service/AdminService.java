package com.santaellamorenofrancisco.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santaellamorenofrancisco.model.Admin;
import com.santaellamorenofrancisco.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository repository;
	
	//public static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	
	/**
	 * Metodo que trae una lista con todos los administradores
	 * @return devuelve una lista de administradores
	 * @throws Exception
	 */
	public List<Admin> getAllAdmins() throws Exception {
		try {
			List<Admin> AdminList = repository.findAll();
			return AdminList;
		} catch (Exception e) {
			throw new Exception("No hay administradores en la base de datos", e);
		}
	}
	
	/**
	 * Metodo que sirve para trar un administraodr según su id
	 * @param id es el id del administrador que queremos buscar
	 * @return Un Admin
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Admin getAdminById(Long id) throws Exception, IllegalArgumentException, NullPointerException {
		if (id != null) {
			try {
				Optional<Admin> getAdminById = repository.findById(id);
				if (getAdminById.isPresent()) {
					return getAdminById.get();
				} else {
					throw new Exception("El Admin no existe");
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
	 * Método que sirve para crear un nuevo administrador, si el parametro que introducimos tiene un id
	 * que coincide con el de la base de datos dicho administrador 
	 * no se creara si no que se modificarán sus valores
	 * @param admin es el administrador que vamos a crear o actualizar
	 * @return Un Admin
	 * @throws Exception Error si no se ha podido completar la operación
	 * @throws NullPointerException Error que da si el administrador que hemos introducido es nulo
	 */
	public Admin createAdmin(Admin admin) throws Exception, NullPointerException {
		if (admin != null && admin.getId()==null) {
			try {
				return repository.save(admin);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else if (admin != null) {

			try {
				return updateAdmin(admin);
			} catch (Exception e) {
				throw new Exception(e);
			}
		}else {
			throw new NullPointerException("El admin es nulo");
		}
		
	}
	

	/**
	 * Método que sirve para actualizar los datos del administrador en la base de datos
	 * @param admin es el administrador que vamos a actualizar en la base de datos 
	 * @return Un Admin
	 * @throws Exception Error si no se ha podido realizar la operación
	 */
	public Admin updateAdmin(Admin admin) throws Exception {
		if (admin != null) {
			try {
				return repository.save(admin);
			} catch (Exception e) {
				throw new Exception(e);
			}
		} else {
			throw new NullPointerException("El administrador es nulo");
		}

	}
	
	
	/**
	 * Método que sirve para borrar un administrador introduciendo su id
	 * @param id es el id que vamos a introducir para buscar dicho administrador y borrarlo
	 * @throws NullPointerException Este error ocurre cuando el id es nulo
	 * @throws IllegalArgumentException Este error ocurre cuando pasamos un parametro
	 *  que no es el debido por ejemplo un string cuando debe ser un entero
	 * @throws Exception
	 */
	public void deleteAdminById(Long id) throws NullPointerException, IllegalArgumentException, Exception {
		if (id != null) {
			Optional<Admin> deleteAdminById;
			try {
				deleteAdminById = Optional.ofNullable(getAdminById(id));
				if (!deleteAdminById.isEmpty()) {
					repository.deleteById(id);
				} else {
					
					throw new Exception("El Admin no existe");
				}
			} catch (IllegalArgumentException e1) {

				throw new IllegalArgumentException("El Admin no existe");
			} catch (NullPointerException e1) {

				throw new NullPointerException("El Admin no existe");
			} catch (Exception e) {

				throw new Exception("El Admin no existe", e);
			}
		} else {

			throw new NullPointerException("El id es nulo");
		}
	}
	
}
