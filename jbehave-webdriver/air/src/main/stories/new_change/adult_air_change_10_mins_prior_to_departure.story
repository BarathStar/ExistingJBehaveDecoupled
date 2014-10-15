Change existing adult flight itinerary

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@storyId BUG2443
@dyna_stubs

Narrative:
In order to fly on a date that I can
As an adult
I want to change my itinerary 10 minutes prior to departure

Scenario: adult air change 10 minutes prior to departure
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|BWI|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |returningFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |departureDate|+0|
    |departureTimeInMinutes|+5|

And I have a flight booked
When I change the flight to a later date
Then I should see the itinerary change confirmation page