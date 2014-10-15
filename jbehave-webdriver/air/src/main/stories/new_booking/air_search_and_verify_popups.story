Adult air search and verify popups

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to see Fare Details and Flight Details popup
As an adult
I want to search for a flight

Scenario: Adult air search and save
Given I am flying a round-trip Southwest Southwest flight
When I successfully search for flights from the flight search page
And I click on routing link for a flight
Then I should see Flight Details popup
When I click fare types
Then I should see Fare Details popup
