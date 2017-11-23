package com.github.ershcl.samples.junit5;

import org.apache.log4j.Logger;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.github.ershcl.samples.junit5.Person;

@RunWith(JUnitPlatform.class)
@ExtendWith(InjectJunitBeforeEachCallBack.class)
@DisplayName("Main Class extend InjectJunitBeforeEachCallBack and will compile and perform tests.")
public class MultipleExtensionJUnit5Test {

	private static Logger log = Logger.getLogger(MultipleExtensionJUnit5Test.class);
	
	@Test
	@DisplayName("initialise() method will invoke SimpleJUnitParameterResolver.")
	@ExtendWith(SimpleJUnitParameterResolver.class)
	void initialize(Person person){
		log.info(this.getClass().getName() + " : test method initialize() is called");
		assumeTrue(LocalDateTime.now().getDayOfWeek().getValue() != 5);
		Assertions.assertEquals(12, 6+6);
	}
}
