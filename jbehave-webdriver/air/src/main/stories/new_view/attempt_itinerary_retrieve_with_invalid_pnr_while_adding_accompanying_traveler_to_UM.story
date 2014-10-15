Attempt to retrieve an Accompanying Traveler Itinerary using an invalid PNR while booking a Minor Itinerary

Meta:
@flow air
@process view
@user anonymous
@traveler anonymous
@not_passing draft

Narrative:
In order to see the OOPS message
I book a flight for a minor with a modal requesting to add an adult passenger over 12
I enter an invalid PNR for the accompanying adult passenger
I see the Oops messages for the invalid PNR entered


Scenario:
Minor (child 5-11) attempting to book with a adult passenger accompanying on a SW-SW round-trip flight air purchase
when an invalid PNR for the accompanying adult is entered (Scenario 8)

Given I am flying a round-trip Southwest Southwest flight
When I book a flight for an unaccompanied young child
And I attempt to add an accompanying traveler using an invalid PNR
Then I view the OOPS message for the invalid PNR on the Purchase page
