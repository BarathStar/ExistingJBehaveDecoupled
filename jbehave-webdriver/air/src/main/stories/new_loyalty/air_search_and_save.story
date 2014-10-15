Save a round trip anytime flight after login

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to save a trip
As an adult
I want to search for a flight and then save it

Scenario: Adult air search and save
Given I am traveling as a:
|Field|Value|
|adultPassengerCount|1|
|seniorPassengerCount|0|

And I have the following itinerary data:
|Field|Value|
|itineraryType|Round Trip|
|departureStation|ANY|
|arrivalStation|ANY|
|departingFlight_carrierCode|WN|
|departingFlight_fareClass|Anytime|
|outboundRouting|Nonstop|
|arrivingFlight_carrierCode|WN|
|arrivingFlight_fareClass|Anytime|
|inboundRouting|Nonstop|

And I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I search for flights from my account
And I select flights and continue
And I click the Save Flight button
Then I should see the modal telling me my flight is saved
When I delete all saved flights
Then I have no flights in my Saved Trips page