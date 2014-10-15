Verify I am directed to the Enter Rapid Rewards Number page with a SouthWest Only Round-Trip

Meta:
@project coda
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid PODIV-1392


Narrative:
In order to add my rapid rewards number to my flight
As a customer
I want to verify I am redirected to Altea


Scenario: Adding RapidRewards Number to an Altea Itinerary

Given I am flying a round-trip Southwest flight with Altea
And I have a flight booked
Then I should see the confirmation page
And I click on the add Rapid Rewards number link
Given I am on the Add Rapid Rewards Number Page
When I enter my PNR on the add Rapid Rewards number page
Then I am redirected to the Altea Add Rapid Rewards page
