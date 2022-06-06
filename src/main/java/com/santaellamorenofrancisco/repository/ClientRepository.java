package com.santaellamorenofrancisco.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santaellamorenofrancisco.model.Client;


@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query(nativeQuery = true, value = "SELECT c.* FROM client c WHERE uid = ?1")
	public Client getUserByUid(@Param("uid") String uid);
	
}
