package com.santaellamorenofrancisco.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santaellamorenofrancisco.model.Client;

/**
 * Repositorio de clientes
 * @author Francisco Antonio Moreno Santaella
 *
 */
@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, Long> {

	/**
	 * Consulta a la base de datos que trae todos los datos de un cliente segun su uid
	 * @param uid es uid que se genera automaticamente en firebase
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT c.* FROM client c WHERE uid = ?1")
	public Client getUserByUid(@Param("uid") String uid);

	
}
