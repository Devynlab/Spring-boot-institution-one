package com.ims.institutionmanagementsystemone.controller;


import javax.validation.Valid;

import com.ims.institutionmanagementsystemone.model.Institution;
import com.ims.institutionmanagementsystemone.service.InstitutionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InstitutionController {
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
  public String saveInstitution(@Valid @ModelAttribute("institution") Institution institution, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      return "create_institution_form";
    }
    try {
      institutionService.saveInstitution(institution);
      redirectAttributes.addFlashAttribute("message", "Success 👍");
      redirectAttributes.addFlashAttribute("alertClass", "alert-success fs-1");
    } catch (DataIntegrityViolationException exception) {
      redirectAttributes.addFlashAttribute("message", "Failed! Institution with the name '" + institution.getName() + "' already exists.");
      redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
    }
    return "redirect:/institutions";
  }

  @GetMapping("/institutions/updateInstitutionForm/{id}")
  public String updateInstitutionForm(@PathVariable(value = "id") long id, Model model) {
    Institution institution = institutionService.getInstitutionById(id);
    model.addAttribute("institution", institution);
    return "update_institution_form";
  }

  @GetMapping("/institutions/deleteInstitution/{id}")
  public String deleteInstitution(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
    try {
      institutionService.deleteInstitutionById(id);
      redirectAttributes.addFlashAttribute("message", "Successfully deleted 👍");
      redirectAttributes.addFlashAttribute("alertClass", "alert-success");
    } catch (DataIntegrityViolationException exception) {
      System.out.println("Cannot delete the institution.");
      redirectAttributes.addFlashAttribute("message", "Failed! '" + institutionService.getInstitutionById(id).getName() + "' has a course assigned to it.");
      redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
    }
    return "redirect:/institutions";
  }
}
