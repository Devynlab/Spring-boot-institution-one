package com.ims.institutionmanagementsystemone.controller;

import javax.validation.Valid;

import com.ims.institutionmanagementsystemone.model.Institution;
import com.ims.institutionmanagementsystemone.service.InstitutionService;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InstitutionController {
  // private static final boolean ConstraintViolationException = false;
  @Autowired
  private InstitutionService institutionService;

  @GetMapping("/institutions")
  public String institutionListView(Model model) {
    model.addAttribute("institutionList", institutionService.listAllInstitutions());
    return "institution_list";
  }

  @GetMapping("/institutions/createInstitutionForm")
  public String createInstitutionForm(Institution institution) {
    return "create_institution_form";
  }

  @PostMapping("/institutions/saveInstitution")
  public String saveInstitution(@Valid @ModelAttribute("institution") Institution institution, BindingResult result, ConstraintViolationException exception) {
    if (result.hasErrors()) {
      return "create_institution_form";
    }
    if (exception != null) {
      System.out.println("------------------------------------------------------------------");
      System.out.println("Catch this error.");
      System.out.println("------------------------------------------------------------------");
      return "create_institution_form";
    }
    institutionService.saveInstitution(institution);
    return "redirect:/institutions";
  }

  @GetMapping("/institutions/updateInstitutionForm/{id}")
  public String updateInstitutionForm(@PathVariable(value = "id") long id, Model model) {
    Institution institution = institutionService.getInstitutionById(id);
    model.addAttribute("institution", institution);
    return "update_institution_form";
  }

  @GetMapping("/institutions/deleteInstitution/{id}")
  public String deleteInstitution(@PathVariable(value = "id") long id) {
    this.institutionService.deleteInstitutionById(id);
    return "redirect:/institutions";
  }
}
