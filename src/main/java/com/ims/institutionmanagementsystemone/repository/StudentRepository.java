package com.ims.institutionmanagementsystemone.repository;

import com.ims.institutionmanagementsystemone.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {}
