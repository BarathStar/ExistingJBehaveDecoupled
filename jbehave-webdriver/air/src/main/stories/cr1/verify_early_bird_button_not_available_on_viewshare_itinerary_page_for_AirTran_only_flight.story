Verify EB upsell button on View itinerary page with FL only Booking

Meta:
@project cr1
@airTranOnly
@flow air
@process view
@user anonymous
@traveler adult
@storyId DCAIR6397 ZR-891
@project_in_dev

Narrative:
As a customer
I want to see the View/Share itinerary page with FL only booking
So that I can verify Early Bird upsell button is not on itinerary page


Scenario: See EB Check-in on itinerary page with FL only Booking

Given I am flying a round-trip AirTran AirTran flight
When I book a flight
And I retrieve my itinerary
Then I should not see the Early Bird upsell button