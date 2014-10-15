Purchase a round-trip anytime air ticket for an adult and request an itinerary email

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to receive an email of my itinerary
As an adult
I want to purchase a flight and request an email

Scenario: Book a flight and email itinerary

Given I am flying a round-trip Southwest Southwest flight
When I book a flight
Then I should see the confirmation page
When I retrieve my itinerary
And I send the itinerary via email
Then I see a thank you popup
