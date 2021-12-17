package com.ims.institutionmanagementsystemone.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "institutions")
public class Institution {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(unique = true)
  private String name;

  private String address;
  private String website;
  private Integer yearFounded;
  private String vision;

  // @ManyToMany(targetEntity = Course.class, mappedBy = "institutions")
  @OneToMany(mappedBy = "institution")
  private List<Course> courses;

  // @OneToMany(mappedBy = "institution")
  // private List<Student> students;

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

  public Integer getYearFounded() {
    return yearFounded;
  }

  public void setYearFounded(Integer yearFounded) {
    this.yearFounded = yearFounded;
  }

  public String getVision() {
    return vision;
  }

  public void setVision(String vision) {
    this.vision = vision;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  // public List<Student> getStudents() {
  //   return students;
  // }

  // public void setStudents(List<Student> students) {
  //   this.students = students;
  // }
}
