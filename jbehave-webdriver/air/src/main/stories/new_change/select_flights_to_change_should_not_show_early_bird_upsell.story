Itinerary Table on Select Flights To Change Page should not show EB Upsell

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to change my flight
As an adult
I do not want to be presented with an early bird upsell when selecting flights to change

Scenario:
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

When I book an Early Bird eligible flight
And I retrieve my reservation for change
Then I should not see an early bird upsell on the select a flight to change page