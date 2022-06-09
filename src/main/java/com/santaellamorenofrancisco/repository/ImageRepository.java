package com.santaellamorenofrancisco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santaellamorenofrancisco.model.Image;

/**
 * Repositorio de imagenes
 * @author Usuario
 *
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	/*@Query("delete from File i where i.uniquename = ?1")
	void deleteByName(@Param("uniquename") String name);*/

	/**
	 * Consulta que devuelve todas las imagenes de la base de datos
	 * @return una lista de imagenes
	 */
	@Query(value = "SELECT i.id,i.originalname,i.uniquename,i.url,i.club_id FROM image i", nativeQuery = true)
	public List<Image> getFilesFromDatabase();

	/**
	 * Consulta que sirve para guardar la informacion de la imagen en la base de datos
	 * @param originalname es el nombre original de la imagen
	 * @param uniquename es el nombre unico autogenerado de la imagen
	 * @param url es el nombre de la carpeta en la que se guardan las imagenes y el nombre unico
	 * @param product_id es el id del producto al que esta vinculada la imagen
	 */
	@Query(value = "insert into image (originalname,uniquename,url,product_id) VALUES (:originalname,:uniquename,:url,:product_id)", nativeQuery = true)
	void insertFile(@Param("originalname") String originalname, @Param("uniquename") String uniquename,
			@Param("url") String url, @Param("product_id") Long product_id);
	
	/**
	 * Consulta que nos trae una imagen segun el id del producto introducido
	 * @param id es el id de producto del que queremos traer su imagen
	 * @return todos los campos de imagen limitado a 1.
	 * 
	 * Cabe recalcar que por como esta diseñada la api un producto podria tener varias imagenes y seria bastante sencillo hacer
	 * un carrucel de imagenes por producto pero he preferido simplificarlo por falta de tiempo
	 */
	@Query(nativeQuery = true, value = "SELECT i.* FROM image i WHERE i.product_id = ?1 ORDER BY i.product_id desc LIMIT 1")
	Image getImgByProductId(@Param("product_id") Long id);
}
