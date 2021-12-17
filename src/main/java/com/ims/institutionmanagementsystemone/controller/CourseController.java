package com.ims.institutionmanagementsystemone.controller;

import java.util.ArrayList;
import java.util.List;

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
  Course course = new Course();

  // List all the courses offered by all institutions
  @GetMapping("/courses")
  public String courseListView(Model model) {
    model.addAttribute("courseList", courseService.listAllCourses());
    return "course_list";
  }

  // List all the courses offered by a specific institution
  @RequestMapping(value = "/courses/institution/{id}")
  public String getCoursesByInstitutionId(@PathVariable long id, Model model) {
    model.addAttribute("course", course);
    model.addAttribute("courseList", courseService.getCoursesByInstitutionId(id));
    return "course_list";
  }

  // Display form for creating a new course
  @GetMapping("/courses/createCourseForm")
  public String createCourseForm(Model model) {
    Course course = new Course();
    ArrayList<Institution> institutions = (ArrayList<Institution>) institutionService.listAllInstitutions();
    model.addAttribute("course", course);
    model.addAttribute("institutions", institutions);
    return "create_course_form";
  }

  // Creating a new course and updating an existing one
  @PostMapping("/courses/saveCourse")
  public String saveCourse(@ModelAttribute("course") Course course, RedirectAttributes redirectAttributes) {
    List<String> courses = new ArrayList<>();
    for (Course courseInInstitution : courseService.getCoursesByInstitutionId(course.getInstitution().getId())) {
      courses.add(courseInInstitution.getName());
    }
    // Check if the provided course name already in the given institution before saving it
    if (!courses.contains(course.getName())) {
      courseService.saveCourse(course);
      redirectAttributes.addFlashAttribute("message", "Success 👍");
      redirectAttributes.addFlashAttribute("alertClass", "alert-success");
    } else {
      redirectAttributes.addFlashAttribute("message", "Failed! Course '" + course.getName() + "' already exists in the given institution.");
      redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
    }
    return "redirect:/courses";
  }

  // Displaying form for updating a course
  @GetMapping("/courses/updateCourseForm/{id}")
  public String updateCourseForm(@PathVariable(value = "id") long id, Model model) {
    ArrayList<Institution> institutions = (ArrayList<Institution>) institutionService.listAllInstitutions();
    Course course = courseService.getCourseById(id);
    model.addAttribute("institutions", institutions);
    model.addAttribute("course", course);
    return "update_course_form";
  }

  // Deleting a course
  @GetMapping("/courses/deleteCourse/{id}")
  public String deleteCourse(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
    try {
      this.courseService.deleteCourseById(id);
      redirectAttributes.addFlashAttribute("message", "Successfully deleted 👍");
      redirectAttributes.addFlashAttribute("alertClass", "alert-success");
    } catch (DataIntegrityViolationException exception) {
      redirectAttributes.addFlashAttribute("message", "Failed! '" + courseService.getCourseById(id).getName() + "' has a student assigned to it.");
      redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
    }
    return "redirect:/courses";
  }
}
