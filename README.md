# Maven Exercises

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
