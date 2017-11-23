package com.github.ershcl.samples.junit5;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "com.github.ershcl.samples.junit5")
public abstract class AbstractSpringConfiguration {

  /**
   * The DataSource can be any DB or any Collection object.Here PersonDataStore class is used.
   */
  public abstract PersonDataStore getDataSource();

}
