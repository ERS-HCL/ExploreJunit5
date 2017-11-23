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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Test class for PersonDaoBean.
 * 
 * Uses JUnit Jupiter API
 *
 */
@DisplayName("Testing PersonDaoBean")
@ExtendWith(JUnit5ExtensionShowcase.class)
@RunWith(JUnitPlatform.class)
public class PersonDaoBeanDynamicTest extends AbstractBaseTest {


  @Nested
  @DisplayName("When Database is populated with objects")
  public class WhenDatabaseIsPopulated {

    private ApplicationContext ctx;

    private PersonDaoBean classUnderTest;

    @BeforeEach
    public void setUp() throws Exception {
      ctx = new AnnotationConfigApplicationContext(JUnitSpringConfiguration.class);
      classUnderTest = ctx.getBean(PersonDaoBean.class);
    }

    @AfterEach
    public void tearDown() throws Exception {
    	//TODO
    }

    @Test
    @DisplayName("Find all objects in the database")
    public void findAll() {
      assertNotNull(classUnderTest, "PersonDaoBean reference cannot be null.");
      List<Person> people = classUnderTest.findAll();
      assertAll("Returned list should not be null, empty, and must contain 7 objects",
          () -> assertNotNull(people),
          () -> assertFalse(people.isEmpty()),
          () -> assertEquals(PersonDataStore.getInstance().getAllPerson().size(), people.size()));
    }

    @Test
    @DisplayName("FindById - Old School")
    public void findById() {
      assertNotNull(classUnderTest, "PersonDaoBean reference cannot be null.");
      assertAll("Assert found objects by IDs 1-5",
          () -> {
            Person person = classUnderTest.findById(1L);
            assertNotNull(person, "Person reference was null");
            performPersonAssertions("Makhija", "Sid", 23, "black", "M", person);
          },
          () -> {
            Person person = classUnderTest.findById(2L);
            assertNotNull(person, "Person reference was null");
            performPersonAssertions("Mukherjee", "Kate", 55, "black","F", person);
          },
          () -> {
            Person person = classUnderTest.findById(3L);
            assertNotNull(person, "Person reference was null");
            performPersonAssertions("Chowdhary", "Arinjay", 4, "black","M", person);
          },
          () -> {
            Person person = classUnderTest.findById(4L);
            assertNotNull(person, "Person reference was null");
            performPersonAssertions("Shastri", "Vikram", 40, "brown","M", person);
          }, () -> {
            Person person = classUnderTest.findById(5L);
            assertNotNull(person, "Person reference was null");
            performPersonAssertions("Iyer", "Ramesh", 63, "black","M", person);
          });
    }

    /**
     * Create same tests as in findById, but create them as DynamicTest instances.
     * 
     * @return
     */
    @Advanced
    @TestFactory
    @DisplayName("FindById - Dynamic Test Generator")
    Stream<DynamicTest> generateFindByIdDynamicTests() {
      Long[] ids = { 1L, 2L, 3L, 4L, 5L, 6L };
      return Stream.of(ids).map(id -> dynamicTest("DynamicTest: Find by ID " + id, () -> {
        Person person = classUnderTest.findById(id);
        assertNotNull(person);
        int index = id.intValue() - 1;
        Person testPerson = PersonDataStore.getInstance().getAllPerson().get(index);
        performPersonAssertions(testPerson.getLastName(), testPerson.getFirstName(),
            testPerson.getAge(), testPerson.getEyeColor(), testPerson.getGender(), person);
      }));
    }

    @Test
    @DisplayName("Find all objects by a specific last name")
    public void findAllByLastName() {
      assertNotNull(classUnderTest, "PersonDaoBean reference cannot be null.");
      assertAll(
          () -> {
            List<Person> people = classUnderTest.findAllByLastName("Makhija");
            assertNotNull(people);
            assertFalse(people.isEmpty());
            assertEquals(1, people.size());
            Person person = people.get(0);
            performPersonAssertions("Makhija", "Sid", 23, "black","M", person);
          },
          () -> {
            List<Person> people = classUnderTest.findAllByLastName("Mathur");
            assertNotNull(people);
            assertFalse(people.isEmpty());
            assertEquals(1, people.size());
            Person person = people.get(0);
            performPersonAssertions("Mathur", "Varsha", 35, "black","F", person);
          });
    }

    @Test
    @DisplayName("Add generated Person should succeed - uses Parameter injection")
    @ExtendWith(GeneratedPersonParameterResolver.class)
    public void add(Person person) {
      assertNotNull(classUnderTest, "PersonDaoBean reference cannot be null.");
      Person personAdded = classUnderTest.add(person);
      assertNotNull(personAdded, "Add failed but should have succeeded");
      assertNotNull(personAdded.getId());
      performPersonAssertions(person.getLastName(), person.getFirstName(), person.getAge(), person.getEyeColor(),
          person.getGender(), personAdded);
    }

    @Test
    @DisplayName("Adding a duplicate Person should fail")
    public void add_duplicate() {
      assertNotNull(classUnderTest, "PersonDaoBean reference cannot be null.");
      // Create new local Person object whose attributes match one that exists in the DB
      Person person = new Person("Mathur", "Varsha", 35, "black","F");
      Person personAdded = classUnderTest.add(person);
      assertNull(personAdded, "Add not succeeded because of duplicate elements.");
    }

    @Test
    @DisplayName("Update existing Person should succeed")
    @ExtendWith(RandomExistingPersonParameterResolver.class)
    @Disabled
    public void update(Person person) {
      assertNotNull(classUnderTest, "PersonDaoBean reference cannot be null.");
      // Update using Random Person returned by Class-level ParameterResolver
      // Add 10 years to the Person's age
      Person personToUpdate = new Person(person.getLastName(), person.getFirstName(), person.getAge() + 10,
          person.getEyeColor(), person.getGender()).withId(person.getId());
      boolean updateSucceeded = classUnderTest.update(personToUpdate);
      assertTrue(updateSucceeded);
    }


    @Test
    @DisplayName("Deleting existing Person should succeed")
    public void delete() {
      assertNotNull(classUnderTest, "PersonDaoBean reference cannot be null.");
      Person person = new Person("Mathur", "Varsha", 35, "black","F");
      person.withId(7L);
      Person personDeleted = classUnderTest.delete(person);
      assertNotNull(personDeleted);
      performPersonAssertions(person.getLastName(), person.getFirstName(), person.getAge(), person.getEyeColor(),
          person.getGender(), personDeleted);
    }

  }


}
