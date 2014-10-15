Verify error message when the itinerary of the accompanying traveler does not match the minor itinerary

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT

Narrative:
In order to book a flight for an infant (PAX AGE: under 5) and a minor (PAX AGE: 5-11) with an accompanying traveler
As an anonymous user
I want to be shown an error message when the itinerary of the accompanying traveler does not match the minor/infant itinerary

Scenario: Error message is shown when the itinerary of the accompanying traveler does not match the minor/infant itinerary
Given I have booked a round-trip Southwest Southwest flight for an adult
And I am flying a round-trip Southwest Southwest flight where the cities do not match the adult itinerary
And I am an infant traveling with a minor
When I attempt to purchase a flight as an infant
And I confirm there will be a person traveling as a companion on the UM modal
And I retrieve my booked flight on the Accompanying Traveler Form
Then I see the Oops message indicating that the itinerary retrieved does not match the current itinerary
