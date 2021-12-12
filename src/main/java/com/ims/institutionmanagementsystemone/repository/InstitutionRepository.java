package com.ims.institutionmanagementsystemone.repository;

import com.ims.institutionmanagementsystemone.model.Institution;

import org.springframework.data.jpa.repository.JpaRepository;

// @Repository  // Optional
public interface InstitutionRepository extends JpaRepository<Institution, Long> {}
