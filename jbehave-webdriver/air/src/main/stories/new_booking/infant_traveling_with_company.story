UM Infants traveling with a companion purchases a flight with a matching itinerary

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT
@not_passing takestoolongtorun

Narrative:
In order to book a flight for an infant with an accompanying traveler
As an anonymous user
I want to be shown that the itinerary of the accompanying traveler matches with the itinerary of the infant on the Accompanying Traveler Form, Purchase Page and Confirmation Page

Scenario: User purchases a flight for an infant who has an accompanying traveler
Given I have booked a round-trip SouthwestUMRoute SouthwestUMRoute flight for an adult
And I am flying a round-trip SouthwestUMRoute SouthwestUMRoute flight
And I am an Infant traveling with a companion
When I attempt to book a flight with an itinerary which matches the itinerary booked before for the adult
And I confirm there will be a person traveling as a companion on the UM modal
And I retrieve my booked flight on the Accompanying Traveler Form
Then I see the matching itinerary on the Accompanying Traveler form
When I click on the continue button on the accompanying adult form
Then I see a message indicating that the passenger will be traveling with an adult
When I fill in the credit card fields and complete the purchase
Then I see that the itinerary displayed matches the itinerary of my booked flight on the Confirmation Page