/*
 * Copyright 2017 HCL Technologies.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ershcl.samples.junit5;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Person {

  private Long id;
  private Date whenCreated;
  private String lastName;
  private String firstName;
  private int age;
  private String eyeColor;
  private String gender;

  public Person() {

  }

  public Person(String lastName, String firstName, int age, String eyeColor, String gender) {
	    this.lastName = lastName;
	    this.firstName = firstName;
	    this.age = age;
	    this.eyeColor = eyeColor;
	    this.gender = gender;
  }

  public Long getId() {
    return id;
  }

  public Person withId(Long id) {
    this.id = id;
    return this;
  }

  public Date getWhenCreated() {
    return whenCreated;
  }

  public Person setWhenCreated(Date whenCreated) {
    this.whenCreated = whenCreated;
    return this;
  }


  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public int getAge() {
    return age;
  }

  public String getEyeColor() {
    return eyeColor;
  }

  public String getGender() {
    return gender;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", age=" + age + ", height="
        + ", eyeColor="
        + eyeColor
        + ", gender=" + gender + ", whenCreated = " + whenCreated + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + age;
    result = prime * result + ((eyeColor == null) ? 0 : eyeColor.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Person other = (Person) obj;
    if (age != other.age)
      return false;
    if (eyeColor == null) {
      if (other.eyeColor != null)
        return false;
    } else if (!eyeColor.equals(other.eyeColor))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (gender == null) {
      if (other.gender != null)
        return false;
    } else if (!gender.equals(other.gender))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    return true;
  }

}
