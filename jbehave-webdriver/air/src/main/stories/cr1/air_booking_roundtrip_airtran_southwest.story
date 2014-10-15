Verify AirTran\Southwest itinerary displayed on Confirmation Page

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user
@traveler adult
@storyId DCQA-35
@dyna_stubs
@project_in_dev

Narrative:
In order to book a flight using AirTran and SouthWest
As a customer
I want to verify AirTran\SouthWest itinerary displayed on confirmation page


Scenario: Viewing a FL_WN Itinerary

Given I am flying a round-trip AirTran SouthwestCodeshare flight
When I book a flight
Then I should see the confirmation page
And I see the AirTran-mixed specific travel guidelines
And I see the Early Bird upsell button
