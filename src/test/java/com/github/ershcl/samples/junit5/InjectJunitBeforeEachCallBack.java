package com.github.ershcl.samples.junit5;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;

public class InjectJunitBeforeEachCallBack implements BeforeEachCallback {

	private static Logger log = Logger
			.getLogger(InjectJunitBeforeEachCallBack.class);

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		log.debug(
				"InjectJunitBeforeEachCallBack: beforeEach() is triggered here "
						+ ReflectionToStringBuilder.toString(context));
	}

}
