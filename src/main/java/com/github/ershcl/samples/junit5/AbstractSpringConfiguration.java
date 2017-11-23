package com.github.ershcl.samples.junit5;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "com.github.ershcl.samples.junit5")
public abstract class AbstractSpringConfiguration {

  /**
   * The DataSource for accessing the persistent store.
   * 
   * Make sure to implement
   */
  public abstract PersonDataStore getDataSource();

}
