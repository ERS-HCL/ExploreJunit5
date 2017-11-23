package com.github.ershcl.samples.junit5;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.github.ershcl.samples.junit5.Person;

public class SimpleJUnitParameterResolver implements BeforeEachCallback,ParameterResolver{

	private static Logger log = Logger.getLogger(SimpleJUnitParameterResolver.class);
	protected Person person;
	@Override
	public boolean supportsParameter(ParameterContext parameterContext,
			ExtensionContext extensionContext)
					throws ParameterResolutionException {
		log.info("SimpleJUnitParameterResolver:: supportsParameter()::Check the Passed Parameter" + parameterContext);
		if(parameterContext.getParameter().getType() == Person.class)
			return true;
		else
			return false;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext,
			ExtensionContext extensionContext)
					throws ParameterResolutionException {
		log.info("SimpleJUnitParameterResolver:: resolveParameter()::Return the Object");
		return new Person("lastName","firstName",40,"black","M");
		//return null;
	}

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		// TODO Auto-generated method stub
		log.info("SimpleJUnitParameterResolver:: beforeEach()::Return the Object");
		person  = new Person("chaudhury","jayant",40,"black","M");
	}

}
