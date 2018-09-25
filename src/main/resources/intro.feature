Feature: Testing Hotel API

Scenario: Testing a status code
Given the content type is JSON
  When a user retrieves all the hotels
  Then the status code reads 200
 
Scenario Outline: Testing add new Hotel
  Given the content type is JSON
  When a user creates a hotel with name "<name>"
  And a user retrieves all the hotels
  Then the hotel with name <id> and "<name>" must be there  
Examples:
    | id   | name |
    | 1 | Veedu |
    | 2 | Veedu 1 |
    | 3 | Veedu 2 |
    | 4 | Veedu 3 |
  
Scenario: Testing the API using parameters
Given a hotel exists with the ID 1
  When a user retrieves the hotel by the id 1
  Then the status code reads 200
  And there is a name available
  
  
Scenario Outline: Testing the API using a table
   Given a hotel exists with the ID <id>
When a user retrieves the hotel by the id <id>
  Then the status code reads 200
   And the name is equal to "<name>"
   
Examples:
    | id   | name |
    | 1 | Veedu |
    | 2 | Veedu 1 |
    | 3 | Veedu 2 |
    | 4 | Veedu 3 |
    