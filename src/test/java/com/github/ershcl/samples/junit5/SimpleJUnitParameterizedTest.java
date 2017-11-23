package com.github.ershcl.samples.junit5;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@DisplayName("Simple Junit5 test to show Parameterized Test")
public class SimpleJUnitParameterizedTest {
	private static Logger log = Logger
			.getLogger(SimpleJUnitParameterizedTest.class);

	/**
	 * This is a demonstration of the Junit test where the test method is
	 * executed with different parameters which are passed in the Value Source
	 * object.
	 * 
	 * @param id
	 */
	@DisplayName("Simple Value Source Parameterzied Test without Test index and arguments")
	@ParameterizedTest
	@ValueSource(longs = {1l, 2l, 3l})
	void performValueSourceTest(Long id) {
		log.debug(
				"SimpleJUnitParameterizedTest::performValueSourceTest(): Id passed is "
						+ id);
		Assumptions.assumingThat(3 == id,
				() -> Assertions.assertEquals(12, 6 + 6));
		Assertions.assertNotEquals(12, 2 + 6);
	}

	/**
	 * This is a demonstration of the Junit test where the test method is
	 * executed with different parameters which are passed in the Value Source
	 * object. Additonally, we can check which test is executed with what id
	 * passed from ParameterizedTest annotations.
	 * 
	 * @param id
	 */
	@DisplayName("Simple Value Source Parameterzied Test")
	//@ParameterizedTest(name = "@performValueSourceParameterzedTest1 Test Nos# {index} with passed ID as {0}")
	@ParameterizedTest(name = "@ValueSource: performValueSourceParameterzedTest1(): Test# {index}: Id: {0}")
	@ValueSource(longs = {1l, 2l, 3l})
	void performValueSourceParameterzedTest1(Long id) {
		log.debug(
				"SimpleJUnitParameterizedTest::performValueSourceParameterzedTest1()");
		Assumptions.assumingThat(3 == id,
				() -> Assertions.assertEquals(3, id.longValue()));
		Assertions.assertNotEquals(12, 2 + 6);
	}
	
	@TestFactory
	@DisplayName("FindById - Dynamic Test Generator")
	@ExtendWith(SimpleJUnitParameterResolver.class)
	Stream<DynamicTest> generateFindByIdDynamicTests(Person person) {
	  Long[] ids = { 1L, 2L, 3L, 4L, 5L };
	  return Stream.of(ids).map(id -> dynamicTest("DynamicTest: Find by ID " + id, () -> {
		  Assertions.assertNotNull(person);
		  Assertions.assertEquals("lastName", person.getLastName());
		  //	    Person person = classUnderTest.findById(id);
			//	    assertNotNull(person);
			//	    int index = id.intValue() - 1;
			//	    Person testPerson = PersonTestEnum.values()[index].getPerson();
			//	    performPersonAssertions(testPerson.getLastName(), testPerson.getFirstName(),
			//	        testPerson.getAge(), testPerson.getEyeColor(), testPerson.getGender(), person);
	  }));
	}

}
