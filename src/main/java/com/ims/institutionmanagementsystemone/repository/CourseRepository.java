package com.ims.institutionmanagementsystemone.repository;

import java.util.List;

import com.ims.institutionmanagementsystemone.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  public List<Course> findByInstitutionId(Long institution_id);
}
