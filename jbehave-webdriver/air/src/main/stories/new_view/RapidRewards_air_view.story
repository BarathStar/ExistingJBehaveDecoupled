View a Rapid Rewards air itinerary in dollars

Meta:
@flow air
@process view
@user rr_member
@traveler adult
@not_passing draft

Narrative:
In order to view my booking reservation
As a Rapid Rewards Member
I want to retrieve my booking using my confirmation number

Scenario: Rapid Rewards Member air view
Given I am flying a round-trip Southwest Southwest flight
And I login as a Rapid Rewards Member
When I book a flight
And I retrieve my itinerary
Then I view my itinerary
