Verify redirect to Early Bird direct purchase path

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@user anonymous
@storyId DCQA31 ZR-891
@project_in_dev


Narrative:
As a customer
I want to select the Early Bird button
So that I can verify redirect to Early Bird direct purchase path

Scenario: Verify redirect to Early Bird direct purchase path from the confirmation page

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
When I book a flight using Early Bird eligibility
Then I should see the confirmation page
When I click the Early Bird upsell button
Then I am taken to the EB direct purchase path

Scenario: See EB Check-in on itinerary page with FL\WN Booking

Given I am flying a round-trip AirTran SouthwestCodeshare flight
When I book a flight
And I retrieve my itinerary
And I click the Early Bird upsell button
Then I am taken to the EB direct purchase path

