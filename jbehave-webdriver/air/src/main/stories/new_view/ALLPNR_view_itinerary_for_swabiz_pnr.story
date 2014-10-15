View alternate View Itinerary screen for SWABIZ PNRs, which also will display a Hotel Dynamic Cross-Sell

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@not_passing draft


Narrative: In order to verify the alternate View Itinerary screen for SWABIZ, which also will display a Hotel Dynamic Cross-Sell
As a southwest.com user
I want to view an alternate screen when retrieving SWABIZ PNRs
So that I have a cleaner "View Itinerary" screen discarding inappropriate information.

Scenario: View alternate View Itinerary screen for SWABIZ PNRs, which also will display a Hotel Dynamic Cross-Sell

Given I am:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+2|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have a flight booked from SWABIZ
When I retrieve my itinerary
Then I view my itinerary
And I see that the Change option is not shown
And I see that the Early Bird option is not shown
And I see that the Hotel Dynamic Cross-Sell shown displaying results for my destination city and dates of travel