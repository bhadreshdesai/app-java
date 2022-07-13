Feature: Test Employee CRUD REST API using DataTable
  Use should be able to call CRUD REST API to maintain entities in the application

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given the user wants to create an employee with the following attributes
      | id  | firstName | lastName | dob         | gender  |
      | 100 | Rachel    | Green    | 1990-01-01  | F       |

    When the user saves the new employee 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'
