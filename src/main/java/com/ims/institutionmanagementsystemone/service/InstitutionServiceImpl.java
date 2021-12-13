package com.ims.institutionmanagementsystemone.service;

import java.util.List;
import java.util.Optional;

import com.ims.institutionmanagementsystemone.model.Course;
import com.ims.institutionmanagementsystemone.model.Institution;
import com.ims.institutionmanagementsystemone.repository.InstitutionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionServiceImpl implements InstitutionService {
  @Autowired
  private InstitutionRepository institutionRepo;

  // @Override
  public List<Institution> listAllInstitutions() {
    return institutionRepo.findAll();
  }

  // @Override
  public Institution getInstitutionById(long id) {
    Optional<Institution> opt = institutionRepo.findById(id);
    Institution institution = null;
    if (opt.isPresent()) {
      institution = opt.get();
    } else {
      throw new RuntimeException("Institution with id '" + id + "' not found!");
    }
    return institution;
  }

  public void saveInstitution(Institution institution) {
    institutionRepo.save(institution);
  }

  public void deleteInstitutionById(long id) {
    Course course = new Course();
    if(course != null ){
      System.out.println("You can not delete the institution");
    }
    institutionRepo.deleteById(id);
  }
}
