Feature: Test HOME REST API
  User should be able to call HOME REST API

  Scenario: Get information about the application by calling /home REST endpoint
    When the user calls the HOME REST API endpoint
    Then the result is success