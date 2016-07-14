# Gradle Exercises

A simple collection of Gradle exercises for use in a 2-hour introductory training session. You should have
[Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or later installed.
[Gradle 2.14](https://services.gradle.org/distributions/gradle-2.14-bin.zip) is recommended, but not strictly required
as you can use `gradlew` to download the right version of Gradle for the project.

You can find the instructions for each exercise below. The exercises build on each other, so you need to complete them
in sequence. If you get stuck on an exercise the answers are available in branches, so for example: if you fail to get
exercise1 working, just `git checkout answer1` and continue from there.

# Exercises

## Exercise 1: Build the project 

Exercise: `git checkout exercise1`

In this exercise you simply need to build the project. Change into the project directory and then do `gradlew jar`.

Oh no! It failed with a compilation error:

```
:compileJava                                                    
warning: [options] bootstrap class path not set in conjunction with -source 1.6
C:\Dev\WBHO\gradle-exercises\src\main\java\za\co\entelect\forums\java\example\App.java:51: error: diamond operator is not supported in -source 1.6
        Set<Vehicle> vehicles = new HashSet<>();
                                            ^
  (use -source 7 or higher to enable diamond operator)
C:\Dev\WBHO\gradle-exercises\src\main\java\za\co\entelect\forums\java\example\domain\Gantry.java:22: error: diamond operator is not supported in -source 1.6
        vehicleTimes = new HashMap<>();
                                   ^
  (use -source 7 or higher to enable diamond operator)
C:\Dev\WBHO\gradle-exercises\src\main\java\za\co\entelect\forums\java\prac\task1\ColorSpotter.java:29: error: diamond operator is not supported in -source 1.6
        Map<Color, List<Vehicle>> vehiclesByColor = new HashMap<>();
                                                                ^
  (use -source 7 or higher to enable diamond operator)
C:\Dev\WBHO\gradle-exercises\src\main\java\za\co\entelect\forums\java\prac\task2\BigBrother.java:20: error: diamond operator is not supported in -source 1.6
        Set<Vehicle> uniqueRegistrations = new HashSet<>();
                                                       ^
  (use -source 7 or higher to enable diamond operator)
C:\Dev\WBHO\gradle-exercises\src\main\java\za\co\entelect\forums\java\prac\task3\Corruption.java:22: error: diamond operator is not supported in -source 1.6
        Map<Vehicle, BigDecimal> vehicleBill = new HashMap<>();
                                                           ^
  (use -source 7 or higher to enable diamond operator)
5 errors                    
1 warning                   
:compileJava FAILED  
```

You'll need to configure the Java Plugin to use 1.8. This is quite easy, just open up `build.gradle` and change the
values of `sourceCompatibility` and `targetCompatibility` to `1.8`.

Once you have this properly configured your build should succeed:
```
:compileJava                                                             
:processResources UP-TO-DATE      
:classes                 
:jar                 
               
BUILD SUCCESSFUL
               
Total time: 6.415 secs
```

You can run the example project with: `java -cp build/libs/gradle-exercises-0.1.0-SNAPSHOT.jar za.co.entelect.forums.java.prac.task1.ColorSpotter`.

Answer: `git checkout answer1` 

## Exercise 2: Repositories

Exercise: `git checkout exercise2`

We're not actually going to use it in the project, but let's just add a big dependency to your `build.gradle` to explore
the benefits of the local repository and/or repository server:
```
dependencies {
    compile group: 'com.badlogicgames.gdx', name: 'gdx', version: '1.6.1'
    testCompile group: 'junit', name: 'junit', version: '4.11'
}
```

To ensure that you don't already have the artifact in your local cache run: `rm -R rm -R ~/.gradle/caches/modules-2/files-2.1/com.badlogicgames.gdx/`.

Now run `gradlew jar` and look at the output:
```
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:jar UP-TO-DATE

BUILD SUCCESSFUL

Total time: 7.714 secs
```

Unlike Maven, Gradle doesn't show what dependencies it downloaded, but you can verify that it has been downloaded by
running `ls ~/.gradle/caches/modules-2/files-2.1/com.badlogicgames.gdx/`.

Run `gradlew jar` again:
```
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:jar UP-TO-DATE

BUILD SUCCESSFUL

Total time: 5.628 secs
```

You'll note that it's a little bit faster thanks to not having to download the dependency again.

The rest of this exercise is optional and deals with configuring Gradle to use the repository manager at your company to
speed up builds and reduce bandwidth used by Gradle.

If you are using Artifactory as your repository manager you can use its web UI to generate a `gradle.build` file for you
that is configured to use that repository manager... for example mine looks as follows:

```
buildscript {
    repositories {
        maven {
            url 'http://esjavatools:8080/artifactory/plugins-release'
             
        }
         
    }
    dependencies {
        classpath(group: 'org.jfrog.buildinfo', name: 'build-info-extractor-gradle', version: '2.0.9')
    }
}
 
allprojects {
    apply plugin: 'artifactory'
}
 
artifactory {
    contextUrl = "${artifactory_contextUrl}"   //The base Artifactory URL if not overridden by the publisher/resolver
    publish {
        repository {
            repoKey = 'libs-release-local'
            maven = true
             
        }
    }
    resolve {
        repository {
            repoKey = 'libs-release'
            maven = true
             
        }
    }
}
```

By contrast if you're using Nexus you just configure it as a custom Maven repository:

```
apply plugin: 'java'
apply plugin: 'maven'

repositories {
    maven {
          url "http://localhost:8081/nexus/content/groups/public"
    }
}

dependencies {
    testCompile "junit:junit:3.8.1"
    compile "org.jbundle.util:org.jbundle.util.jbackup:2.0.0"
    compile "net.sf.webtestfixtures:webtestfixtures:2.0.1.3"
    compile "org.shoal:shoal-gms-api:1.5.8"
    compile "org.ow2.util:util-i18n:1.0.22"
    compile "com.sun.grizzly:grizzly-lzma:1.9.19-beta5"
}
```

Answer: `git checkout answer2`


## Exercise 3: Tests

Exercise: `git checkout exercise3`

Your example project includes some tests (OK - a test). To run it, execute `gradle test` and look at the output:
```
:compileJava                                                                     
:processResources UP-TO-DATE      
:classes                 
:compileTestJava                                                         
:processTestResources UP-TO-DATE      
:testClasses                 
:test                                                         
               
BUILD SUCCESSFUL
               
Total time: 15.148 secs
```

So our build is succeeding so hopefully tests were run and passed... but it might be nicer to see which tests were run
so we can verify that they are actually being run. So add the following to `build.gradle`:

```
test {
    testLogging {
        events "passed", "skipped", "failed"
    }
}
```

Run `gradle test` again:

```
:compileJava UP-TO-DATE                                                          
:processResources UP-TO-DATE      
:classes UP-TO-DATE      
:compileTestJava UP-TO-DATE                                              
:processTestResources UP-TO-DATE      
:testClasses UP-TO-DATE      
:test UP-TO-DATE                                              
               
BUILD SUCCESSFUL
               
Total time: 8.633 secs
```

Still nothing?! Well actually Gradle is just being clever and not running tests that have already been compiled and run
a second time. To rerun the tests execute `gradle cleanTest test`:

```
:cleanTest                                                                       
:compileJava UP-TO-DATE                                          
:processResources UP-TO-DATE      
:classes UP-TO-DATE      
:compileTestJava UP-TO-DATE                                              
:processTestResources UP-TO-DATE      
:testClasses UP-TO-DATE      
:test                                                         
                      
za.co.entelect.forums.java.AppTest > testApp PASSED
                                         
BUILD SUCCESSFUL
               
Total time: 6.65 secs
```

This time we can see that our test ran and passed.

The simple test included in your example code is written for a pretty old version of JUnit - make the following changes
to `AppTest.java` to modernise it:
 * Remove all import statements.
 * Remove `extends TestCase` from the class definition.
 * Remove the constructor.
 * Remove the static method `suite`.
 * Replace `assertTrue(true);` with `Assert.assertTrue(true);`.
 * Annotate the `testApp()` method with `@Test`.
 * Add `import org.junit.Assert;`.
 * Add `import org.junit.Test;`.
  
Also update your `build.gradle` to use `junit:4.12`.

Now run `gradle test` to make sure your changes are working.  

Answer: `git checkout answer3`


## Exercise 4: Multi-module Web Project

Exercise: `git checkout exercise4`

### Step 1: Domain module

As your final exercise, let's convert the project into a multi-module web project. The overall structure of the new
version will be as follows:
```
 gradle-exercises
   \_ domain: All the current code in the project will move into the domain.
   \_ web: Web module will contain a single controller and page that will show the output of the corruption exercise.
```

Create a new sub-module of type Java using your IDE. This should change your `settings.gradle` to the following:

```
rootProject.name = 'gradle-exercises'
include 'domain'
```

Now move the code from `src/main/java/` and `src/test/java` in your parent project into the new `domain` module.

Let's test by building just the domain module from the project root - run `gradle :domain:test`. It should fail with
some errors due to the Java version again... To fix it let's specify the fact that these are Java projects and which
Java version to use in the root `build.gradle` file. So edit `gradle-exercises/build.gradle` and essentially just wrap
the current configuration in a `allprojects {}` block:

```
allprojects {
    group 'za.co.entelect.forums.java'
    version '0.1.0-SNAPSHOT'

    apply plugin: 'java'

    sourceCompatibility = '1.8'
    targetCompatibility = '1.8'

    repositories {
        mavenCentral()
    }

    dependencies {
        compile group: 'com.badlogicgames.gdx', name: 'gdx', version: '1.6.1'
        testCompile group: 'junit', name: 'junit', version: '4.12'
    }

    test {
        testLogging {
            events "passed", "skipped", "failed"
        }
    }
}
```

Also delete the contents of `gradle-exercises/domain/build.gradle`. Now `gradle :domain:test` should succeed as the
domain module is now inheriting all .

### Step 2: Web module

Create a new sub-module of type Web using your IDE. This should change your `settings.gradle` to the following:

```
rootProject.name = 'gradle-exercises'
include 'domain'
include 'web'
```

You could collapse those include lines into a single line that looks like this: `include 'domain', 'web'`.

Delete `gradle-exercises/web/src/main/webapp/index.jsp` (we'll add our own implementation files shortly).

Edit the generated `gradle-exercises/web/build.gradle` to look as follows:

```
apply plugin: 'war'

ext {
    springVersion = '4.1.1.RELEASE'
}

dependencies {
    compile project(':domain')
    compile(group: 'org.springframework', name: 'spring-core', version: springVersion) {
        exclude module: 'commons-logging'
    }
    compile group: 'org.springframework', name: 'spring-webmvc', version: springVersion
    compile group: 'org.slf4j', name: 'jcl-over-slf4j', version: '1.7.5'
    compile group: 'ch.qos.logback', name: 'logback-classic', version: '1.0.13'
    compile group: 'jstl', name: 'jstl', version: '1.2'
}
```

The additional plugin is to build this module into a WAR (Web ARchive) that we can deploy into an application server.
The `ext` block contains some properties that we want to reuse multiple times, so I put it in there to avoid
duplicating the values. Finally the various compile items add the dependencies needed for a Spring web application. One
of special interest is the `spring-core` dependency which also has an exclude on one of its transitive dependencies to
avoid conflicting with the logging dependencies provided by slf4j and logback.

Now let's add the files needed to make our web application (create any directories that are missing).

Firstly let's add our Spring Web MVC controller `gradle-exercises/web/src/main/java/za/co/entelect/forums/java/maven/web/BaseController.java`:

```
package za.co.entelect.forums.java.maven.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.co.entelect.forums.java.example.App;
import za.co.entelect.forums.java.example.domain.Gantry;
import za.co.entelect.forums.java.example.domain.Vehicle;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BaseController {

    private static final String VIEW_INDEX = "index";
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        Map<Vehicle, BigDecimal> vehicleBills = getVehicleBills();

        model.addAttribute("vehicles", vehicleBills.keySet());
        model.addAttribute("bills", vehicleBills);
        logger.debug("Showing vehicle bills : " + vehicleBills.toString());

        // Spring uses InternalResourceViewResolver and return back index.jsp
        return VIEW_INDEX;
    }

    private Map<Vehicle, BigDecimal> getVehicleBills() {
        List<Gantry> gantries = App.getGantryList();
        Map<Vehicle, BigDecimal> vehicleBill = new HashMap<>();

        for (Gantry gantry : gantries) {
            for (Vehicle vehicle : gantry.getVehicles()) {
                if (!vehicleBill.containsKey(vehicle)) {
                    vehicleBill.put(vehicle, new BigDecimal(gantry.getToll()));
                } else {
                    BigDecimal value = vehicleBill.get(vehicle);
                    vehicleBill.put(vehicle, value.add(new BigDecimal(gantry.getToll())));
                }
            }
        }

        return vehicleBill;
    }
}
```

This is a pretty basic controller that will generate a model of vehicle bills and render it with the view `index.jsp`.

Next let's add the view `gradle-exercises/web/src/main/webapp/WEB-INF/pages/index.jsp`:

```
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Corruption Bills</h1>

<c:forEach var="vehicle" items="${vehicles}">
    <h2>Vehicle : ${vehicle.registrationNumber}</h2>
    <p>Bill: R ${bills[vehicle]}</p>
</c:forEach>
</body>
</html>
```

Now we need to configure the Spring servlet dispatcher, so add `gradle-exercises/web/src/main/webapp/WEB-INF/web.xml`:

```
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">

    <display-name>Corruption Web Application</display-name>

    <servlet>
        <servlet-name>mvc-dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>mvc-dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/mvc-dispatcher-servlet.xml</param-value>
    </context-param>

    <listener>
        <listener-class>
            org.springframework.web.context.ContextLoaderListener
        </listener-class>
    </listener>
</web-app>
```

This registers the Spring DispatcherServlet at the application root, which will intercept any requests under that
location and route them to any controllers we've declared that serve requests for the matching path. The `contextConfigLocation`
tells Spring which Spring context to load.

So let's add that Spring context in `gradle-exercises/web/src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml`:

```
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="za.co.entelect.forums.java.maven.web"/>

    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix">
            <value>/WEB-INF/pages/</value>
        </property>
        <property name="suffix">
            <value>.jsp</value>
        </property>
    </bean>
</beans>
```

The last we need is just a configuration for our logging `gradle-exercises/web/src/main/resources/logback.xml`:

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">

            <Pattern>
                %d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
            </Pattern>

        </layout>
    </appender>

    <logger name="za.co.entelect.forums.java.maven.web" level="debug"
            additivity="false">
        <appender-ref ref="STDOUT"/>
    </logger>

    <root level="debug">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

Last thing for you to be able to test is to add another plugin to `gradle-exercises/web/build.gradle` that will allow
you to run the web application an application server directly from your build (this is very useful for development and
testing). Make the following changes:
 * Add `apply plugin: 'jetty'`
 * Add `jettyRunWar.contextPath = ''` to run in the root context when deploying the WAR
 * Add `jettyRun.contextPath = ''` to run in the root context when deploying the exploded WAR

So now if all went well you should able to build and run your web application with `gradle :web:jettyRun`. After it
finishes starting up you can visit http://locahost:8080/ to see your handiwork in action. 

Exercise: `git checkout answer4`
