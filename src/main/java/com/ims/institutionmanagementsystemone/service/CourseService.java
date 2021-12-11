package com.ims.institutionmanagementsystemone.service;

import java.util.List;

import com.ims.institutionmanagementsystemone.model.Course;

public interface CourseService {
  List<Course> listAllCourses();
  Course getCourseById(long id);
  void saveCourse(Course course);
  void deleteCourseById(long id);
}
