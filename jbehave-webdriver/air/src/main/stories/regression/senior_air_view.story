View an air itinerary

Meta:
@suite nightlyRun
@flow air
@process view
@user
@traveler senior

Narrative:
In order to view an air itinerary
As a senior
I want to view a booked flight

Scenario: senior air view

Given I am flying a round-trip Southwest Southwest flight
And I am on the Search Flight page
When I select the Senior passenger type
And I book a flight as a senior
And I retrieve my itinerary
Then I view my itinerary