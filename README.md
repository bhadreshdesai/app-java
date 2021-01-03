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
    -d groupId=bdd.demo. \
    -d artifactId=app-java \
    -d description=Demo%20java%20application \
    -d dependencies=web,devtools,lombok,h2,data-jpa \
| tar -xzvf -
```
Add the following line to `src/main/resources/application.properties`.
```
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
```
id 'jacoco'
```
Add the following to the `build.gradle` file at the end.
```
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
```
language: java
jdk:
  - openjdk11
after_success:
  - bash <(curl -s https://codecov.io/bash)
```

### gitpod project setup
Create a new file `.gitpod.yml` with the following content.
```
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
