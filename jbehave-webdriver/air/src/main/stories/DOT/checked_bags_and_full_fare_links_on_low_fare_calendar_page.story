Checked baggage policy link on low fare calendar page

Meta:
@project dot
@flow air
@process booking
@user anonymous
@traveler adult
@storyId CHCO1113

Narrative:
In order to verify that the checked baggage policy link on the low fare calendar page
As a customer
I want to search for flights from the low fare calendar page

Scenario: Viewing the checked baggage policy link on the low fare calendar page

Given I am flying a round-trip Southwest Southwest flight
When I search and book a flight from the low fare calendar page
Then I should see a link that opens the checked baggage policy in a new window
And I should see a link that opens the full fare disclosure in a new window
