Verify that my trip has been saved from search flight page as a RR member

Meta:
@flow rr
@process loyalty
@user rr_member
@passenger adult
@dyna_stubs
@not_live

Narrative:
As a Rapid Rewards Member
In order to save my trip
I want to see a pop-up confirmation trip saved
So that

Scenario: Verify that my trip has been saved from search flight page as a RR member

Given I am traveling as a:

|Field|Value|
|adultPassengerCount|1|
|seniorPassengerCount|0|

And I have the following itinerary data:

|Field|Value|
|itineraryType|Round Trip|
|departureStation|DAL|
|arrivalStation|SMF|
|departingFlight_carrierCode|WN|
|departingFlight_fareClass|Anytime|
|outboundRouting|Nonstop|
|arrivingFlight_carrierCode|WN|
|arrivingFlight_fareClass|Anytime|
|inboundRouting|Nonstop|

And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I get to the Price page
When I click the Save Flight button on price page
Then I should see the modal telling me my flight is saved