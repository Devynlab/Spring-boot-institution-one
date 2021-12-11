package com.ims.institutionmanagementsystemone.service;

import java.util.List;
import java.util.Optional;

import com.ims.institutionmanagementsystemone.model.Student;
import com.ims.institutionmanagementsystemone.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
  @Autowired
  private StudentRepository studentRepo;

  @Override
  public List<Student> listAllStudents() {
    return studentRepo.findAll();
  }

  @Override
  public Student getStudentById(long id) {
    Optional<Student> opt = studentRepo.findById(id);
    Student student = null;
    if (opt.isPresent()) {
      student = opt.get();
    } else {
      throw new RuntimeException("Student with id '" + id + "' not found!");
    }
    return student;
  }

  @Override
  public void saveStudent(Student student) {
    this.studentRepo.save(student);
  }

  @Override
  public void deleteStudentById(long id) {
    this.studentRepo.deleteById(id);
    ;
  }
}
