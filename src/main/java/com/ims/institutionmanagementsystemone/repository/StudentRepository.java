package com.ims.institutionmanagementsystemone.repository;

import java.util.List;

import com.ims.institutionmanagementsystemone.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  public List<Student> findByCourseId(Long course_id);
  public List<Student> findByInstitutionId(long institution_id);
}
