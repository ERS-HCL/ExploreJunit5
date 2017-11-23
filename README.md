#ExploreJunit5
Here we have worked out with few worked out examples which can highlight the features of JUnit5.
Features like
- New Asserts in Junit5
- Exploring the power of using @Extension annotations
- New annotations in Junit5.
- Using Dynamic test in Junit5
- Using Repeated test in JUnit5
- Using Parameterized test in JUnit5
- Using Parameter Resolver in Junit5

For Running old Junit4 Test Classes with Junit5 , we need to add the dependencies for junit-platform-surefire-provider and junit- vintage-engine plugin with maven –surefire-plugin
To execute Both New JUnit5 and Old JUnit4 Test Classes together, we need to add the dependency for junit-jupiter-engine.
To execute only New JUnit5 Test Classes, we need to add the dependency for junit-jupiter-engine and use maven-compiler-plugin to run the test classes.
Add junit-platform-runner for executing tests and test suites on the JUnit Platform in a JUnit 4 environment(like in Eclipse IDE till it supports JUnit5).
