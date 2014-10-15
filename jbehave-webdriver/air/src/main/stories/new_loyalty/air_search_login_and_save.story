Save a round trip anytime flight then login

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_passing Flaky Test

Narrative:
In order to save a trip
As an adult
I want to search for a flight and then save it

Scenario: Adult air search, login and save
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

When I successfully search for flights from the flight search page
And I select flights and continue
And I click the Save Flight button
Then I should see the login modal popup
When I login using the modal popup
Then I should see the modal telling me my flight is saved
When I delete all saved flights
Then I have no flights in my Saved Trips page