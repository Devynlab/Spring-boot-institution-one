package com.ims.institutionmanagementsystemone.service;

import java.util.List;

import com.ims.institutionmanagementsystemone.model.Student;

public interface StudentService {
  List<Student> listAllStudents();
  Student getStudentById(long id);
  void saveStudent(Student student);
  void deleteStudentById(long id);
}
