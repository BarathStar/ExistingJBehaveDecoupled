Validate BS upsell modal with a 'n/a' for WGA segment when I am updating a flight to AT - WGA and BS is sold out for inbound, as anonymous user in .com.

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@acs_bs_modal



Narrative:
As an anonymous user in Southwest.com
I want to verify that I see BS upsell modal with a 'n/a' for WGA segment when I am updating a flight to AT - WGA and BS is sold out for inbound
So that I know that upsell doesn't apply for WGA in this situation.

Scenario: Validate BS upsell modal with a 'n/a' for WGA segment when updating a flight to AT - WGA and BS is sold out for inbound.

Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureDate|+1|
    |departureStation|MCO|
    |arrivalStation|LAS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|

And I have a flight booked
And I am changing the following itinerary data to:

    |Field|Value|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_fareClass|Anytime|

And BS is sold out for the inbound flight
When I retrieve my reservation for change and select a new price
Then I see that the upsell modal displays only the inbound flight as not eligible for upgrade to BS