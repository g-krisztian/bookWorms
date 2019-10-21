Feature: Librarian can successfully create, find, update and deactivate customers

    Scenario: Librarian gets created customer
        When the librarian creates a customer
      #  And the database successfully persists the customer
        Then the responded status code is 200
        And the responded customer is correct