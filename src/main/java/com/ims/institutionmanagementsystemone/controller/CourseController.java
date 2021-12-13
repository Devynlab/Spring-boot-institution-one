package com.ims.institutionmanagementsystemone.controller;

import java.util.ArrayList;

import com.ims.institutionmanagementsystemone.model.Course;
import com.ims.institutionmanagementsystemone.model.Institution;
import com.ims.institutionmanagementsystemone.service.CourseService;
import com.ims.institutionmanagementsystemone.service.InstitutionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {
  @Autowired
  private CourseService courseService;
  @Autowired
  private InstitutionService institutionService;

  @GetMapping("/courses")
  public String courseListView(Model model) {
    model.addAttribute("courseList", courseService.listAllCourses());
    return "course_list";
  }

  @GetMapping("/courses/createCourseForm")
  public String createCourseForm(Model model) {
    Course course = new Course();
    ArrayList<Institution> institutions = (ArrayList<Institution>) institutionService.listAllInstitutions();
    // System.out.println(institutions);
    model.addAttribute("course", course);
    model.addAttribute("institutions", institutions);
    return "create_course_form";
  }

  @PostMapping("/courses/saveCourse")
  public String saveCourse(@ModelAttribute("course") Course course) {
    courseService.saveCourse(course);
    return "redirect:/courses";
  }

  @GetMapping("/courses/updateCourseForm/{id}")
  public String updateCourseForm(@PathVariable(value = "id") long id, Model model) {
    Course course = courseService.getCourseById(id);
    ArrayList<Institution> institutions = (ArrayList<Institution>) institutionService.listAllInstitutions();
    model.addAttribute("course", course);
    model.addAttribute("institutions", institutions);
    return "update_course_form";
  }

  @GetMapping("/courses/deleteCourse/{id}")
  public String deleteCourse(@PathVariable(value = "id") long id) {
    this.courseService.deleteCourseById(id);
    return "redirect:/courses";
  }
}
