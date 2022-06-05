package com.santaellamorenofrancisco.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.santaellamorenofrancisco.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(nativeQuery = true, value = "SELECT p.* FROM product p ORDER BY creation_date desc")
	Page<Product> getProductsByPage(Pageable var1);
	
	@Query(nativeQuery = true, value = "SELECT p.* FROM product p WHERE p.type = 'anillo' ORDER BY creation_date desc")
	Page<Product> getRingProductsByPage(Pageable var1);
	
	@Query(nativeQuery = true, value = "SELECT p.* FROM product p WHERE p.type = 'collar' ORDER BY creation_date desc")
	Page<Product> getnecklaceProductsByPage(Pageable var1);
}
