[![Build Status](https://travis-ci.com/bhadreshdesai/app-java.svg?branch=develop)](https://travis-ci.com/bhadreshdesai/app-java)
[![codecov](https://codecov.io/gh/bhadreshdesai/app-java/branch/develop/graph/badge.svg)](https://codecov.io/gh/bhadreshdesai/app-java)

# app-java
Demo java application using Spring Boot

## Table of Content
- [Introduction](#introduction)
- [What you will need](<#what-you-will-need>)
- [Initial project setup](#initial-project-setup)
  - [Create a github repository](#create-a-github-repository)
  - [Open a github repository using gitpod](#open-a-github-repository-using-gitpod)
  - [Create project using spring initializr](#create-project-using-spring-initializr)
  - [Setup code coverage using jacoco](#setup-code-coverage-using-jacoco)
  - [Setup CI/CD using travis](#setup-ci%2Fcd-using-travis)
  - [gitpod project setup](#gitpod-project-setup)
- [Setup Behavior Driven Development using Cucumber](#setup-behavior-driven-development-using-cucumber)
  - [Setup Cucumber Reports](#setup-cucumber-reports)

## Introduction
Create a project using online tools.

## What you will need
You will need accounts on the following websites.

* https://github.com/
* https://gitpod.io/ (linked to your github account)
* https://travis-ci.com/ (linked to your github account)
* https://about.codecov.io/ (linked to your github account)

## Initial project setup

### Create a github repository
Create a new github repository.

Enter Repository name.

Tick Add a README file tickbox.

Click Create repository

### Open a github repository using gitpod
In your browser, navigate to gitpod.io/#/<github repo url>

For e.g. https://gitpod.io/#/https://github.com/bhadreshdesai/app-java

### Create project using spring initializr
We will create a spring boot project using gradle.

We will using web,devtools,lombok,h2,data-jpa spring boot dependencies for our project.

Execute the following command in the gitpod terminal to create a spring boot project.
```
curl https://start.spring.io/starter.tgz \
    -d type=gradle-project \
    -d language=java \
    -d javaVersion=11 \
    -d groupId=bdd.demo \
    -d artifactId=app-java \
    -d description=Demo%20java%20application \
    -d dependencies=web,devtools,lombok,h2,data-jpa \
| tar -xzvf -
```
Add the following line to `src/main/resources/application.properties`.
```properties
spring.main.banner-mode=off
spring.output.ansi.enabled=ALWAYS
```

To test the project setup, execute the following command in the gitpod terminal.
```
./gradlew check
```
The build should run successfully.

You can check the test results in `build/reports/tests/test/index.html`

### Setup code coverage using jacoco
Add the following to the `build.gradle` in the plugin section.
```groovy
id 'jacoco'
```
Add the following to the `build.gradle` file at the end.
```groovy
jacocoTestReport {
	reports {
		xml.enabled = true
		// HTML report: build/reports/jacoco/test/html/index.html
		html.enabled = true
	}
}

check.dependsOn jacocoTestReport
```
To test the code coverage, execute the following command in the gitpod terminal.
```
./gradlew check
```
The build should run successfully.

Check the unit test and code coverage reports in the following files.

`build/reports/tests/test/index.html`

`build/reports/jacoco/test/html/index.html`

### Setup CI/CD using travis
Create a new file `.travis.yml` with the following content.
```yml
language: java
jdk:
  - openjdk11
after_success:
  - bash <(curl -s https://codecov.io/bash)
```

### gitpod project setup
Create a new file `.gitpod.yml` with the following content.
```yml
tasks:
  - init: ./gradlew build
    command: ./gradlew check
vscode:
  extensions:
    - redhat.java
    - vscjava.vscode-java-dependency
    - vscjava.vscode-java-debug
    - pkief.material-icon-theme
```
*Note: Using the latest redhat.java and vscjava.vscode-java-dependency plugins to fix an issue with the JAVA PROJECTS panel on gitpod.*

*Optionally add Material Icon Theme extension by PKief to the project.*

## Setup Behavior Driven Development using Cucumber
Add the following testImplementation dependencies to `build.gradle`
```
testImplementation "io.cucumber:cucumber-java:6.9.1"
testImplementation "io.cucumber:cucumber-junit-platform-engine:6.9.1"
```
*Tips: you can use a variable for version number. See `ver_cucumber` in `build.gradle` in the repository.*

Create a test runner class with `@Cucumber` annotation to run the cucumber tests.

Add `src/test/java/bdd/demo/appjava/CucumberTestRunner.java` with the following content.
```java
package bdd.demo.appjava;

import io.cucumber.junit.platform.engine.Cucumber;

@Cucumber
public class CucumberTestRunner {
}
```
Create a feature file describing the behavior.

Add `src/test/resources/bdd/demo/appjava/Calc.feature` with the following content.

```feature
Feature: Calculator
  As a user
  I want to use a calculator to add, subtract numbers
  So that I don't need to add myself

  Scenario: Add two numbers -2 & 3
    Given I have a calculator
    When I add -2 and 3
    Then the result should be 1
   
  Scenario: Add two numbers 10 & 15
    Given I have a calculator
    When I add 10 and 15
    Then the result should be 25

  Scenario: Subtract 10 from 15
    Given I have a calculator
    When I subtract 10 from 15
    Then the result should be 5
```
Run the test by exexuting the following command in the gitpod terminal.
```
./gradlew check
```
The test fails with the following error as the step mentioned in the feature file is undefined.
```java
io.cucumber.junit.platform.engine.UndefinedStepException: The step "I have a calculator" is undefined. You can implement it using the snippet(s) below:

@Given("I have a calculator")
public void i_have_a_calculator() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
```

See `build/reports/tests/test/index.html` file for more details.

Let's define the steps used in the feature file.

Create a new step definition class `CalcStepDef` in `src/test/java/bdd/demo/appjava/sample/CalcStepDef.java`
```java
package bdd.demo.appjava.sample;

import io.cucumber.java.en.Given;

public class CalcStepDef {
    Calculator calculator;

    @Given("I have a calculator")
    public void i_have_a_calculator() {
        calculator = new Calculator();
    }
}
```

Create a new `Calculator` class required by the above code in `src/main/java/bdd/demo/appjava/sample/Calculator.java`
```java
package bdd.demo.appjava.sample;

public class Calculator {

}
```
Run the test by exexuting the following command in the gitpod terminal.
```
./gradlew check
```
The test fails with the following error as the step mentioned in the feature file is undefined.
```java
io.cucumber.junit.platform.engine.UndefinedStepException: The step "I add -2 and 3" is undefined. You can implement it using the snippet(s) below:

@When("I add {int} and {int}")
public void i_add_and(Integer int1, Integer int2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
```
Update the step definition class `CalcStepDef` in `src/test/java/bdd/demo/appjava/sample/CalcStepDef.java` to add the undefined step.
```java
    @When("I add {int} and {int}")
    public void i_add_and(Integer int1, Integer int2) {
        result = calculator.add(int1, int2);
    }
}
```
*Note: result is a member variable of type Integer*

Add the missing `add` method to the `Calculator` class in `src/main/java/bdd/demo/appjava/sample/Calculator.java`
```java
    public Integer add(Integer num, Integer to) {
        return num + to;
    }
```
Run the test by exexuting the following command in the gitpod terminal.
```
./gradlew check
```
The test fails with the following error as the step mentioned in the feature file is undefined.
```java
io.cucumber.junit.platform.engine.UndefinedStepException: The step "the result should be 1" is undefined. You can implement it using the snippet(s) below:

@Then("the result should be {int}")
public void the_result_should_be(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
```
Update the step definition class `CalcStepDef` in `src/test/java/bdd/demo/appjava/sample/CalcStepDef.java` to add the undefined step.
```java
    @Then("the result should be {int}")
    public void the_result_should_be(Integer expectedResult) {
        assertEquals(expectedResult, result);
    }
```
Run the test by exexuting the following command in the gitpod terminal.
```
./gradlew check
```
The test fails with the following error as the step mentioned in the feature file is undefined.
```java
io.cucumber.junit.platform.engine.UndefinedStepException: The step "I subtract 10 from 15" is undefined. You can implement it using the snippet(s) below:

@When("I subtract {int} from {int}")
public void i_subtract_from(Integer int1, Integer int2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
```
Update the step definition class `CalcStepDef` in `src/test/java/bdd/demo/appjava/sample/CalcStepDef.java` to add the undefined step.
```java
    @When("I subtract {int} from {int}")
    public void i_subtract_from(Integer int1, Integer int2) {
        result = calculator.subtract(int1, int2);
    }
}
```
Add the missing `subtract` method to the `Calculator` class in `src/main/java/bdd/demo/appjava/sample/Calculator.java`
```java
    public Integer subtract(Integer num, Integer from) {
		return from - num;
	}
```
Run the test by exexuting the following command in the gitpod terminal.
```
./gradlew check
```
Test should pass successfully.

### Setup Cucumber Reports
Add the following to the `build.gradle` in the plugin section.
```groovy
id "com.github.spacialcircumstances.gradle-cucumber-reporting" version "0.1.22"
```
Create `src/test/resources/junit-platform.properties` with the following content
```properties
cucumber.execution.parallel.enabled=false
cucumber.plugin=json:build/reports/cucumber/cucumber-report.json
cucumber.publish.enabled=false
cucumber.publish.quiet=true
```
Run the test by exexuting the following command in the gitpod terminal.
```
./gradlew check
```
Check the cucumber report in `build/reports/cucumber/cucumber-html-reports/overview-features.html`
