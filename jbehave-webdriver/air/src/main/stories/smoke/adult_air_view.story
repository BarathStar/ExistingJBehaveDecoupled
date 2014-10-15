View an air itinerary

Meta:
@suite smoke
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@project_AccordionPage

Narrative:
In order to verify my flight details
As an adult
I want to view my air itinerary


Scenario: adult air view

Given I search for a roundTrip air ticket from DAL to HOU
When I have a flight booked
And I retrieve my itinerary
Then I view my itinerary
