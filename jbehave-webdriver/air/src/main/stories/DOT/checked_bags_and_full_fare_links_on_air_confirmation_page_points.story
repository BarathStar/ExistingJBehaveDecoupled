Checked baggage policy link on confirmation page with air purchase using points

Meta:
@links
@project dot
@flow air
@process booking
@user rr_member
@traveler adult
@storyId CHCO1113
@not_passing broken
@project dot_draft

Narrative:
As a Southwest.com user, I would like to know where to access the baggage fee details
prior to and/or while booking a flight.

In order to verify baggage policy link on the confirmation page with points
As a customer
I want to search and select flights, switch to points, complete purchase, and continue to the confirmation page

Scenario: Viewing the checked baggage policy link on the confirmation page

Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a AlistPreferred Rapid Rewards member on the Search Flights page
When I book a flight with points
Then I should receive an air confirmation
And I should see a link that opens the checked baggage policy in a new window
And I should see a link that opens the full fare disclosure in a new window
