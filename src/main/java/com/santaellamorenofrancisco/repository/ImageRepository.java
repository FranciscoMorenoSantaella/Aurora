package com.santaellamorenofrancisco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santaellamorenofrancisco.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	/*@Query("delete from File i where i.uniquename = ?1")
	void deleteByName(@Param("uniquename") String name);*/

	@Query(value = "SELECT i.id,i.originalname,i.uniquename,i.url,i.club_id FROM image i", nativeQuery = true)
	public List<Image> getFilesFromDatabase();

	@Query(value = "insert into image (originalname,uniquename,url,product_id) VALUES (:originalname,:uniquename,:url,:product_id)", nativeQuery = true)
	void insertFile(@Param("originalname") String originalname, @Param("uniquename") String uniquename,
			@Param("url") String url, @Param("product_id") Long product_id);
	
	@Query(nativeQuery = true, value = "SELECT i.* FROM image i WHERE i.product_id = ?1 ORDER BY i.product_id desc LIMIT 1")
	Image getImgByProductId(@Param("product_id") Long id);
}
