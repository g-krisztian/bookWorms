Feature: Librarian can successfully create, find, update and deactivate books

    Scenario: Librarian gets all book
        When the librarian asks for all books
        Then the responded status code is 200
        And the responded books are correct

