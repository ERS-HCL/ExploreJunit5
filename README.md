<h2>ExploreJunit5</h2>
<p>
JUnit5 comes with a fresh breathe of air, having junit4 for more than a decade.We had one big Junit4 which was used both by dev tools and build tools. It was becoming outdated nor it was extensible with new tools.
JUnit5 is written completely in Java8 but it has backward compatability with test classes written in Junit 4 or Junit3.
<p>
<b>NOTE: Any project not using JAVA 8 should NOT consider migrating to Junit5.</B>
<p>
Junit5 is modular as well as extensible ,comes with new features and plugins which can be used by different IDE tools or Build tools
<img style="max-width:100%;" alt="junit5_arch" src="https://user-images.githubusercontent.com/12508591/33179625-195ccc46-d090-11e7-810a-9fe2c6d84658.gif">

Different plugins/jars are packaged with
- Jupiter engine
- Vintage engine
- Third Party engine

<img style="max-width:100%;" alt="figure-1" src="https://user-images.githubusercontent.com/12508591/33179996-38607042-d091-11e7-9b80-9052c1ff5e20.png">


Here we have worked out with few worked out examples which can highlight the features of JUnit5.
Features like
- New Asserts in Junit5
- Exploring the power of using @Extension annotations
- New annotations in Junit5.
- Using Dynamic test in Junit5
- Using Repeated test in JUnit5
- Using Parameterized test in JUnit5
- Using Parameter Resolver in Junit5
<p>This repository used Maven to build the files and for its execution.Though Gradle can also be used.
<p> Few Important things while updating POM.xml
  <ul style="list-style-type:circle">
  <li>For Running old Junit4 Test Classes with JUnit5 framework , we need to add the dependencies for junit-platform-surefire-provider and junit-vintage-engine plugin with maven –surefire-plugin.</li>
  <li> To execute Both New JUnit5 and Old JUnit4 Test Classes together in Junit5 framework, we need to add the dependency for junit-jupiter-engine.</li>
  <li>To execute only New JUnit5 Test Classes, we need to add the dependency for junit-jupiter-engine and use maven-compiler-plugin to run the test classes.</li>
</ul>

  
 <p> <i>Currently, IDE like Eclipse, IntelliJ,Netbeans can execute JUnit5 framework by adding <b>@RunWith(JUnitPlatform.class)</b> i.e. junit-platform-runner for executing tests and test suites on the JUnit5 Platform in a JUnit 4 environment(like in Eclipse IDE till it supports JUnit5).</i>
  
<h3> File Information </h3>
<ul style="list-style-type:circle">
<li>  <i>Person</i> : This is an normal POJO class.
<li>  <i>PersonDao</i> :This interface defines all kind of crud related methods.
<li>  <i>PersonDaoBean</i> : Implementation of the PersonDao interface.
<li>  <i>PersonDataStore</i> : Singleton class containing a dump of Person object data.
<li>  <i>AbstractSpringConfiguration</i> : This is to invoke Spring IOC Container.
</ul>
<p>
<h3> Test Related Files </h3>
<ul style="list-style-type:circle">
<li>  <i>GeneratedParameterResolver</i>: This class implements ParameterResolver interface and contains callback methods which will
       be invoked by any Test Class using the @ExtendWith annotation like <i>@ExtendWith(SimpleJUnitParameterResolver.class)</i>.
<li>  <i>JUnitSpringConfiguration</i> : This is to invoke the Spring IOC in JUnit enviornemnt.
<li>  <i>PersonDaoBeanDyamicTest</i>: This class will explore many new Junit5 assserts, annotations and most importantly using  Dynamic            test.
<li>  <i>PersonDaoBeanRepeatedTest</i>: This class will explore how we can implement new feature of Junit5 i.e. Repeated Test.
<li>  <i>PersonDaoBeanParameterizedTest</i>: This class will explore the very important feature of Junit5 i.e. Parameterized test using             either @MethodSource, @ValueSource or @EnumSource. @EnumSource is left explicitly for your practise.
</ul>
<p>
 There are few more Test or call back interfaces are provided.You can explore more or play out with the code and get your hand dirty in Junit5.
  
Using <i>mvn clean install</i> will compile and execute all the test classes.Alternatively, this files can also be imported to Eclipse or any other IDE.
<p>
Here is the screenshot of PersonDaoBeanParameterizedTest when executed in Eclipse will give you a report like below
<img style="max-width:100%;" alt="junit_run" src="https://user-images.githubusercontent.com/12508591/33185747-c32fec3e-d0aa-11e7-9360-6ec9553ffac1.GIF">
