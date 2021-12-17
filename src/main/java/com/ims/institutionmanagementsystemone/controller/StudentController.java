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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {
  @Autowired
  private StudentService studentService;
  @Autowired
  private InstitutionService institutionService;
  @Autowired
  private CourseService courseService;

  // Gender options
  private static List<String> gender;
  static {
    gender = new ArrayList<>();
    gender.add("Male");
    gender.add("Female");
  }

  // List all students
  @GetMapping("/students")
  public String studentListView(Model model) {
    model.addAttribute("studentList", studentService.listAllStudents());
    return "student_list";
  }

  // List all students doing a specific course
  @RequestMapping(value = "/students/course/{id}")
  public String getCoursesByInstitutionId(@PathVariable long id, Model model) {
    model.addAttribute("studentList", studentService.getStudentsByCourseId(id));
    return "student_list";
  }

  // List all students in a given institution
  @RequestMapping(value = "/students/institution/{id}")
  public String getStudentsByInstitutionId(@PathVariable long id, Model model) {
    // model.addAttribute("studentList", studentService.getStudentsByInstitutionId(id));
    return "student_list";
  }

  // Display form for adding a new student
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

  // Saving a new student and updating a student
  @PostMapping("/students/saveStudent")
  public String saveStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
    try {
      studentService.saveStudent(student);
      redirectAttributes.addFlashAttribute("message", "Success 👍");
      redirectAttributes.addFlashAttribute("alertClass", "alert-success");
    } catch (DataIntegrityViolationException exception) {
      redirectAttributes.addFlashAttribute("message", "Failed! Student with Reg. Number '" + student.getRegNum() + "' already exists.");
      redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
    }
    return "redirect:/students";
  }

  // Display form for updating a student
  @GetMapping("/students/updateStudentForm/{id}")
  public String updateStudentForm(@PathVariable(value = "id") long id, Model model) {
    ArrayList<Course> courses = (ArrayList<Course>) courseService.listAllCourses();
    Student student = studentService.getStudentById(id);
    model.addAttribute("student", student);
    model.addAttribute("courses", courses);
    model.addAttribute("gender", gender);
    return "update_student_form";
  }

  // Deleting a student
  @GetMapping("/students/deleteStudent/{id}")
  public String deleteStudent(@PathVariable(value = "id") long id) {
    this.studentService.deleteStudentById(id);
    return "redirect:/students";
  }
}
