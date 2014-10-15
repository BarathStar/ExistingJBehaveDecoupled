Retrieve and Change southwest to airtran oneway flight

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId DCAIR4895
@dyna_stubs

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change a one-way itinerary


Scenario: Changing a WN OneWay flight itinerary to FL OneWay flight itinerary

Given I am flying a one-way Southwest flight
When I have a flight booked
Then I should see the confirmation page
When I change to a one-way AirTran flight
Then I should see the itinerary change confirmation page
