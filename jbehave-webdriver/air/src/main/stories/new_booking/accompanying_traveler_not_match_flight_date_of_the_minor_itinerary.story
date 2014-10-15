Verify an error message when the flight date of the accompanying traveler does not match the flight date of the minor´s itinerary

Meta:
@flow air
@process booking
@traveler adult_minor_child
@user anonymous
@project UM/YT
@dyna_stubs
@removedFromAirbooking

Narrative:
In order to book a flight for an infant(PAX AGE under 5) and a minor (PAX 5-11) with an accompanying traveler
As a User
I want to be shown an error message when the flight date of the accompanying traveler does not match the flight date of the minor/infant itinerary

Scenario: Error message is shown when the flight date of the accompanying traveler does not match the date of the minor/infant itinerary
Given I have booked a round-trip Southwest Southwest flight for an adult
And I am flying a round-trip Southwest Southwest flight
And I am an infant traveling with a minor
When I attempt to book a flight with a date which DOES NOT match the flight date of the Adult itinerary
And I confirm there will be a person traveling as a companion on the UM modal
And I retrieve my booked flight on the Accompanying Traveler Form
Then I see the Oops message indicating that the itinerary retrieved does not match the current itinerary