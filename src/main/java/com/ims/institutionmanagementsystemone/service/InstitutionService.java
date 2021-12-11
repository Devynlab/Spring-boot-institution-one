package com.ims.institutionmanagementsystemone.service;

import java.util.List;

import com.ims.institutionmanagementsystemone.model.Institution;

public interface InstitutionService {
  List<Institution> listAllInstitutions();
  Institution getInstitutionById(long id);
  void saveInstitution(Institution institution);
  void deleteInstitutionById(long id);
}
