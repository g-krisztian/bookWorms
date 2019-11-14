Feature: Librarian can successfully create, find, update and deactivate customers

    Scenario: Librarian gets created customer
        When the librarian creates customers with emails:
            | myEmail@email.test |
        Then the responded status code is 200
        And the responded customer is correct with emails:
            | myEmail@email.test |

    Scenario: Librarian finds created customer by email
        When the librarian creates customers with emails:
            | myUniqueEmail@email.test |
        Then the responded status code is 200
        And the responded customer is correct with emails:
            | myUniqueEmail@email.test |
        When the librarian search by emails:
            | myUniqueEmail@email.test |
        Then the responded status code is 200
        And the responded customer is correct with emails:
            | myUniqueEmail@email.test |


