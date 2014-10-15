Verify that mixed fare types that include DING are displayed correctly in confirmation page

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@storyId ZR878
@dyna_stubs
@project_in_dev

Narrative:
As an adult
I want to purchase a round trip with DING and ANYTIME fares

Scenario: Booking a mixed itinerary with DING fare OB and AT fare IN and in the confirmation page the fares should be displayed correctly.

Given I want to fly a round-trip flight
And I want to pay for my flight with dollars
And I want to use an available Ding fare for my outbound flight
And I want to use an available anytime fare for my inbound flight
When I book a flight that matches my criteria
Then I should receive an air confirmation
And I should see Ding! fare for outbound flight
And I should see Anytime fare for inbound flight
