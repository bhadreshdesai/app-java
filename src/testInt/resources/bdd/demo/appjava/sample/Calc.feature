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

  Scenario: Multiply 5 and 10
    Given I have a calculator
    When I multiply 5 and 10
    Then the result should be 50

  Scenario: Multiply any number with 0
    Given I have a calculator
    When I multiply 15 and 0
    Then the result should be 0

  Scenario: Divide 10 by 2
    Given I have a calculator
    When I divide 10 by 2
    Then the result should be 5