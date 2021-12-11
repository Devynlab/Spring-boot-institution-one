package com.ims.institutionmanagementsystemone.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "institutions")
public class Institution {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  @NotBlank(message = "Institution Name cannot be empty.")
  @Size(min = 5, max = 250)
  private String name;
  @NotBlank(message = "Institution Address cannot be empty.")
  private String address;
  @NotBlank(message = "Institution Website cannot be empty.")
  @URL(message = "Enter a valid url.")
  private String website;
  @NotBlank(message = "Institution Year Founded cannot be empty.")
  @Size(min = 1900, max = 2021)
  @Column(name = "year_founded")
  private int yearFounded;
  @NotBlank(message = "Institution vision cannot be empty.")
  private String vision;
  @NotBlank
  @OneToMany(mappedBy = "institution")
  private List<Student> students;
  @NotEmpty
  @NotBlank
  @OneToMany(mappedBy = "institution")
  private List<Course> courses;
  // @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
  // @JoinColumn(name = "is_fk", referencedColumnName = "id")
  // @OneToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
  // @JoinColumn(name = "ic_fk", referencedColumnName = "id")

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public int getYearFounded() {
    return yearFounded;
  }

  public void setYearFounded(int yearFounded) {
    this.yearFounded = yearFounded;
  }

  public String getVision() {
    return vision;
  }

  public void setVision(String vision) {
    this.vision = vision;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
