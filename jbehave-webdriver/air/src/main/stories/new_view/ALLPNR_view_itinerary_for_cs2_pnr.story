View alternate View Itinerary screen for CS2 PNRs, which also will display a Hotel Dynamic Cross-Sell

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative: In order to verify the alternate View Itinerary screen for CS2 PNRs, which also will display a Hotel Dynamic Cross-Sell
As a southwest.com user
I want to view an alternate screen when retrieving CS2 PNRs
So that I have a cleaner "View Itinerary" screen discarding inappropriate information.

Scenario: View alternate View Itinerary screen for CS2 PNRs, which also will display a Hotel Dynamic Cross-Sell

Given I am:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have a flight booked from CS2
When I retrieve my itinerary
Then I view my itinerary
And I see that the Change option is not shown
And I see that the Resend Receipt option is not shown
And I see that the Depart/Return data is not shown
And I see that the Fare Product data is not shown
And I see that the Check In option is shown
And I see that the Hotel Dynamic Cross-Sell shown displaying results for my destination city and dates of travel