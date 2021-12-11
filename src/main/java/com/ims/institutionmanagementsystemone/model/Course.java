package com.ims.institutionmanagementsystemone.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String code;
  private String name;
  @OneToMany(mappedBy = "course")
  private List<Student> students;
  @ManyToOne
  private Institution institution;
  // @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
  // @JoinColumn(name = "cs_fk", referencedColumnName = "id")

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public Institution getInstitution() {
    return institution;
  }

  public void setInstitution(Institution institution) {
    this.institution = institution;
  }
}
