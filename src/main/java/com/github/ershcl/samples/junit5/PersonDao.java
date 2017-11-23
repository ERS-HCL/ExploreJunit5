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

import java.util.List;

public interface PersonDao {

  /**
   * Find all Person objects and return the collection to the caller.
   * 
   * @return List<Person> a List of Person objects. Represents all Person
   *         objects in the data store.
   */
  public List<Person> findAll();

  /**
   * Find the Person object by the specified ID.
   * 
   * @param id
   *          The unique ID of the Person object in the Repository
   * 
   * @return Person - the Person object, or null if it could not be found
   *         using the specified ID.
   */
  public Person findById(Long id);

  /**
   * Find all Person objects in the DB with the specified last name.
   * 
   * @param lastName
   * @return List<Person> - a List of Person objects whose lastName matches
   *         the specified last name, or an empty List if no matches were found.
   */
  public List<Person> findAllByLastName(String lastName);

  /**
   * Add the specified Person object to the DB.
   * 
   * @param person
   *          The Person object to add.
   * 
   * @return Person - the Person object just added, or null if
   *         there was a problem.
   */
  public Person add(Person person);

  /**
   * Updates the specified Person object, if it could be located
   * in the Repository.
   * 
   * @param person
   *          The Person object with the new field value(s)
   * 
   * @return boolean - true if the Person was updated, false otherwise.
   */
  public boolean update(Person person);

  /**
   * Deletes the specified Person object, if it exists in the
   * repository.
   * 
   * @param person
   *          The Person object with the new field value(s)
   * 
   * @return Person - the Person object that was updated or null if the object could not be deleted.
   */
  public Person delete(Person person);

}
