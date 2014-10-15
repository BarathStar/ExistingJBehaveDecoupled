Purchase a round-trip anytime air ticket for an adult and add EarlyBird from EB check-in page

Meta:
@flow air
@process booking
@user anonymous
@global_nav_regression
@traveler adult
@dyna_stubs
@project_AccordionPage

Narrative:
In order to add early bird option to my flight from early bird check in page
As an adult
I want to purchase a flight

Scenario: Adult Air Booking and add early bird
Given I am flying a round-trip Southwest Southwest flight
When I have a flight booked
And I am on the early bird page
And I retrieve itinerary on the Early Bird check-in page
Then I am taken to the EB direct purchase path
When I purchase earlybird for my flight
Then I see earlybird purchase successful page

