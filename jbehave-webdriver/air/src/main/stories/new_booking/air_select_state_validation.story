Verify the states of the page elements under specific flight search conditions

Meta:
@flow air
@process booking
@user anonymous
@traveler adult

Narrative:
In order to see that web elements are appropriately enable and displayed
I want to specify one-way flight searches
Then change my preferences and the page updates accordingly

Scenario: start search for one-way flights
Given I am flying a one-way Southwest flight
When I only search a flight
Then The return and return date fields are disabled