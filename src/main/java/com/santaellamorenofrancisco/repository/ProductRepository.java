package com.santaellamorenofrancisco.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santaellamorenofrancisco.model.Product;
@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	/**
	 * Consulta que trae la lista de productos de forma paginada 
	 * @param var1 es la variable que contiene el numero de la pagina de la consulta que queremos traer y su limite
	 * por ejemplo funciona de tal manera que si pido la pagina 0 con limite 10 me traera los 10 primeros productos (del 1 al 10)
	 * pero si pido la pagina 1 con limite 10 me traera los productos (del 11 al 21)
	 * @return una pagina de productos
	 */
	@Query(nativeQuery = true, value = "SELECT p.* FROM product p ORDER BY creation_date desc")
	Page<Product> getProductsByPage(Pageable var1);
	
	/**
	 * Consulta que trae la lista de productos de tipo anillo  de forma paginada 
	 * @param var1 es la variable que contiene el numero de la pagina de la consulta que queremos traer y su limite
	 * por ejemplo funciona de tal manera que si pido la pagina 0 con limite 10 me traera los 10 primeros productos (del 1 al 10)
	 * pero si pido la pagina 1 con limite 10 me traera los productos (del 11 al 21)
	 * @return una pagina de productos
	 */
	@Query(nativeQuery = true, value = "SELECT p.* FROM product p WHERE p.type = 'anillo' ORDER BY creation_date desc")
	Page<Product> getRingProductsByPage(Pageable var1);
	
	/**
	 * Consulta que trae la lista de productos de tipo collar de forma paginada 
	 * @param var1 es la variable que contiene el numero de la pagina de la consulta que queremos traer y su limite
	 * por ejemplo funciona de tal manera que si pido la pagina 0 con limite 10 me traera los 10 primeros productos (del 1 al 10)
	 * pero si pido la pagina 1 con limite 10 me traera los productos (del 11 al 21)
	 * @return una pagina de productos
	 */
	@Query(nativeQuery = true, value = "SELECT p.* FROM product p WHERE p.type = 'collar' ORDER BY creation_date desc")
	Page<Product> getnecklaceProductsByPage(Pageable var1);
	

}
