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
