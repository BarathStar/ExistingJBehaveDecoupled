Adapt ACS winner: Upsell Modal after Select Flight Page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
As an Anonymous user
I want to see the ACS modal allowing me to upsell fares in my round-trip flight
In order to verify the ACS modal round trip feature

Scenario: ACS modal allowing me to upsell fares in my round-trip flight
Given I have selected the following itinerary data:
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

When I search and select my flight and attempt to continue to the Price page
Then I verify that the upsell modal is being displayed
And I verify that both of my segments are eligible for upsell