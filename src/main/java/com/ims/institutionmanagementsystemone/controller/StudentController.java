package com.ims.institutionmanagementsystemone.controller;

import java.util.ArrayList;
import java.util.List;

import com.ims.institutionmanagementsystemone.model.Course;
import com.ims.institutionmanagementsystemone.model.Institution;
import com.ims.institutionmanagementsystemone.model.Student;
import com.ims.institutionmanagementsystemone.service.CourseService;
import com.ims.institutionmanagementsystemone.service.InstitutionService;
import com.ims.institutionmanagementsystemone.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
  @Autowired
  private StudentService studentService;
  @Autowired
  private InstitutionService institutionService;
  @Autowired
  private CourseService courseService;

  private static List<String> gender;
  static {
    gender = new ArrayList<>();
    gender.add("Male");
    gender.add("Female");
  }

  @GetMapping("/students")
  public String studentListView(Model model) {
    model.addAttribute("studentList", studentService.listAllStudents());
    return "student_list";
  }

  @GetMapping("/students/createStudentForm")
  public String createStudentForm(Model model) {
    Student student = new Student();
    ArrayList<Institution> institutions = (ArrayList<Institution>) institutionService.listAllInstitutions();
    ArrayList<Course> courses = (ArrayList<Course>) courseService.listAllCourses();
    model.addAttribute("student", student);
    model.addAttribute("gender", gender);
    model.addAttribute("institutions", institutions);
    model.addAttribute("courses", courses);
    return "create_student_form";
  }

  @PostMapping("/students/saveStudent")
  public String saveStudent(@ModelAttribute("student") Student student) {
    studentService.saveStudent(student);
    return "redirect:/students";
  }

  @GetMapping("/students/updateStudentForm/{id}")
  public String updateStudentForm(@PathVariable(value = "id") long id, Model model) {
    Student student = studentService.getStudentById(id);
    ArrayList<Institution> institutions = (ArrayList<Institution>) institutionService.listAllInstitutions();
    ArrayList<Course> courses = (ArrayList<Course>) courseService.listAllCourses();
    model.addAttribute("student", student);
    model.addAttribute("gender", gender);
    model.addAttribute("institutions", institutions);
    model.addAttribute("courses", courses);
    return "update_student_form";
  }

  @GetMapping("/students/deleteStudent/{id}")
  public String deleteStudent(@PathVariable(value = "id") long id) {
    this.studentService.deleteStudentById(id);
    return "redirect:/students";
  }
}
