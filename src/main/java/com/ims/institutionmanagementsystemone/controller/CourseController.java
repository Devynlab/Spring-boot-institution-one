package com.ims.institutionmanagementsystemone.controller;

import java.util.ArrayList;

import com.ims.institutionmanagementsystemone.model.Course;
import com.ims.institutionmanagementsystemone.model.Institution;
import com.ims.institutionmanagementsystemone.service.CourseService;
import com.ims.institutionmanagementsystemone.service.InstitutionService;

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

  @RequestMapping(value = "/courses/institution/{id}")
  public String getCoursesByInstitutionId(@PathVariable long id, Model model) {
    model.addAttribute("courseList", courseService.getCoursesByInstitutionId(id));
    return "course_list";
  }

  @GetMapping("/courses/createCourseForm")
  public String createCourseForm(Model model) {
    Course course = new Course();
    ArrayList<Institution> institutions = (ArrayList<Institution>) institutionService.listAllInstitutions();
    model.addAttribute("course", course);
    model.addAttribute("institutions", institutions);
    return "create_course_form";
  }

  @PostMapping("/courses/saveCourse")
  public String saveCourse(@ModelAttribute("course") Course course, RedirectAttributes redirectAttributes) {
    if (course.getName() != null) {
      courseService.saveCourse(course);
      redirectAttributes.addFlashAttribute("message", "Success 👍");
      redirectAttributes.addFlashAttribute("alertClass", "alert-success");
    } else {
      redirectAttributes.addFlashAttribute("message", "Failed");
      redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
    }
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
  public String deleteCourse(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
    try {
      this.courseService.deleteCourseById(id);
      redirectAttributes.addFlashAttribute("message", "Success");
      redirectAttributes.addFlashAttribute("alertClass", "alert-success");
    } catch (DataIntegrityViolationException exception) {
      redirectAttributes.addFlashAttribute("message", "Failed");
      redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
    }
    return "redirect:/courses";
  }
}
