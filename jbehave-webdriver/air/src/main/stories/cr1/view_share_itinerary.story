View share Itinerary

Meta:
@project cr1
@airTranOnly
@flow air
@process view
@dyna_stubs
@traveler adult
@user anonymous
@storyId DCAIR-4839, ZR-899
@not_passing draft

Narrative:
As a customer
I want to purchase the itinerary and view my reservation using credit card option
So that I view my itinerary.


Scenario: View Itinerary

Given I am on the Itinerary Page
Then I view the message for southwest only itinerary

Scenario: I see the itinerary not found error message

Given I am flying a round-trip AirTran SouthwestCodeshare flight
And I retrieve my Itinerary using the credit card number 4111111111113333
Then I view the message for credit card



!-- Scenario: I view my itinerary for FL/WN and FL/WN operated
!--
!-- Given I am flying a round-trip AirTran SouthwestCodeshare flight
!-- Given I am a customer searching for round-trip flights from the search flights page
!-- When I select and purchase a flight
!-- When I retrieve my Itinerary using the credit card number
!-- Then I view my itinerary
