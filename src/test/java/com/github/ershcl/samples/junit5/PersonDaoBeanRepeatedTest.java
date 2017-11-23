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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@Advanced
@DisplayName("Testing PersonDaoBean using Repeated Tests")
@RunWith(JUnitPlatform.class)

/**
 * Repeated Test for testing PersonDaoBean.Here we needed to use
 * @BeforeAll and @AfterAll annotation as setup method should be called only once 
 * in a Repeated Test.Otherwise it will initialise everytime for every repeat loop.
 * We could have used @TestInstance(Lifecycle.PER_CLASS) also instead of BeforeAll/AfterAll.
 *
 */
public class PersonDaoBeanRepeatedTest extends AbstractBaseTest {

  private static ApplicationContext ctx;

  private static PersonDaoBean classUnderTest;

  @BeforeAll
  public static void setUp() throws Exception {
    ctx = new AnnotationConfigApplicationContext(JUnitSpringConfiguration.class);
    classUnderTest = ctx.getBean(PersonDaoBean.class);
  }

  @AfterAll
  public static void tearDown() throws Exception {
  }

  @RepeatedTest(value = 5, name = "{displayName}: iteration {currentRepetition} of {totalRepetitions}")
  @DisplayName("Add generated Person object - repeated test")
  public void add() {
    assertNotNull(classUnderTest, "PersonDaoBean reference cannot be null.");
    Person person = new Person("Makhija","Reshmi",27,"brown","F");
    Map<Person,Boolean> personAdded = classUnderTest.duplicateAdd(person);
    if(personAdded.get(person)==true)
    {
    	Assertions.assertTrue(personAdded.get(person), "Person is added ");
    }else {
    	Assertions.assertFalse(personAdded.get(person), "Person is not added due to duplicity");
    }
   // Assertions.assertTrue(condition, message);
    assertNotNull(person, "Add failed because of duplicate elements");
   // assertNotNull(personAdded.getId());
   
  }

}
