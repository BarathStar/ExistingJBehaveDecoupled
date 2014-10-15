Verify AirTran Business Select itinerary displayed on Confirmation Page

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user
@traveler adult
@storyId DCAIR-5255
@dyna_stubs
@project_in_dev

Narrative:
In order to book a BusinessSelect AirTran flight
As a customer
I want to verify BusinessSelect AirTran itinerary displayed on confirmation page


Scenario: Booking a BusinessSelect Airtran flight

Given I am flying a BusinessSelect round-trip AirTran AirTran flight
When I book a flight
Then I should see the confirmation page
And I see the AirTran-only specific travel guidelines
And I should not see the Early Bird upsell button
And I see the Business Select text on the Confirmation Page
