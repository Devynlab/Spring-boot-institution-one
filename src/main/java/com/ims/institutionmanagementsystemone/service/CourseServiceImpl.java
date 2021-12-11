package com.ims.institutionmanagementsystemone.service;

import java.util.List;
import java.util.Optional;

import com.ims.institutionmanagementsystemone.model.Course;
import com.ims.institutionmanagementsystemone.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
  @Autowired
  private CourseRepository courseRepo;

  @Override
  public List<Course> listAllCourses() {
    return courseRepo.findAll();
  }

  @Override
  public Course getCourseById(long id) {
    Optional<Course> opt = courseRepo.findById(id);
    Course course = null;
    if (opt.isPresent()) {
      course = opt.get();
    } else {
      throw new RuntimeException("Course with id '" + id + "' not found!");
    }
    return course;
  }

  @Override
  public void saveCourse(Course Course) {
    this.courseRepo.save(Course);
  }

  @Override
  public void deleteCourseById(long id) {
    this.courseRepo.deleteById(id);
  }
}
