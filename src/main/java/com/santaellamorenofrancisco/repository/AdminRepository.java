package com.santaellamorenofrancisco.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santaellamorenofrancisco.model.Admin;
@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
