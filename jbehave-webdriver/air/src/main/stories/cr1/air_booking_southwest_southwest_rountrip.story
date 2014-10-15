Verify Southwest itinerary displayed on Confirmation Page

Meta:
@project cr1
@flow air
@process booking
@traveler adult
@dyna_stubs
@project_in_dev

Narrative:
In order to book a flight using SouthWest
As a customer
I want to verify SouthWest itinerary displayed on confirmation page


Scenario: Viewing a WN Itinerary

Given I am flying a round-trip Southwest Southwest flight
When I book a flight
Then I should see the confirmation page
And I see the Early Bird upsell button
