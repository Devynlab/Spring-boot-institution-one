package com.ims.institutionmanagementsystemone.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "institutions")
public class Institution {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(unique = true)
  @NotNull(message = "Institution Name cannot be empty.")
  @Size(min = 5, max = 250)
  private String name;

  @NotNull(message = "Institution Address cannot be empty.")
  private String address;

  @NotNull(message = "Institution Website cannot be empty.")
  private String website;

  @NotNull(message = "Institution Year Founded cannot be empty.")
  @Size(min = 1900, max = 2021)
  @Column(name = "year_founded")
  private int yearFounded;

  @NotNull(message = "Institution vision cannot be empty.")
  private String vision;

  @NotNull
  @OneToMany(mappedBy = "institution")
  private List<Student> students;

  @NotNull
  @OneToMany(mappedBy = "institution")
  private List<Course> courses;
  // @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
  // @JoinColumn(name = "is_fk", referencedColumnName = "id")
  // @OneToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
  // @JoinColumn(name = "ic_fk", referencedColumnName = "id")

  public Institution() {}

  public Institution(@NotNull(message = "Institution Name cannot be empty.") @Size(min = 5, max = 250) String name,
      @NotNull(message = "Institution Address cannot be empty.") String address,
      @NotNull(message = "Institution Website cannot be empty.") String website,
      @NotNull(message = "Institution Year Founded cannot be empty.") @Size(min = 1900, max = 2021) int yearFounded,
      @NotNull(message = "Institution vision cannot be empty.") String vision) {
    this.name = name;
    this.address = address;
    this.website = website;
    this.yearFounded = yearFounded;
    this.vision = vision;
  }

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
