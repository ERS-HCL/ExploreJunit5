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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

/**
 * Used to showcase the various callbacks used to extend Junit 5.
 *
 */
public class JUnit5ExtensionShowcase
    implements AfterAllCallback, AfterEachCallback, AfterTestExecutionCallback, BeforeAllCallback, BeforeEachCallback,
    BeforeTestExecutionCallback, ParameterResolver, TestExecutionExceptionHandler {

  private static final Logger log = Logger.getLogger(JUnit5ExtensionShowcase.class);

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    log.debug("supports(): parameterContext => " + ReflectionToStringBuilder.toString(parameterContext)
        + ", extensionContext => "
        + ReflectionToStringBuilder.toString(extensionContext));
    return false;
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    log.debug("resolve(): parameterContext => " + ReflectionToStringBuilder.toString(parameterContext)
        + ", extensionContext => "
        + ReflectionToStringBuilder.toString(extensionContext));
    return null;
  }

  @Override
  public void beforeTestExecution(ExtensionContext context) throws Exception {
    log.debug("beforeTestExecution(): TestExecutionContext => "
        + ReflectionToStringBuilder.toString(context));
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    log.debug("beforeEach(): TestExecutionContext => "
        + ReflectionToStringBuilder.toString(context));
  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    log.debug("beforeAll(): ContainerExtensionContext => "
        + ReflectionToStringBuilder.toString(context));
  }

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    log.debug("afterTestExecution(): TestExecutionContext => "
        + ReflectionToStringBuilder.toString(context));
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    log.debug("afterEach(): TestExecutionContext => "
        + ReflectionToStringBuilder.toString(context));
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    log.debug("afterAll(): ContainerExtensionContext => "
        + ReflectionToStringBuilder.toString(context));
  }

  @Override
  public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
    log.debug("handleTestExecutionException(): TestExecutionContext => "
        + ReflectionToStringBuilder.toString(context) + ", Throwable => "
        + ReflectionToStringBuilder.toString(throwable));
    //
    throw throwable;
  }

}
