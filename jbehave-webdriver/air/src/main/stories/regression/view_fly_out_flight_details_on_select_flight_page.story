View Fly Out Flight Details On Select Flight Page

Meta:
@suite regression
@flow air
@process booking
@user anonymous
@not_passing draft


Narrative:
In order to see the flight break down on the select flight page
As a customer
I want to select routing in order to see the details

Scenario: Open Flight Detail Flyout On Flight Select Page

Given I have markets and flights available for Southwest-Southwest-RoundTrip-Multiple-Stops
And I am a customer searching for round-trip flights from the search flights page
When I click the 1 stop link for WN on the select flight page
Then I view the flight detail fly out
When I click the 2 stops link for WN on the select flight page
Then I view the flight detail fly out
When I click the flyout close button
Then I view the flight detail fly out close
