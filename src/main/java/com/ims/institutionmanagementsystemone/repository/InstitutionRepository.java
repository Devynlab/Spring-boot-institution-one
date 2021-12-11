package com.ims.institutionmanagementsystemone.repository;

import com.ims.institutionmanagementsystemone.model.Institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {}
