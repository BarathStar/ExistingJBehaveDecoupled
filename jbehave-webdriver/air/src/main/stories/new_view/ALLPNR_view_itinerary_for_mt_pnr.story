View alternate View Itinerary screen for MarkTravel PNRs, which will NOT display a Hotel Dynamic Cross-Sell

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative: In order to verify the alternate View Itinerary screen for MarkTravel PNRs, which will NOT display a Hotel Dynamic Cross-Sell
As a southwest.com user
I want to view an alternate screen when retrieving 3rd party PNRs
So that I have a cleaner "View Itinerary" discarding inappropriate information.

Scenario: View alternate View Itinerary screen for MarkTravel PNRs, which will NOT display a Hotel Dynamic Cross-Sell

Given I am:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|MDW|
    |arrivalStation|LAS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have a flight booked from MT
When I retrieve my itinerary
Then I view my itinerary
And I see that the Fare Product data is not shown
And I see that the Depart/Return data is not shown
And I should not see the Hotel Dynamic Cross-Sell