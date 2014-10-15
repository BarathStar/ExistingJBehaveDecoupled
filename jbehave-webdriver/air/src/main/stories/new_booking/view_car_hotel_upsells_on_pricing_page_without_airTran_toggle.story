Verify car and hotel up-sells are displayed on pricing page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to verify car and hotel upsells are displayed on pricing page
As an adult
I want to verify that car and hotel up-sells are displayed on pricing page

Scenario: Viewing a WN Round Trip Itinerary
Given I am flying a round-trip Southwest Southwest flight
And I am a customer searching for round-trip flights from the search flights page
When I select and view the Price page for a flight
Then I should see the correct pricing page without logo verify
And I should see the hotel upsell widget
And I should see the car upsell widget
