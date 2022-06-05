package com.santaellamorenofrancisco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santaellamorenofrancisco.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
